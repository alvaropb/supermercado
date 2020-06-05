	function cifrar() {
		alert("mensaje de alerta");
		console.log('cifrar y conseguir hash');
		
		//recupero la contraseña del input a atves de su ID
		var contrasenia = document.getElementById('pass').value;
		
		// recuperar campo repass
		
		var repass = document.getElementById('repass').value;
		
		
		//consigo el hash mediante la libreria incluida en el pie.jsp
		var hash = md5(contrasenia);
				
		
		if ( repass != null ) {// estamos editando
			if(repass == contrasenia ){
				document.getElementById('pass').value = hash;
			}else{
				document.getElementById('pass').value ="contraseinas no coinciden";
			}
			
		}else{// estamos creando
			//guardo en el inpput el codigo hash
			document.getElementById('pass').value = hash;		

		}
		
		//enviar formulario
		return true; // si ponemos false no se envia el formulario
		
	}