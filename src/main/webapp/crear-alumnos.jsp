<jsp:include page="include/header.jsp">
	<jsp:param value="Registro alumno" name="titulo" />
	<jsp:param value="registro-alumno" name="pagina" />
</jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<section>
	<h2>Insertar alumno</h2>
	

	<form action="alumnos-crear" method="post" class="my-3" onsubmit="cifrar()">
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
		<!-- ocultar password si usuario existe -->
		
		<c:if test="${usuario.id == 0}">
			<div class="form-group">
				<label for="pass">password</label> <input class="form-control"
					id="pass" type="password" name="pass" placeholder="password"
					value="${usuario.contrasenia }" />
			</div>
		</c:if>
		
		<c:if test="${usuario.id != 0}">
			<div class="form-group">
				<button class="btn btn-warning" type="button" data-toggle="collapse" data-target="#resetPass" aria-expanded="false" aria-controls="collapseExample">
				    Resetear contraseña
				</button>
				
				<div class="collapse" id="resetPass">
					<div class="form-group">
						<label for="pass">password</label> <input class="form-control"
							id="pass" type="password" name="pass" placeholder="Introduzca password"	/>
					</div>
					<div class="form-group">
						<label for="repass">Confirmar password</label> <input class="form-control"
							id="repass" type="password" name="repass" placeholder="Confirmar password" />
					</div>
				</div>
			</div>
		</c:if>
		
		<div class="form-group">
			<label for="rol">Id Rol</label>
			<select class="custom-select" name="id_rol" id="rol">
			 		
			  		<c:forEach items="${roles}" var="rol">
			  			<option  ${(usuario.rol.id eq rol.id)?'selected':'' } value="${rol.id}">${rol.nombre}</option>
			  		</c:forEach>
			</select>
		</div>

		<button type="submit" class="btn btn-primary">Registrar
			Alumno</button>

	</form>
	<a href="index.jsp" class="btn btn-primary">volver</a>
</section>
<jsp:include page="include/footer.jsp" />