package proxy;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketConnect implements SocketInter {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    /**
     * @param socket
     * @param in
     * @param out
     */
    public SocketConnect(String host, int port) {
        try {

            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Conectado OK ");
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

    @Override
    public String readLine() {
        String str = null;
        try {
            str = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str;
    }

    @Override
    public void writeLine(String str) {
        // TODO Auto-generated method stub
        // 4. The wrapper delegates to the target
        try {
            DataOutputStream data = new DataOutputStream(socket.getOutputStream());
            
            data.writeUTF(str);
            System.out.println("Dato enviado");

        } catch (IOException e) {
            // TODO Auto-generated catch block
            System.out.println("Dato no enviado: " + e.getMessage());
        }

    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub
        try {
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
