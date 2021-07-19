/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netInterface;

import interfaces.ICallbackException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author erik0
 */
public class Cliente {
    private DatagramSocket socket;
    private InetAddress endereco;
    private int porta;
    
    private byte[] buf = new byte[1024];
    
    private ICallbackException callbackException;
    
    public Cliente(ICallbackException callbackException, String endereco, int porta) {
        try {
            
            this.callbackException = callbackException;
            
            this.endereco = InetAddress.getByName(endereco);
            this.porta = porta;
            
            socket = new DatagramSocket();

            
        } catch (Exception e) {
            callbackException.run(e);
        }
    }
    
    public String enviar(String msg) {
        try {
            enviarRequisicao(msg);
            
            String received = receberResposta();
  
            return received;
        } catch (IOException ex) {
            callbackException.run(ex);
        }
        
        return null;
    }

    private String receberResposta() throws IOException {
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String received = new String(
                packet.getData(), 0, packet.getLength());
        return received;
    }

    private void enviarRequisicao(String msg) throws IOException {
        byte[] msgBytes = msg.getBytes();
        
        DatagramPacket packet
                = new DatagramPacket(msgBytes, msgBytes.length, endereco, porta);
        socket.send(packet);
    }
    
    
    public void close() {
        socket.close();
    }
}
