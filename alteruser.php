<?php
session_start();
include_once("controller.php");

switch($_SESSION['id']) {
	case null:
	case "":
	case "-1":
	case "0":
		header("Location: index.php");
		break;

}

switch($_SESSION['admin']) {
	case null:
	case "":
	case "0":
		header("Location: findStable.php");
		break;
}

$result = getUser();
?>

<html>
<head>
<title>Horstel</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

<div style="float: right;text-align: right;">
	<a href='findstable.php'>Back</a>
</div>
<h1>Alter user</h1>
<?php
$count = 1;

$countSplit = explode("; ", $result);

for($i=0;$i<$countSplit[0];$i++) {
	$stringSplit = explode(", ", $countSplit[$count]);
	echo $stringSplit[1] . " - ";
	if($stringSplit[3] == 1) {
		echo "Active user <a href='controller.php?t=au&id=" . $stringSplit[0] . "&a=0'>Deactivate user</a>";
	} elseif($stringSplit[3] == 0) {
		echo "Deactivated user <a href='controller.php?t=au&id=" . $stringSplit[0] . "&a=1'>Activate user</a>";
	}
	echo "<br>";
	$count++;
}
?>


</body>
</html>
