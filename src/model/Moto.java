package model;

public class Moto extends Vehicule implements Assurable {
    private int cylindree;

    public Moto(String immat, String marque, String modele, double prix, int cyl) {
        super(immat, marque, modele, prix);
        this.cylindree = cyl;
    }

    @Override
    public double calculerPrixLocation(int j) {
        return prixBase * j * 0.9;
    }

    @Override
    public double calculerPrimeAssurance() {
        return 8.0;
    }

    @Override
    public boolean estAssurable() {
        return cylindree <= 125;
    }

    public int getCylindree() { return cylindree; }
    public void setCylindree(int c) { this.cylindree = c; }
}
