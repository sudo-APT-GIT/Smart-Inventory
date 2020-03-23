/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MAHE
 */
public class Leastsquare {
    static int getrowcount(ResultSet rs1)
    {
        int size=0;
        try {
            rs1.last();
            size=rs1.getRow();
            rs1.beforeFirst();
            
            
        } catch (Exception e) {
        }
       
        return size; 
    }
    public static void main(String[] args) 
    {
        try {
            DB_Connect conn= new DB_Connect();
            Statement stmt= conn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlq="SELECT DISTINCT(WORKING_HRS),SALARY from employee";
            ResultSet rs=stmt.executeQuery(sqlq);
            int k=getrowcount(rs);
            double testx[]=new double[k];/// get from database
            double testy[]=new double[k];/// get from database
            int s=0;
            while (rs.next())
            {
                testx[s]=rs.getDouble("WORKING_HRS");
                testy[s]=rs.getDouble("SALARY");
                s++;
            }
            for(int index=0; index<k; index++)
            {
                System.out.print(testx[index]+"\t");
                System.out.print(testy[index]+"\n");
                
            }
            
            double sumx=0;
            double sumy=0;
            for(int i=0;i<testx.length;i++)
            {
                sumx=sumx+testx[i];
                sumy=sumy+testy[i];
            }   double meanx=sumx/testx.length;
            double meany=sumy/testy.length;
            double xdev[]=new double[testx.length],ydev[]=new double[testy.length];
            for(int i=0;i<testx.length;i++)
            {
                xdev[i]=testx[i]-meanx;
                ydev[i]=testy[i]-meany;
            }   double multdev[]=new double[testx.length];
            double xdevsquare[]=new double[testx.length];
            for(int i=0;i<testx.length;i++)
            {
                multdev[i]=xdev[i]*ydev[i];
                xdevsquare[i]=xdev[i]*xdev[i];
            }   double sumxydev=0;
            double sumxdevsquare=0;
            for(int i=0;i<testx.length;i++)
            {
                sumxydev=sumxydev+multdev[i];
                sumxdevsquare=sumxdevsquare+xdevsquare[i];
            }   double slope;
            slope=sumxydev/sumxdevsquare;
            System.out.println(slope);
            double yintercept;
            yintercept=meany-slope*meanx;
            System.out.println(yintercept);
        } catch (SQLException ex) {
            Logger.getLogger(Leastsquare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
