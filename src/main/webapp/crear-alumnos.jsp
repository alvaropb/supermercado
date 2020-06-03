<jsp:include page="include/header.jsp">
	<jsp:param value="Registro alumno" name="titulo" />
	<jsp:param value="registro-alumno" name="pagina" />
</jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section>
	<h2>Insertar alumno</h2>
	<form action="alumnos-crear" method="post" class="my-3">
		<div class="form-group">
			<label for="idUsuario">Id usuario</label> <input id="idUsuario"
				type="text" name="id" value="${usuario.id }" class="form-control"
				readonly />
		</div>
		<div class="form-group">
			<label for="nombre">nombre</label> <input class="form-control"
				id="nombre" type="text" name="nombre" placeholder="nombre"
				value="${usuario.nombre }" />
		</div>
		<button type="submit" class="btn btn-primary">Registrar
			Alumno</button>
	</form>
	<a href="index.jsp" class="btn btn-primary">volver</a>
</section>


<jsp:include page="include/footer.jsp" />