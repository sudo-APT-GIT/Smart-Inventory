/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;

import com.mathworks.engine.MatlabEngine;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MAHE
 */
public class least_graph {
     int getrowcount(ResultSet rs1)
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
    public  double slope;
    public  double yintercept;
    public  double testx[];
    public  double testy[];
    public  void getslope() 
    {
        try {
            DB_Connect conn= new DB_Connect();
            Statement stmt= conn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlq="SELECT DISTINCT(WORKING_HRS),SALARY from employee";
            ResultSet rs=stmt.executeQuery(sqlq);
            int k=getrowcount(rs);
            testx=new double[k];/// get from database
            testy=new double[k];/// get from database
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
            conn.con.close();
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
            }   
            slope=sumxydev/sumxdevsquare;
            System.out.println(slope);
            
            yintercept=meany-slope*meanx;
            System.out.println(yintercept);
        } catch (SQLException ex) {
            Logger.getLogger(Leastsquare.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public  void start1() 
    {
    
        getslope();
        System.out.println(yintercept);
        System.out.println(slope);
        double test2x[]=new double[testx.length];
        double m=slope;
        double c=yintercept;
       for(int i=0;i<testx.length;i++)
        {
            test2x[i]=m*testx[i];
        }            
        double test2y[]=new double[testy.length];
        for(int i=0;i<testy.length;i++)
        {
            test2y[i]=test2x[i]+c;
        }
        
        try
        {            
            MatlabEngine eng = MatlabEngine.startMatlab();
            String xl="Working hours";
            String yl="Salary";
             eng.feval("plot",(Object) testx,(Object) testy, "*",(Object)testx,(Object)test2y);
             eng.feval("xlabel", (Object)xl);
            eng.feval("ylabel", (Object)yl);
            eng.feval("xlabel", (Object)xl);
            eng.feval("ylabel", (Object)yl);
            eng.eval("uiwait");
            eng.close();
        }
        catch(Exception e)
        {
            System.out.println("Error!");
        }    

}
}