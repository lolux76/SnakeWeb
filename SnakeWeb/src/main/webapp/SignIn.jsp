<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In</title>
<link rel="stylesheet" href="SignIn.css"/>
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Rubik:400,700">
</head>
<body>
<div class="boite">
	<form method="post">
		<h1>Login</h1>
		<div class="formulaire">
		<div class="input">
		<input name="nom" type="text" placeholder="NOM"/>
		</div>
		<div class="input">
		<input name="mdp" type="password" placeholder="PASSWORD"/>
		</div>
		</div>
		<input name="command" value="Login" type="submit"/>
	</form>
</div>
<div class="boite">
	<form method="post">
		<h1>Register</h1>
		<div class="formulaire">
		<div class="input">
		<input name="nom" type="text" placeholder="NOM"/>
		</div>
		<div class="input">
		<input name="mdp" type="password" placeholder="PASSWORD"/>
		</div>
		</div>
		<input name="command" value="Register" type="submit"/>
	</form>
</div>
</body>
</html>