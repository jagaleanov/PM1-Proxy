package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;


import proxy.SocketInter;
import proxy.SocketProxy;
import decorador.CifradoHill;

public class EnviodeDatos extends JFrame implements ActionListener {

    JTextField textmensaje;
    JButton btsend;

    /**
     *
     */
    public EnviodeDatos() {
        textmensaje = new JTextField();
        textmensaje.setBounds(10, 10, 200, 120);
        add(textmensaje);
        btsend = new JButton();
        btsend.setText("SEND");
        btsend.setBounds(10, 135, 120, 20);
        btsend.addActionListener((ActionListener) this);
        add(btsend);
        setLayout(null);
        setSize(400, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        new EnviodeDatos();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        if (arg0.getSource() == btsend) {
            
            SocketInter socket = (SocketInter) new SocketProxy("127.0.0.1", 8080);
            
            socket.writeLine(textmensaje.getText());

            //	textmensaje.setText(socket.readLine());
            socket.dispose();
        }
    }

}
