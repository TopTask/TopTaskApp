<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TopTask</title>
<link href="css/css.css" rel="stylesheet" type="text/css" media="all" />
<%
	session.setAttribute("page", "index");
%>
</head>
<body>
	<div id="pagina">
		<div id="pagina-bg">

			<SPAN
				style="position: relative; top: 200 px; left: 300 px; width: 150px; height: 270px; margin-top: 45px; margin-left: 130px; float: left;">
				<img width="250px" height="450px" src="res/images/image1.png">
			</SPAN>

			<div id="caixa">
				<div class="caixa-texto">

					<h2>
						<em>Organize sua vida</em>
					</h2>
					<div id="login" class="">
						<form method="POST" action="#" class="ls-form row">
							<br>
							<label class="ls-label col-md-6 col-xs-12"> <b
								class="ls-label-text">Email</b> <input name="email" id="email"
								class="ls-field	" type="email" maxlength="60"
								placeholder="Digite seu email" required />
							</label>
							
							 <label class="ls-label col-md-6 col-xs-12"> <b
								class="ls-label-text">Senha</b> <input name="txtSenha"
								id="txtSenha" class="ls-field" type="password" maxlength="60"
								placeholder="Digite seu Senha" required minlength="6" />

							</label>
							<label class="ls-label col-md-6 col-xs-12"> 
								<input class="ls-field" type="submit" name="submit"
								value="Conectar" id="submit" />
							</label>
						</form>
					</div>
					
					<a href="usuario/novaConta.jsp">Ainda nao possuo uma conta
						TopTask</a>
				</div>
			</div>
		</div>
	</div>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="js/jquery.js">
		
	</script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.js"></script>
	<script src="js/index.js">
		
	</script>

</body>
</html>
