<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>두번째 뷰</title>
</head>
<body>
<h3> 플로우 변수 영역</h3>

요청 범위 변수(첫번째 뷰 설정) : ${requestscope_start_username } <br>
요청 범위 변수(두번째 뷰 on-render 설정) : ${requestscope_second_username } <br>
플래시 범위 변수 : ${flashscope_username } <br>
플래시 범위 변수(두번째 뷰 on-entry 설정) : ${flashscope_onentry_username } <br>
뷰 범위 변수(첫번째 뷰 설정) : ${viewscope_start_username } <br>
뷰 범위 변수(두번째 뷰 설정) : ${viewscope_second_username } <br>
플로우 범위 변수 : ${flowscope_username } <br>
컨버세이션 범위 변수 : ${conversationcope_username } <br>

<h3> 내장 객체 사용 </h3>

이벤트 : ${eventname}<br>
뷰 URL : ${url} <br>
에러 메시지 : ${hasserrormsg } <br>
플로우 실행 시작 : ${flowstarted}  <br>
플로우 실행 활성화 : ${flowisactive} <br>
플로우 실행 종료 : ${flowended} <br>
컨텍스트 경로 : ${contextpath} <br>
응답 허용 : ${responseallowed} <br>
응답 완료 : ${responsecomplete} <br>
로케일 : ${currentlocale} <br>
요청 컨텍스트 : ${requestcontext }

</body>
</html>