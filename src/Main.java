import java.util.*;
import java.io.*;
import java.time.LocalDate;
import model.*;
import service.Parc;
import exceptions.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Parc<Vehicule> parc = new Parc<Vehicule>();
    static List<Client> clients = new ArrayList<Client>();

    public static void main(String[] args) {
        chargerDonnees();
        int choix = -1;
        while (choix != 0) {
            try {
                afficherMenu();
                System.out.print("Votre choix : ");
                choix = sc.nextInt();
                sc.nextLine(); 

                switch (choix) {
                    case 1:
                        ajouterVehicule();
                        break;
                    case 2:
                        parc.afficherTous();
                        break;
                    case 3:
                        ajouterClient();
                        break;
                    case 4:
                        louerVehicule();
                        break;
                    case 5:
                        retournerVehicule();
                        break;
                    case 6:
                        afficherLocations();
                        break;
                    case 7:
                        afficherDepenses();
                        break;
                    case 0:
                        sauvegarderDonnees();
                        System.out.println("Au revoir !");
                        break;
                    default:
                        System.out.println("Choix invalide.");
                }
            } catch (Exception e) {
                System.out.println("Erreur : " + e.getMessage());
                if (sc.hasNextLine()) sc.nextLine();
            }
        }
    }

    static void afficherMenu() {
        System.out.println("\n--- Menu Gestion Location ---");
        System.out.println("1. Ajouter un vehicule");
        System.out.println("2. Afficher les vehicules");
        System.out.println("3. Ajouter un client");
        System.out.println("4. Louer un vehicule");
        System.out.println("5. Retourner un vehicule");
        System.out.println("6. Afficher les locations");
        System.out.println("7. Afficher depenses");
        System.out.println("0. Quitter");
    }

    static void ajouterVehicule() {
        System.out.println("Type: 1. Voiture, 2. Moto, 3. Camion");
        int type = sc.nextInt(); sc.nextLine();
        System.out.print("Immatriculation : "); String im = sc.nextLine();
        System.out.print("Marque : "); String mq = sc.nextLine();
        System.out.print("Modele : "); String md = sc.nextLine();
        System.out.print("Prix de base : "); double p = sc.nextDouble();

        if (type == 1) {
            System.out.print("Nombre de portes : "); int dr = sc.nextInt();
            parc.ajouter(new Voiture(im, mq, md, p, dr));
        } else if (type == 2) {
            System.out.print("Cylindree : "); int cy = sc.nextInt();
            parc.ajouter(new Moto(im, mq, md, p, cy));
        } else if (type == 3) {
            System.out.print("Poids max : "); double w = sc.nextDouble();
            parc.ajouter(new Camion(im, mq, md, p, w));
        }
        System.out.println("Vehicule ajoute avec succes !");
    }

    static void ajouterClient() {
        System.out.print("ID Client : "); String id = sc.nextLine();
        System.out.print("Nom : "); String n = sc.nextLine();
        System.out.print("A un permis ? (oui/non) : ");
        boolean p = sc.nextLine().equalsIgnoreCase("oui");
        clients.add(new Client(id, n, p));
        System.out.println("Client ajoute avec succes !");
    }

    static void louerVehicule() throws Exception {
        System.out.print("ID Client : "); String id = sc.nextLine();
        Client c = trouverClient(id);
        if (c == null) {
            System.out.println("Client introuvable.");
            return;
        }

        System.out.print("Immatriculation du vehicule : "); String im = sc.nextLine();
        Vehicule v = parc.trouver(im);
        if (v == null) {
            System.out.println("Vehicule introuvable.");
            return;
        }

        for (int i = 0; i < clients.size(); i++) {
            List<Location> locs = clients.get(i).getLocations();
            for (int j = 0; j < locs.size(); j++) {
                if (locs.get(j).getVehicule().getImmatriculation().equals(im)) {
                    throw new VehiculeIndisponibleException("Erreur : Ce vehicule est deja loue.");
                }
            }
        }

        System.out.print("Nombre de jours : "); int nbJours = sc.nextInt(); sc.nextLine();
        System.out.print("Voulez-vous une assurance ? (oui/non) : ");
        boolean ass = sc.nextLine().equalsIgnoreCase("oui");

        c.louer(v, LocalDate.now(), LocalDate.now().plusDays(nbJours), ass);
        System.out.println("Location enregistree !");
    }

    static void retournerVehicule() {
        System.out.print("ID Client : "); String id = sc.nextLine();
        Client c = trouverClient(id);
        if (c == null) {
            System.out.println("Client introuvable.");
            return;
        }

        System.out.print("Immatriculation du vehicule : "); String im = sc.nextLine();
        Vehicule v = parc.trouver(im);
        if (v != null) {
            c.retournerVehicule(v);
            System.out.println("Le vehicule a ete retourne.");
        } else {
            System.out.println("Vehicule non trouve dans le parc.");
        }
    }

    static void afficherLocations() {
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).afficherHistorique();
        }
    }

    static void afficherDepenses() {
        System.out.print("ID Client : "); String id = sc.nextLine();
        Client c = trouverClient(id);
        if (c != null) {
            System.out.println("Total des depenses pour " + c.getNom() + " : " + c.getTotalDepenses() + " DT");
        } else {
            System.out.println("Client non trouve.");
        }
    }

    static Client trouverClient(String id) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getId().equals(id)) {
                return clients.get(i);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    static void chargerDonnees() {
        File f = new File("agence.dat");
        if (!f.exists()) return;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            parc = (Parc<Vehicule>) ois.readObject();
            clients = (List<Client>) ois.readObject();
            ois.close();
        } catch (Exception e) {}
    }

    static void sauvegarderDonnees() {
        try {
            FileOutputStream fos = new FileOutputStream("agence.dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(parc);
            oos.writeObject(clients);
            oos.close();
        } catch (Exception e) {}
    }
}
