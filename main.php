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

<html>
<head>
<title>Horstel</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

<a href='logout.php'>Logud</a> - <a href='horses.php'>Horses</a> - <a href='stable.php'>Stable</a> - 
<a href='findStable.php'>FindStable</a> - <a href='reviewstable.php'>ReviewStable</a> - 
<a href='createadmin.php'>CreateAdmin</a> - <a href='alteruser.php'>AlterUser</a>
<br><br>

</body>
</html>