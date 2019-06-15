/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import com.sun.javafx.image.impl.IntArgb;
import com.sun.prism.PresentableState;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TreeMap;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ACER
 */
public class FrmJual extends javax.swing.JFrame {
private DefaultTableModel model;



    /**
     * Creates new form FrmJual
     */
    public FrmJual() {
        initComponents();
        combo_pelanggan();
        auto_key();
        txtNofa.disable();
        
        TxtIdPelanggan.hide();
        TxtStock.hide();
        TxtDateTime.hide();
        
        
        
        model =new DefaultTableModel();
        TblDetail.setModel(model);
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barnag");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Total");
        model.addColumn("Tanggal");
        
        TblDetail.getColumnModel().getColumn(5).setMinWidth(0);
        TblDetail.getColumnModel().getColumn(5).setMaxWidth(0);
        TblDetail.getColumnModel().getColumn(5).setWidth(0);
        
        TblDetail.getColumnModel().getColumn(0).setMinWidth(0);
        TblDetail.getColumnModel().getColumn(0).setMaxWidth(0);
        TblDetail.getColumnModel().getColumn(0).setWidth(0);
        
        loadData();
        Date date = new Date();
        JdateJual.setDate(date);
    }
    
    
    

   public void Batal(){
    int x, y, z;
    x = Integer.parseInt(TxtStock.getText());
    y = Integer.parseInt(TxtQty.getText());
    z = x+y;
    
    String kode_barang=this.TxtKode.getText();
     try{
       Connection c= config.configDB();  
       String sql ="UPDATE barang set jumlah_barang=? WHERE kode_barang=?";  
       PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
           p.setInt(1,z);
           p.setString(2,kode_barang);
           p.executeUpdate();  
           p.close();  
     }catch(SQLException e){  
       System.out.println("Terjadi Kesalahan Batal");  
     }finally{   
       //JOptionPane.showMessageDialog(this,"Stock barang telah di update Diubah");  
     }
     
     try {
        Connection c= config.configDB();
        String sql="DELETE From transaksi_detail WHERE kode_transaksi='"+this.txtNofa.getText()+"' AND  tanggal ='"+this.TxtDateTime.getText()+"'";
        PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);
        p.executeUpdate();
        p.close();
    }catch(SQLException e){
        System.out.println("Terjadi Kesalahan Hapus Data");
    }finally{
        loadData();
        JOptionPane.showMessageDialog(this,"Sukses Hapus Data...");
    }  
   }
   
    
   public void Cari_Kode(){
   int i=TblDetail.getSelectedRow();  
   if(i==-1)  
   { return; }  
   String ID=(String)model.getValueAt(i, 0); 
   TxtKode.setText(ID);
   }
    
    
   public void ShowData(){
   try {
        Connection c=config.configDB();
        String sql="Select * from transaksi_detail, barang WHERE transaksi_detail.kode_barang = barang.kode_barang AND transaksi_detail.kode_barang='"+this.TxtKode.getText()+"'"; 
        Statement st = config.configDB().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
        this.TxtQty.setText(rs.getString("total_barang"));
        this.TxtNama.setText(rs.getString("nama_barang"));
        this.TxtHJual.setText(rs.getString("harga"));
        this.TxtSubTotal.setText(rs.getString("total"));
        this.TxtDateTime.setText(rs.getString("tanggal"));
        }
        rs.close(); st.close();}
        catch (SQLException e) {
            System.out.println("Error show data");
        }
 }

   
   public void ShowSisa(){
   try {
        Connection c=config.configDB();
        String sql="Select * from barang WHERE kode_barang ='"+this.TxtKode.getText()+"'"; 
        Statement st = config.configDB().createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){
        this.TxtStock.setText(rs.getString("jumlah_barang"));      
        }
        rs.close(); st.close();}
        catch (SQLException e) {
            System.out.println("error sisa");
        }
 } 

  
    
