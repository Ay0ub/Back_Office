package org.ensak.back_office.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.ensak.back_office.metier.beans.Division;
import org.ensak.back_office.metier.beans.Employe;

public class EmployeDao implements InterfaceDaoEmploye{
    private Connection conn = ConnexionBD.connexion();
    private ResultSet rs;
    private PreparedStatement pstm;

    public EmployeDao() {
    }

    /**
     * cette methode permet de recuperer tous les employes present dans la base de donnees
     * et retourne une arraylist contenant tous les employés enregistrés
     * @return
     */

    public ArrayList<Employe> getAllEmployes(){
        ArrayList<Employe> employes = new ArrayList<Employe>();
        Employe empl;
        try {
            pstm = conn.prepareStatement("select * from employes");
            rs = pstm.executeQuery();
            while (rs.next()) {
                empl = new Employe();
                empl.setNumero(rs.getInt("id"));
                empl.setNom(rs.getString("nom"));
                empl.setPrenom(rs.getString("prenom"));
                empl.setEmail(rs.getString("email"));
                empl.setTel(rs.getString("telephone"));
                empl.setPassword(rs.getString("password"));
                empl.setIdDivision(rs.getInt("id_division"));
                employes.add(empl);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employes;

    }

    /**
     * cette methode permet de recuperer les informations d'un employe au travers de son
     * id elle prend en parametre l'id de l'employe que l'on veut recuperer dans la base de données
     * @param id
     * @return
     */
    public Employe getEmployeById(Integer id){
        Employe empl = null;
        try {
            pstm = conn.prepareStatement("select * from employes where id=?");
            pstm.setInt(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                empl = new Employe();
                empl.setNumero(rs.getInt("id"));
                empl.setNom(rs.getString("nom"));
                empl.setPrenom(rs.getString("prenom"));
                empl.setEmail(rs.getString("email"));
                empl.setTel(rs.getString("telephone"));
                empl.setPassword(rs.getString("password"));
                empl.setIdDivision(rs.getInt("id_division"));


            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empl;

    }

    /**
     * cette methode permet d'inserer un employe dans la base de données
     * elle prend en paramatre l'employe que l'on souhaite enregistrer
     * @param emp
     * @return
     */
    public boolean addEmploye(Employe emp){

        try {
            String query = "INSERT INTO employes (nom,prenom,email,telephone,password) VALUES(?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,emp.getNom());
            preparedStatement.setString(2,emp.getPrenom());
            preparedStatement.setString(3,emp.getEmail());
            preparedStatement.setString(4,emp.getTel());
            preparedStatement.setString(5,emp.getPassword());
            preparedStatement.execute();
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }


    /**
     * cette methode permet de supprimer un employe dans la base de
     * données elle prend en parametre l'id de l'employé que l'on veut
     * supprimer
     * @param id
     * @return
     */

    @Override
    public boolean deleteEmployeById(int id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("delete from employes where id=?")) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    @Override
    public boolean updateDivisionIdEmploye(int idDivision,int idEmploye){

        String query = "UPDATE employes set id_division=? where id=?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setInt(1, idDivision);
            preparedStatement.setInt(2, idEmploye);
            preparedStatement.execute();
            return true;
        }catch (Exception e)
        {
            return false;
        }

    }



}
