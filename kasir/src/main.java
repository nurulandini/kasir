/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class main extends javax.swing.JFrame {

    /**
     * Creates new form main
     */
    public main() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        keluar = new javax.swing.JButton();
        daftar_barang = new javax.swing.JButton();
        kasir = new javax.swing.JButton();
        beli = new javax.swing.JButton();
        pemasok = new javax.swing.JButton();
        faktur = new javax.swing.JButton();
        Transaksi = new javax.swing.JButton();
        nama = new javax.swing.JLabel();
        username = new javax.swing.JLabel();
        tanggal = new javax.swing.JLabel();
        date = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText(" FANUTRI SWALAYAN");

        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });

        daftar_barang.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        daftar_barang.setText("Daftar Barang");
        daftar_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftar_barangActionPerformed(evt);
            }
        });

        kasir.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        kasir.setText("Kasir");
        kasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kasirActionPerformed(evt);
            }
        });

        beli.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        beli.setText("Pembelian");
        beli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beliActionPerformed(evt);
            }
        });

        pemasok.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        pemasok.setText("Pemasok");

        faktur.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        faktur.setText("Faktur");

        Transaksi.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        Transaksi.setText("Transaksi");
        Transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransaksiActionPerformed(evt);
            }
        });

        nama.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        nama.setText("Nama     : ");

        username.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        username.setText("<nama_user>");

        tanggal.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        tanggal.setText("Tanggal :");

        date.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        date.setText("<tanggal>");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(115, 115, 115)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(beli, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(daftar_barang))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(kasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(pemasok, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(faktur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(Transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nama)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(tanggal)
                                        .addGap(22, 22, 22)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(date)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(username)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(keluar)))))))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(keluar)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nama)
                        .addComponent(username)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tanggal)
                    .addComponent(date))
                .addGap(72, 72, 72)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(faktur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(kasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(daftar_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pemasok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(beli, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(75, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void daftar_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftar_barangActionPerformed
        // TODO add your handling code here:
    new daftar_barang().setVisible(true);
    }//GEN-LAST:event_daftar_barangActionPerformed

    private void kasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kasirActionPerformed
        // TODO add your handling code here:
        new kasir_view().setVisible(true);
    }//GEN-LAST:event_kasirActionPerformed

    private void beliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beliActionPerformed
        new tambah_barang().setVisible(true);   
// TODO add your handling code here:
    }//GEN-LAST:event_beliActionPerformed

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new login().setVisible(true);
    }//GEN-LAST:event_keluarActionPerformed

    private void TransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransaksiActionPerformed
        // TODO add your handling code here:
        new daftar_transaksi().setVisible(true);
    }//GEN-LAST:event_TransaksiActionPerformed

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
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Transaksi;
    private javax.swing.JButton beli;
    private javax.swing.JButton daftar_barang;
    private javax.swing.JLabel date;
    private javax.swing.JButton faktur;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton kasir;
    private javax.swing.JButton keluar;
    private javax.swing.JLabel nama;
    private javax.swing.JButton pemasok;
    private javax.swing.JLabel tanggal;
    private javax.swing.JLabel username;
    // End of variables declaration//GEN-END:variables
}
