<?php
session_start();

$result = "";
$port = 4244; //the port on which we are connecting to the "remote" machine
$host = "mn-web.dk"; //the ip of the remote machine (in this case it's the same machine)

$sock = socket_create(AF_INET, SOCK_STREAM, 0) //Creating a TCP socket
		or die("error: could not create socket\n");
		
$succ = socket_connect($sock, $host, $port) //Connecting to to server using that socket
		or die("error: could not connect to host\n");
		
function sendSQL($sqlString) {
	global $port;
	global $host;
	global $sock;
	global $succ;

	$text = $sqlString; //the text we want to send to the server

	socket_write($sock, $text . "\n", strlen($text) + 1) //Writing the text to the socket
		or die("error: failed to write to socket\n");
		
	socket_recv($sock, $result, 10000, MSG_PEEK)
		or die("error: failed to recieve message\n");

	return $result;
}


function createUser($name, $phoneNr, $adress, $email, $password) {
	$message = "CreateUser][" . $name . "; " . $phoneNr . "; " . $adress . "; " . $email . "; " . $password;
	sendSQL($message);
	header("Location: index.php");
}

function loginCheck($email, $password) {
	$message = "Login][" . $email . "; " . $password;
	$result = sendSQL($message);
	$login = explode(", ", $result);
	
	
	if($login[0] == "-1") {
		header("Location: index.php");
	} else {
		 $_SESSION["id"] = $login[0];
		 $_SESSION["admin"] = $login[1];
		 $_SESSION["superAdmin"] = $login[2];
		 
		header("Location: findstable.php");
	}
}

function getStable($accountID) {
	$message = "GetStable][" . $accountID;
	return sendSQL($message);
}

function createStable($fee, $description, $adress, $spaces, $name, $zipCode, $accountID) {
	$message = "CreateStable][" . $accountID . "; " . $fee . "; " . $description . "; " . $adress . "; " . $spaces . "; " . $name . "; " . $zipCode;
	sendSQL($message);
	header("Location: stable.php");
}

function getHorse($accountID) {
	$message = "GetHorse][" . $accountID;
	return sendSQL($message);
}

function addHorse($accountID, $name) {
	$message = "AddHorse][" . $accountID . "; " . $name;
	sendSQL($message);
	header("Location: horses.php");
}

function findStable() {
	$message = "FindStable][";
	return sendSQL($message);
}

function showStable($id) {
	$message = "ShowStable][" . $id;
	return sendSQL($message);
}

function createReservation($startDate, $endDate, $rentAccID, $stableID) {
	$message = "CreateReservation][" . $startDate . "; " . $endDate . "; " . $rentAccID . "; " . $stableID;
	sendSQL($message);
	header("Location: main.php");
}

function getReservation($id) {
	$message = "GetReservation][" . $id;
	$reservation = sendSQL($message);
	$_SESSION['reservation'] = $reservation;
	header("Location: showstable.php?id=" . $id);
}

function getReviewStable($id) {
	$message = "GetReviewStable][" . $id;
	return sendSQL($message);
}

function reviewStable($sid, $rating, $rid) {
	$message = "ReviewStable][" . $sid . "; " . $rating . "; " . $rid;
	sendSQL($message);
	header("Location: findStable.php");
}

function getUser() {
	$message = "GetUser][";
	return sendSQL($message);
}

function createAdmin($id) {
	$message = "CreateAdmin][" . $id;
	$result = sendSQL($message);
	header("Location: createadmin.php");
}

function activateUser($id, $active) {
	$message = "ActivateUser][" . $id . "; " . $active;
	$result = sendSQL($message);
	header("Location: alteruser.php");
}

// Create User
$type = $_GET['t'];

switch($type) {
	case 'cu':
		$name = $_POST['name'];
		$phoneNr = $_POST['phoneNr'];
		$adress = $_POST['adress'];
		$email = $_POST['email'];
		$password = md5($_POST['password']);
		createUser($name, $phoneNr, $adress, $email, $password);
		break;
		
	case 'l':
		$email = $_POST['email'];
		$password = md5($_POST['password']);
		loginCheck($email, $password);
		break;
		
	case "cs":
		$accountID = $_SESSION['id'];
		$name = $_POST['name'];
		$adress = $_POST['adress'];
		$description = $_POST['description'];
		$fee = $_POST['fee'];
		$spaces = $_POST['spaces'];
		$zipCode = $_POST['zipCode'];
		createStable($accountID, $fee, $description, $adress, $spaces, $name, $zipCode);
		break;
		
	case "ah":
		$accountID = $_SESSION['id'];
		$name = $_POST['name'];
		addHorse($accountID, $name);
		break;
		
	case "ss":
		$id = $_GET['id'];
		showStable($id);
		break;
	
	case "cr":
		$startDate = $_POST['startDate'];
		$endDate = $_POST['endDate'];
		$rentAccID = $_SESSION['id'];
		$stableID = $_POST['stableID'];
		createReservation($startDate, $endDate, $rentAccID, $stableID);
		break;
	
	case "gr":
		$id = $_GET['id'];
		getReservation($id);
		break;
		
	case "rs":
		$sid = $_GET['sid'];
		$rating = $_GET['rating'];
		$rid = $_GET['rid'];
		reviewStable($sid, $rating, $rid);
		break;
		
	case "ca":
		$id = $_GET['id'];
		createAdmin($id);
		break;
		
	case "au":
		$id = $_GET['id'];
		$active = $_GET['a'];
		activateUser($id, $active);
		break;
		
}

?>