
<jsp:include page="include/header.jsp">
	<jsp:param value="Inicio" name="titulo"/>
	<jsp:param value="inicio" name="pagina"/>
</jsp:include>


<h1>Pagina principal</h1>
<%
	out.print("<p>esta linea est� en java</p>");
%>
<a href="MiPrimerServlet">Mi Primer Servlet Ejemplo </a>
<br>
<a href="getYpost.jsp?titulo=GetYPost">get y post</a>
<a href="ver-tabla-alumnos">Ver alumnos en tabla</a>
<br>
<a href="productos">Ver productos</a>
<a href="crear-productos.jsp">crear un producto</a>
<a href="alumnos-crear?id=0">crear un alumno</a>
<a href="cv/formulario.jsp">ir a formulario cv</a><br>

<a href="productos?cards=1" >productos-card</a>

<jsp:include page="include/footer.jsp"/>
