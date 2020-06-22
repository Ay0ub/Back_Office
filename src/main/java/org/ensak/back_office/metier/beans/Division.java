package org.ensak.back_office.metier.beans;

import java.util.ArrayList;

public class Division {
    private int id;
    private String nomDivision;
    private Employe chefDivision;
    private ArrayList<Employe> employes;
    public static final int NB_EMPLOYES_MIN =1;

    public Division() {
        super();
        this.employes = new ArrayList<Employe>();
    }

    public Division(int id, String nomDivision, Employe chefDivision, ArrayList<Employe> employes) {
        this.id = id;
        this.nomDivision = nomDivision;
        this.chefDivision = chefDivision;
        this.employes = employes;
    }
    public Division(String nomDivision, Employe chefDivision, ArrayList<Employe> employes) {
        this.id = id;
        this.nomDivision = nomDivision;
        this.chefDivision = chefDivision;
        this.employes = employes;
    }

    public Division(String nomDivision, Employe chefDivision) {
        this.nomDivision = nomDivision;
        this.chefDivision = chefDivision;
    }


    public int getId() {
        return this.id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public ArrayList<Employe> getEmployes() {
        return this.employes;
    }

    public void setEmployes(final ArrayList<Employe> employes) {
        this.employes = employes;
    }

    public Employe getChefDivision() {
        return this.chefDivision;
    }

    public void setChefDivision(final Employe chefDivision) {
        this.chefDivision = chefDivision;
    }

    public String getNomDivision() {
        return this.nomDivision;
    }

    public void setNomDivision(final String nomDivision) {
        this.nomDivision = nomDivision;
    }
}
