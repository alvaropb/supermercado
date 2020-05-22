<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Crear Productos</title>
</head>
<body>

	<section>
		<h2>Crear producto</h2>
		
		
		<form action="productos-crear" method="post">
			<input type="text" name="nombre"  placeholder="Introduzca nombre del producto"/> <input type="submit"
				value="crear" />
		</form>
	</section>
	<p>${mensaje}</p>
	<a href="index.jsp">volver</a>
</body>
</html>