/**
 * 
 */
function Element(id) {
	return document.getElementById(id);
}

function validarSenha() {
	if (Element('txtSenha').value.length > 5) {
		if (Element('txtSenha').value === Element('txtConfSenha').value)
			return true;
		else
			Element('erroConteudo').innerHTML = "<p>"
					+ "As Senhas nao conferem." + "</p>";
		
		$('#erroSenha').modal('show');
		
		return false;
	} else {

		Element('erroConteudo').innerHTML = "<p>"
				+ "Senha deve conter no minimo 6 caracteres ." + "</p>";

		$('#erroSenha').modal('show');

		return false;
	}

}