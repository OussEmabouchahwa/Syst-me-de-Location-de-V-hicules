package service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.Vehicule;

public class Parc<T extends Vehicule> implements Serializable {
    private List<T> listeVehicules;

    public Parc() {
        this.listeVehicules = new ArrayList<>();
    }

    public void ajouter(T v) {
        listeVehicules.add(v);
    }

    public void supprimer(String immat) {
        for (int i = 0; i < listeVehicules.size(); i++) {
            if (listeVehicules.get(i).getImmatriculation().equals(immat)) {
                listeVehicules.remove(i);
                break;
            }
        }
    }

    public T trouver(String immat) {
        for (int i = 0; i < listeVehicules.size(); i++) {
            T v = listeVehicules.get(i);
            if (v.getImmatriculation().equals(immat)) {
                return v;
            }
        }
        return null;
    }

    public void afficherTous() {
        if (listeVehicules.isEmpty()) {
            System.out.println("Aucun vehicule dans le parc.");
        } else {
            for (int i = 0; i < listeVehicules.size(); i++) {
                listeVehicules.get(i).afficherInfos();
            }
        }
    }

    public List<T> getListeVehicules() {
        return listeVehicules;
    }
    
    public List<T> getVehiculesDisponibles(List<model.Location> allLocations) {
        List<T> dispo = new ArrayList<>();
        for (T v : listeVehicules) {
            boolean loue = false;
            for (model.Location loc : allLocations) {
                if (loc.getVehicule().getImmatriculation().equals(v.getImmatriculation())) {
                    loue = true;
                    break;
                }
            }
            if (!loue) dispo.add(v);
        }
        return dispo;
    }
}
