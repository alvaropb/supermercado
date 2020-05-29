<jsp:include page="include/header.jsp">
	<jsp:param value="Registro producto" name="titulo"/>
	<jsp:param value="registro-producto" name="pagina"/>
</jsp:include>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    

	<section>
		<h2>Crear producto</h2>
		<div class="row">
			<div class="col">
				<form action="productos-crear" method="post">
					<div class="form-group">
						<label for="id">Id</label>
						<input type="text" name="id" class="form-control"  readonly value="${producto.id }" />
					</div>
					<div class="form-group">
						<label for="nombre">Nombre</label>
						<input type="text" id="nombre" name="nombre" class="form-control"  value="${producto.nombre }" placeholder="Introduzca nombre del producto"/> 
					</div>
					<div class="form-group">
						<label for="precio">Precio</label>
						<input type="text" id="precio" name="precio" class="form-control"  value="${producto.precio }" placeholder="0.0€"/> 
					</div>
					<div class="form-group">
						<label for="imagen">Imagen</label>
						<input type="text" id="imagen" name="imagen" class="form-control"  value="${producto.foto }" placeholder="URL de la imagen"/> 
					</div>
						<button type="submit" class="btn btn-primary">Registrar producto</button>
						
				</form>
			</div>
			<div class="col">
				<img alt="" src="${producto.foto }">
			</div>
		</div>
		

	</section>

	
	
<jsp:include page="include/footer.jsp"/>