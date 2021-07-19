/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.net.InetAddress;

/**
 *
 * @author erik0
 */
public interface Iprocessavel {
    String processar(String recebido, InetAddress enderecoCliente, int porta);
}
