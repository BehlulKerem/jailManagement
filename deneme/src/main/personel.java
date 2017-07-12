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

public class personel {
    public Connection conn = null;
    public void kaydet(int personelno,int ssn,String isim,String soyisim,String pozisyon,double maas,String blok,String camasirhane,String revir,Boolean basmi){
        
        
        try{  
            jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            PreparedStatement ps = null;
            ResultSet rs,rs2 = null;
           
        if(pozisyon.equals("Gardiyan")){
            
            if((!blok.equals("")&&!camasirhane.equals(""))||(!blok.equals("")&&!revir.equals(""))||
                    (!revir.equals("")&&!camasirhane.equals(""))){
                 JOptionPane.showMessageDialog(null, "Gardiyan birden fazla konumda bulunamaz! Tek bir yer seçin.");
            }
            else{
                ps = conn.prepareStatement("insert into personel values(?,'?','?',?,?,?);"); 
                ps.setInt(1, ssn);
                ps.setString(2, isim);
                ps.setString(3, soyisim);
                ps.setDouble(4, maas);
                ps.setInt(5,personelno);
                ps.setInt(6,ssn);
                ps.execute();
                ps = conn.prepareStatement("insert into gpersonel values('?',?,'?','?',?);");
                ps.setString(1, revir);
                ps.setString(2, basmi.toString().toUpperCase());
                ps.setString(3, blok);
                ps.setString(4, camasirhane);
                ps.setInt(5, ssn);
                ps.close();
                conn.close();
            }
            
        }
        else if(pozisyon.equals("Sağlık Personeli")){
            ps = conn.prepareStatement("insert into personel values(?,'?','?',?,?,?);"); 
                ps.setInt(1, ssn);
                ps.setString(2, isim);
                ps.setString(3, soyisim);
                ps.setDouble(4, maas);
                ps.setInt(5,personelno);
                ps.setInt(6,ssn);
                ps.execute();
            ps = conn.prepareStatement("insert into spersonel values(?,'?');");
                ps.setInt(1, ssn);
                ps.setString(2, blok);
                ps.execute();
                
                ps.close();
                conn.close();
        }
        else if(pozisyon.equals("Yemekhane Personeli")){
            ps = conn.prepareStatement("insert into personel values(?,'?','?',?,?,?);"); 
                ps.setInt(1, ssn);
                ps.setString(2, isim);
                ps.setString(3, soyisim);
                ps.setDouble(4, maas);
                ps.setInt(5,personelno);
                ps.setInt(6,ssn);
                ps.execute();
            ps = conn.prepareStatement("insert into ypersonel values(?,'?');");
                ps.setInt(1, ssn);
                ps.setString(2, blok);
                ps.execute();
                
                ps.close();
                conn.close();
        }
        else if(pozisyon.equals("Temizlik Personeli")){
            ps = conn.prepareStatement("insert into personel values(?,'?','?',?,?,?);"); 
                ps.setInt(1, ssn);
                ps.setString(2, isim);
                ps.setString(3, soyisim);
                ps.setDouble(4, maas);
                ps.setInt(5,personelno);
                ps.setInt(6,ssn);
                ps.execute();
            ps = conn.prepareStatement("insert into tpersonel values(?,'?');");
                ps.setInt(1, ssn);
                ps.setString(2, blok);
                ps.execute();
                
                ps.close();
                conn.close();
        }
        }catch (SQLException se) {
          System.out.println(se);
        } //mkutlu point
    }
}
