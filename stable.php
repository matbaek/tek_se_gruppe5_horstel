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

$result = getStable($_SESSION['id']);
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
	<br>
  	Zip Code:<input type="number" name="zipCode" value="">
  	<br><br>
  	<input type="submit" value="Submit">
</form>

<?php
$count = 1;

$countSplit = explode(";", $result);

for($i=0;$i<$countSplit[0];$i++) {
	$stringSplit = explode(",", $countSplit[$count]);
	echo $stringSplit[0] . ", " . $stringSplit[1] . " - <a href='#'>link</a>";
	echo "<br>";
	$count++;
}
?>

</body>
</html>