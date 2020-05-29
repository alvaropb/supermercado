<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


</head>
<body>
	<h1>Ejemplos de GET y POST</h1>
	<p>es necesario un enlace, aunque se puede hacer desde un formulario si cambiamos el METHOD</p>
	<p>Normalmente es para solicitar informacion al servidor, aunque tambien lo usemos para eliminar</p>
	
	<a href="sumar?op1=2&op2=3">Vamos a sumar 2 + 3 desde el get</a>
	<h2>Ejemplo de POST</h2>
	
	<p>Siempre es necesario un formulario. Las peticiones POST sirven para crear o modificar recursos </p>
	<form action="sumar" method="post">
		<input type="number" name="op1" value="${op1}" placeholder="Introduce un numero">
		<br>
		<input type="number" name="op2" value="${op2}" placeholder="Introduce otro un numero">
		<br>
		<input type="submit" value="Sumar">
	</form>
	<h2>RESULTADO</h2>
	${resultado}

</body>
</html>