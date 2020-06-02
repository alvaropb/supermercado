<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="include/header.jsp">
	<jsp:param value="Inicio" name="titulo" />
	<jsp:param value="inicio" name="pagina" />
</jsp:include>
<h1>PAGINA DE LOGIN</h1>

<form action="login" method="post">
		
	<div class="form-group">
		<label for="nombre">Nombre</label> <input
			type="text" class="form-control" id="nombre" name="nombre" placeholder="nombre" >
	</div>
	

	<div class="form-group">
		<label for="exampleInputPassword1">Password</label> <input
			type="password" class="form-control" id="exampleInputPassword1" name="pass" placeholder="Contraseña" >
	</div>
	
	<select class="custom-select" name="idioma">
		<option selected>elija un idioma</option>
		<option value="en">Ingles</option>
		<option value="es">Español</option>
		<option value="eu">Euskera</option>
	</select>
	
	<div class="form-group">
		<label for="email">Email address</label>
		 <input type="email" placeholder="email" name="email" class="form-control" id="email"
			value="${cookie['email'].value }">
	</div>
	<div class="input-group-text ">
		<input type="checkbox" name="recordar"
			aria-label="Checkbox for following text input"
			${(cookie['recordar'].value eq 'on')?'checked':'' }>recordar
		email
	</div>
		<button type="submit" class="btn btn-primary my-3">Logearse</button>
	


	
</form>
<jsp:include page="include/footer.jsp" />