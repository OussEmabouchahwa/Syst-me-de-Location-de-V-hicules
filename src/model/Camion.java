package model;

public class Camion extends Vehicule {
    private double poidMax;

    public Camion(String immat, String marque, String modele, double prix, double pMax) {
        super(immat, marque, modele, prix);
        this.poidMax = pMax;
    }

    @Override
    public double calculerPrixLocation(int j) {
        return prixBase * j * 1.2;
    }

    public double getPoidMax() { return poidMax; }
    public void setPoidMax(double p) { this.poidMax = p; }
}
