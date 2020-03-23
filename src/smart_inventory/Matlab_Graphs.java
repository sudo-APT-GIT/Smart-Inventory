/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;
import com.mathworks.engine.MatlabEngine;
import com.mathworks.matlab.types.HandleObject;

/**
 *
 * @author MAHE
 */
public class Matlab_Graphs {
     public static void main(String[] args) 
    {
        double testx[]=new double[]{8,2,11,6,5,4,12,9,6,1};
        double testy[]=new double[]{3,10,3,6,8,12,1,4,9,14};
        double test2x[]=new double[testx.length];
        double m=-1.1;
        double c=14;
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
             
            eng.feval("plot",(Object) testx,(Object) testy, "*",(Object)testx,(Object)test2y);
            
            eng.eval("uiwait");
            
            eng.close();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
