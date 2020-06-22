package org.ensak.back_office.dao;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * class connexion a la base de données qui respecte le patthern singleton
 * empechant a cette classe d'etre instanciée
 */
public class ConnexionBD {


        String url = "jdbc:mysql://localhost:3306/projet_java";
        String user = "root";
        String passwd = "";
        private static Connection conn=null;

    private ConnexionBD() {
        super();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, passwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection connexion()
    {
        if (conn==null)
            new ConnexionBD();
        return conn;
    }



}
