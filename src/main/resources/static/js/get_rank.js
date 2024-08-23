async function fetchAndParseHTML(url) {

    const proxyUrl = 'https://cors-anywhere.herokuapp.com/';
    const targetUrl = proxyUrl + url;

    try {
        const response = await fetch(targetUrl, {
            headers: {
                "Origin": "http://3.37.173.105"
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const text = await response.text();

        const parser = new DOMParser();
        const doc = parser.parseFromString(text, 'text/html');
        return doc;

    } catch (error) {
        console.error("HTML을 가져오는데 실패했습니다:", error);
        return null;
    }
}