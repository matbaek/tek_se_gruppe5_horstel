<?php

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

	echo $result;
}


function createUser($name, $phoneNr, $adress, $email, $password) {
	$message = "Login][username, 12093812903, hjemasmas 12, oads@asldk.dk, 123123";
	sendSQL($message);
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
		
}

?>