function getDataFromPage(page){
	$.get( page, function( data ) {
		  $("#main").html(data);
		});	

}
	
function getMenuFromPage(page){
	$.get( page, function( data ) {
		  $("#cabMenu").html(data);
		});		
}

	
function update(sender){
	var botaoSelecionado = sender.text;
	$("#main").empty();
	
	if (botaoSelecionado === "Home"){
		getDataFromPage("index.jsp");
		getMenuFromPage("cabecalho/padrao.jsp");
		
	}else if (botaoSelecionado === "Cadastro"){
		getDataFromPage("usuario/novaConta.jsp");
		getMenuFromPage("cabecalho/padrao.jsp");
		
	}else if (botaoSelecionado === "Exemplo"){
		getDataFromPage("exemplo/exemplo.jsp");
		getMenuFromPage("cabecalho/projetos.jsp");
	}else if (botaoSelecionado === "Tarefas"){
		getDataFromPage("tarefa/novaTarefa.jsp");
		getMenuFromPage("cabecalho/projetos.jsp");
	}
}