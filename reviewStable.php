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

$result = getReviewStable($_SESSION['id']);
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
<?php
$count = 1;

$countSplit = explode("; ", $result);

for($i=0;$i<$countSplit[0];$i++) {
	$stringSplit = explode(", ", $countSplit[$count]);
	echo $stringSplit[4] . ": <a href='controller.php?t=rs&sid=" .  $stringSplit[0] . "&rating=1&rid=" .  $stringSplit[1] . "'>1</a> - 
		<a href='controller.php?t=rs&sid=" .  $stringSplit[0] . "&rating=2&rid=" .  $stringSplit[1] . "'>2</a> - 
		<a href='controller.php?t=rs&sid=" .  $stringSplit[0] . "&rating=3&rid=" .  $stringSplit[1] . "'>3</a> - 
		<a href='controller.php?t=rs&sid=" .  $stringSplit[0] . "&rating=4&rid=" .  $stringSplit[1] . "'>4</a> - 
		<a href='controller.php?t=rs&sid=" .  $stringSplit[0] . "&rating=5&rid=" .  $stringSplit[1] . "'>5</a>
		<br>Date: " . $stringSplit[2] . " - " . $stringSplit[3] . "<hr>";
	$count++;
}
?>


</body>
</html>
