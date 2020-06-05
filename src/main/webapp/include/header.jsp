<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!doctype html>
<html lang="es">
  <head>
    <title>${param.titulo } | supermerkado </title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link rel="stylesheet" href="css/supermercado.css">    
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/dt/jszip-2.5.0/pdfmake-0.1.18/dt-1.10.12/af-2.1.2/b-1.2.2/b-colvis-1.2.2/b-flash-1.2.2/b-html5-1.2.2/b-print-1.2.2/cr-1.3.2/fc-3.2.2/fh-3.1.2/kt-2.1.3/r-2.1.0/rr-1.1.2/sc-1.4.2/se-1.2.0/datatables.min.css"/>
  </head>
  <body>
      
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
 	<!-- navbar-->
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
        <a class="navbar-brand" href="#"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
      
        <div class="collapse navbar-collapse" id="navbarsExampleDefault">
            <ul class="navbar-nav mr-auto">

                    <li class="nav-item ">
                        <i class="fas fa-cart-arrow-down"></i>
                        
                    </li>
                    <li class="nav-item ${('inicio' eq param.pagina)?'active':'' }">
                        <a class="nav-link" href="index.jsp">Inicio </a>
                    </li>

                    <li class="nav-item ${('registro-producto' eq param.pagina)?'active':'' }">
                        <a class="nav-link" href="crear-productos.jsp" >Formulario registro de producto</a>
                    </li>
                    <c:if test="${not empty usuario_logueado }">
	                    <li class="nav-item ${('listado-productos' eq param.pagina)?'active':'' }">
	                        <a class="nav-link" href="productos" >Listado de productos y buscador</a>
	                    </li>
	                </c:if>
		             <c:if test="${not empty usuario_logueado }">
	                    <li class="nav-item ${('listado-alumnos' eq param.pagina)?'active':'' }">
	                        <a class="nav-link" href="alumnos-crear" >Listado de alumnos y buscador</a>
	                    </li>
	                </c:if>
 

            </ul>
                    <div class="form-inline my-2 my-lg-0">
                     <c:if test="${empty usuario_logueado }"> <!-- TODO pintar en la cabecera el nombre y quitar el btn de login si isLogeado es ok -->
								<a class="btn btn-warning" href="login.jsp" >Login</a>
	                        <!-- TODO pintar en la cabecera el nombre y quitar el btn de login si isLogeado es ok -->
	                     </c:if>
			             <c:if test="${not empty usuario_logueado }">
			             	<strong class="text-light">Ultima conexion: ${cookie['ultima_conexion'].value}|</strong>
	                        <strong class="text-light">idioma: ${cookie['idioma'].value}|</strong>
	                        <strong class="text-light">  usuario: ${usuario_logueado.nombre }</strong>
	                         <a class="btn btn-warning" href="logout" >Logout</a>
						 </c:if>
					</div>

        </div>
    </nav>
<!-- navbar-->

<!-- main-->
      <main id="principal" class="container" >
	 <c:if test="${alerta != null }">     
		<div class="alert alert-${alerta.tipo } alert-dismissible fade show" role="alert">
			  <strong>${alerta.texto}</strong>
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
		</div>
	</c:if>
	
	
		
	<%
		session.setAttribute("alerta", null);
	
	%>		
	

		
		
	
      