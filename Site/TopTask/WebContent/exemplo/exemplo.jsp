<%
	session.setAttribute("page", "exemplo");
%>
<%@include file="../menu/menuLogin.html" %>


<div class="container-fluid">
	<h1 class="ls-title-intro ls-ico-list2">
		<em id="passo2">Meus Projetos</em>
	</h1>
	<button id="btnTour" class="ls-btn-primary" onClick="iniciar()">Iniciar Tour</button>

	<table class="ls-table">
		<thead>
			<tr>
				<th>Título</th>
				<th>Dono</th>
				<th class="hidden-xs">Data de Conclusão</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td><a href="" title="" id="passo3" onclick="proximo()">Semana de Provas</a></td>
				<td class="hidden-xs">Edylson</td>
				<td class="hidden-xs">30/11/2014</td>
			</tr>
			<tr>
				<td><a href="" title="">Trabalho de Conclusão de Curso</a></td>
				<td class="hidden-xs">Laís</td>
				<td class="hidden-xs">25/11/2014</td>
			</tr>
		</tbody>
	</table>
</div>

	<script type="text/javascript" src="js/exemplo.js"></script>