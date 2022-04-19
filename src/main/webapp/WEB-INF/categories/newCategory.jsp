<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page isErrorPage="true" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>

	<!-- <link rel="stylesheet" type="text/css" href="/css/style.css"> -->
	<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />

	<!-- script type="text/javascript" src="/js/script.js"></script> -->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<h1 class="display-4 mb-3">New Category:</h1>
			<div class="col-5">
				<form:form action="/categories" modelAttribute="category" method="POST" class="p-3">
					<div class="mb-3">
						<form:label path="name" class="form-label">Name:</form:label>
						<form:input type="text" path="name" class="form-control" />
					</div>
					<div><form:errors path="name" /></div>
					<input type="submit" value="Create" class="btn btn-sm btn-light" />
				</form:form>
			</div>
		</div>
	</div>

</body>
</html>