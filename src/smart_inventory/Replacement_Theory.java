/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;

import java.util.Arrays;

/**
 *
 * @author MAHE
 */
public class Replacement_Theory {
    public static void main(String[] args) {
        double c[]={1000,2500,4000,6000,9000,12000,16000,20000};
        int i;
        double p=61000; //principle value of the machine ; need to query these values from machinery and repair details(extrapolation)
        double sn=1000;//re sale value of the machine. Assume constant for every year
        System.out.println(c.length);
        for(i=0;i<c.length-1;i++)
        {
            c[i+1]=c[i]+c[i+1];      //finding cumulative frequnecy      
        }     
        
        double var=p-sn;
        for(i=0;i<c.length;i++)
        {
            c[i]=(c[i]+var)/(i+1);   //calculating avg annual cost incurred on the machine    
            
        }
        Arrays.sort(c);
        System.out.println(c[0]);     
        
    }
    }

