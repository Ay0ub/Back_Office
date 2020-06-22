package org.ensak.back_office.dao;


import org.ensak.back_office.metier.beans.Division;
import org.ensak.back_office.metier.beans.Employe;

import java.sql.SQLException;
import java.util.List;

public interface InterfaceDaoDivision {
    public Object getDivisionById(int id) throws SQLException;
    public boolean addDivision(Division division) throws SQLException;
    public boolean updateDivision(Division division) throws SQLException;
    public boolean deleteDivision(int id) throws SQLException;
    public static List<Division> getAllDivision() {
        return null;
    }
    public int getLastIdDivision();
    public boolean addEmployeDivision(Division division, Employe employe) throws SQLException;
}
