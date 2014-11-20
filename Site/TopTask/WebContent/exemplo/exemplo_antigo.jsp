<!DOCTYPE html>
<html class="ls-theme-green">
  <head>
    <title>TopTask</title>

    <meta charset="utf-8">
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="description" content="TopTask - O gerenciador de projetos para a sua vida!">
    <link href="http://assets.locaweb.com.br/locastyle/3.6.0/stylesheets/locastyle.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="../css/hopscotch.css"></link>
    
  </head>
  <body class="ls-theme-orange">
    <div class="ls-topbar">

      <!-- Notification bar -->
      <div class="ls-notification-topbar ls-theme-orange">
        <!-- Link of support/help -->
        <div class="ls-alerts-list">
          <a href="#" class="ls-ico-text-2" data-counter="5" data-ls-module="topbarCurtain" data-target="#ls-notification-curtain"><span>Notificações</span></a>
          <a href="#" class="ls-ico-bullhorn" data-ls-module="topbarCurtain" data-target="#ls-help-curtain"><span>Ajuda</span></a>
          <a href="#" class="ls-ico-question" data-ls-module="topbarCurtain" data-target="#ls-feedback-curtain"><span>Sugestões</span></a>
        </div>

        <!-- User details -->
        <div data-ls-module="dropdown" class="ls-dropdown ls-user-account">
          <a href="#" class="ls-ico-user">
            TopTask
            <small>(Gerenciador de projetos)</small>
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
      <h1 id="passo1" class="ls-brand-name"><a class="" href="/TopTask/">
         TopTask</a></h1>
    </div>

    <main class="ls-main ">
      <div  id="main" class="container-fluid">
		<h1  class="ls-title-intro ls-ico-list2">
			<em id="passo2">Meus Projetos</em> 
		</h1>

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
					<td><a href="" title="" id="passo3">Semana de Provas</a></td>
					<td class="hidden-xs">Edylson</td>
					<td class="hidden-xs">30/11/2014</td>
				</tr>
				<tr>
					<td><a href="" title="">Projeto de Conclusão de Curso</a></td>
					<td class="hidden-xs">Laís</td>
					<td class="hidden-xs">25/11/2014</td>
				</tr>
			</tbody>
		</table>
	</div>
    </main>

    <aside class="ls-sidebar ">
      <!-- Defails of user account -->
      <div data-ls-module="dropdown" class="ls-dropdown ls-user-account">
        <a href="#" class="ls-ico-user">
          Abigail Lucas
          <small>(lstyle)</small>
        </a>
        <nav class="ls-dropdown-nav ls-user-menu">
          <ul>
            <li><a href="#">submenu</a></li>
          </ul>
        </nav>
      </div>

      <nav class="ls-menu">
        <ul>
          <li><a href="#" class="ls-ico-home">Página inicial</a></li>
        </ul>
      </nav>
    </aside>

    <aside class="ls-notification">
      <nav class="ls-notification-list" id="ls-notification-curtain" style="left: 1716px;">
        <h3 class="ls-title-2">Notificações</h3>
        <ul>
          <li class="ls-dismissable">
            <a href="#">Blanditiis est est dolorem iure voluptatem eos deleniti repellat et laborum consequatur</a>
            <a href="#" data-ls-module="dismiss" class="ls-ico-close ls-close-notification"></a>
          </li>
          <li class="ls-dismissable">
            <a href="#">Similique eos rerum perferendis voluptatibus</a>
            <a href="#" data-ls-module="dismiss" class="ls-ico-close ls-close-notification"></a>
          </li>
          <li class="ls-dismissable">
            <a href="#">Qui numquam iusto suscipit nisi qui unde</a>
            <a href="#" data-ls-module="dismiss" class="ls-ico-close ls-close-notification"></a>
          </li>
          <li class="ls-dismissable">
            <a href="#">Nisi aut assumenda dignissimos qui ea in deserunt quo deleniti dolorum quo et consequatur</a>
            <a href="#" data-ls-module="dismiss" class="ls-ico-close ls-close-notification"></a>
          </li>
          <li class="ls-dismissable">
            <a href="#">Sunt consequuntur aut aut a molestiae veritatis assumenda voluptas nam placeat eius ad</a>
            <a href="#" data-ls-module="dismiss" class="ls-ico-close ls-close-notification"></a>
          </li>
        </ul>
      </nav>

      <nav class="ls-notification-list" id="ls-help-curtain" style="left: 1756px;">
        <h3 class="ls-title-2">Feedback</h3>
        <ul>
          <li><a href="#">&gt; quo fugiat facilis nulla perspiciatis consequatur</a></li>
          <li><a href="#">&gt; enim et labore repellat enim debitis</a></li>
        </ul>
      </nav>

      <nav class="ls-notification-list" id="ls-feedback-curtain" style="left: 1796px;">
        <h3 class="ls-title-2">Ajuda</h3>
        <ul>
          <li class="ls-txt-center hidden-xs">
            <a href="#" class="ls-btn-dark ls-btn-tour">Fazer um Tour</a>
          </li>
          <li><a href="#">&gt; Guia</a></li>
          <li><a href="#">&gt; Wiki</a></li>
        </ul>
      </nav>
    </aside>

    <!-- We recommended use jQuery 1.10 or up -->
    <script type="text/javascript" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script src="http://assets.locaweb.com.br/locastyle/3.6.0/javascripts/locastyle.js" type="text/javascript"></script>
    
    <script src="../js/hopscotch.js"></script>
    <script type="text/javascript" src="../js/exemplo.js"></script>
  </body>
</html>