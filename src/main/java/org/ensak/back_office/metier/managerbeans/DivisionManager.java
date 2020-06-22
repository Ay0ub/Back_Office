package org.ensak.back_office.metier.managerbeans;

import org.ensak.back_office.dao.DaoDivision;
import org.ensak.back_office.metier.beans.Division;
import org.ensak.back_office.metier.beans.Employe;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DivisionManager {

    /**
     * methode permettant de pouvoir recuperer
     * toutes les division
     * @return
     * @throws SQLException
     */
    public static ArrayList<Division> getAllDivisions() throws SQLException {
        return (ArrayList<Division>) DaoDivision.getAllDivision();
    }

    /**
     * cette methode permet de recuperer une division
     * au travers de son id donné en parametre
     * @param id
     * @return
     * @throws SQLException
     */
    public static Division getDivisionById(int id) throws SQLException {
        DaoDivision daoDivision = new DaoDivision();
        return daoDivision.getDivisionById(id);
    }

    /**
     * cette methode permet de supprimer une division
     * au travers de son ed entré en parametre
     * @param id
     */
    public static boolean deleteDivision(int id) throws SQLException {
        DaoDivision daoDivision = new DaoDivision();
        return daoDivision.deleteDivision(id);

    }

    public static int getLastIdDivision()
    {
        DaoDivision daoDivision = new DaoDivision();
        return  daoDivision.getLastIdDivision();
    }

    public static boolean saveDivision(Division division)
    {
        DaoDivision daoDivision = new DaoDivision();
        if(daoDivision.addDivision(division))
        {
            int idDivision = getLastIdDivision();
            ArrayList<Employe> employes =  employes = division.getEmployes();
            for (Employe employe: employes)
            {
                EmployeManager.updateDivisionIdEmploye(idDivision,employe.getNumero());
            }
            return true;
        }
        return false;
    }
}
