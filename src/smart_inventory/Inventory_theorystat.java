/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;

import static java.lang.Math.sqrt;
import javax.swing.JOptionPane;

/**
 *
 * @author MAHE
 */
public class Inventory_theorystat {
    public void start1(double c1, double c2, double c3, double r) {
        // TODO code application logic here
    double els;//economic lot size
    double t0;//reorder time
    double n0;//frequency of replenishment
    double c;//min avg cost
    double s0;//max inventory level
    double msl;//max storage level
    
    //c1=holding cost per unit good per unit time; c2=shortage cost per unit good per unit time;<-----INPUTS
    //c3=setup cost per production run; r=demand rate; <----- INPUTS
    els=sqrt(2*c3*r/c1)*sqrt((c1+c2)/c2);

    t0=els/r;
    n0=1/t0;
    c=sqrt(c2/(c1+c2))*sqrt(2*c1*c3*r);
    s0=(c2*els)/(c1+c2);
    msl=els-s0;
    JOptionPane.showMessageDialog(null, "The economic lot size is "+els+"\nThe reorder time is "+t0+"\nThe frequency of replinishment is "+n0+"\nThe min avg cost is "+c+"\nThe max inventory level is "+s0+"\nThe max storage level is "+msl, " ", 1);      
}
}
