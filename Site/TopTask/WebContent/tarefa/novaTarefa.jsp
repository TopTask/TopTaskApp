<%
	session.setAttribute("page", "novaTarefa");
%>

<%@include file="../menu/menuProjeto.html" %>

<div class="container-fluid">
	<h1 class="ls-title-intro ls-ico-text">
		<em>Tarefa</em>
	</h1>

	<form action="rs/novaTarefa" method="post" class="ls-form">
		<fieldset>
			<label class="ls-label col-md-6 col-xs-12">
			 	<b class="ls-label-text">Nome</b>
			 	<input name="txtNome" id="txtNome" class="ls-field" type="text"
					maxlength="60" placeholder="Nome da Tarefa" required/>
			</label>
			
			 <label class="ls-label col-md-6 col-xs-12"> 
			 	<b class="ls-label-text">Descrição</b>
					<textarea name="txtDescricao" id="txtDescricao" class="ls-textarea-autoresize"
						required ></textarea>

			</label>
			<label class="ls-label col-md-6 col-xs-12"> 
				<b class="ls-label-text">Data Término</b>
			 	<input type="text" name="txtData" class="datepicker"  >
			</label>
			<label class="ls-label col-md-6 col-xs-12">
			 	<b class="ls-label-text">Prioridade</b> 
				<select id="cbPrioridade" class="ls-custom-select">
      				<option value="0"> Alta </option>
      				<option value="1"> Média </option>
    				<option value="2"> Baixa </option>
  				</select>
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