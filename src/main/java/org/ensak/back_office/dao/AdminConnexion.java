package org.ensak.back_office.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminConnexion {

    private static final Connection conn = ConnexionBD.connexion();
    private static final String SELECT_QUERY = "SELECT  * FROM admin WHERE login =? and password =?";
    public AdminConnexion() {}

    /**
     * cette methode permet de valider les données entré par
     * l'administrateur ce qui lui permettra de se connecter a la base de données
     * elle prend en parametre son login et son mot de passe pour verifier sa conformité
     * avec celui enregistré dans la base de données
     * @param login
     * @param pass
     * @return
     * @throws SQLException
     */
    public boolean validation(String login, String pass) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(SELECT_QUERY);
        preparedStatement.setString(1,login);
        preparedStatement.setString(2,pass);

        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }




}
