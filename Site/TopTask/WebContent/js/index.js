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

$.post( "rs/login",{ email: Element('email').value, senha: Element('senha').value },
  function( data ){
	alert(data);
 });

function logar() {
	var params = "{'email':"+ Element('email').value +"}";
	var email = $("#email").val();
    var senha = $("#senha").val();
	$.ajax({
		type : "POST",
		url : "UserControlsService.asmx/GetUserControl",
		data : {
			"email" : email,
			"senha" : senha
		},
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success: function (data) {
            alert("success");
        },
        error: function (e) {
            alert("error" + e)
        }
	});

}