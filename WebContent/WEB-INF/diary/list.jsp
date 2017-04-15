<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  <!-- le문 인 jstl 사용하기 위해서 --> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>파일업로드</title>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>

</head>

<body>


<h1>${membe.id}</h1>
<h1>${membe.pw}</h1>


<%
Cookie[] cks = request.getCookies();  //모든 쿠키 가지고 오는거 

if(cks==null||cks.length==0){    //cks널이거나 없으면 
	
	out.print("<h1> 아무 쿠키도 없스 </h1>");
}else{
	
	for(Cookie ck: cks){     //Cookie 라는 클라스가 들어있는가보네 
 		out.print("<h2>"+ck.getName()+"</h2>"); 
		out.print("<h2>"+ck.getValue()+"</h2>");
		out.print("<hr/>");
	}
}

%>


<style>

.imageUL { list-style: none; }

.imageUL li {height: 100px;}

.imageUL img { width: 100px; }

</style> 


<ul class="list-group">
<c:forEach items="${list}" var="diary">
<li class="list-group-item list-group-item-succes">
<div class="panel">
<h2>${diary.dno}</h2>
<h2>${diary.title}</h2>
<p>${diary.content}</p>
<ul class='imageUL'>
<c:forEach items="${diary.fileLists}" var="fileStr">

<li><img scr='/display?name=${fileStr}'/></li>

 </c:forEach>

</ul>

</div>

</li>
</c:forEach>
</ul>



<ul class="pagination">
 <c:if test="${pager.prev}">
  <li> <a href='list?pageNum=${pager.start -1 }'> << </a> </li>  <!-- <<단추 만들어주려고 -->
 </c:if>

<c:forEach begin="${pager.start}" end="${pager.end }" var="num">   <!-- 페이지 숫자 출력해주는거  -->
  <li><a href="list?pageNum=${num}">${num}</a></li>
</c:forEach>

<c:if test="${pager.next}">
  <li> <a href='list?pageNum=${pager.end +1 }'> >> </a> </li>    <!-- >>단추 만들어주려고 -->
</c:if> 

</ul>






</body>
</html>