public void UpdateStock(){
    int x, y, z;
    x = Integer.parseInt(TxtStock.getText());
    y = Integer.parseInt(TxtQty.getText());
    z = x-y;
    
    String Barang_ID=this.TxtKode.getText();
     try{
       Connection c= config.configDB();  
       String sql ="UPDATE barang set jumlah_barang=? WHERE kode_barang=?";  
       PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
           p.setInt(1,z);
           p.setString(2,Barang_ID);
           p.executeUpdate();  
           p.close();  
     }catch(SQLException e){  
       System.out.println("Terjadi Kesalahan Update");  
     }finally{   
       //JOptionPane.showMessageDialog(this,"Stock barang telah di update Diubah");  
     }
}
    
    
   public final void loadData(){
   model.getDataVector().removeAllElements();
   model.fireTableDataChanged();
   try{  
     Connection c= config.configDB();
       Statement s= c.createStatement();
     String sql="Select * from transaksi_detail, barang WHERE transaksi_detail.kode_barang = barang.kode_barang AND transaksi_detail.kode_transaksi='"+this.txtNofa.getText()+"'"; 
       ResultSet r=s.executeQuery(sql);
     while(r.next()){
       Object[]o=new Object[6];
       o[0]=r.getString("kode_barang");
       o[1]=r.getString("nama_barang");
       o[2]=r.getString("harga");
       o[3]=r.getString("total_barang");
       o[4]=r.getString("total");
       o[5]=r.getString("jumlah_barang");
       model.addRow(o);
     }  
     r.close();  
     s.close();   
   }catch(SQLException e){  
     System.out.println(e.getMessage()+"Error Load Data");  
   }
   int total = 0;
   for (int i =0; i< TblDetail.getRowCount(); i++){
       int amount = Integer.parseInt((String)TblDetail.getValueAt(i, 4));
       total += amount;
   }
   TxtTotal.setText(""+total);
 }  
    
    public void AutoSum() {     
        int a, b, c;
        a = Integer.parseInt(TxtHJual.getText());
        b = Integer.parseInt(TxtQty.getText());
        c = a*b;
        TxtSubTotal.setText(""+c);
    }
    
    
        public void HitungKembali() {     
        int d, e, f;
        d = Integer.parseInt(TxtTotal.getText());
        e = Integer.parseInt(TxtCash.getText());
        f = e-d;
        TxtKembali.setText(""+f);
    }
        
        
        
        
    public void auto_key(){  
   try {  
   java.util.Date tgl = new java.util.Date();  
   java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyMMdd");  
   java.text.SimpleDateFormat tanggal = new java.text.SimpleDateFormat("yyyyMMdd");  
     Connection c=config.configDB();  
     String sql = "select max(kode_transaksi) from transaksi WHERE tanggal ="+tanggal.format(tgl);   
     Statement st = config.configDB().createStatement();  
     ResultSet rs = st.executeQuery(sql);  
     while(rs.next()){  
     Long a =rs.getLong(1);
       if(a == 0){  
         this.txtNofa.setText(kal.format(tgl)+"000"+(a+1));  
       }else{  
         this.txtNofa.setText(""+(a+1));  
       }  
   }  
   rs.close(); st.close();}  
   catch (Exception e) {  
       JOptionPane.showMessageDialog(null, "Terjadi kesalaahan auto key");  
   }  
 }  
   
    


   
    public void Selesai(){   
   String kode_transaksi =this.txtNofa.getText();  
   String id=this.TxtIdPelanggan.getText();    
   String total_transaksi=this.TxtTotal.getText();
   String bayar =this.TxtCash.getText();
   String kembali =this.TxtKembali.getText();
   
   //Date date = new Date();
   //JdateJual.setDate(date);
        
   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
   Date tgl = new Date(); 
   tgl = JdateJual.getDate(); 
   String tanggal = dateFormat.format(tgl);

   try{  
     Connection c=config.configDB();  
     String sql="Insert into transaksi (kode_transaksi,tanggal,id,total_transaksi,bayar,kembali) values (?,?,?,?,?,?)";  
     PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
     p.setString(1,kode_transaksi);
     p.setString(2,tanggal);
     p.setString(3,id);
     p.setString(4,total_transaksi);
     p.setString(5,bayar);
     p.setString(6,kembali);
     p.executeUpdate();
     p.close();
   }catch(SQLException e){ 
   System.out.println(e.getMessage()+"Error Simpan Data");  
   }finally{
       JOptionPane.showMessageDialog(this,"Data Telah Tersimpan");  
  }
   
  auto_key();
  loadData();
 }  
    

   public void TambahDetail(){
   Date HariSekarang = new Date( );
   SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
      
   String kode_transaksi =this.txtNofa.getText();  
   String kode_barang =this.TxtKode.getText();  
   String harga=this.TxtHJual.getText();  
   String total_barang=this.TxtQty.getText();
   String total =this.TxtSubTotal.getText();
   String tanggal = ft.format(HariSekarang);
   
   
   try{  
     Connection c=config.configDB();  
     String sql="Insert into transaksi_detail (kode_transaksi,kode_barang,harga,total_barang,total,tanggal) values (?,?,?,?,?,?)";  
     PreparedStatement p=(PreparedStatement)c.prepareStatement(sql);  
     p.setString(1,kode_transaksi);
     p.setString(2,kode_barang);
     p.setString(3,harga);
     p.setString(4,total_barang);
     p.setString(5,total);
     p.setString(6,tanggal);
     p.executeUpdate();
     p.close();
   }catch(SQLException e){ 
   System.out.println(e.getMessage()+"Error Tambah");  
   }finally{  
   //loadData();
       //JOptionPane.showMessageDialog(this,"Data Telah Tersimpan");  
  }
 }
    
    


    public void cari_id(){
        try {
        Connection c=config.configDB();
        String sql = "select * from barang where kode_barang='"+this.TxtKode.getText()+"'"; 
        Statement st = config.configDB().createStatement();
        ResultSet rs = st.executeQuery(sql);
        
        while(rs.next()){
        this.TxtNama.setText(rs.getString("nama_barang"));
        this.TxtHJual.setText(rs.getString("harga_barang"));
        this.TxtStock.setText(rs.getString("jumlah_barang"));
        }
        rs.close(); st.close();}
        catch (SQLException e) {
            System.out.println("Error Cari");
        }
}
  
    public void cari_nama()
    {
        try {
        Connection c=config.configDB();
        String sql_t = "select id from pegawai where nama_lengkap='"+cmbPelanggan.getSelectedItem()+"'"; 
        Statement st = config.configDB().createStatement();
        ResultSet rs = st.executeQuery(sql_t);          
        
        while(rs.next()){
        this.TxtIdPelanggan.setText(rs.getString("id")); 
        }
        rs.close(); st.close();
         
        } catch (SQLException e) {
            System.out.println("Error cari nama");
        }     
    }
    
    
    public  void bersihkan(){
        TxtKode.setText("");
        TxtNama.setText("");
        TxtHJual.setText("");
        TxtQty.setText("");
        TxtCash.setText("");
        TxtSubTotal.setText("");
        TxtKembali.setText("");
       
    }
    
    
    
    
    
    public void combo_pelanggan()
    {
        try {
        Connection c=config.configDB();
        Statement st = c.createStatement();
        String sql_tc = "select id, nama_lengkap from pegawai order by id asc";
        ResultSet rs = st.executeQuery(sql_tc);

        while(rs.next()){
            String nama = rs.getString("nama_lengkap");
            cmbPelanggan.addItem(nama);
        }
        rs.close(); st.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        daftar_barang = new javax.swing.JButton();
        kasir = new javax.swing.JButton();
        faktur = new javax.swing.JButton();
        Transaksi = new javax.swing.JButton();
        pemasok = new javax.swing.JButton();
        exp = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        JdateJual = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        txtNofa = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        BtnBatal = new javax.swing.JButton();
        cmbPelanggan = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TblDetail = new javax.swing.JTable();
        TxtTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        TxtKode = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        TxtNama = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        TxtHJual = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        TxtQty = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        TxtSubTotal = new javax.swing.JTextField();
        BtnAdd = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        TxtCash = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        TxtKembali = new javax.swing.JTextField();
        BtnSimpan = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        TxtStock = new javax.swing.JTextField();
        TxtIdPelanggan = new javax.swing.JTextField();
        TxtDateTime = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(0, 153, 153));

        jLabel20.setIcon(new javax.swing.ImageIcon("C:\\Users\\acer\\Downloads\\cashier2_117800 (1).png")); // NOI18N

        jLabel21.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Sistem Kasir Java NetBeans");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel21)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addGap(13, 13, 13)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        daftar_barang.setBackground(new java.awt.Color(255, 255, 255));
        daftar_barang.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        daftar_barang.setText("Daftar Barang");
        daftar_barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daftar_barangActionPerformed(evt);
            }
        });

        kasir.setBackground(new java.awt.Color(153, 255, 204));
        kasir.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        kasir.setText("Kasir");
        kasir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kasirActionPerformed(evt);
            }
        });

        faktur.setBackground(new java.awt.Color(255, 255, 255));
        faktur.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        faktur.setText("Faktur");

        Transaksi.setBackground(new java.awt.Color(255, 255, 255));
        Transaksi.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        Transaksi.setText("Transaksi");
        Transaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TransaksiActionPerformed(evt);
            }
        });

        pemasok.setBackground(new java.awt.Color(255, 255, 255));
        pemasok.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        pemasok.setText("Pemasok");
        pemasok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pemasokActionPerformed(evt);
            }
        });

        exp.setBackground(new java.awt.Color(255, 255, 255));
        exp.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        exp.setText("Expired");
        exp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jButton5.setText("Daftar Kategori");
        jButton5.setPreferredSize(new java.awt.Dimension(85, 25));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton4.setBackground(new java.awt.Color(255, 255, 255));
        jButton4.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        jButton4.setText("Home");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(daftar_barang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(kasir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(faktur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(Transaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pemasok, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 8, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(daftar_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(kasir, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(faktur, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(pemasok, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exp, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JdateJual.setDateFormatString("yyyy-MM-dd");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel2.setText("Nomor Transaksi");

        txtNofa.setName(""); // NOI18N
        txtNofa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNofaActionPerformed(evt);
            }
        });
        txtNofa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNofaKeyPressed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel3.setText("Tanggal");

        BtnBatal.setText("Batal");
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });

        cmbPelanggan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pilih" }));
        cmbPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cmbPelangganMouseClicked(evt);
            }
        });
        cmbPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPelangganActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel4.setText("Pegawai");

        TblDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Nama  Barang", "Harga", "Qty", "Sub Total", "jual_time"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TblDetail.getTableHeader().setReorderingAllowed(false);
        TblDetail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TblDetailMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TblDetail);
        if (TblDetail.getColumnModel().getColumnCount() > 0) {
            TblDetail.getColumnModel().getColumn(0).setMinWidth(0);
            TblDetail.getColumnModel().getColumn(0).setPreferredWidth(0);
            TblDetail.getColumnModel().getColumn(0).setMaxWidth(0);
            TblDetail.getColumnModel().getColumn(1).setResizable(false);
            TblDetail.getColumnModel().getColumn(2).setResizable(false);
            TblDetail.getColumnModel().getColumn(3).setResizable(false);
            TblDetail.getColumnModel().getColumn(4).setResizable(false);
            TblDetail.getColumnModel().getColumn(5).setMinWidth(0);
            TblDetail.getColumnModel().getColumn(5).setMaxWidth(0);
        }

        TxtTotal.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        TxtTotal.setName(""); // NOI18N
        TxtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtTotalActionPerformed(evt);
            }
        });
        TxtTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtTotalKeyPressed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel6.setText("Total Pembelian (Rp)");

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel5.setText("Kode Barang");

        TxtKode.setName(""); // NOI18N
        TxtKode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtKodeActionPerformed(evt);
            }
        });
        TxtKode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtKodeKeyPressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel7.setText("Nama");

        TxtNama.setName(""); // NOI18N
        TxtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNamaActionPerformed(evt);
            }
        });
        TxtNama.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtNamaKeyPressed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel8.setText("Harga Jual");

        TxtHJual.setName(""); // NOI18N
        TxtHJual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtHJualActionPerformed(evt);
            }
        });
        TxtHJual.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtHJualKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel9.setText("Qty");

        TxtQty.setName(""); // NOI18N
        TxtQty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TxtQtyMouseClicked(evt);
            }
        });
        TxtQty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtQtyActionPerformed(evt);
            }
        });
        TxtQty.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                TxtQtyPropertyChange(evt);
            }
        });
        TxtQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtQtyKeyPressed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel10.setText("Sub Total");

        TxtSubTotal.setName(""); // NOI18N
        TxtSubTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtSubTotalActionPerformed(evt);
            }
        });
        TxtSubTotal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtSubTotalKeyPressed(evt);
            }
        });

        BtnAdd.setText("Tambahkan");
        BtnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAddActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel11.setText("Cash");

        TxtCash.setName(""); // NOI18N
        TxtCash.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtCashKeyPressed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel13.setText("Kembali");

        TxtKembali.setName(""); // NOI18N

        BtnSimpan.setText("Simpan");
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 0, 14)); // NOI18N
        jLabel12.setText("........................");

        TxtIdPelanggan.setName(""); // NOI18N
        TxtIdPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtIdPelangganActionPerformed(evt);
            }
        });
        TxtIdPelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TxtIdPelangganKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(TxtKode, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addComponent(TxtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(TxtHJual, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(10, 10, 10)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel9)
                                            .addComponent(TxtQty, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(jLabel10))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(TxtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(BtnBatal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(BtnAdd)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(TxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 630, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JdateJual, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtNofa, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(69, 69, 69)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(TxtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TxtCash, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(30, 30, 30)
                                        .addComponent(BtnSimpan))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel13)
                                        .addComponent(jLabel11)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TxtDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(TxtStock, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(TxtIdPelanggan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(74, 74, 74)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(71, 71, 71)
                                        .addComponent(JdateJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(36, 36, 36)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(txtNofa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel3)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabel4))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(cmbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(0, 0, 0)
                                        .addComponent(TxtKode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(0, 0, 0)
                                        .addComponent(TxtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel8)
                                        .addGap(0, 0, 0)
                                        .addComponent(TxtHJual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addGap(3, 3, 3)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(TxtQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(TxtSubTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(BtnBatal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnAdd)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addComponent(jLabel11)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(TxtCash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(TxtKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(BtnSimpan))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(TxtStock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtIdPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TxtDateTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNofaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNofaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNofaActionPerformed

    private void txtNofaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNofaKeyPressed

    }//GEN-LAST:event_txtNofaKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        // TODO add your handling code here:
        Selesai();
        bersihkan();
        
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        Batal();
        bersihkan();
    }//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAddActionPerformed
       TambahDetail();
       UpdateStock();
       loadData();
       bersihkan();
       
    }//GEN-LAST:event_BtnAddActionPerformed

    private void cmbPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPelangganActionPerformed
        // TODO add your handling code here:
        cari_nama();
    }//GEN-LAST:event_cmbPelangganActionPerformed

    private void TxtKodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtKodeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtKodeActionPerformed

    private void TxtKodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtKodeKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {     
            cari_id();
        }
    }//GEN-LAST:event_TxtKodeKeyPressed

    private void TxtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNamaActionPerformed

    private void TxtNamaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNamaKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNamaKeyPressed

    private void TxtHJualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtHJualActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtHJualActionPerformed

    private void TxtHJualKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtHJualKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtHJualKeyPressed

    private void TxtQtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtQtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtQtyActionPerformed

    private void TxtQtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtQtyKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  
        AutoSum();
        }
    }//GEN-LAST:event_TxtQtyKeyPressed

    private void TxtSubTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtSubTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSubTotalActionPerformed

    private void TxtSubTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtSubTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtSubTotalKeyPressed

    private void TblDetailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TblDetailMouseClicked
        this.Cari_Kode();
        this.ShowData();
        this.ShowSisa();
    }//GEN-LAST:event_TblDetailMouseClicked

    private void TxtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTotalActionPerformed

    private void TxtTotalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtTotalKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtTotalKeyPressed

    private void TxtQtyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TxtQtyMouseClicked
        // TODO add your handling code here:
       
    }//GEN-LAST:event_TxtQtyMouseClicked

    private void TxtQtyPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_TxtQtyPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtQtyPropertyChange

    private void TxtCashKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCashKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {  
        HitungKembali();
        }
    }//GEN-LAST:event_TxtCashKeyPressed

    private void TxtIdPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtIdPelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtIdPelangganActionPerformed

    private void TxtIdPelangganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtIdPelangganKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtIdPelangganKeyPressed

    private void cmbPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cmbPelangganMouseClicked
        // TODO add your handling code here:
        
        cari_nama();
        
    }//GEN-LAST:event_cmbPelangganMouseClicked

    private void daftar_barangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daftar_barangActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new daftar_barang().setVisible(true);
    }//GEN-LAST:event_daftar_barangActionPerformed

    private void kasirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kasirActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new FrmJual().setVisible(true);
    }//GEN-LAST:event_kasirActionPerformed

    private void TransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TransaksiActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new daftar_transaksi().setVisible(true);
    }//GEN-LAST:event_TransaksiActionPerformed

    private void pemasokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pemasokActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new pemasok().setVisible(true);
    }//GEN-LAST:event_pemasokActionPerformed

    private void expActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expActionPerformed
        this.dispose();
        new expired().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_expActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.dispose();
        new kategori().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        this.dispose();
        new main().setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(FrmJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmJual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmJual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAdd;
    private javax.swing.JButton BtnBatal;
    private javax.swing.JButton BtnSimpan;
    private com.toedter.calendar.JDateChooser JdateJual;
    private javax.swing.JTable TblDetail;
    private javax.swing.JButton Transaksi;
    private javax.swing.JTextField TxtCash;
    private javax.swing.JTextField TxtDateTime;
    private javax.swing.JTextField TxtHJual;
    private javax.swing.JTextField TxtIdPelanggan;
    private javax.swing.JTextField TxtKembali;
    private javax.swing.JTextField TxtKode;
    private javax.swing.JTextField TxtNama;
    private javax.swing.JTextField TxtQty;
    private javax.swing.JTextField TxtStock;
    private javax.swing.JTextField TxtSubTotal;
    private javax.swing.JTextField TxtTotal;
    private javax.swing.JComboBox<String> cmbPelanggan;
    private javax.swing.JButton daftar_barang;
    private javax.swing.JButton exp;
    private javax.swing.JButton faktur;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton kasir;
    private javax.swing.JButton pemasok;
    private javax.swing.JTextField txtNofa;
    // End of variables declaration//GEN-END:variables
}
