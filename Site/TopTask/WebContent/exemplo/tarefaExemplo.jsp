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
				<th>Título</th>
				<th>Dono</th>
				<th>Prioridade</th>
				<th class="hidden-xs">Data de Conclusão</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href="" title="" id="tour2">Prova de Matemática</a></td>
				<td class="hidden-xs">João</td>
				<td>Alta</td>
				<td class="hidden-xs">28/11/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Prova de Geografia</a></td>
				<td class="hidden-xs" id="tour4">Bruno</td>
				<td>Média</td>
				<td class="hidden-xs">27/11/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Prova de Química</a></td>
				<td class="hidden-xs">Edylson</td>
				<td id="tour3">Alta</td>
				<td class="hidden-xs">27/11/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Apresentação de Química</a></td>
				<td class="hidden-xs">Edylson</td>
				<td>Baixa</td>
				<td class="hidden-xs">04/12/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Prova de Português</a></td>
				<td class="hidden-xs">João</td>
				<td>Média</td>
				<td class="hidden-xs">27/11/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Lista de Física</a></td>
				<td class="hidden-xs">Laís</td>
				<td>Baixa</td>
				<td class="hidden-xs">26/12/2014</td>
			</tr>
		</tbody>
	</table>
</div>

	<script type="text/javascript" src="js/tarefaExemplo.js"></script>