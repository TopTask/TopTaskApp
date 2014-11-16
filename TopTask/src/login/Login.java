package login;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import bean.Usuario;
import dao.Usuarios;

@Path("/")
public class Login {

	public static Usuarios usuarios;
	@GET
	@Produces("text/xml")
	public String get() {
		return "<html> " + "<title>" + "Hello Teste" + "</title>"
				+ "<body><h1>" + "Hello World" + "</body></h1>" + "</html> ";
	}

	@POST
	@Path("novo")
	@Produces("text/html")
	public String cadastrarUsuario(@FormParam("txtNome") String nome,
			@FormParam("txtEmail") String email,
			@FormParam("txtSenha") String senha,
			@FormParam("txtConfSenha") String confSenha,
			@FormParam("carregaArq") String arqFoto) {
		
		Usuario u = new Usuario(nome, email, senha, arqFoto);
		String emailCadastrado = usuarios.inserir(u);
		return emailCadastrado;
	}

	
	@POST
	@Path("login")
	@Produces("text/html")
	public String login(@FormParam("email") String email,
			@FormParam("senha") String senha) {

		System.out.println("Email = " + email + " Senha = " + senha);
		/*Usuario u = (Usuario) usuarios.pesquisar(email);
		if (u != null && u.getEmail().equals(email)
				&& u.getSenha().equals(senha))
			return generateJsonForUser(u);
		else*/
			return "{\"id\":-1}";
	}

	/**
	 * Método que gera as informações de um certo usuário em um json
	 * 
	 * @param u
	 *            Usuário que terá as informações convertidas
	 * @return Json com informações de usuário
	 */
	private String generateJsonForUser(Usuario u) {
		String json = "{\"id\":" + u.getId() + ",\"nome\": " + u.getNome()
				+ ",\"email\":" + u.getEmail() + ",\"senha\":" + u.getSenha()
				+ ",\"foto\":" + u.getFoto() + "}";
		return json;
	}

}
