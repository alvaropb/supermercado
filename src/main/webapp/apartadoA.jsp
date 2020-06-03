<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ApartadoA</title>
</head>
<body>
	<h1>apartado A</h1>
	<h3>Alvaro Parga Barreiro</h3>
	<form action="apartado-a" method="post">
		<label for="nombre">nombre</label><input type="text" name="nombre" id="nombre" required placeholder="Introduzca su nombre">
		<br><br>
		<label for="color">seleccione un color</label>
		<select  name="color" id="color">
			<option selected>elija un color</option>
			<option value="rojo">rojo</option>
			<option value="verde">verde</option>
			<option value="azul">azul</option>
		</select><br><br>
	
		<input type="submit" value="enviar"/>
	</form>	
</body>
</html>