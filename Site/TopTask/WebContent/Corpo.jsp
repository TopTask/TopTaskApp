<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html class="ls-theme-green">
<head>
<title>TopTask</title>

<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport"
	content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="description"
	content="TopTask - O gerenciador de projetos para a sua vida!">
<link href="css/locastyle.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="css/hopscotch.css"></link>

</head>
<body class="ls-theme-orange">
	<div class="ls-topbar">

		<!-- Notification bar -->
		<div class="ls-notification-topbar ls-theme-orange">
			<!-- Link of support/help -->
			<div id="cabMenu" class="ls-alerts-list">
				<a class="ls-ico-text-2" data-target="#ls-notification-curtain"
					data-ls-module="topbarCurtain" data-counter="5" href="#"></a> <a
					class="ls-ico-bullhorn" data-target="#ls-help-curtain"
					data-ls-module="topbarCurtain" href="#"></a> <a
					class="ls-ico-question" data-target="#ls-feedback-curtain"
					data-ls-module="topbarCurtain" href="#"></a>
			</div>

			<!-- User details -->
			<div data-ls-module="dropdown" class="ls-dropdown ls-user-account">
				<a href="#" class="ls-ico-user"> TopTask <small>(Gerenciador
						de projetos)</small>
				</a>
				<nav class="ls-dropdown-nav ls-user-menu">
					<ul>
						<li><a href="#">Conta</a></li>
						<li><a href="#">Logout</a></li>
					</ul>
				</nav>
			</div>
		</div>

		<span class="ls-show-sidebar ls-ico-menu"></span>

		<!-- Nome do produto/marca -->
		<h1 id="passo1" class="ls-brand-name">
			<a class="" href="/TopTask/">TopTask</a>
		</h1>
	</div>

	<main class="ls-main " id="main"> <%
 	session.setMaxInactiveInterval(1000);
 	String resourceAtual = (String) session.getAttribute("page");
 %> <%
 	if (resourceAtual == null || resourceAtual.equals("index")) {
 		session.setAttribute("page", "index");
 %> <%@include file="index.jsp"%> <%
 	} else if (resourceAtual.equals("novaConta")) {
 		session.setAttribute("page", "novaConta");
 %> <%@include file="../usuario/novaConta.jsp"%>
	<%
		} else if (resourceAtual.equals("exemplo")) {
			session.setAttribute("page", "exemplo");
	%> <%@include file="../exemplo/exemplo.jsp"%> <%
 	} else if (resourceAtual.equals("exemploTarefa")) {
 		session.setAttribute("page", "exemploTarefa");
 %> <%@include file="../exemplo/tarefaExemplo.jsp"%>
	<%
		} else if (resourceAtual.equals("novaTarefa")) {
			session.setAttribute("page", "novaTarefa");
	%> <%@include file="../tarefa/novaTarefa.jsp"%>
	<%
		}
	%>
	</main>

	<%@include file="menu/padrao.html"%>

	<aside class="ls-notification">
		<nav class="ls-notification-list" id="ls-notification-curtain"
			style="left: 1716px;">
			<h3 class="ls-title-2">Notificações</h3>
			<ul>
				<li class="ls-dismissable"><a href="#">Blanditiis est est
						dolorem iure voluptatem eos deleniti repellat et laborum
						consequatur</a> <a href="#" data-ls-module="dismiss"
					class="ls-ico-close ls-close-notification"></a></li>
				<li class="ls-dismissable"><a href="#">Similique eos rerum
						perferendis voluptatibus</a> <a href="#" data-ls-module="dismiss"
					class="ls-ico-close ls-close-notification"></a></li>
				<li class="ls-dismissable"><a href="#">Qui numquam iusto
						suscipit nisi qui unde</a> <a href="#" data-ls-module="dismiss"
					class="ls-ico-close ls-close-notification"></a></li>
				<li class="ls-dismissable"><a href="#">Nisi aut assumenda
						dignissimos qui ea in deserunt quo deleniti dolorum quo et
						consequatur</a> <a href="#" data-ls-module="dismiss"
					class="ls-ico-close ls-close-notification"></a></li>
				<li class="ls-dismissable"><a href="#">Sunt consequuntur
						aut aut a molestiae veritatis assumenda voluptas nam placeat eius
						ad</a> <a href="#" data-ls-module="dismiss"
					class="ls-ico-close ls-close-notification"></a></li>
			</ul>
		</nav>

		<nav class="ls-notification-list" id="ls-help-curtain"
			style="left: 1756px;">
			<h3 class="ls-title-2">Feedback</h3>
			<ul>
				<li><a href="#">&gt; quo fugiat facilis nulla perspiciatis
						consequatur</a></li>
				<li><a href="#">&gt; enim et labore repellat enim debitis</a></li>
			</ul>
		</nav>

		<nav class="ls-notification-list" id="ls-feedback-curtain"
			style="left: 1796px;">
			<h3 class="ls-title-2">Ajuda</h3>
			<ul>
				<li class="ls-txt-center hidden-xs"><a href="#"
					class="ls-btn-dark" onClick="iniciar()">Fazer um Tour</a></li>
				<li><a href="#">&gt; Guia</a></li>
				<li><a href="#">&gt; Wiki</a></li>
			</ul>
		</nav>
	</aside>

	<!-- We recommended use jQuery 1.10 or up -->
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<!-- We recommended use jQuery 1.10 or up 
	<script type="text/javascript" src="js/jquery.js"></script>-->
	<script src="js/locastyle.js" type="text/javascript"></script>

	<script src="js/hopscotch.js"></script>
	<script type="text/javascript" src="js/Corpo.js"></script>
	<script type="text/javascript" src="js/exemplo.js"></script>
	<script type="text/javascript" src="js/index.js"></script>
	<script type="text/javascript" src="js/tarefaExemplo.js"></script>
</body>
</html>