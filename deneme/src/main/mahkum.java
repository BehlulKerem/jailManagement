/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MesutKutlu
 */
public class mahkum {
    public Connection conn = null;
    
    void kaydet(int mahkum_ssn, String mahkum_isim, String mahkum_soyisim, String giris_tarihi, String cikis_tarihi, int hucre_no, String hucre_tipi, String blok) {
       
         try{  
            jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
             PreparedStatement ps,ps2 = null;
            ResultSet rs,rs1 = null;
          
            if(hucre_tipi.equals("Tek kişilik hücre")){
                 ps = conn.prepareStatement("select count(*) from thucre where blok_ismi=? and hucre_no=?;");
                 ps.setString(1,blok);
                 ps.setInt(2, hucre_no);
                 int count = 0;
                 rs = ps.executeQuery();
                 
                if(rs.getInt("count")!=0){
                      ps2 = conn.prepareStatement("insert into mahkum values(?,?,?,?,?,?,?,?)");
                      ps2.setInt(1,mahkum_ssn);
                      ps2.setString(2,mahkum_isim);
                      ps2.setString(3,mahkum_soyisim);
                      ps2.setString(4,giris_tarihi);
                      ps2.setString(5,cikis_tarihi);
                      ps2.setString(6, hucre_tipi);
                      ps2.setInt(7,hucre_no);
                      ps2.setString(8, blok);
                      
                }
                else{
                     JOptionPane.showMessageDialog(null, "Tek kisilik hucrelerde boyle bir hucre no bulunmamaktadir");//alert
                }
            }
            else{
                  ps = conn.prepareStatement("select count(*) from chucre where blok_ismi=? and hucre_no=?;");
                  ps.setString(1, blok);
                  ps.setInt(2, hucre_no);
                  rs = ps.executeQuery();
                if(rs.getInt("count")!=0){
                    ps2 = conn.prepareStatement("insert into mahkum values(?,?,?,?,?,?,?,?)");
                      ps2.setInt(1,mahkum_ssn);
                      ps2.setString(2,mahkum_isim);
                      ps2.setString(3,mahkum_soyisim);
                      ps2.setString(4,giris_tarihi);
                      ps2.setString(5,cikis_tarihi);
                      ps2.setString(6, hucre_tipi);
                      ps2.setInt(7,hucre_no);
                      ps2.setString(8, blok);
                }
                else{
                     JOptionPane.showMessageDialog(null, "Cok kisilik hucrelerde boyle bir hucre no bulunmamaktadir");//alert
                }
            }         
          
 
         }catch (SQLException se) {}
    }
    
     public void hepsi(DefaultTableModel model,int rowCount){
       
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
       
         try{      
        jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            Statement st = null;
            ResultSet rs = null;
            st = conn.createStatement();
            
            rs = st.executeQuery("select * from mahkum");  
            while (rs.next()){
                int ssn = rs.getInt("ssn");
                String isim =rs.getString("isim") ;
                String soyisim = rs.getString("soyisim");
                String blok = rs.getString("blok");
                String hucre_no = rs.getString("hucre_no");
                String hucre_tipi = rs.getString("hucre_tipi");
                String giris_tarihi = rs.getString("giris_tarihi");
                String cikis_tarihi = rs.getString("cikis_tarihi");
                model.addRow(new Object[]{ssn,isim,soyisim,blok,hucre_no,hucre_tipi,giris_tarihi,cikis_tarihi});
            }
            conn.close();
            st.close();
            rs.close();
        }catch (SQLException se) {
        } 
    }
}
