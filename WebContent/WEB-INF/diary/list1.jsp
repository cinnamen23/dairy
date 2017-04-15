<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<!-- Latest compiled and minified CSS -->
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

<style>
.img {
width : 100px;
}
</style>

<ul class="list-group">
  <c:forEach items="${list}" var="diary">
  <li class="list-group-item list-group-item-success">
    <div class="panel">
      <h2>${diary.dno}</h2>
      <h2><a href="/diary/view?dno=${diary.dno}&pageNum=${pageNum}">${diary.title}</a></h2>
      <p>${diary.content}</p>
      <p>${diary.writer}</p>
      <c:forEach items="${diary.fileLists }" var="list">
      	<a target="_blank" href ="/display?name=${list }"><img class="img" src="/display?name=${list }"></a>
      </c:forEach>      
    </div>
  </li>
  </c:forEach>
  
</ul>

<ul class="pagination">
 <c:if test="${pager.prev}">
  <li> <a href='list?pageNum=${pager.start -1 }'> << </a> </li>
 </c:if>

<c:forEach begin="${pager.start}" end="${pager.end }" var="num">
  <li><a href="list?pageNum=${num}">${num}</a></li>
</c:forEach>

<c:if test="${pager.next}">
  <li> <a href='list?pageNum=${pager.end +1 }'> >> </a> </li>
</c:if> 

</ul>
<button onclick="location.href='/diary/write'">글쓰기</button>

<script>
var msg = '${param.msg}';

if(msg == 'writetrue'){
	alert("등록 완료");
	window.history.replaceState(null,null,"/diary/list");
} else if(msg == 'deletetrue'){
	alert("삭제 완료");
	window.history.replaceState(null,null,"/diary/list");
}
</script>

</body>
</html>