/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.HeadlessException;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class daftar_barang extends javax.swing.JFrame {
    public String tanggal;
    public String tanggal2;
    public static int statusSearching = 0;
     /**
     * 
     * Creates new form daftar_barang
     */
    public daftar_barang() {
        initComponents();
        load_table();
        kosong();
        AutoNomor();
    }
    public void AutoNomor() {
        try {
            String sql = "select max(kode_barang) from barang";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while (res.next()){
                int a = res.getInt(1);
                id_barang.setText(""+ Integer.toString(a+1));
            }
        } catch (Exception e) {
            System.out.println(""+ e.getMessage());
        }
    }

    
    private void load_table(){
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID BARANG");
        model.addColumn("NAMA BARANG");
        model.addColumn("KATEGORI BARANG");
        model.addColumn("HARGA BARANG");
        model.addColumn("JUMLAH BARANG");
        model.addColumn("TANGGAL MASUK");
        model.addColumn("DISTRIBUTOR");
        model.addColumn("TANGGAL EXPIRED");
        
        //menampilkan data database kedalam tabel
        try {
            String sql = "select * from barang";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(8),
                    res.getString(7)
                });
            }
            tabel_barang.setModel(model);
        } catch (SQLException e) {
        }
    }
    private void kosong(){
        id_barang.setText(null);
        nama_barang.setText(null);
        combokategori.setSelectedItem(null);
        harga_barang.setText(null);  
        jumlah_barang.setText(null);
        combodistributor.setSelectedItem(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        dkms = new javax.swing.JLabel();
        id_barang = new javax.swing.JTextField();
        nama_barang = new javax.swing.JTextField();
        harga_barang = new javax.swing.JTextField();
        jumlah_barang = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_barang = new javax.swing.JTable();
        tambah = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        cari = new javax.swing.JTextField();
        caributton = new javax.swing.JButton();
        combokategori = new javax.swing.JComboBox<>();
        combodistributor = new javax.swing.JComboBox<>();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("DAFTAR BARANG");

        dkms.setText("ID BARANG");

        jumlah_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jumlah_barangActionPerformed(evt);
            }
        });

        jLabel2.setText("NAMA BARANG");

        jLabel3.setText("KATEGORI BARANG");

        jLabel4.setText("HARGA BARANG");

        jLabel5.setText("JUMLAH BARANG");

        jLabel6.setText("TANGGAL MASUK");

        jLabel7.setText("DISTRIBUTOR");

        tabel_barang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID BARANG", "NAMA BARANG", "KATEGORI BARANG", "HARGA BARANG", "JUMLAH BARANG", "TANGGAL MASUK", "DISTRIBUTOR", "TANGGAL EXPIRED"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabel_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_barangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_barang);
        if (tabel_barang.getColumnModel().getColumnCount() > 0) {
            tabel_barang.getColumnModel().getColumn(0).setResizable(false);
            tabel_barang.getColumnModel().getColumn(1).setResizable(false);
            tabel_barang.getColumnModel().getColumn(2).setResizable(false);
            tabel_barang.getColumnModel().getColumn(3).setResizable(false);
            tabel_barang.getColumnModel().getColumn(4).setResizable(false);
            tabel_barang.getColumnModel().getColumn(5).setResizable(false);
            tabel_barang.getColumnModel().getColumn(6).setResizable(false);
            tabel_barang.getColumnModel().getColumn(7).setResizable(false);
        }

        tambah.setText("TAMBAH");
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });

        jButton1.setText("UBAH");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("HAPUS");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("KEMBALI");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel8.setText("TANGGAL EXPIRED");

        caributton.setText("CARI");
        caributton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caributtonActionPerformed(evt);
            }
        });

        combokategori.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        combokategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combokategoriActionPerformed(evt);
            }
        });

        combodistributor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih Distributor" }));
        combodistributor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combodistributorActionPerformed(evt);
            }
        });

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });

        jDateChooser2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser2PropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(344, 344, 344))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(caributton))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 826, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dkms)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(140, 140, 140)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nama_barang)
                            .addComponent(harga_barang)
                            .addComponent(jumlah_barang)
                            .addComponent(combokategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(combodistributor, 0, 137, Short.MAX_VALUE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(id_barang)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(tambah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jButton3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(62, 62, 62)
                                .addComponent(jLabel9)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caributton))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(dkms)
                                            .addComponent(id_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tambah))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(nama_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel2)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(19, 19, 19)
                                                .addComponent(jButton1)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(combokategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(harga_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(jLabel4))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jumlah_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(jButton2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton3))))
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(combodistributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8))
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jumlah_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jumlah_barangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jumlah_barangActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
        try {
            String sql = "INSERT INTO barang VALUES ('"+id_barang.getText()+"','"+nama_barang.getText()+"','"+combokategori.getSelectedItem()+"','"+harga_barang.getText()+"','"+jumlah_barang.getText()+"','"+tanggal+"','"+tanggal2+"','"+combodistributor.getSelectedItem()+"')";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        new daftar_barang().setVisible(true);
        this.dispose();
        kosong();
        load_table();
        AutoNomor();
        // TODO add your handling code here:
    }//GEN-LAST:event_tambahActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        new main().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void tabel_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_barangMouseClicked
        int baris = tabel_barang.rowAtPoint(evt.getPoint());
        String kode_barang = tabel_barang.getValueAt (baris, 0).toString();
        id_barang.setText(kode_barang);
        String nama = tabel_barang.getValueAt(baris, 1).toString();
        nama_barang.setText(nama);
        String jenis = tabel_barang.getValueAt(baris, 2).toString();
        combokategori.setSelectedItem(jenis);
        String harga=tabel_barang.getValueAt(baris, 3).toString();
        harga_barang.setText(harga);
        String jumlah = tabel_barang.getValueAt(baris, 4).toString();
        jumlah_barang.setText(jumlah);
        String s=(String)tabel_barang.getValueAt(baris, 5).toString();
        try{
            SimpleDateFormat format=new SimpleDateFormat("yyyy-M-dd"); 
            java.util.Date d=format.parse(s);
            jDateChooser1.setDate(d);
            }catch(Exception ex){
            ex.printStackTrace();
        }  
        String dist = tabel_barang.getValueAt(baris, 6).toString();
        combodistributor.setSelectedItem(dist);
        String exp = (String)tabel_barang.getValueAt(baris, 7).toString();
        try{
            SimpleDateFormat f=new SimpleDateFormat("yyyy-M-dd"); 
            java.util.Date k=f.parse(exp);
            jDateChooser2.setDate(k);
            }catch(Exception ex){
        }  
 // TODO add your handling code here:
    }//GEN-LAST:event_tabel_barangMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            String sql ="UPDATE barang SET kode_barang = '"+id_barang.getText()+"', nama_barang = '"+nama_barang.getText()+"', nama_kategori = '"+combokategori.getSelectedItem()+"',harga_barang= '"+harga_barang.getText()+"',jumlah_barang= '"+jumlah_barang.getText()+"',tanggal_masuk='"+tanggal+"',nama_dist='"+combodistributor.getSelectedItem()+"',tanggal_exp='"+tanggal2+"' WHERE kode_barang = '"+id_barang.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di edit");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal"+e.getMessage());
        }
        new daftar_barang().setVisible(true);
        this.dispose();
        load_table();
        kosong();
        AutoNomor();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    try {
            String sql ="delete from barang where kode_barang ='"+id_barang.getText()+"'";
            java.sql.Connection conn=(Connection)config.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "berhasil di hapus");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        new daftar_barang().setVisible(true);
        this.dispose();
        load_table();
        kosong();
        AutoNomor();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void caributtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caributtonActionPerformed

   statusSearching=1;
   if(cari.getText().isEmpty())
     { statusSearching = 0;}
   else if(statusSearching==1){
     DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID BARANG");
        model.addColumn("NAMA BARANG");
        model.addColumn("JENIS BARANG");
        model.addColumn("HARGA BARANG");
        model.addColumn("JUMLAH BARANG");
        model.addColumn("TANGGAL MASUK");
        model.addColumn("NAMA DISTRIBUTOR");
        model.addColumn("TANGGAL EXPIRED");
     String find = cari.getText();
     try{
          String sql = "Select * From barang Where kode_barang LIKE '%"+find+"%'"
                    + "OR nama_barang LIKE '%"+find+"%'"+ "OR nama_kategori LIKE '%"+find+"%'";
          java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while(res.next()){
                model.addRow(new Object[]{
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                    res.getString(5),
                    res.getString(6),
                    res.getString(8),
                    res.getString(7)
                });
                //nomortabel++;
          }
            tabel_barang.setModel(model);
          }catch(SQLException ex){
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

          }
    }  // TODO add your handling code here:
    }//GEN-LAST:event_caributtonActionPerformed

    private void combokategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combokategoriActionPerformed
       try {
        String sql = "select nama_kategori from kategori"; 
        java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);         
        
        while(res.next()){               
                combokategori.addItem(res.getString("nama_kategori"));
        }
        res.close(); stm.close();
         
        } catch (SQLException e) {
            System.out.println("Error cari nama");
        } 
        // TODO add your handling code here:
    }//GEN-LAST:event_combokategoriActionPerformed

    private void combodistributorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combodistributorActionPerformed
        try {
        String sql = "select nama_distributor from distributor"; 
        java.sql.Connection conn=(Connection)config.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);         
        
        while(res.next()){               
                combodistributor.addItem(res.getString("nama_distributor"));
        }
        res.close(); stm.close();
         
        } catch (SQLException e) {
            System.out.println("Error cari nama");
        } 
        // TODO add your handling code here:
    }//GEN-LAST:event_combodistributorActionPerformed

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tanggal=String.valueOf(format.format(jDateChooser1.getDate()));
      }catch(Exception e){
          
      }
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jDateChooser2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser2PropertyChange
         try{
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            tanggal2=String.valueOf(format.format(jDateChooser2.getDate()));
      }catch(Exception e){
          
      }
        // TODO add your handling code here:
    }//GEN-LAST:event_jDateChooser2PropertyChange

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
            java.util.logging.Logger.getLogger(daftar_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(daftar_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(daftar_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(daftar_barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new daftar_barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cari;
    private javax.swing.JButton caributton;
    private javax.swing.JComboBox<String> combodistributor;
    private javax.swing.JComboBox<String> combokategori;
    private javax.swing.JLabel dkms;
    private javax.swing.JTextField harga_barang;
    private javax.swing.JTextField id_barang;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jumlah_barang;
    private javax.swing.JTextField nama_barang;
    private javax.swing.JTable tabel_barang;
    private javax.swing.JButton tambah;
    // End of variables declaration//GEN-END:variables
}
