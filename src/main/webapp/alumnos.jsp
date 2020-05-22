
<%@page import="com.ipartek.formacion.modelo.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%
	ArrayList<Usuario> usuarios = (ArrayList<Usuario>) request.getAttribute("alumnos");
%>

<table>
	<tr>
		<th>Id</th>
		<th>Nombre</th>
	</tr>
	<%
		for (Usuario usuario : usuarios) {
	%>
	<tr>
		<td><%= usuario.getId() %></td>
		<td><%= usuario.getNombre() %></td>
	</tr>
	<%
		}
	%>
</table>