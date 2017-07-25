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

public class personel {
    public Connection conn = null;
    void kaydet(int personelno,int ssn,String isim,String soyisim,String pozisyon,double maas,String blok,String camasirhane,String revir,Boolean basmi){
        
        
        try{  
            jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            PreparedStatement ps = null;
            ResultSet rs,rs2 = null;
           
        if(pozisyon.equals("Gardiyan")){
         
            if((!blok.equals(" ")&&!camasirhane.equals(" "))||(!blok.equals(" ")&&!revir.equals(" "))||
                    (!revir.equals(" ")&&!camasirhane.equals(" "))){
                 JOptionPane.showMessageDialog(null, "Gardiyan birden fazla konumda bulunamaz! Tek bir yer seçin.");
            }
            else{
                ps = conn.prepareStatement("insert into personel values(?,?,?,?,?,?);"); 
                ps.setInt(1, ssn);
                ps.setString(2, isim);
                ps.setString(3, soyisim);
                ps.setDouble(4, maas);
                ps.setInt(5,personelno);
                ps.setInt(6,ssn);
                ps.execute();
                ps = conn.prepareStatement("insert into gpersonel values(?,?,?,?,?);");
                ps.setString(1, revir);
                ps.setBoolean(2, basmi);
                ps.setString(3, blok);
                ps.setString(4, camasirhane);
                ps.setInt(5, ssn);
                ps.execute();
                ps.close();
                //conn.close();
            }
            
        }
        else if(pozisyon.equals("Sağlık Personeli")){
            ps = conn.prepareStatement("insert into personel values(?,?,?,?,?,?);"); 
                ps.setInt(1, ssn);
                ps.setString(2, isim);
                ps.setString(3, soyisim);
                ps.setDouble(4, maas);
                ps.setInt(5,personelno);
                ps.setInt(6,ssn);
                ps.execute();
            ps = conn.prepareStatement("insert into spersonel values(?,?);");
                ps.setInt(1, ssn);
                ps.setString(2, blok);
                ps.execute();
                
                ps.close();
                //conn.close();
        }
        else if(pozisyon.equals("Yemekhane Personeli")){
            ps = conn.prepareStatement("insert into personel values(?,?,?,?,?,?);"); 
                ps.setInt(1, ssn);
                ps.setString(2, isim);
                ps.setString(3, soyisim);
                ps.setDouble(4, maas);
                ps.setInt(5,personelno);
                ps.setInt(6,ssn);
                ps.execute();
            ps = conn.prepareStatement("insert into ypersonel values(?,?);");
                ps.setInt(1, ssn);
                ps.setString(2, blok);
                ps.execute();
                
                ps.close();
                //conn.close();
        }
        else if(pozisyon.equals("Temizlik Personeli")){
            ps = conn.prepareStatement("insert into personel values(?,?,?,?,?,?);"); 
                ps.setInt(1, ssn);
                ps.setString(2, isim);
                ps.setString(3, soyisim);
                ps.setDouble(4, maas);
                ps.setInt(5,personelno);
                ps.setInt(6,ssn);
                ps.execute();
            ps = conn.prepareStatement("insert into tpersonel values(?,?);");
                ps.setInt(1, ssn);
                ps.setString(2, blok);
                ps.execute();
                
                ps.close();
                //conn.close();
        }
        }catch (SQLException se) {
          System.out.println(se);
        } //mkutlu point
    }
    void hepsi(DefaultTableModel model,int rowCount){
       
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }
       
