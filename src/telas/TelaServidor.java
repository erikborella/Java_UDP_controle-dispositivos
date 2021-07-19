/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dispostivos.ControladorDispositivosJson;
import dispostivos.DispositivoBase;
import dispostivos.GerenciadorDispositivos;
import info.DispositivosLoader;
import java.net.InetAddress;
import javax.swing.text.html.HTMLDocument;
import netInterface.Servidor;
import utils.OptionsPanelsUtils;

/**
 *
 * @author erik0
 */
public class TelaServidor extends javax.swing.JFrame {

    private Servidor servidor;
    
    private GerenciadorDispositivos dispositivos = new GerenciadorDispositivos();
    private ControladorDispositivosJson controladorDispositivos = 
            new ControladorDispositivosJson(dispositivos);
    
    public TelaServidor(int porta) {
        initComponents();
        
        adicionarLog("Carregando dispositivos...");
        
        int nDispositivos = carregarDispositivos();
        
        adicionarLog(nDispositivos + " carregados");
        
        servidor = new Servidor(
                (json, enderecoCliente, portaCliente) -> {
                    
                    adicionarLogRequisicao(enderecoCliente, portaCliente, json);
                    String retorno = processar(json);
                    adicionarLogResposta(enderecoCliente, portaCliente, retorno);
                    
                    return retorno;
                }, 
                e -> mostrarException(e), 
                porta);
        
        adicionarLog("Servidor rodando na porta: " + porta);
        
        servidor.start();
    }

    private int carregarDispositivos() {
        DispositivoBase[] disTemp = DispositivosLoader.carregarDispositivos();
        
        for (DispositivoBase dis : disTemp)
            dispositivos.adicionarDispositivo(dis);
        
        return disTemp.length;
    }

    private String processar(String json) {
        return controladorDispositivos.executar(json);
    }
    
    private void mostrarException(Exception e) {
        OptionsPanelsUtils.mostrarMensagemErro(e.getMessage());
    }
    
    private void adicionarLog(String mensagem) {
        HTMLDocument document = 
                (HTMLDocument) logTextPane.getStyledDocument();
        
        try {
            document.insertAfterEnd(
                    document.getCharacterElement(document.getLength()),
                    mensagem + "<br><br>"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        scrollDown();
    }
    
    private void adicionarLogRequisicao(InetAddress endereco, int porta, String msg) {
        adicionarLog(String.format("Requisição recebida -> %s:%d\n%s\n ",
                endereco.toString(), porta, msg));
    }
    
    private void adicionarLogResposta(InetAddress endereco, int porta, String msg) {
        adicionarLog(String.format("respondendo para -> %s:%d\n%s\n ",
                endereco.toString(), porta, msg));
    }
    
    private void scrollDown() {
        logTextPane.selectAll();
        int x = logTextPane.getSelectionEnd();
        logTextPane.select(x, x);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextPane = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("log"));

        logTextPane.setEditable(false);
        logTextPane.setContentType("text/html"); // NOI18N
        jScrollPane1.setViewportView(logTextPane);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane logTextPane;
    // End of variables declaration//GEN-END:variables
}
