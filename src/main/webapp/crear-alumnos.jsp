<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<section>
		<h2>Insertar alumno</h2>
		<form action="alumnos-crear" method="post">
			<input type="text" name="nombre" placeholder="nombre" />
			 <input type="submit" value="crear" />
		</form>
		<p>${mensaje}</p>
		<a href="index.jsp">volver</a>
	</section>
</body>
</html>