package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import exceptions.PermisManquantException;
import exceptions.VehiculeNonAssurableException;

public class Client implements Serializable {
    private String id;
    private String nom;
    private boolean aPermis;
    private List<Location> locations;

    public Client(String id, String nom, boolean aPermis) {
        this.id = id;
        this.nom = nom;
        this.aPermis = aPermis;
        this.locations = new ArrayList<>();
    }

    public void louer(Vehicule v, LocalDate debut, LocalDate fin, boolean assurer) throws Exception {
        if (!aPermis) {
            throw new PermisManquantException("Erreur : Le client n'a pas de permis.");
        }
        if (assurer) {
            if (v instanceof Assurable) {
                if (!((Assurable) v).estAssurable()) {
                    throw new VehiculeNonAssurableException("Erreur : Ce vehicule n'est pas assurable.");
                }
            } else {
                throw new VehiculeNonAssurableException("Erreur : Ce type de vehicule ne supporte pas d'assurance.");
            }
        }
        Location loc = new Location(debut, fin, v, assurer);
        locations.add(loc);
    }

    public void retournerVehicule(Vehicule v) {
        for (int i = 0; i < locations.size(); i++) {
            if (locations.get(i).getVehicule().getImmatriculation().equals(v.getImmatriculation())) {
                locations.remove(i);
                break;
            }
        }
    }

    public double getTotalDepenses() {
        double total = 0;
        for (int i = 0; i < locations.size(); i++) {
            total += locations.get(i).getMontantTotal();
        }
        return total;
    }

    public void afficherHistorique() {
        System.out.println("Historique de " + nom + " :");
        if (locations.isEmpty()) {
            System.out.println("Aucune location en cours.");
        } else {
            for (int i = 0; i < locations.size(); i++) {
                locations.get(i).afficherFacture();
            }
        }
    }

    public String getId() { return id; }
    public String getNom() { return nom; }
    public boolean isaPermis() { return aPermis; }
    public List<Location> getLocations() { return locations; }
}
