/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proxy;

import decorador.CifradoHill;

/**
 *
 * @author jgale
 */
public class SocketProxy implements SocketInter {
    
    SocketConnect objReal;
    
    public SocketProxy(String host, int port) {
    objReal = new SocketConnect(host, port);
    }

    @Override
    public String readLine() {
        
        return objReal.readLine();
    }

    @Override
    public void writeLine(String str) {
        
        CifradoHill mostrar=new CifradoHill();
        String encriptedMessage = mostrar.cifrar(str);
        objReal.writeLine(encriptedMessage);
    }

    @Override
    public void dispose() {
        objReal.dispose();
    }

}
