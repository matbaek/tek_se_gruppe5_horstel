<?php
session_start();

if($_SESSION['id']) {
	header("Location: main.php");
} 
?>

<html>
<head>
<title>Horstel</title>
</head>
<body>

<h1>Create User</h1>

<form action="controller.php?t=cu" method="POST">
	Name:<input type="text" name="name" value="">
	<br>
  	Phone number:<input type="text" name="phoneNr" value="">
	<br>
  	Adress:<input type="text" name="adress" value="">
	<br>
  	Email:<input type="text" name="email" value="">
	<br>
  	Password:<input type="password" name="password" value="">
  	<br><br>
  	<input type="submit" value="Submit">
</form>
<br><br><br>
<h1>Login</h1>
<form action="controller.php?t=l" method="POST">
  	Email:<input type="text" name="email" value="">
	<br>
  	Password:<input type="password" name="password" value="">
  	<br><br>
  	<input type="submit" value="Submit">
</form>

</body>
</html>