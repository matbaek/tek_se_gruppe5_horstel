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

$result = showStable($_GET['id']);

?>

<html>
<head>
	<title>Horstel</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<style>
	.startPlaceholder {
		position: relative;
	}

	.startPlaceholder::after {
		position: absolute;
		left: 70px;
		top: 1px;
		content: attr(data-placeholder);
		pointer-events: none;
		opacity: 0.6;
		letter-spacing: -1px;
	}
	.endPlaceholder {
		position: relative;
	}

	.endPlaceholder::after {
		position: absolute;
		left: 65px;
		top: 1px;
		content: attr(data-placeholder);
		pointer-events: none;
		opacity: 0.6;
		letter-spacing: -1px;
	}
	</style>
</head>
<body>

<div style="float: right;text-align: right;">
	<a href='findstable.php'>Back</a>
</div>
<?php 
$stringSplit = explode("; ", $result);
$countSplit = explode("][", $stringSplit[1]);

?>
<div style="float: left; width: 70%;">
	<?php echo $countSplit[6]; ?>
	<div style="float: right; width: 30%;">
		<?php
		if(($countSplit[4]/$stringSplit[0]) == 0) {
			echo "<img src='images/zeroStars.png'>";
		} else if($countSplit[4]/$stringSplit[0] >= 4.5) {
			echo "<img src='images/fiveStars.png'>";
		} else if($countSplit[4]/$stringSplit[0] >= 3.5) {
			echo "<img src='images/fourStars.png'>";
		} else if($countSplit[4]/$stringSplit[0] >= 2.5) {
			echo "<img src='images/threeStars.png'>";
		} else if($countSplit[4]/$stringSplit[0] >= 1.5) {
			echo "<img src='images/twoStars.png'>";
		} else if($countSplit[4]/$stringSplit[0] >= 0.5) {
			echo "<img src='images/oneStar.png'>";
		}
		?>
	</div>
	<br>
	<?php echo $countSplit[2] . " - " . $countSplit[7]; ?>
</div>
<div style="float: right; width: 30%;">
	<img src="http://projects.mn-web.dk/Horstel/images/<?php echo $countSplit[3]; ?>" style="width:40%;height:40%;">
	<br>
	<?php echo $countSplit[1]; ?>
	<br><br>
	Fee: <?php echo $countSplit[0]; ?> €
</div>
<br><br>
<a href='controller.php?t=gr&id=<?php echo $_GET['id']; ?>'>Update Reservation</a>
<br><br>

<?php 
$date01 = false; $date02 = false; $date03 = false; $date04 = false; $date05 = false; $date06 = false; $date07 = false;
$date08 = false; $date09 = false; $date10 = false; $date11 = false; $date12 = false; $date13 = false; $date14 = false; 
$date15 = false; $date16 = false; $date17 = false; $date18 = false; $date19 = false; $date20 = false; $date21 = false; 
$date22 = false; $date23 = false; $date24 = false; $date25 = false; $date26 = false; $date27 = false; $date28 = false; 
$date29 = false; $date30 = false; $date31 = false; 

$eachCalenderSplit = explode(";", $_SESSION['reservation']);
$count = 1;
$countTwo = 0;

while($countTwo < $eachCalenderSplit[0]) {
	$calenderSplit = explode(",", $eachCalenderSplit[$count]);
	$firstDayStart = explode("-", $calenderSplit[0]);
	$firstDayEnd = explode("-", $calenderSplit[1]);
	$count++;
	$countTwo++;
	
	for($i=$firstDayStart[0]; $i<=$firstDayEnd[0]; $i++) {
		if($i == 01 || $i == 1) {
			$date01 = true;
  		} if($i == 02 || $i == 2) {
			$date02 = true;
  		} if($i == 03 || $i == 3) {
	  		$date03 = true;
  		} if($i == 04 || $i == 4) {
	  		$date04 = true;
  		} if($i == 05 || $i == 5) {
	 		$date05 = true;
  		} if($i == 06 || $i == 6) {
	  		$date06 = true;
  		} if($i == 07 || $i == 7) {
	  		$date07 = true;
  		} if($i == 08 || $i == 8) {
	  		$date08 = true;
  		} if($i == 09 || $i == 9) {
	  		$date09 = true;
  		} if($i == 10) {
	  		$date10 = true;
  		} if($i == 11) {
	  		$date11 = true;
  		} if($i == 12) {
	  		$date12 = true;
  		} if($i == 13) {
	  		$date13 = true;
  		} if($i == 14) {
	  		$date14 = true;
  		} if($i == 15) {
	  		$date15 = true;
  		} if($i == 16) {
	  		$date16 = true;
  		} if($i == 17) {
	  		$date17 = true;
  		} if($i == 18) {
	  		$date18 = true;
  		} if($i == 19) {
	  		$date19 = true;
  		} if($i == 20) {
	  		$date20 = true;
  		} if($i == 21) {
	  		$date21 = true;
  		} if($i == 22) {
	  		$date22 = true;
  		} if($i == 23) {
	  		$date23 = true;
  		} if($i == 24) {
	  		$date24 = true;
  		} if($i == 25) {
	  		$date25 = true;
  		} if($i == 26) {
	  		$date26 = true;
  		} if($i == 27) {
	  		$date27 = true;
  		} if($i == 28) {
	  		$date28 = true;
  		} if($i == 29) {
	  		$date29 = true;
  		} if($i == 30) {
	  		$date30 = true;
  		} if($i == 31) {
	  		$date31 = true;
 		} 
	}
}
unset($_SESSION['reservation']);
?>

