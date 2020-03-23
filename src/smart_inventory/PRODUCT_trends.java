/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author MAHE
 */
public class PRODUCT_trends {
    
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
public static void start1() {
            
            try {
                DB_Connect conn= new DB_Connect();
                Statement stmt= conn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                String sqlq="SELECT YR, sum(NO_SOLD) from production group by(YR)";
                ResultSet rs=stmt.executeQuery(sqlq);
                int ss=getrowcount(rs);
                double y[]=new double[ss];
                int mm=0;
                int yer[]= new int[ss+4];
                while (rs.next())
                {
                    y[mm]=rs.getDouble("SUM(NO_SOLD)");
                    yer[mm]=rs.getInt("YR");
                    mm++;
                }
	int last=yer[mm-1];
                //System.out.println("last"+last);
                //System.out.println("jj"+jj);
           while(mm<(ss+4))
           {
               //System.out.print(oo);
              
               last++;
                yer[mm]=last;
                
                System.out.print("  "+yer[mm]);
                 ++mm;
           }
    double x[]=new double[y.length];
    double x2[]=new double[y.length];
    double xy[]=new double[y.length];
    int o=0;
    int d= y.length%2; // to know if the length is even or odd so that a teensy modification of the algorithm in order to simplify the solving of 2 linesr equations
   
    if(d==0)//if length is even then set values of x as odd numbers 
    {
    	o=(y.length*-1)+1;
    	for(int i=0;i<(0+x.length);i++)
    	{
    		x[i]=o;
    		o=o+2;
    		
    	}
    	/*for(int i=(0+x.length/2);i<x.length/2;i++)
    	{
    		x[i]=o+2;
    	}*/
    }
    if(d==1)//if length is even then set values of x as continuous numbers
    {
    	o=(y.length/2)*-1;
    	for(int i=0;i<(0+x.length);i++)
    	{
    		x[i]=o;
    		o=o+1;
    		
    	}
    	
    }
    for (int i=0;i<x.length;i++)
    	
    {
    	x2[i]=x[i]*x[i];
    	xy[i]=x[i]*y[i];
    }
    
    System.out.println();
    double s=0;
    double s2=0;
    double cy=0;
    double sy=0;
for (int i=0;i<x.length;i++)
    	
    {
    	s=s+x[i];
    	s2=s2+x2[i];
    	cy=cy+xy[i];
    	sy=sy+y[i];
    	
    }
    

double a=sy/(y.length);
double b=cy/s2;

double c[]=new double[x.length+4];


//filling of tables go with respect to the length of the initial table therefore the conditions are checked accordingly and filled 
if(d==1)
{o=(y.length/2)*-1;
for (int i=0;i<c.length;i++) {

c[i]=a+(b*+o);
o=o+1;
 JOptionPane.showMessageDialog(null, "The trend for "+yer[i]+" is "+c[i], " ", 1);

}
}

if(d==0)
{
o=(y.length*-1)+1;
for (int i=0;i<c.length;i++) {

c[i]=a+(b*+o);
o=o+2;
JOptionPane.showMessageDialog(null, "The trend for "+yer[i]+" is "+c[i], " ", 1);
}
}

}
            catch(Exception e)
            {
                e.printStackTrace();
            }
}
    public static void main(String[] args) {
        start1();
    }
    
}
