<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Formulario CV</title>
<link rel="stylesheet" href="../css/estilos.css">
</head>
<body>
	<h1>Formulario Completo</h1>
	TODO 1:maquetar formaulario del CV 
	<br> TODO 2:llamara aun controlador "formulario-completo"
	<br> TODO 3:mostrar una nueva JSP para resumir los datos
	<main class="container">
	
	
	
		<c:if test="${not empty errores}" >
			<div style="background-color: #DDD;">
				<ul>
					<c:forEach items="${errores}" var="error">
						<li>Error: ${error}</li>	
					</c:forEach>
				</ul>
			</div>
		</c:if>
				
		<form action="/supermerkado-master/formulario-completo" method="POST" >
			<h2>Rellena tu CV</h2>
			<fieldset>
				<legend>Nombre y apellidos</legend>
				<label for="nombre">Nombre</label><br>
				<input type="text" name="nombre" maxlength="30" value="${nombre }">
				<br> <label for="apellidos">Apellidos</label><br>
				<input id="apellidos" type="text" name="apellidos" maxlength="80"  value="${ apellidos}" ><br>
				<label for="contraseina">Contraseña</label><br>
				<input id="contraseina" type="password" name="contraseina"
					maxlength="10"   value="${ contraseina}"><br> <label for="dni">DNI</label> <br>
				<input id="dni" type="text" name="dni" size="9" value="${dni }"><br>
			</fieldset>
			<fieldset>
				<legend>Sexo</legend>
				<input type="radio" name="sexo" value="Hombre" id="hombre" ${sexo == 'Hombre'? 'checked':''}><label
					for="hombre">Hombre</label><br>
					 <input type="radio" name="sexo" value="Mujer" id="mujer" ${sexo == 'Mujer'? 'checked':''}>
					 <label for="mujer">Mujer</label><br>
			</fieldset>
			<fieldset>
				<legend>Estudios</legend>
				<label for="estudios">Estudios</label><br> <select
					name="estudios" id="estudios">
					<option value="u">Universitarios</option>
					<option value="p">Formacion profesional</option>
					<option value="o" selected="selected">Otros</option>
				</select>
			</fieldset>
			<fieldset>
				<legend>datos adicionales</legend>
				<label for="notas">Notas adicionales</label><br>
				<textarea name="notas" id="notas" cols="30" rows="10"></textarea>
			</fieldset>
			<fieldset>
					<input type="checkbox"
					name="subscribirse" id="subscribir" checked><label
					for="subscribir">Suscribirme alo boletin de novedades</label> <br>
				<input type="submit" value="Guardar cambios">
				<input type="reset" value="Borrar los datos introducidos">
			</fieldset>
		</form>
	</main>
</body>
</html>