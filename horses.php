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

<a href='main.php'>Back</a>
<br><br>
<h1>Add horse</h1>
<form action="controller.php?t=ah" method="POST">
  	Name:<input type="text" name="name" value="">
  	<br><br>
  	<input type="submit" value="Submit">
</form>
<br><br>
<?php



?>