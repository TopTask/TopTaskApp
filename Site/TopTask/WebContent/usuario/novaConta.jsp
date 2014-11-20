<%
	session.setAttribute("page", "novaConta");
%>
<link href="css/locastyle.css" rel="stylesheet" type="text/css">


<div class="container-fluid">
	<h1 class="ls-title-intro ls-ico-user-add">
		<em>Cadastro</em>
	</h1>

	<div class="ls-modal fade test" id="erroSenha">
		<div class="ls-modal-box">
			<div class="ls-modal-header">
				<button data-dismiss="modal">&times;</button>
				<h4 class="ls-modal-title">Erro no Cadastro</h4>
			</div>
			<div class="ls-modal-body" id="erroConteudo"></div>
			<div class="ls-modal-footer">
				<button class="ls-btn ls-float-right" data-dismiss="modal">Ok</button>
			</div>
		</div>
	</div>


	<form action="rs/novo" method="post" onSubmit="return validarSenha()"
		class="ls-form">
		<fieldset>
			<label class="ls-label col-md-6 col-xs-12">
			 	<b class="ls-label-text">Nome</b>
			 	<input name="txtNome" id="txtNome" class="ls-field" type="text"
					maxlength="60" placeholder="Nome Completo" required/>
			</label>
			
			 <label class="ls-label col-md-6 col-xs-12"> 
			 	<b class="ls-label-text">Email</b>
					<input name="txtEmail" id="txtEmail" class="ls-field" type="email"
						maxlength="60" placeholder="Digite seu email" required />

			</label>
			<label class="ls-label col-md-6 col-xs-12"> 
				<b class="ls-label-text">Senha</b>
			 	<input name="txtSenha" id="txtSenha" class="ls-field" type="password"
					maxlength="60" placeholder="Digite seu Senha" required
					minlength="6" />

			</label>
			<label class="ls-label col-md-6 col-xs-12">
			 	<b class="ls-label-text">Confirmação de Senha</b> 
				<input name="txtConfSenha" id="txtConfSenha"
					class="ls-field" type="password" maxlength="60"
					placeholder="Digite a confirmação de sua senha" required
					minlength="6" />
			</label>
			<label class="ls-label col-md-6 col-xs-12">
			 	<b class="ls-label-text">Foto de Perfil:</b> 
				<input type="file" name="carregaArq" id="carregaArq"
					class="ls-field" accept="image/gif, image/jpeg, image/png"
					required/>
			</label>
		</fieldset>
		
		<div class="ls-actions-btn">
			<button type="submit" class="ls-btn">Enviar</button>
			<button type="reset" class="ls-btn-danger">Limpar</button>
		</div>
	</form>
</div>


<!-- We recommended use jQuery 1.10 or up -->
<script type="text/javascript"
	src="http://code.jquery.com/jquery-2.0.3.js"></script>
<script src="js/locastyle.js" type="text/javascript"></script>

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="js/bootstrap.js"></script>
<script src="js/novaConta.js">
	
</script>