package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Location implements Serializable {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Vehicule vehicule;
    private boolean estAssure;

    public Location(LocalDate debut, LocalDate fin, Vehicule v, boolean assure) {
        this.dateDebut = debut;
        this.dateFin = fin;
        this.vehicule = v;
        this.estAssure = assure;
    }

    public int dureeEnJours() {
        return (int) ChronoUnit.DAYS.between(dateDebut, dateFin);
    }

    public double getMontantTotal() {
        int jours = dureeEnJours();
        double total = vehicule.calculerPrixLocation(jours);
        if (estAssure && vehicule instanceof Assurable) {
            total += ((Assurable) vehicule).calculerPrimeAssurance() * jours;
        }
        return total;
    }

    public void afficherFacture() {
        System.out.println("--- Facture de Location ---");
        vehicule.afficherInfos();
        System.out.println("Periode : du " + dateDebut + " au " + dateFin);
        System.out.println("Duree : " + dureeEnJours() + " jours");
        System.out.println("Assurance : " + (estAssure ? "Oui" : "Non"));
        System.out.println("Montant Total : " + getMontantTotal() + " DT");
        System.out.println("---------------------------");
    }

    public Vehicule getVehicule() { return vehicule; }
}