         try{      
        jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            Statement st=null,st2=null,st3=null,st4=null,st5=null;
            ResultSet rs = null,rs2 = null,rs3 = null,rs4 = null,rs5 = null;
            st = conn.createStatement();
            st2 = conn.createStatement();
            st3 = conn.createStatement();
            st4 = conn.createStatement();
            st5 = conn.createStatement();
            rs = st.executeQuery("select * from personel");  
            while ( rs.next()){
                int personelno = rs.getInt("personelno");
                int ssn = rs.getInt("ssn");
                String isim =rs.getString("isim") ;
                String soyisim = rs.getString("soyisim");
                double maas = rs.getDouble("maas");
                rs2 = st2.executeQuery("select count(*) from gpersonel where ssn="+ssn);
                rs2.next();
                if(rs2.getInt("count")!=0)
                    model.addRow(new Object[]{personelno,ssn,isim,soyisim,"Gardiyan",maas});
                rs3 = st3.executeQuery("select count(*) from spersonel where ssn="+ssn);
                rs3.next();
                if(rs3.getInt("count")!=0)
                    model.addRow(new Object[]{personelno,ssn,isim,soyisim,"Sağlık P.",maas});
                rs4 = st4.executeQuery("select count(*) from tpersonel where ssn="+ssn);
                rs4.next();
                if(rs4.getInt("count")!=0)
                    model.addRow(new Object[]{personelno,ssn,isim,soyisim,"Temizlik P.",maas});
                rs5 = st2.executeQuery("select count(*) from ypersonel where ssn="+ssn);
                rs5.next();
                if(rs5.getInt("count")!=0)
                    model.addRow(new Object[]{personelno,ssn,isim,soyisim,"Yemekhane P.",maas});
            }
            //conn.close();
            st.close();
            rs.close();
        }catch (SQLException se) {
        } 
    }
    void arama(Integer personelno,Integer ssn,String isim,String soyisim,String pozisyon,Double maas,String blok,String camasirhane,String revir,Boolean basmi,DefaultTableModel model,int rowCount){
        String aramametini="";
        try{      
        jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            PreparedStatement ps = null;
            ResultSet rs = null;
            int count=1,count2=1;
            if(pozisyon.equals("-"))
                aramametini  ="select * from personel p where ";
            if(pozisyon.equals("Gardiyan"))
                aramametini  ="select p.personelno,p.ssn,p.isim,p.soyisim,p.maas from gpersonel g,personel p where p.ssn=g.ssn ";
            else if(pozisyon.equals("Sağlık Personeli"))
                aramametini  ="select p.personelno,p.ssn,p.isim,p.soyisim,p.maas from spersonel g,personel p where p.ssn=g.ssn ";
            else if(pozisyon.equals("Yemekhane Personeli"))
                aramametini  ="select p.personelno,p.ssn,p.isim,p.soyisim,p.maas from ypersonel g,personel p where p.ssn=g.ssn ";
            else if(pozisyon.equals("Temizlik Personeli"))
                aramametini  ="select p.personelno,p.ssn,p.isim,p.soyisim,p.maas from tpersonel g,personel p where p.ssn=g.ssn ";
            else if(pozisyon.equals("Yönetim"))
                aramametini  ="select p.personelno,p.ssn,p.isim,p.soyisim,p.maas from yopersonel g,personel p where p.ssn=g.ssn ";
            
            ps = conn.prepareStatement(aramametini);

            System.out.println("-"+isim+"-");
            if(personelno==null);
            else{
                aramametini+="and ";
                aramametini+="p.personelno=? ";
                ps = conn.prepareStatement(aramametini);
                count2++;
            }
            if(ssn==null);
            else{
                if(count2!=1)
                    aramametini+="and ";
                aramametini+="p.ssn=? ";
                ps = conn.prepareStatement(aramametini);
                count2++;
            }
            if(isim.equals(""));
            else{
                if(count2!=1)
                    aramametini+="and ";
                aramametini+="p.isim=? ";
                ps = conn.prepareStatement(aramametini);
                count2++;
            }
            if(soyisim.equals(""));
            else{
                if(count2!=1)
                    aramametini+="and ";
                aramametini+="p.soyisim=? ";
                ps = conn.prepareStatement(aramametini);
                count2++;
            }
            if(maas==null);
            else{
                if(count2!=1)
                    aramametini+="and ";
                aramametini+="p.maas=? ";
                ps = conn.prepareStatement(aramametini);
                count2++;
            }
            if(personelno==null);
            else{
                ps.setInt(count, personelno);
                count++;
            }
            if(ssn==null);
            else{
                ps.setInt(count, personelno);
                count++;
            }
            if(isim.equals(""));
            else{
                ps.setString(count, isim);
                count++;
            }
            if(soyisim.equals(""));
            else{
                ps.setString(count, soyisim);
                count++;
            }
            if(maas==null);
            else{
                ps.setDouble(count, maas);
                count++;
            }
            aramametini+=";";
            System.out.println(ps.toString());
            rs = ps.executeQuery();
            for (int i = rowCount - 1; i >= 0; i--) {
                model.removeRow(i);
            }
            while ( rs.next()){
                int ssn2 = rs.getInt("ssn");
                String isim2 =rs.getString("isim") ;
                String soyisim2 = rs.getString("soyisim");
                double maas2 = rs.getDouble("maas");
                int personelno2 = rs.getInt("personelno");
                //EKLEME YAPILACAK.
                model.addRow(new Object[]{personelno2,ssn2,isim2,soyisim2,"-",maas2});
            }

            ps.close();
            rs.close();
        }catch (SQLException se) {
            System.out.println(se);
        } 
    }
    void edit(String isim, String soyisim, Integer ssn, Integer personelno, Double maas, String pozisyon, String text, String text0, Integer text1, String text2, Double text3, Integer text4) {
         try{  
            jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            PreparedStatement ps = null;
            ps = conn.prepareStatement("update personel set isim=?,soyisim=?,personelno=?,maas=?,ssn=?"
                    + " where isim=? and soyisim=? and personelno=? and maas=? and ssn=?;");
              ps.setString(1, text);
              ps.setString(2, text0);
              ps.setInt(3, text1);
              ps.setDouble(4, text3);
              ps.setInt(5, text4);
              ps.setString(6, isim);
              ps.setString(7, soyisim);
              ps.setInt(8, personelno);
              ps.setDouble(9, maas);
              ps.setInt(10, ssn);
              ps.execute();
             System.out.println(ps.toString());
           }catch (SQLException se) {
          System.out.println(se);
        } 
    }
    void sil(String text, String text0, Integer valueOf, String text1, Double valueOf0, Integer valueOf1) {
        try{  
            jdbc sub = new jdbc();
            conn = sub.connectToDatabaseOrDie();
            PreparedStatement ps,ps2 = null;
            ps = conn.prepareStatement("delete from personel where isim=? and soyisim=? and ssn=? and maas=? and personelno=?");
            
              ps.setString(1, text);
              ps.setString(2, text0);
              ps.setInt(3, valueOf);
              ps.setDouble(4, valueOf0);
              ps.setInt(5, valueOf1);              
              ps.execute();
             System.out.println(ps.toString());
             if(text1.equals("Gardiyan")){
                  ps2 = conn.prepareStatement("delete from gpersonel where ssn=?");
                  ps2.setInt(1, valueOf);
                  ps2.execute();
             }
             else if(text1.equals("Sağlık P.")){
                  ps2 = conn.prepareStatement("delete from gpersonel where ssn=?");
                  ps2.setInt(1, valueOf);
                  ps2.execute();
             }
             else if(text1.equals("Yemekhane P.")){
                  ps2 = conn.prepareStatement("delete from gpersonel where ssn=?");
                  ps2.setInt(1, valueOf);
                  ps2.execute();
             }
             else if(text1.equals("Temizlik P.")){
                  ps2 = conn.prepareStatement("delete from gpersonel where ssn=?");
                  ps2.setInt(1, valueOf);
                  ps2.execute();
             }
           }catch (SQLException se) {
          System.out.println(se);
        } 
    }
        
}
