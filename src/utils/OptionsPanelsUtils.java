/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javax.swing.JOptionPane;

/**
 *
 * @author erik0
 */
public class OptionsPanelsUtils {
    public static void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }
    
    public static int lerInt(String descricao, Integer valorPredefinido) {
        while (true) {
            String tempInt = JOptionPane.showInputDialog(null, descricao, valorPredefinido);

            try {
                int val = Integer.parseInt(tempInt);
                return val;
            } catch (NumberFormatException e) {
                 OptionsPanelsUtils.mostrarMensagemErro("Digite um numero!");
                 continue;
            }
        }
    }
    
    public static int lerInt(String descricao) {
        return OptionsPanelsUtils.lerInt(descricao, null);
    }
}
