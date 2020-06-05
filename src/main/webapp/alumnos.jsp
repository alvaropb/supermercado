<jsp:include page="include/header.jsp">
	<jsp:param value="Listado de alumnos" name="titulo" />
	<jsp:param value="listado-alumnos" name="pagina" />
</jsp:include>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<a href="index.jsp">volver</a>

	<table id="tabla-alumnos" class="container">
		<thead>
			<tr>
				<td>Id</td>
				<td>Nombre</td>
				<td>[Id Rol] Nombre Rol</td>
				<td>OPERACIONES</td>
				
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${alumnos}" var="alumno">
				<tr>
					<td>${alumno.id}</td>
					<td>${alumno.nombre}</td>
					<td>[${alumno.rol.id}]{alumno.rol.nombre}</td>
					<td><a href="alumnos-crear?id=${alumno.id}" class="fas fa-edit "></a>
						<a href="alumnos-eliminar?id=${alumno.id}" class="far fa-trash-alt"> </a>
					</td>
					
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	
	      	<!-- jQuery -->
	<script language="javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- El JavaScript de DataTables -->
	<script language="javascript" type="text/javascript" src="https://cdn.datatables.net/v/dt/jszip-2.5.0/pdfmake-0.1.18/dt-1.10.12/af-2.1.2/b-1.2.2/b-colvis-1.2.2/b-flash-1.2.2/b-html5-1.2.2/b-print-1.2.2/cr-1.3.2/fc-3.2.2/fh-3.1.2/kt-2.1.3/r-2.1.0/rr-1.1.2/sc-1.4.2/se-1.2.0/datatables.min.js"></script>



      <script language="javascript">
		$('#tabla-alumnos').DataTable();
	</script>



<jsp:include page="include/footer.jsp" />