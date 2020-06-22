package org.ensak.back_office.metier.managerbeans;


import org.ensak.back_office.dao.EmployeDao;
import org.ensak.back_office.metier.beans.Employe;


import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeManager {
    static  EmployeDao employeDao = new EmployeDao();

    /**
     * methode qui renvoi tous les employes recuperes de la base de données
     * elle renvoi une arraylist
     * @return
     */
    public static ArrayList<Employe> getAllEmployes(){
        return  employeDao.getAllEmployes();
    }

    /**
     * methode qui renvoi un object employé contenant toutes les
     * informations relatives a un employé
     * @param id
     * @return
     * @throws SQLException
     */

    public static Employe getEmployeById(int id) throws SQLException {
        return employeDao.getEmployeById(id);
    }

    /**
     * methode qui prend en entré un id et supprime l'employé qui fait reference
     * a ce id
     * @param id
     * @return
     */

    public static boolean deleteEmploye(int id)  {
        return employeDao.deleteEmployeById(id);
    }

    /**
     * methode prenant en paramettre un employé l'enregistre dans la base de données
     * @param employe
     * @return
     */

    public  static  boolean saveEmploye(Employe employe) {
        return employeDao.addEmploye(employe);
    }

    public static boolean updateDivisionIdEmploye(int idDivision,int idEmploye)
    {
        return employeDao.updateDivisionIdEmploye(idDivision,idEmploye);
    }

}
