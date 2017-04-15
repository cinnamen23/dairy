<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
	
		<div class="row">
			<div class="col-md-8">
			
			
			  <form method="post" enctype="multipart/form-data">  <!-- enctype="multipart/from-data"  멀티파트 처리   -->
			  
				<div class="form-group">
					<label for="exampleInputEmail1">Title</label> <input
						type="text" name='title' class="form-control" id="exampleInputEmail1"
						placeholder="Title" value="샘플제목">
				</div>
				
				
				<div class="form-group">
					<label for="exampleInputEmail1">Writer</label> <input
						type="text" name='writer' class="form-control" id="exampleInputEmail1"
						placeholder="Title" value="user00">
				</div>
				
				
				<div class="form-group">
					<label for="exampleInputPassword1">Content</label> 
					<textarea rows="3" cols="50" name='content'>샘플 컨텐츠</textarea>
				</div>
				
				
				<div class="form-group">
					<label for="exampleInputFile">File input</label> 
					<input type="file" name='file1'>
					<input type="file" name='file2'>
					<input type="file" name='file3'>
					
				</div>
				<button type="submit" class="btn btn-default">등록하기!</button>
	</form>
	
	
	</div>
	</div>
</body>
</html>