package model;

import java.io.Serializable;

public abstract class Vehicule implements Serializable {
    protected String immatriculation;
    protected String marque;
    protected String modele;
    protected double prixBase;

    public Vehicule(String immat, String marque, String modele, double prix) {
        this.immatriculation = immat;
        this.marque = marque;
        this.modele = modele;
        this.prixBase = prix;
    }

    public abstract double calculerPrixLocation(int jours);

    public void afficherInfos() {
        System.out.println("Immat: " + immatriculation + " | Marque: " + marque + " | Modele: " + modele + " | Prix: " + prixBase + "dt/j");
    }

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String im) { this.immatriculation = im; }
    public String getMarque() { return marque; }
    public void setMarque(String m) { this.marque = m; }
    public String getModele() { return modele; }
    public void setModele(String m) { this.modele = m; }
    public double getPrixBase() { return prixBase; }
    public void setPrixBase(double p) { this.prixBase = p; }
}
