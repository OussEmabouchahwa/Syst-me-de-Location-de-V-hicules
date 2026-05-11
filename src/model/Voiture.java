package model;

public class Voiture extends Vehicule implements Assurable {
    private int nombrePortes;

    public Voiture(String immat, String marque, String modele, double prix, int portes) {
        super(immat, marque, modele, prix);
        this.nombrePortes = portes;
    }

    @Override
    public double calculerPrixLocation(int j) {
        return prixBase * j;
    }

    @Override
    public double calculerPrimeAssurance() {
        return 10.0;
    }

    @Override
    public boolean estAssurable() {
        return nombrePortes >= 4;
    }

    public int getNombrePortes() { return nombrePortes; }
    public void setNombrePortes(int n) { this.nombrePortes = n; }
}
