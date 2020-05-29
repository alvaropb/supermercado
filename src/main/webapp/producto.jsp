<jsp:include page="include/header.jsp">
	<jsp:param value="Listado de productos" name="titulo"/>
	<jsp:param value="listado-productos" name="pagina"/>
</jsp:include>
   
 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    

<a href="index.jsp">volver</a>
	<h1>Listado Productos</h1>
	<p>${mensaje}</p>

	<%
		// Podemos usar el ${}  EL - Expresion Lenguage en los JSPs
		// suele ser mas comodo que tener que usar < % % >, a estos porcentajes se les llama SCRIPLETS
		// ademas se pueden combinar con JSTL - Java Servlet Tag Libraries
	%>	
	
	<table id="tabla-productos"  class="container">
		<thead>
			<tr>
				<td>Id</td>
				<td>Nombre</td>
				<td>Precio</td>
				<td>Imagen</td>
				<td>EDITAR</td>
				<td>ELIMINAR</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${productos}" var="p">
				<tr>
					<td>${p.id}</td> <% // no hace falta usar el getter p.id == p.getId() %>
					<td>${p.nombre}</td>
					<td>${p.precio}</td>
					<td><img src="${p.foto}" height="100px" width="auto"> </td>
					<td><a href="productos-crear?id=${p.id}" class="fas fa-edit "></a></td>
					<td><a href="producto-eliminar?id=${p.id}" class="far fa-trash-alt"> </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	      	<!-- jQuery -->
	<script language="javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<!-- El JavaScript de DataTables -->
	<script language="javascript" type="text/javascript" src="https://cdn.datatables.net/v/dt/jszip-2.5.0/pdfmake-0.1.18/dt-1.10.12/af-2.1.2/b-1.2.2/b-colvis-1.2.2/b-flash-1.2.2/b-html5-1.2.2/b-print-1.2.2/cr-1.3.2/fc-3.2.2/fh-3.1.2/kt-2.1.3/r-2.1.0/rr-1.1.2/sc-1.4.2/se-1.2.0/datatables.min.js"></script>


      <script language="javascript">
		$('#tabla-productos').DataTable();
	</script>
	
<jsp:include page="include/footer.jsp"/>
