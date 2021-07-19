/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dispostivos.TipoStatus;
import javax.swing.ButtonModel;

/**
 *
 * @author erik0
 */
public class DialogOnOff extends javax.swing.JDialog {
    
    private static TipoStatus tipo;

    public DialogOnOff(java.awt.Frame parent) {
        super(parent, true);
        initComponents();
    }
    
    public static TipoStatus ler(java.awt.Frame parent) {
        new DialogOnOff(parent).setVisible(true);
        return tipo;
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        onOffRadioGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        onRadio = new javax.swing.JRadioButton();
        offRadio = new javax.swing.JRadioButton();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Escolha o status que deseja setar:");

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        onOffRadioGroup.add(onRadio);
        onRadio.setSelected(true);
        onRadio.setText("ON");
        jPanel1.add(onRadio);

        onOffRadioGroup.add(offRadio);
        offRadio.setText("OFF");
        jPanel1.add(offRadio);

        okButton.setText("ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(okButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        ButtonModel btn = onOffRadioGroup.getSelection();
        
        if (btn == onRadio.getModel())
            tipo = TipoStatus.on;
        else
            tipo = TipoStatus.off;
        
        this.dispose();
    }//GEN-LAST:event_okButtonActionPerformed

 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton offRadio;
    private javax.swing.JButton okButton;
    private javax.swing.ButtonGroup onOffRadioGroup;
    private javax.swing.JRadioButton onRadio;
    // End of variables declaration//GEN-END:variables
}
