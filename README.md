![image](https://github.com/user-attachments/assets/459bb73a-8904-4f9f-8ace-9315d350f9ed)


· 작업기간 :  2024.05.13. - 2024.06.03. (3주)
<br>
· 작업인원 :  1명 (이혜리)
<br>
· 작업툴 :  
          java, Spring Boot(async-await), JPA, Maria DB, intelliJ,
          Ajax, thyemleaf, API통신, AWS,
          html, css, java script, jqery, figma, Boot Strap
<br>
· 개발 환경
 IDE : intelliJ
 java version : 17
 build system : gradle - groovy
 spring framework ver: 3.0.3


· 기능
- ① 회원가입  
- Spring Security를 이용한 회원가입 기능 구현

② 로그인/로그아웃  
- Spring Security를 이용한 로그인/로그아웃 구현. 로그인한 회원만 사이트에 접근 가능하며 권한에 따라 접근 가능한 페이지 존재.

③ 예매 진행 중 경기 소개  
- inven 웹사이트에서 오늘 날짜부터 2주간 경기 일정을 받아와 데이터로 입력하여 현재 판매중인 경기 티켓으로 보여진다.
  
![image](https://github.com/user-attachments/assets/0c75d8d3-a2f7-451d-a232-d9f149ba1bbd)


④ 티켓 예매  
- JavaScript를 이용하여 좌석 생성 후 DB에 예매된 좌석은 선택 하지 못하게 설정. 소비자가 선택한 데이터를 넘긴다.

![image](https://github.com/user-attachments/assets/dbf0224c-b265-45d4-95de-76f1463f94e5)



⑤ 예매한 티켓 내역  
- 현재 소비자의 예약 티켓을 보여준다. JavaScript와 Ajax로 취소 기능 구현.

⑥ MD샵 상품 소개  
- 현재 판매중인 상품을 보여준다.

⑦ 상품 등록  
- 권한이 'ADMIN'인 유저 즉, 운영자만 접근 가능한 페이지로 가격, 상품 이름, 수량, 이미지를 받아서 상품을 등록한다.

⑧ 상품 상세 정보 
- 각 상품의 상세 정보를 보여주며 상품 갯수에 따라 가격이 달라진다. 수량보다 많은 주문일 경우 경고창이 생성된다. 최대 최문 갯수는 999개로 설정.

⑨ 장바구니
- 장바구니에 담은 상품을 한번에 결제 가능하며 선택한 만큼의 가격을 보여준다.

⑩ 구매내역 
- 소비자의 구매 내역을 보여준다. JavaScript와 Ajax로 취소 기능 구현.

⑪ 이벤트 게시판  
- 운영자만 접근 가능한 페이지로 현재 진행할 이벤트 목록을 SpringBoot 게시판으로 생성한다.

⑫ 경기장 위치  
- 카카오 developer에서 생성한 key로 카카오맵 api를 통하여 경기장 위치를 지도상으로 보여준다.

⑬ 마이 페이지  
- 현재 로그인한 유저의 닉네임, 이메일을 보여준다.

