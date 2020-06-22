package org.ensak.back_office.dao;

import org.ensak.back_office.metier.beans.Division;
import org.ensak.back_office.metier.beans.Employe;

import java.sql.SQLException;
import java.util.ArrayList;

public interface InterfaceDaoEmploye {

        public ArrayList<Employe> getAllEmployes() throws SQLException;
        public Employe getEmployeById(Integer id) throws SQLException;
        public boolean addEmploye(Employe emp) throws SQLException;
        public boolean deleteEmployeById(int id);
        public boolean  updateDivisionIdEmploye(int idDivision,int idEmploye)  throws SQLException;
}
