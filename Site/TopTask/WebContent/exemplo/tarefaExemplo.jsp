<%
	session.setAttribute("page", "exemploTarefa");
%>

<%@include file="../menu/menuProjeto.html" %>

<div class="container-fluid">
	<h1 class="ls-title-intro ls-ico-list2">
		<em id="tour1">[Semana de Provas] - Tarefas</em>
	</h1>

	<table class="ls-table">
		<thead>
			<tr>
				<th>T�tulo</th>
				<th>Dono</th>
				<th>Prioridade</th>
				<th class="hidden-xs">Data de Conclus�o</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href="" title="" id="tour2">Prova de Matem�tica</a></td>
				<td class="hidden-xs">Jo�o</td>
				<td>Alta</td>
				<td class="hidden-xs">28/11/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Prova de Geografia</a></td>
				<td class="hidden-xs" id="tour4">Bruno</td>
				<td>M�dia</td>
				<td class="hidden-xs">27/11/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Prova de Qu�mica</a></td>
				<td class="hidden-xs">Edylson</td>
				<td id="tour3">Alta</td>
				<td class="hidden-xs">27/11/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Apresenta��o de Qu�mica</a></td>
				<td class="hidden-xs">Edylson</td>
				<td>Baixa</td>
				<td class="hidden-xs">04/12/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Prova de Portugu�s</a></td>
				<td class="hidden-xs">Jo�o</td>
				<td>M�dia</td>
				<td class="hidden-xs">27/11/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Lista de F�sica</a></td>
				<td class="hidden-xs">La�s</td>
				<td>Baixa</td>
				<td class="hidden-xs">26/12/2014</td>
			</tr>
		</tbody>
	</table>
</div>

	<script type="text/javascript" src="js/tarefaExemplo.js"></script>