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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                 rs = ps.executeQuery();
                 rs.next();
                if(rs.getInt("count")!=0){
                  
                      ps2 = conn.prepareStatement("insert into mahkum values(?,?,?,?,?,?,?,?)");
                      ps2.setInt(1,mahkum_ssn);
                      ps2.setString(2,mahkum_isim);
                      ps2.setString(3,mahkum_soyisim);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date parsed = format.parse(giris_tarihi);
                        java.sql.Date sql = new java.sql.Date(parsed.getTime());
                        Date parsed2 = format.parse(giris_tarihi);
                        java.sql.Date sql2 = new java.sql.Date(parsed2.getTime());
                      ps2.setDate(4,sql);
                      ps2.setDate(5,sql2);
                      ps2.setString(6, hucre_tipi);
                      ps2.setInt(7,hucre_no);
                      ps2.setString(8, blok);
                      ps2.execute();
                }
                else{
                     JOptionPane.showMessageDialog(null, "Tek kisilik hucrelerde boyle bir hucre no bulunmamaktadir");//alert
                }
            } //çok kisilik hucre imp.
            else{
                  ps = conn.prepareStatement("select count(*) from chucre where blok_ismi=? and hucre_no=?;");
                  ps.setString(1, blok);
                  ps.setInt(2, hucre_no);
                  rs = ps.executeQuery();
                  rs.next();
                if(rs.getInt("count")!=0){
                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MMM-dd"); 
                    Date parsedDate=df.parse(giris_tarihi); 
                    Date parsedDate2=df.parse(cikis_tarihi); 
                    ps2 = conn.prepareStatement("insert into mahkum values(?,?,?,?,?,?,?,?)");
                      ps2.setInt(1,mahkum_ssn);
                      ps2.setString(2,mahkum_isim);
                      ps2.setString(3,mahkum_soyisim);
                      ps2.setDate(4, (java.sql.Date) parsedDate);
                      ps2.setDate(5, (java.sql.Date) parsedDate2);
                      ps2.setString(6, hucre_tipi);
                      ps2.setInt(7,hucre_no);
                      ps2.setString(8, blok);
                      ps2.execute();
                }
                else{
                     JOptionPane.showMessageDialog(null, "Cok kisilik hucrelerde boyle bir hucre no bulunmamaktadir");//alert
                }
            }         
          //conn.close();
          ps.close();
          ps2.close();
          rs.close();
         }catch (SQLException se) {
             System.out.println(se);
         } catch (ParseException ex) {
            Logger.getLogger(mahkum.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            //conn.close();
            st.close();
            rs.close();
        }catch (SQLException se) {
        } 
    }
}
