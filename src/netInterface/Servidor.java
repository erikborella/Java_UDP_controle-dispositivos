/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package netInterface;

import interfaces.ICallbackException;
import interfaces.Iprocessavel;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author erik0
 */
public class Servidor extends Thread {
    private DatagramSocket socket;
    private boolean estaRodando;
    private byte[] buf = new byte[1024];
    
    private int porta;
    
    private Iprocessavel iprocessavel;
    private ICallbackException callbackException;
    
    public Servidor(Iprocessavel iprocessavel, ICallbackException callbackException, int porta) {
        try {
            this.callbackException = callbackException;
            this.iprocessavel = iprocessavel;
            
            this.porta = porta;
            
            socket = new DatagramSocket(porta);
        } catch (SocketException ex) {
            callbackException.run(ex);
        }
    }
    
    @Override
    public void run() {
        
       estaRodando = true;
       
       while (estaRodando) {
           try {
               DatagramPacket packet = new DatagramPacket(buf, buf.length);
               
               String recebida = receberMensagem(packet);
                
               String resposta = this.iprocessavel.processar(
                       recebida, 
                       packet.getAddress(), 
                       packet.getPort()
               );
               
               mandarResposta(packet, resposta);
           } catch (IOException ex) {
               callbackException.run(ex);
           }
       }
       socket.close();
    }

    private void mandarResposta(DatagramPacket packet, String resposta) throws IOException {
        InetAddress enderecoCliente = packet.getAddress();
        int portaCliente = packet.getPort();
        
        byte[] temp = resposta.getBytes();
        DatagramPacket tempPacket = 
                new DatagramPacket(temp, temp.length, enderecoCliente, portaCliente);
        
        socket.send(tempPacket);
    }
    
    private String receberMensagem(DatagramPacket packet) throws IOException {
        socket.receive(packet);
               
        return new String(
            packet.getData(), 0, packet.getLength()
        );
    }
}
