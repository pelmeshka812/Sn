package socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        int port = 999;
        ServerSocket ss = new ServerSocket(port);
        System.out.println("Waiting for a client..");
        Socket socket = ss.accept(); //ждемс подключения..
        System.out.println("Got a client!");
        System.out.println();
        // входной и выходной поток сокета
        InputStream sIn = socket.getInputStream();
        OutputStream sOut = socket.getOutputStream();
        // меняем тип потока
        DataInputStream in = new DataInputStream(sIn);
        DataOutputStream out = new DataOutputStream(sOut);
        String line = null;
        while(true){
            line = in.readUTF();
            System.out.println("Client sent a message " + line);
            out.writeUTF(line);
            out.flush();// поток заканчивает передачу даннных
            System.out.println("Waiting last message ");
        }
    }
}
