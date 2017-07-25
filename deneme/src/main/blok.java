/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class blok {  

    void blok_ekle(JComboBox<String> jComboBox6, JComboBox<String> jComboBox7, JComboBox<String> jComboBox8, JComboBox<String> jComboBox9, JComboBox<String> jComboBox10, JComboBox<String> jComboBox11, JTextField jTextField5) {
        // TODO add your handling code here:
               String blokisim = "";
        try {
            adminPage yeni = new adminPage();
                Connection conn = null;   
                jdbc sub = new jdbc();
                conn = sub.connectToDatabaseOrDie();
                blokisim = jTextField5.getText();
                PreparedStatement ps = null;
                ResultSet rs,rs1 = null;
               
                int count = 0;
                 ps = conn.prepareStatement("select count(*) from blok where blok_ismi=?;");
                 ps.setString(1, blokisim);
                 rs = ps.executeQuery();
                 rs.next();
                 if(rs.getInt("count")!=0){
                  JOptionPane.showMessageDialog(null, "Blok ismi veritabanında mevcut. Lütfen düzelterek tekrara giriş yapın");//alert  
                 }
                 else{
                    jComboBox6.addItem(blokisim);
                    jComboBox7.addItem(blokisim);
                    jComboBox8.addItem(blokisim);
                    jComboBox9.addItem(blokisim);
                    jComboBox10.addItem(blokisim);
                    jComboBox11.addItem(blokisim);
                     ps = conn.prepareStatement("INSERT INTO blok(blok_ismi) values(?);");
                     ps.setString(1, blokisim);
                     ps.execute();
                 }
                 ps.close();
                 conn.close();
                 rs.close();
        }catch (SQLException se) {
            System.err.println(se.getMessage());
        }
    }

    void revir_ekle(JTextField jTextField13, JComboBox<String> jComboBox6) {
        int sayi  = Integer.parseInt(jTextField13.getText()); 
        try{      
            Connection conn = null; 
            jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String blok = jComboBox6.getSelectedItem().toString();
            ps = conn.prepareStatement("insert into revir values(?,?);");    
            ps.setInt(1, sayi);
            ps.setString(2, blok);
            ps.execute();
        }catch (SQLException se) {
            System.out.println(se);
        }
    }

    void camasirhane_ekle(JComboBox<String> jComboBox7) {
         try{      
            Connection conn = null; 
            jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            PreparedStatement ps = null;
            ResultSet rs = null;            
            String blok = jComboBox7.getSelectedItem().toString();
            ps = conn.prepareStatement("insert into camasirhane values(?)");    
            ps.setString(1, blok);
            ps.execute();
        }catch (SQLException se) {
        }
    }

    void yemekhane_ekle(JComboBox<String> jComboBox8) {
   try{      
        Connection conn = null; 
        jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            PreparedStatement ps = null;
            ResultSet rs = null;
            String blok = jComboBox8.getSelectedItem().toString();
            ps = conn.prepareStatement("insert into yemekhane values(?)");
            ps.setString(1, blok);
            ps.execute();
        }catch (SQLException se) {
            
        }
    }

    void tkhucre_ekle(JComboBox<String> jComboBox10, JTextField jTextField9) {
        Connection conn = null; 
        Connection conn2 = null; 
 //for statement sadece query execution kapsayacak şekilde yazıldığı takdirde sadece 1 bağlantı oluyor.
       //Her loop için bağlantı tekrar kurulması gerekiyor.
        int hucresayisi = Integer.parseInt(jTextField9.getText());
        for(int i=0;i<hucresayisi;i++){
            try{      
                jdbc sub = new jdbc();
                conn = sub.connectToDatabaseOrDie();
                PreparedStatement ps=null;
                ResultSet rs= null;
                 String blok = jComboBox10.getSelectedItem().toString();
                    ps = conn.prepareStatement("insert into hucre (blok_ismi) values (?) returning hucre_no");
                    ps.setString(1, blok);
                    rs = ps.executeQuery();
                    //rs = st.getGeneratedKeys();
                    while(rs.next()){
                        jdbc sub2 = new jdbc();
                        conn2 = sub2.connectToDatabaseOrDie();
                        PreparedStatement ps2=null;
                        ps2 = conn.prepareStatement("insert into thucre values(?,?);");
                        System.out.println(rs.getInt(1));
                        ps2.setInt(1, rs.getInt(1));
                        ps2.setString(2, blok);
                        ps2.execute();
                        conn2.close();
                    }
                    ps.close();
                    rs.close();
             }catch (SQLException se) {
                System.out.println(se);
             } 
        }    
    }

    void ckhucre_ekle(JComboBox<String> jComboBox11, JTextField jTextField15) {
          Connection conn = null; 
          Connection conn2 = null; 
        //for statement sadece query execution kapsayacak şekilde yazıldığı takdirde sadece 1 bağlantı oluyor.
       //Her loop için bağlantı tekrar kurulması gerekiyor.
        int hucresayisi = Integer.parseInt(jTextField15.getText());
        for(int i=0;i<=hucresayisi;i++){
            try{      
                jdbc sub = new jdbc();
                conn = sub.connectToDatabaseOrDie();
                PreparedStatement ps=null;
                ResultSet rs= null;
                String blok = jComboBox11.getSelectedItem().toString();
                    //st.execute("insert into thucre (blok_ismi) values ('"+blok+"');",Statement.RETURN_GENERATED_KEYS);
                    ps = conn.prepareStatement("insert into hucre (blok_ismi) values (?) returning hucre_no");
                    ps.setString(1, blok);
                    rs = ps.executeQuery();
                    //rs = st.getGeneratedKeys();
                    while(rs.next()){
                        jdbc sub2 = new jdbc();
                        conn2 = sub2.connectToDatabaseOrDie();
                        PreparedStatement ps2=null;
                     
                        ps =conn.prepareStatement("insert into chucre values(?,?);");
                        ps.setInt(1, rs.getInt(1));
                        ps.setString(2, blok);
                        ps.execute();
                        conn2.close();
                    }
                   ps.close();
                   rs.close();
             }catch (SQLException se) {
                System.out.println(se);
             } 
        }
    }
}
