package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ServerProxy extends JFrame implements Runnable{

	JTextArea textmensaje; 
	JButton btsend; 
	/**
	 * 
	 */
	public ServerProxy() {
		textmensaje= new JTextArea(); 
		textmensaje.setBounds(10, 10, 300, 300);
		add(textmensaje); 
		setLayout(null); 
		setSize(400,400); 
		setVisible(true); 
		Thread hilo =new Thread(this);
		hilo.start();
		
	}
 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ServerProxy();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
			ServerSocket server = new ServerSocket(8080);
			Socket socket;
			socket= server.accept(); 
			System.out.println("Puerto abierto");
			DataInputStream Data=new DataInputStream(socket.getInputStream()); 
			String Dato=Data.readUTF();
			textmensaje.append("\n"+Dato);
			socket.close();
			//System.out.println("Puerto abierto");
			if(Dato.equalsIgnoreCase("Done")) {
				server.close();
				break; 
			}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error conexión server");
			System.out.println(e.getMessage());
		}
		
	}

	

}
