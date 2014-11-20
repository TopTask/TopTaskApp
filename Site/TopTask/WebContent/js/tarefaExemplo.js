function proximo() {
	$("#main").empty();
	$.get("exemplo/paginaTarefa.jsp", function(data) {
		$("#main").html(data);
	});
}

var tour = {
	id : "myTour",
	i18n : {
		nextBtn : "Pr�ximo",
		prevBtn : "Anterior",
		doneBtn : "Ok",
		skipBtn : "Pular",
		closeTooltip : "Fechar"
	},
	steps : [
			{
				target : 'tour1',
				placement : 'bottom',
				width : 270,
				title : '<h3><bold>Projetos</bold></h3>',
				xOffset : 15,
				content : "Aqui podemos ver entre '[]' o nome do projeto que est� aberto no momento."

			},
			{
				target : 'tour2',
				placement : 'right',
				title : '<h3><bold>Tarefas</bold></h3>',
				content : "Cada linha desta tabela representa uma Tarefa que ainda deve ser feita. " +
						"Primeiramente, analisamos o nome da Tarefa como vemos ao lado 'Prova de Matem�tica'",
				showPrevButton : true,
				width : 270
			},
			{
				target : 'tour3',
				placement : 'left',
				title : '<h3><bold>Tarefas - Prioridade</bold></h3>',
				content : "TopTask trabalha com as prioridades da Tarefa a fim de melhorar o desempenho do projeto, " +
						"facilitando a visualiza��o plana do projeto.",
				showPrevButton : true,
				width : 250
			},
			{
				target : 'tour4',
				placement : 'left',
				title : '<h3><bold>Dono</bold></h3>',
				content : "Ao lado podemos ver quem criou esta Tarefa. Qual pessoa que faz parte do projeto designou esta tarefa � equipe." +
						"Clique em 'Prova de Matem�tica' para entender mais sobre a Tarefa",
				showPrevButton : true,
				width : 250
			} ]
}

hopscotch.startTour(tour);
