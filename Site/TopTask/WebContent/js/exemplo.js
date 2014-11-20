function proximo() {
	$("#main").empty();
	$.get("exemplo/tarefaExemplo.jsp", function(data) {
		$("#main").html(data);
	});
}

var tour = {
	id : "myTour",
	i18n : {
		nextBtn : "Próximo",
		prevBtn : "Anterior",
		doneBtn : "Ok",
		skipBtn : "Pular",
		closeTooltip : "Fechar"
	},
	steps : [
			{
				target : 'passo1',
				placement : 'bottom',
				width : 270,
				title : '<h3><bold>Bem Vindo!</bold></h3>',
				xOffset : 15,
				content : "Seja Bem Vindo Ao TopTask."

			},
			{
				target : 'passo2',
				placement : 'left',
				title : 'Conceitos',
				content : "TopTask trabalha com o conceito de projetos,"
						+ "como podemos ver abaixo.",
				showPrevButton : true,
				width : 270
			},
			{
				target : 'passo3',
				placement : 'right',
				title : 'Exemplo',
				content : "Veja um exemplo de projeto, uma Semana de Provas é um projeto."
						+ "Vamos analisar como isso funciona. Clique no link ao lado",
				showPrevButton : true,
				width : 250
			}]
}


function iniciar() {
	hopscotch.startTour(tour);
}
