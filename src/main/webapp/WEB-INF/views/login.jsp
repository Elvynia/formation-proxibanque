<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="templates/header.jsp" />
</head>
<body>
	<div class="container">
		<h2>Veuillez vous authentifier :</h2>
		<form action="<c:url value="/login" />" method="post">
			<div class="form-group">
				<label for="login">Login conseiller</label>
				<input id="login" name="username" required />
			</div>
			<div class="form-group">
				<label for="pwd">Mot de passe</label>
				<input type="password" id="pwd" name="password" required />
			</div>
			<button class="btn btn-primary">Valider</button>
		</form>
	</div>
</body>
</html>