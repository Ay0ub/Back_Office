package org.ensak.back_office.dao;

import org.ensak.back_office.metier.beans.Division;
import org.ensak.back_office.metier.beans.Employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoDivision implements InterfaceDaoDivision {

    private static final Connection conn = ConnexionBD.connexion();
    private static String TABLE_NAME = "division";
    private static String TB_EMPLOYES_NAME = "employes";
    private Division division;
    private static String query_employes = "SELECT * FROM" + TB_EMPLOYES_NAME + "WHERE chef_division=?";

    /**
     * cette methode permet de recuperer une
     * division au travers de son id entré en parametre
     * @param id
     * @return
     * @throws SQLException
     */

    @Override
    public Division getDivisionById(int id) throws SQLException {

        ArrayList<Employe> employes = new ArrayList<Employe>();
        EmployeDao employeDao = new EmployeDao();
        //String query = "SELECT * FROM" + TABLE_NAME + "WHERE id =?";
        PreparedStatement preparedStatement = DaoDivision.conn.prepareStatement("select * from division where id=?");
        PreparedStatement preparedStatement1 = conn.prepareStatement("select * from employes where id_division=?");
        preparedStatement.setInt(1, id);
        preparedStatement1.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        ResultSet resultSetemployes = preparedStatement1.executeQuery();
        division = new Division();
        if (resultSet.next()) {
            division.setId(resultSet.getInt(1));
            division.setNomDivision(resultSet.getString(2));
            division.setChefDivision(employeDao.getEmployeById(resultSet.getInt(3)));
        }
        while (resultSetemployes.next()) {
            Employe employe = new Employe();
            employe.setNumero(resultSetemployes.getInt(1));
            employe.setNom(resultSetemployes.getString(2));
            employe.setPrenom(resultSetemployes.getString(3));
            employes.add(employe);
        }
        division.setEmployes(employes);

        return division;
    }

    @Override
    public boolean addDivision(Division division) {
        try {
            String query = "INSERT INTO division (nom,chef_division) VALUES(?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,division.getNomDivision());
            preparedStatement.setInt(2,division.getChefDivision().getNumero());
            preparedStatement.execute();
            return true;
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return false;
        }

    }


    @Override
    public final boolean updateDivision(final Division division) throws SQLException {

        String query = "INSERT INTO" +TABLE_NAME+"(nom,chef_division) VALUES(?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,division.getNomDivision());
        preparedStatement.setInt(2,division.getChefDivision().getNumero());

        return preparedStatement.execute();
    }

    /**
     * cette methode permet de supprimer une division presente dans la base de
     * données aiu travers de son id
     * @param id
     * @return
     * @throws SQLException
     */

    @Override
    public final boolean deleteDivision(int id) throws SQLException {

        //String query = "DELETE FROM"+TABLE_NAME+"WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement("delete from division where id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return preparedStatement.execute();
        }


    }

    /**
     * cette methode permet de recuperer toutes les division
     * presentes dans la base de données
     * @return
     * @throws SQLException
     */

    public static List<Division> getAllDivision() throws SQLException {
        PreparedStatement preparedStatement=null;
        List<Division> divisions = new ArrayList<>();
        ArrayList<Employe> employes = new ArrayList<Employe>();
        EmployeDao employeDao = new EmployeDao();
        String query  = "SELECT * FROM"+TABLE_NAME;
        try {
            /**
             * execution de la commande sql permettant
             * d'obtenir toutes les division presentes dans
             * la base de données
             */
            preparedStatement = conn.prepareStatement("select * from division");

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                int id =resultSet.getInt(1);
               PreparedStatement preparedStatement1 =conn.prepareStatement("select * from employes where id=?");
                preparedStatement1.setInt(1,id);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next())
                {
                    employes.add(employeDao.getEmployeById(id));
                }


                Division division = new Division(id,resultSet.getString(2),employeDao.getEmployeById(resultSet.getInt(3)),employes);

                divisions.add(division);
            }

            return divisions;
        } finally {
            /**
             * on ajoute ce final pour effectuer le netoyage
             * pour liberer la ressource.
             */
            if (preparedStatement != null) preparedStatement.close();
        }

    }

    @Override
    public int getLastIdDivision() {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select MAX(id) from division");
            ResultSet resultSet = preparedStatement.executeQuery();
            int maxid = 0;
            while (resultSet.next())
            {
                maxid =  resultSet.getInt(1);
            }
            return maxid;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return 0;
        }
    }


    @Override
    public boolean addEmployeDivision(Division division, Employe employe) throws SQLException {
        String query = "INSERT INTO employe (id_division) VALUES (?) WHERE id =?";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1,division.getId());
        preparedStatement.setInt(2,employe.getNumero());
        return preparedStatement.execute();
    }


}
