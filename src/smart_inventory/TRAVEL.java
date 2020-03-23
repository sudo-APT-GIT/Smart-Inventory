/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author MAHE
 */
public class TRAVEL {
   private int numberOfNodes;
   private Stack<Integer> stack;
   static int arr[]=new int[3];
   public TRAVEL()
   {
    stack = new Stack<Integer>();
}

public void tsp(int adjacencyMatrix[][])
{
    numberOfNodes = adjacencyMatrix[1].length - 1;
    int[] visited = new int[numberOfNodes + 1];
    visited[1] = 1;
    stack.push(1);
    int element, dst = 0, i;
    int min = Integer.MAX_VALUE;
    boolean minFlag = false;
    System.out.print(1 + "\t");
    arr[0]=1;
    int ind=1;
    while (!stack.isEmpty())
    {

        element = stack.peek();
        i = 1;
        min = Integer.MAX_VALUE;
        while (i <= numberOfNodes)
        {
            if (adjacencyMatrix[element][i] > 1 && visited[i] == 0)
            {
                if (min > adjacencyMatrix[element][i])
                {
                    min = adjacencyMatrix[element][i];
                    dst = i;
                    minFlag = true;
                }
            }
            i++;
        }
        if (minFlag)
        {
            visited[dst] = 1;
            arr[ind++]=dst;
            stack.push(dst);


            System.out.print("ll"+dst+"\t");

            minFlag = false;
            continue;
        }
        stack.pop();

    }
}

public static void main(String... arg){
    
}
public void start1(int n1, int n2, int n3, int n4, int n5, int n6)
{
    int adjacency_matrix[][] = {{0, 0, 0, 0},{0, 0, n1,n2},{0, n3,0,n4},{0,n5,n6,0}};

    for (int i = 1; i <= 3; i++)
    {
        for (int j = 1; j <= 3; j++)
        {
            if (adjacency_matrix[i][j] == 1 && adjacency_matrix[j][i] == 0)
            {
                adjacency_matrix[j][i] = 1;
            }
        }
    }

    TRAVEL tspNearestNeighbour = new TRAVEL();
    tspNearestNeighbour.tsp(adjacency_matrix);
    System.out.println("the citys are visited as follows");

    JOptionPane.showMessageDialog(null, "The cities are visited as follows City "+ arr[0]+" City "+arr[1]+" City "+arr[2], " ", 1);   


}
}
