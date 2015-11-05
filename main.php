<?php
session_start();

switch($_SESSION['id']) {
	case null:
	case "":
	case "-1":
	case "0":
		header("Location: index.php");
		break;

}
?>

<a href='logout.php'>Logud</a> - <a href='controller.php?t=gh'>Horses</a>
<br><br>
<h1>Create stable</h1>
<form action="controller.php?t=cs" method="POST">
  	Name:<input type="text" name="name" value="">
	<br>
  	Adress:<input type="text" name="adress" value="">
	<br>
  	Description:<input type="text" name="description" value="">
	<br>
  	Fee:<input type="number" name="fee" value="">
	<br>
  	Spaces:<input type="number" name="spaces" value="">
  	<br><br>
  	<input type="submit" value="Submit">
</form>