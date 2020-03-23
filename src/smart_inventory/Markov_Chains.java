/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import javax.swing.JOptionPane;
import static smart_inventory.Leastsquare.getrowcount;

/**
 *
 * @author MAHE
 */
public class Markov_Chains {
    //int yr_profit[];
     int getrowcount(ResultSet rs1)
    {
        int size=0;
        try {
            rs1.last();
            size=rs1.getRow();
            rs1.beforeFirst();
            
            
        } catch (Exception e) {
            System.out.println("Error1");
        }
       
        return size; 
    }
    public void start1(int num1) {
        try {
            DB_Connect conn= new DB_Connect();
            Statement stmt= conn.con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            String sqlq="SELECT YR ,sum(PROFIT) from production group by(YR)";
            ResultSet rs=stmt.executeQuery(sqlq);
            int ss=getrowcount(rs);
             int yr_profit[]= new int[ss];
             int yr_profitsort[]= new int[ss];
            int s=0;
            while (rs.next())
            {
                yr_profit[s]=rs.getInt("sum(PROFIT)");
                yr_profitsort[s]=rs.getInt("sum(PROFIT)");
                s++;
            }
             int len=yr_profit.length;
       Arrays.sort(yr_profitsort);
        System.out.println(yr_profitsort[0]);
        System.out.println(yr_profit[0]);
       int sum11=0;
       for(int q=0; q<len; q++)
       {
          sum11+=yr_profit[q];
       }
       int avg=sum11/len;
        int x=(yr_profitsort[0]+avg)/2, y=avg, z=(yr_profitsort[len-1]+avg)/2, ctrx=0,ctry=0,ctrz=0;
        System.out.println(x+"  "+y+"  "+z);
        for(int pk=0; pk<len; pk++)
        {
            if(yr_profit[pk]<=x)
            {
                
                yr_profit[pk]=x;
                
            }
            else if(yr_profit[pk]<=y)
            {
                yr_profit[pk]=y;
                            }
            else if(yr_profit[pk]<=z)
            {
                yr_profit[pk]=z;
            }
            else
            {
                yr_profit[pk]=z;
            }
            System.out.print(yr_profit[pk]+"\t");
        }
            
        int c11=0;
        int c12=0;
        int c13=0;
        int c21=0;
        int c22=0;
        int c23=0;
        int c31=0;
        int c32=0;
        int c33=0;
        for(int i=0; i<len-1; i++)
        {
            if(yr_profit[i]==x)
            {
                ctrx++;
            }
            if(yr_profit[i]==y)
            {
                ctry++;
            }
            if(yr_profit[i]==z)
            {
                ctrz++;
            }           
            
        }
        for(int i=1;i<yr_profit.length;i++)
        {
            if(yr_profit[i-1]==x&& yr_profit[i]==x)
            {
               c11++; 
            }
            if(yr_profit[i-1]==y && yr_profit[i]==y)
            {
               c22++; 
            }
            if(yr_profit[i-1]==z && yr_profit[i]==z)
            {
               c33++; 
            }
            if(yr_profit[i-1]==x&& yr_profit[i]==y)
            {
               c12++; 
            }
            if(yr_profit[i-1]==x&& yr_profit[i]==z)
            {
               c13++; 
            }
            if(yr_profit[i-1]==y&& yr_profit[i]==x)
            {
               c21++; 
            }
            if(yr_profit[i-1]==y&& yr_profit[i]==z)
            {
               c23++; 
            }
            if(yr_profit[i-1]==z&& yr_profit[i]==x)
            {
               c31++; 
            }
            if(yr_profit[i-1]==z&& yr_profit[i]==y)
            {
               c32++; 
            }
        
        
    }
        float t[][]={{c11, c12, c13},{c21, c22, c23},{c31, c32, c33}};
        for(int j=0;j<3; j++)
        {
            if(ctrx!=0 )
            {
                t[0][j]=t[0][j]/ctrx;
            }
           else
            {
                t[0][j]=0;
            }
               if(ctry!=0)
             {
                  t[1][j]=t[1][j]/ctry;
            }
               else
            {
                t[1][j]=0;
            }
              if(ctrz!=0 )
            {
                t[2][j]=t[2][j]/ctrz;              
            }
              else
            {
                
                t[2][j]=0;
            }
            System.out.print(t[0][j]+" "+t[1][j]+" "+t[2][j]);
           
        }
        int v=yr_profit[yr_profit.length-1];
        
        float w[]={0,0,0};
        if(v<=x)
            w[0]=1;
        else if(v<=y)
            w[1]=1;
        else if(v<=z)
            w[2]=1;
        else
           w[2]=1;
        
        for(int i=0; i<3; i++)
        {
            System.out.print(w[i]+" ");
        }
        System.out.println("");
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                System.out.print(t[i][j]+"   ");
            }
            System.out.println();
        }
        float sum=0; int i=0;
        float a[]=new float[3];
        float ans[][]=new float[3][3];
        float dup[][]=new float[3][3];
        dup=t;
            for(int num=0; num<num1; num++) 
           {
                for(int h=0;h<3;h++)
                    for(int g=0;g<3;g++)
                        ans[h][g]=0;
                for( i=0;i<3;i++)
                { 
                    for(int j=0;j<3;j++)
                    {
                        for(int k=0; k<3; k++)
                        {
                          ans[i][j]=ans[i][j]+ dup[i][k]*t[k][j] ;    
                        }

                    }

                }
                for(int h=0;h<3;h++)
                    System.arraycopy(ans[h], 0, t[h], 0, 3);
                
           }
            
           double ans2[]=new double[3];
            for(int j=0;j<3;j++)
            {
                sum=0;
                for(int k=0;k<3;k++)
                {
                    sum=sum + w[k]*t[k][j];
                }
                ans2[j]=sum;
             }
         
        

        
            System.out.println();
             for( i=0; i<3; i++)
            {
                System.out.print(ans2[i]+"    ");
            }
             
             JOptionPane.showMessageDialog(null, "The probability of having profit "+x+" is "+ans2[0]+"\nThe probability of having profit "+y+" is "+ans2[1]+"\nThe probability of having profit "+z+" is "+ans2[2], " ", 1);
         
            conn.con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            
               
   }
   
    
}
