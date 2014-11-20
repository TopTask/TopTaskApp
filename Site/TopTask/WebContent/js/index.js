/**
 * 
 */
function Element(id) {
	return document.getElementById(id);
}

$("#email").focus(function() {
	$("#login").removeClass("has-error");
});

/*
 * function logar() { $.ajax({ url: "http://localhost/TopTask/rs/login"
 * }).then(function(data) { if (data.id == -1)
 * $("#login").addClass("has-error"); alert("Resultado "); }); }
 */

function logar() {
	var params = "{'email':" + Element('email').value + "}";
	var email = $("#email").val();
	var senha = $("#senha").val();
	$.post("rs/login", {
		email : Element('email').value,
		senha : Element('senha').value
	}, function(data) {
		alert(data);
	});

}