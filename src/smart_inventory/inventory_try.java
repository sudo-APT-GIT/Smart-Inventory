package smart_inventory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import smart_inventory.DB_Connect;

public class inventory_try {
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
public  void start1(String mac) {

    try {
        DB_Connect conn= new DB_Connect();
        Statement stmt= conn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sqlq="SELECT EXTRACT(YEAR FROM DATE_OF_REPAIR), SUM(REPAIR_COST) FROM repair_details WHERE M_ID='MAC1001' GROUP BY (EXTRACT(YEAR FROM DATE_OF_REPAIR))";
        ResultSet rs=stmt.executeQuery(sqlq);
        int ss=getrowcount(rs);
        double y[]=new double[ss];
        int mm=0;
        while (rs.next())
        {
            y[mm]=rs.getDouble("SUM(REPAIR_COST)");

            mm++;
        }
               // double y[]={3000,4500,5500};

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
                    
                }
                if(d==1)//if length is even then set values of x as continuous numbers
                {
                    o=(y.length/2)*-1;
                    for(int i=0;i<(0+x.length);i++)
                    {
                        x[i]=o;
                        o=o+1;
                        //System.out.println(x[i]);
                    }
                    
                }
                for (int i=0;i<x.length;i++)

                {
                    x2[i]=x[i]*x[i];
                    xy[i]=x[i]*y[i];
                }
                
                
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
                /*for (int i=0;i<x.length;i++) {
                    c[i]=y[i];}*/

//filling of tables go with respect to the length of the initial table therefore the conditions are checked accordingly and filled
                    if(d==1)
                        {o=(y.length/2)*-1;
                            for (int i=0;i<c.length;i++) {

                                c[i]=a+(b*+o);
                                o=o+1;
    //System.out.println(c[i]);
                            }
                        }

                        if(d==0)
                        {
                            o=(y.length*-1)+1;
                            for (int i=0;i<c.length;i++) {

                                c[i]=a+(b*+o);
                                o=o+2;
                            }
                        }
                        //actual replacement theory starts
                        int i;

                        String sqlq1="SELECT COST, RESALE_VAL from machinery where M_ID=?";
                        PreparedStatement pst=conn.con.prepareStatement(sqlq1);
                        pst.setString(1, mac);
            double p=0; //principle value of the machine ; need to query these values from machinery and repair details(extrapolation)
            double sn=0;
            ResultSet rs1=pst.executeQuery();
            int k=getrowcount(rs);
            int s1=0;
            while (rs1.next())
            {
                p=rs1.getDouble("COST");
                sn=rs1.getDouble("RESALE_VAL");
                s1++;
            }
            
            for(i=0;i<c.length-1;i++)
            {
                c[i+1]=c[i]+c[i+1];      //finding cumulative frequnecy
            }

            double var=p-sn;
            double v[]=new double[c.length];
            System.out.println("hiii");
            for(i=0;i<c.length;i++)
            {
                double kk=(c[i]+var)/(i+1);
                c[i]=kk; 
                v[i]=kk;//calculating avg annual cost incurred on the machine
                System.out.println(c[i]+" "+v[i]);
                
            }
            //double v[]=c;

                String sqlq2="SELECT DISTINCT(EXTRACT(YEAR FROM DATE_OF_REPAIR))\"YEAR\" FROM repair_details WHERE M_ID=?";
                PreparedStatement pst2=conn.con.prepareStatement(sqlq2);
                pst2.setString(1, mac);

                ResultSet rs2=pst2.executeQuery();
                int jj=getrowcount(rs2);
                int yer[]=new int[jj+4];
                int oo=0;
                System.out.println("YEAR");
                while (rs2.next())
                {
                    yer[oo]=rs2.getInt("YEAR");
                    System.out.print(yer[oo]);
                    oo++;
                }
               
                int last=yer[oo-1];
            
                while(oo<(jj+4))
                {
                 System.out.print(oo);

                 last++;
                 yer[oo]=last;

                 System.out.print("  "+yer[oo]);
                 ++oo;
                }

                Arrays.sort(v);

                for (i=0;i<v.length;i++)
                {
                    System.out.println(c[i]+" "+v[0]);
                    if(c[i]==v[0])
                    {
                        JOptionPane.showMessageDialog(null, "The optimal year for replacement of the machine is "+yer[i], " ", 1);//GIVES ERROR
                    }
                    conn.con.close();
                } 
              }
             catch (SQLException ex) {
                    System.out.println("Error!");
             }
}
public static void main(String[] args) {
        //start1("MAC1005");
}
}