<table border=1">
	<tr>
		<td colspan="7" align="center">
			<b>December</b>
		</td>
	</tr>
	<tr>
		<td>
			<b>Monday</b>
		</td>
		<td>
			<b>Tuesday</b>
		</td>
		<td>
			<b>Wednesday</b>
		</td>
		<td>
			<b>Thursday</b>
		</td>
		<td>
			<b>Friday</b>
		</td>
		<td>
			<b>Saturday</b>
		</td>
		<td>
			<b>Sunday</b>
		</td>
	</tr>
	<tr>
		<td>
			
		</td>
		<td <?php if($date01 == true) { echo "bgcolor='#FF0000'"; } ?>>
			01
		</td>
		<td <?php if($date02 == true) { echo "bgcolor='#FF0000'"; } ?>>
			02
		</td>
		<td <?php if($date03 == true) { echo "bgcolor='#FF0000'"; } ?>>
			03
		</td>
		<td <?php if($date04 == true) { echo "bgcolor='#FF0000'"; } ?>>
			04
		</td>
		<td <?php if($date05 == true) { echo "bgcolor='#FF0000'"; } ?>>
			05
		</td>
		<td <?php if($date06 == true) { echo "bgcolor='#FF0000'"; } ?>>
			06
		</td>
	</tr>
	<tr>
		<td <?php if($date07 == true) { echo "bgcolor='#FF0000'"; } ?>>
			07
		</td>
		<td <?php if($date08 == true) { echo "bgcolor='#FF0000'"; } ?>>
			08
		</td>
		<td <?php if($date09 == true) { echo "bgcolor='#FF0000'"; } ?>>
			09
		</td>
		<td <?php if($date10 == true) { echo "bgcolor='#FF0000'"; } ?>>
			10
		</td>
		<td <?php if($date11 == true) { echo "bgcolor='#FF0000'"; } ?>>
			11
		</td>
		<td <?php if($date12 == true) { echo "bgcolor='#FF0000'"; } ?>>
			12
		</td>
		<td <?php if($date13 == true) { echo "bgcolor='#FF0000'"; } ?>>
			13
		</td>
	</tr>
	<tr>
		<td <?php if($date14 == true) { echo "bgcolor='#FF0000'"; } ?>>
			14
		</td>
		<td <?php if($date15 == true) { echo "bgcolor='#FF0000'"; } ?>>
			15
		</td>
		<td <?php if($date16 == true) { echo "bgcolor='#FF0000'"; } ?>>
			16
		</td>
		<td <?php if($date17 == true) { echo "bgcolor='#FF0000'"; } ?>>
			17
		</td>
		<td <?php if($date18 == true) { echo "bgcolor='#FF0000'"; } ?>>
			18
		</td>
		<td <?php if($date19 == true) { echo "bgcolor='#FF0000'"; } ?>>
			19
		</td>
		<td <?php if($date20 == true) { echo "bgcolor='#FF0000'"; } ?>>
			20
		</td>
	</tr>
	<tr>
		<td <?php if($date21 == true) { echo "bgcolor='#FF0000'"; } ?>>
			21
		</td>
		<td <?php if($date22 == true) { echo "bgcolor='#FF0000'"; } ?>>
			22
		</td>
		<td <?php if($date23 == true) { echo "bgcolor='#FF0000'"; } ?>>
			23
		</td>
		<td <?php if($date24 == true) { echo "bgcolor='#FF0000'"; } ?>>
			24
		</td>
		<td <?php if($date25 == true) { echo "bgcolor='#FF0000'"; } ?>>
			25
		</td>
		<td <?php if($date26 == true) { echo "bgcolor='#FF0000'"; } ?>>
			26
		</td>
		<td <?php if($date27 == true) { echo "bgcolor='#FF0000'"; } ?>>
			27
		</td>
	</tr>
	<tr>
		<td <?php if($date28 == true) { echo "bgcolor='#FF0000'"; } ?>>
			28
		</td>
		<td <?php if($date29 == true) { echo "bgcolor='#FF0000'"; } ?>>
			29
		</td>
		<td <?php if($date30 == true) { echo "bgcolor='#FF0000'"; } ?>>
			30
		</td>
		<td <?php if($date31 == true) { echo "bgcolor='#FF0000'"; } ?>>
			31
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
		<td>
			
		</td>
	</tr>
</table>
<br><br>
<h1>Reserve stable</h1>
<form action="controller.php?t=cr" method="POST">
	<div class="startPlaceholder" data-placeholder="__-__-____">
		Start date:
		<input type="text" name="startDate"> 
	</div>
	<div class="endPlaceholder" data-placeholder="__-__-____">
		End Date:<input type="text" name="endDate">
	</div>
	<input type="hidden" name="stableID" value="<?php echo $_GET['id']; ?>">
  	<br><br>
  	<input type="submit" value="Submit">
</form>
</body>
</html>