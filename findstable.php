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

$result = findStable();
?>

<html>
<head>
<title>Horstel</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>
<a href='findStable.php'>Frontside</a> - <a href='horses.php'>Horses</a> - 
<a href='stable.php'>Stable</a> - <a href='reviewstable.php'>ReviewStable</a>
<?php
if($_SESSION['admin'] == 1){
	echo " - <a href='alteruser.php'>AlterUser</a>";
} elseif($_SESSION['superAdmin'] == 1) {
	echo " - <a href='createadmin.php'>CreateAdmin</a>";
}
?>
<div style="float: right;text-align: right;">
	<a href='logout.php'>Logud</a>
</div>
<br><br>

<?php
$count = 1;

$countSplit = explode(";", $result);

for($i=0;$i<$countSplit[0];$i++) {
	$stringSplit = explode(", ", $countSplit[$count]);
	echo "<a href=showstable.php?id=" . $stringSplit[0] . ">" . $stringSplit[1] . "</a> <b>Location:</b> " . $stringSplit[2] . " - " . $stringSplit[3] . "<hr>";
	$count++;
}

unset($_SESSION['result']);
unset($_SESSION['reservation']);
?>


</body>
</html>
