<?php
session_start();

$result = "";
$port = 4343; //the port on which we are connecting to the "remote" machine
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
	
	if($result == "-1") {
		header("Location: index.php");
	} else {
		 $_SESSION["id"] = $result;
		header("Location: main.php");
	}
}

function createStable($accountID, $fee, $description, $adress, $spaces, $name) {
	$message = "CreateStable][" . $accountID . "; " . $fee . "; " . $description . "; " . $adress . "; " . $spaces . "; " . $name;
	sendSQL($message);
	header("Location: main.php");
}

function getHorse($accountID) {
	$message = "GetHorse][" . $accountID;
	$result = sendSQL($message);
	
	header("Location: horses.php");
}

function addHorse($accountID, $name) {
	$message = "AddHorse][" . $accountID . "; " . $name;
	sendSQL($message);
	header("Location: main.php");
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
		createStable($accountID, $fee, $description, $adress, $spaces, $name);
		break;
		
	case "gh":
		$accountID = $_SESSION['id'];
		getHorse($accountID);
		break;
		
	case "ah":
		$accountID = $_SESSION['id'];
		$name = $_POST['name'];
		AddHorse($accountID, $name);
		break;
}

?>