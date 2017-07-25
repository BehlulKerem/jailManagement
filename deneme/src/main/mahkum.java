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
                        Date parsed2 = format.parse(cikis_tarihi);
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
                     SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        Date parsed = format.parse(giris_tarihi);
                        java.sql.Date sql = new java.sql.Date(parsed.getTime());
                        Date parsed2 = format.parse(cikis_tarihi);
                        java.sql.Date sql2 = new java.sql.Date(parsed2.getTime());
                    ps2 = conn.prepareStatement("insert into mahkum values(?,?,?,?,?,?,?,?)");
                      ps2.setInt(1,mahkum_ssn);
                      ps2.setString(2,mahkum_isim);
                      ps2.setString(3,mahkum_soyisim);
                      ps2.setDate(4,sql);
                      ps2.setDate(5,sql2);
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
     void sil(Integer valueOf, String text, String text0, String text1, String text2, Integer valueOf0, String text3, String text4) {
         try{  
            jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            PreparedStatement ps,ps2 = null;
            ps = conn.prepareStatement("delete from mahkum where ssn=? and isim=? and soyisim=? and blok=? and hucre_tipi=? and hucre_no=? and giris_tarihi=? and cikis_tarihi=?");
              ps.setInt(1, valueOf);
              ps.setString(2, text);
              ps.setString(3, text0);
              ps.setString(4, text1);
              ps.setString(5, text2);
              ps.setInt(6, valueOf0);
              ps.setString(7, text3);
              ps.setString(8, text4);          
              ps.execute();
             System.out.println(ps.toString());
           }catch (SQLException se) {
          System.out.println(se);
        } 
    }
    void edit(Integer ssn, String isim, String soyisim, String blok, String hucre_tipi, Integer hucre_no, String giris_tarihi, String cikis_tarihi,
            Integer valueOf, String text, String text0, String text1, String text2, Integer valueOf0, String text3, String text4) {
         try{  
            jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            PreparedStatement ps = null;
            ps = conn.prepareStatement("update mahkum set ssn=?,isim=?,soyisim=?,blok=?,hucre_tipi=?,hucre_no=?,giris_tarihi=?,cikis_tarihi=?"
                    + " where ssn=? and isim=? and soyisim=? and blok=? and hucre_tipi=? and hucre_no=? and giris_tarihi=? and cikis_tarihi=?");
              ps.setInt(1, valueOf);
              ps.setString(2, text);
              ps.setString(3, text0);
              ps.setString(4, text1);
              ps.setString(5, text2);
              ps.setInt(6, valueOf0);
              ps.setString(7, text3);
              ps.setString(8, text4);
              ps.setInt(9, ssn);
              ps.setString(10, isim);
              ps.setString(11, soyisim);
              ps.setString(12, blok);
              ps.setString(13, hucre_tipi);
              ps.setInt(14, hucre_no);
              ps.setString(15, giris_tarihi);
              ps.setString(16, cikis_tarihi);
              ps.execute();
             System.out.println(ps.toString());
           }catch (SQLException se) {
          System.out.println(se);
        } 
    }
    void hepsi(DefaultTableModel model,int rowCount){
       
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
