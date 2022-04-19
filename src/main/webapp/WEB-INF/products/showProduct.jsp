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
	
	<!-- <script type="text/javascript" src="/js/script.js"></script> -->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>

<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<h1 class="display-3"><c:out value="${product.name}" /></h1>
			<div class="row p-3">
				<div class="col-6 p-3">
					<h3>Categories:</h3>
					<ul>
						<c:forEach var="category" items="${product.categories}">
							<li><c:out value="${category.name}" /></li>
						</c:forEach>
					</ul>
				</div>
				<div class="col-6 p-3">
					<form action="/products/${product.id}" method="POST" class="p-3">
						<!-- <input type="hidden" name="_method" value="PUT" /> -->
						<div class="row mb-3">
							<label for="category" class="form-label">Add Category:</label>
							<select name="category" id="category" class="form-control">
								<option selected>Choose a category to add...</option>
								<c:forEach var="unusedCategory" items="${unusedCategories}">
									<option value="${unusedCategory.id}"><c:out value="${unusedCategory.name}" /></option>
								</c:forEach>
							</select>
						</div>
						<input type="submit" value="Add" class="btn btn-sm btn-primary" />
					</form>
				</div>	
			</div>
		</div>
	</div>

</body>
</html>