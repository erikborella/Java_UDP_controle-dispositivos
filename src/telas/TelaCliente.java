/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dispostivos.TipoStatus;
import info.DispositivosLoader;
import javax.swing.JOptionPane;
import netInterface.Cliente;
import org.json.JSONObject;
import utils.DialogOnOff;
import utils.OptionsPanelsUtils;

/**
 *
 * @author erik0
 */
public class TelaCliente extends javax.swing.JFrame {

    private Cliente cliente;
    
    private String[] locais = DispositivosLoader.getLocaisDisponiveis();
    
    public TelaCliente(String enderecoServidor, int portaSevidor) {
        initComponents();
        
        this.cliente = new Cliente(
                e -> mostrarException(e), 
                enderecoServidor, 
                portaSevidor);
        
        adicionarLocais();
    }

    private void adicionarLocais() {
        for (String local : locais)
            locaisComboBox.addItem(local);
    }
    
    private void mostrarException(Exception e) {
        OptionsPanelsUtils.mostrarMensagemErro(e.getMessage());
    }
    
    private void lerStatus() {
        String local = locaisComboBox.getSelectedItem().toString();
        String jsonReq = criarJsonGet(local);
        
        String jsonRes = cliente.enviar(jsonReq);
        exibirStatus(jsonRes);
        
    }
    
    
    private void setDispositivo() {
        TipoStatus status = DialogOnOff.ler(this);
        String local = locaisComboBox.getSelectedItem().toString();
        
        String jsonReq = criarJsonSet(local, status);
        
        String jsonRes = cliente.enviar(jsonReq);
        exibirStatus(jsonRes);
    }
    
    private String criarJsonGet(String local) {
        JSONObject json = new JSONObject();
        
        json.put("command", "get");
        json.put("locate", local);
        
        return json.toString();
    }
    
    private String criarJsonSet(String local, TipoStatus status) {
        JSONObject json = new JSONObject();
        
        json.put("command", "set");
        json.put("locate", local);
        json.put("value", status);
        
        return json.toString();
    }
    
    private void exibirStatus(String json) {
        JSONObject ob = new JSONObject(json);
        
        if (!ob.has("status")) {
            OptionsPanelsUtils.mostrarMensagemErro("Campo \"status\" não encontrado");
            return;
        }
        
        String status = ob.getString("status");
        
        JOptionPane.showMessageDialog(this, "Status: " + status);
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        locaisComboBox = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lerStatusButton = new javax.swing.JButton();
        setStatusButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("local:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("Gerenciador dispositivos");

        jPanel1.setLayout(new java.awt.GridLayout(1, 2, 50, 0));

        lerStatusButton.setText("Ler status");
        lerStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lerStatusButtonActionPerformed(evt);
            }
        });
        jPanel1.add(lerStatusButton);

        setStatusButton.setText("Setar status");
        setStatusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setStatusButtonActionPerformed(evt);
            }
        });
        jPanel1.add(setStatusButton);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(locaisComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel2)
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(locaisComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(34, 34, 34)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lerStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lerStatusButtonActionPerformed
        lerStatus();
    }//GEN-LAST:event_lerStatusButtonActionPerformed

    private void setStatusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_setStatusButtonActionPerformed
        setDispositivo();
    }//GEN-LAST:event_setStatusButtonActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton lerStatusButton;
    private javax.swing.JComboBox<String> locaisComboBox;
    private javax.swing.JButton setStatusButton;
    // End of variables declaration//GEN-END:variables
}
