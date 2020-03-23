/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smart_inventory;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author MAHE
 */
public class DB_Connect {
    public Connection con;
    public DB_Connect()
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/smart_inventory","amrutha","2JjnaaNnpq8oSd5s");
            System.out.println("connected");
        } catch (Exception e) {
            System.out.println("Error"+e);
        }
    }
}
