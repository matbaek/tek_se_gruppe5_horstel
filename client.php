<?php


function isRunning()  {
        $address = 'darestium.dyndns-free.com';
        $port = 4343;
        $socket = socket_create(AF_INET, SOCK_STREAM, getprotobyname('tcp'));
        $message = 'loolololol';

        try {
            socket_connect($socket, $address, $port);

            $status = socket_sendto($socket, $message, strlen($message), MSG_EOF, $address, $port);

            if ($status != false)  {
                return true;
		echo "true";
            }

            return false;
	    echo "false";
        } catch (Exception $e)  {
            return false;
	    echo "false";
        }
    }
?>
