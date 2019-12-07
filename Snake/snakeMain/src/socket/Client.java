package socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws IOException {
        int serverPort = 999 ;
        String address = "127.0.0.1";
        InetAddress ipAddress = InetAddress.getByName(address);
        System.out.println("Any of you heard of a socket with IP address" + address + "and port" + serverPort);
        Socket socket = new Socket(ipAddress, serverPort);
        System.out.println("Yes! I just got hold of the program");
        InputStream sIn = socket.getInputStream();
        OutputStream sOut = socket.getOutputStream();
        DataInputStream in = new DataInputStream(sIn);
        DataOutputStream out = new DataOutputStream(sOut);
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        System.out.println("Type in something and press enter. Will send it to the server and tell ya what it thinks");
        System.out.println();
        while (true){
            line = keyboard.readLine();
            System.out.println("Sending this line to the server...");
            out.writeUTF(line);
            out.flush();
            line = in.readUTF();
            System.out.println("The server was very polite. It sent me this : " + line);

        }
    }
}
