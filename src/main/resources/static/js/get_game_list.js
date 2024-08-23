async function fetchAndParseHTML(url) {

    // 타 웝사이트의 데이터를 보안 정책 때문에 직접 가져오지 못하는 경우,
    // CORS문제를 우회하기 위하여 프록시 URL을 설정한다.
    const proxyUrl = 'https://cors-anywhere.herokuapp.com/';
    const targetUrl = proxyUrl + url;

    try {
        const response = await fetch(targetUrl, {
            headers: {
                // fetch API 를 사용하여 targetURL에 GET 요청을 보낸다.
                // CORS 문제 해결을 위하여 origin 헤더에 "http://localhost" 를 임의로 추가한다.
                "Origin": "http://3.37.173.105"
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        // 응답으로 받은 HTML 문서를 텍스트 형태로 변환하여 저장한다.
        const text = await response.text();

        // DOMParser 객체를 사용하여 텍스트 구조로 된 HTML 문서를 파싱하여 DOM 구조로 변환한다.
        // 변환된 doc은 HTML 문서 전체를 JavaScript 객체로 다룰 수 있게 한다.
        const parser = new DOMParser();
        const doc = parser.parseFromString(text, 'text/html');
        return doc;

    } catch (error) {
        console.error("HTML을 가져오는데 실패했습니다:", error);
        return null;
    }
}

function getDateAfterNDays(n) {
    const today = new Date();
    const futureDate = new Date(today.getTime() + n * 24 * 60 * 60 * 1000);
    const year = futureDate.getFullYear();
    const month = String(futureDate.getMonth() + 1).padStart(2, '0');
    const day = String(futureDate.getDate()).padStart(2, '0');
    return `${year}-${month}-${day}`;
}

async function main(query) {
    const todayDate = new Date(query);
    if (todayDate.getDay() == 0) {

        // 만약 오늘이 일요일이라면 날짜를 하루 더하여 월요일로 설정한다. (일요일은 그 주 경기가 더이상 없기 때문)
        todayDate.setDate(todayDate.getDate() + 1);
        // toISOString() 메서드를 사용하여 날짜를 ISO 8601 형식으로 변환하고 (ex- 2023-08-31T01:07:25.295Z)
        // split('T')[0] 으로 시간 부분을 제거한다.
        query = todayDate.toISOString().split('T')[0];
    }

    // 최대 날짜를 2024-09-15로 설정
    const maxDate = new Date("2024-09-15");
    const maxDateString = maxDate.toString().split('T')[0];

    // 최대 날짜와 비교하여 query 설정
    if (new Date(query) > maxDate) {
        query = maxDateString;
    }

    // 위에서 구한 날짜를 이용하여 'url'을 생성한다. 이 url은 주간 경기 일정을 가리킨다.
    const url = `https://esports.inven.co.kr/schedule/?mode=week&date=${query}`;

    // 주어진 url에서 HTML 데이터를 가져오고, 이를 DOM으로 파싱하여 'doc' 변수에 저장한다.
    const doc = await fetchAndParseHTML(url);

    // 파싱된 doc에서 경기 일정이 있는 특정 HTML 요소를 가져오고 변수에 저장한다.
    const week = doc.querySelector('#esportsBody > div.commu-wrap > section > article > section.commu-center > div.commu-body.pcMain > div > div.contentContainer > div > div');
    if (week) {
        let index = 0;

        // 경기 일정이 있는 'div' 요소들을 모두 선택하여 days에 저장한다.
        const days = week.querySelectorAll('div');
        const matches = [];

        // 각 day 요소에서 날짜 정보가 있는 p.info 요소를 찾는다.
        days.forEach(day => {
            const dateP = day.querySelector("p.info");
            // 해당 요소가 없다면, 이 'day'는 무시하고 다음으로 넘어간다.
            if (!dateP) {
                return;
            }

            const date = dateP.textContent;
            const matchDate = new Date(date);

            // 오늘이전의 날짜 데이터는 제외
            if (matchDate < todayDate || matchDate > maxDate) {
                return;
            }

            // day 요소 내에서 경기 정보가 포함된 table 요소를 찾는다.
            // table이 있다면 그 안의 모든 경기(li)를 선택하여 순회한다.
            const target = day.querySelector('table');

            if (target) {
                const lis = target.querySelectorAll("tbody > tr > td.match > ul > li");
                lis.forEach(li => {
                    let timeElement = li.querySelector("a > div.time");
                    let team1Element, team2Element;
                    let time, team1, team2;


                   // 웹 사이트에서 팀이 '미정'인 경기와 팀 정보가 나온 경기의 구조가 다르다! 잘 구분하여 선택해줘야 한다.
                   if (!timeElement) {
                        timeElement = li.querySelector("div.time");
                        team1Element = li.querySelector("div.teamList.clearBoth > div.group1 > span");
                        team2Element = li.querySelector("div.teamList.clearBoth > div.group2 > span");
                    } else {
                        team1Element = li.querySelector("a > div.teamList.clearBoth > div.group1 > span");
                        team2Element = li.querySelector("a > div.teamList.clearBoth > div.group2 > span");
                    }

                    time = timeElement.textContent;
                    team1 = team1Element.textContent.trim().replace("팀 :", "");
                    team2 = team2Element.textContent.trim().replace("팀 :", "");

                    let place = "서울 LoL PARK";
                    let price = 25000;

                    // 결승전과 결증 진출전은 장소와 가격이 다르다.
                    // 날짜 비교를 위하여 모두 타임스태프 형태로 변환한다.
                    const last1 = new Date("2024-09-07").getTime();
                    const last2 = new Date("2024-09-08").getTime();
                    const matchDate = new Date(date).getTime();

                    if (matchDate === last1) {
                        place = "경주 실내체육관";
                        price = 85000;
                    }

                    if (matchDate === last2) {
                        place = "경주 실내체육관";
                        price = 100000;
                    }

                    matches.push({
                        id: index,
                        price: price,
                        place: place,
                        title: team1 + " vs " + team2,
                        gameDate: date,
                        gameTime: time,
                        redTeam: team1,
                        blueTeam: team2,
                    });
                    index++;
                });
            }
        });
        return matches;
    } else {
        console.error('Not found');
    }
}

const matches = [];

// 페이지가 완전히 로드되면 실행되는 코드이다. (비동기 작업)
$(document).ready(async function() {
    const queries = [];

    queries.push(getDateAfterNDays(7 * 0));
    queries.push(getDateAfterNDays(7 * 1));
    queries.push(getDateAfterNDays(7 * 2));

    for (let day of queries) {
        // 경기 정보를 추출한 matches 배열을 얻는다.
        const result = await main(day);
        matches.push(...result);
    }

    const csrfToken = $("meta[name='_csrf']").attr("content");
    const csrfHeader = $("meta[name='_csrf_header']").attr("content");

    $.ajax({
        url: '/games/list',
        method: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(matches),  // 배열을 JSON 형식으로 변환하여 전송한다.
        beforeSend: function(xhr) {
            xhr.setRequestHeader(csrfHeader, csrfToken);
        },
        success: function(response) {
            window.location.href = response;
        },
        error: function(xhr, status, error) {
            console.error('전송에 실패했습니다:', error);
        }
    });
});
