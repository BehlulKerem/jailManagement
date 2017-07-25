/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import javax.swing.JOptionPane;

/**
 *
 * @author MesutKutlu
 */
public class personelDetails extends javax.swing.JFrame {
    public boolean edit=false;
    String isim,soyisim,pozisyon;
    Integer personelno,ssn;
    Double maas;
    /**
     * Creates new form personelDetails
     */
    public personelDetails() {
        
        initComponents();
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("images.jpg"))); // NOI18N
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateComponentFormatter1 = new org.jdatepicker.impl.DateComponentFormatter();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(400, 300));
        getContentPane().setLayout(null);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(10, 30, 120, 150);

        jLabel2.setText("İsim:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(140, 20, 60, 14);

        jLabel3.setText("Soyisim:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(140, 50, 80, 14);

        jLabel4.setText("SSN:");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(140, 80, 50, 14);

        jLabel5.setText("Pozisyonu:");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(140, 140, 80, 14);

        jLabel6.setText("Maaş:");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(140, 170, 60, 14);

        jTextField1.setEditable(false);
        jTextField1.setText("jTextField1");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(210, 20, 110, 20);

        jTextField2.setEditable(false);
        jTextField2.setText("jTextField2");
        getContentPane().add(jTextField2);
        jTextField2.setBounds(210, 50, 110, 20);

        jTextField3.setEditable(false);
        jTextField3.setText("jTextField3");
        getContentPane().add(jTextField3);
        jTextField3.setBounds(210, 80, 110, 20);

        jTextField4.setEditable(false);
        jTextField4.setText("jTextField4");
        getContentPane().add(jTextField4);
        jTextField4.setBounds(210, 140, 110, 20);

        jTextField5.setEditable(false);
        jTextField5.setText("jTextField5");
        getContentPane().add(jTextField5);
        jTextField5.setBounds(210, 170, 110, 20);

        jButton1.setText("Düzenle");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(80, 210, 90, 30);

        jButton2.setText("Sil");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(200, 210, 100, 30);

        jTextField6.setEditable(false);
        jTextField6.setText("jTextField6");
        getContentPane().add(jTextField6);
        jTextField6.setBounds(210, 110, 110, 20);

        jLabel7.setText("Personel No:");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(140, 110, 90, 14);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(edit==false){
            jTextField1.setEditable(true);
            jTextField2.setEditable(true);
            jTextField3.setEditable(true);
            jTextField4.setEditable(true);
            jTextField5.setEditable(true);
            jTextField6.setEditable(true);
            jButton1.setText("Kaydet");
            edit = true;
        }
        else{
            personel yeni = new personel();
            yeni.edit(this.isim,this.soyisim,this.ssn,this.personelno,this.maas,this.pozisyon,
                    jTextField1.getText(),jTextField2.getText(),Integer.valueOf(jTextField3.getText()),jTextField4.getText(),
                    Double.valueOf(jTextField5.getText()),Integer.valueOf(jTextField6.getText()));
            jTextField1.setEditable(false);
            jTextField2.setEditable(false);
            jTextField3.setEditable(false);
            jTextField4.setEditable(false);
            jTextField5.setEditable(false);
            jTextField6.setEditable(false);
            jButton1.setText("Düzenle");
            edit = false;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        personel yeni = new personel();
        int value = JOptionPane.showConfirmDialog(null, "Silmek istediğinize emin misiniz?");
        if(value==0){
            yeni.sil(jTextField1.getText(),jTextField2.getText(),Integer.valueOf(jTextField3.getText()),jTextField4.getText(),
                     Double.valueOf(jTextField5.getText()),Integer.valueOf(jTextField6.getText()));
            dispose();
        }
         
    }//GEN-LAST:event_jButton2ActionPerformed
    
    void infos(Integer personelno, Integer ssn, String isim, String soyisim, String pozisyon, Double maas) {
        this.isim = isim;
        this.soyisim = soyisim;
        this.pozisyon = pozisyon;
        this.ssn = ssn;
        this.personelno = personelno;
        this.maas = maas;
        jTextField1.setText(isim);
        jTextField2.setText(soyisim);
        jTextField3.setText(ssn.toString());
        jTextField4.setText(pozisyon);
        jTextField5.setText(maas.toString());
        jTextField6.setText(personelno.toString());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(personelDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(personelDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(personelDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(personelDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new personelDetails().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.jdatepicker.impl.DateComponentFormatter dateComponentFormatter1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables

}
