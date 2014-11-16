<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>TopTask</title>
<!-- Bootstrap -->
<link href="../style/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="../style/css.css" rel="stylesheet" type="text/css"
	media="all" />

</head>
<body>
	<div id="pagina">
		<div id="pagina-bg">
			<div id="cabecalho-container">
				<div id="cabecalho">
					<div id="logo">
						<figure>
							<img src="../res/images/logo.png" width="50" height="50" alt="">
						</figure>
					</div>
					<div id="logo">
						<h1>
							<a href="/TopTask/">TopTask</a>
						</h1>
					</div>
					<div id="menu">
						<ul>
							<li class="active"><a href="/TopTask/">Home</a></li>
							<li><a href="novaConta.jsp">Nova Conta</a></li>
							<li><a href="exemplo.jsp">Exemplo</a></li>
							<li><a href="contato.jsp">Fale Conosco</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div id="form">
				<section>
					<header>
						<h2>
							<span class="label label-info">Formulário de Cadastro</span>
						</h2>
						<br />
					</header>
					<body>
						<div class="modal fade" id="erroSenha">
							<div class="modal-dialog modal-sm">
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal">
											<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
										</button>
										<h4 class="modal-title">Erro no Cadastro!</h4>
									</div>
									<div id="erroConteudo" class="modal-body"></div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default"
											data-dismiss="modal">Close</button>
									</div>
								</div>
								<!-- /.modal-content -->
							</div>
							<!-- /.modal-dialog -->
						</div>
						<!-- /.modal -->
						<div>
							<form role="form" method="post" action="../rs/novo"
								onSubmit="return validarSenha()">
								<div class="form-group">
									<label for="txtNome">Nome: </label> <input name="txtNome"
										id="txtNome" class="form-control" type="text" maxlength="60"
										placeholder="Digite seu nome" required="true" />
								</div>
								<div class="form-group">
									<label for="txtEmail">Email: </label> <input name="txtEmail"
										id="txtEmail" class="form-control" type="email" maxlength="60"
										placeholder="Digite seu email" required="true" />
								</div>
								<div class="form-group">
									<label for="txtSenha">Senha: </label> <input name="txtSenha"
										id="txtSenha" class="form-control" type="password"
										maxlength="60" placeholder="Digite seu Senha" required="true"
										minlength="6" />
								</div>
								<div class="form-group">
									<label for="txtConfSenha">Confirmação de Senha: </label> <input
										name="txtConfSenha" id="txtConfSenha" class="form-control"
										type="password" maxlength="60"
										placeholder="Digite a confirmação de sua senha"
										required="true" minlength="6" />
								</div>
								<div class="form-group">
									<label for="carregaArq">Foto de Perfil: </label> <input
										type="file" name="carregaArq" id="carregaArq"
										class="form-control" accept="image/gif, image/jpeg, image/png"
										required="true" />
								</div>
								<div class="form-group">
									<button type="submit" class="btn btn-success">Cadastrar
									</button>
									<button type="reset" class="btn btn-default ">Limpar</button>
								</div>

							</form>
						</div>
					</body>
				</section>
			</div>
		</div>
	</div>

	<footer id="footerBody">
		<h4>Projeto Desenvolvido por:</h4>
		<h5>Bruno Mattoso Goncalves, Edylson Torikai, Lais Rocha
			Tardelli, Joao Vitor Gengo Vendrame.</h5>
	</footer>


	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="../js/jquery.js">
		
	</script>

	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="../js/bootstrap.js"></script>
	<script src="../js/novaConta.js">
		
	</script>

</body>
</html>