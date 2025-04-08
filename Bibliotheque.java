package BibliothequeUniversitaire;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Bibliotheque {
    private List<Document> documents = new ArrayList<>();
    private List<Exemplaire> exemplaires = new ArrayList<>();
    private List<Adherent> adherents = new ArrayList<>();
    private List<Emprunt> emprunts = new ArrayList<>();
    private List<Commande> commandes = new ArrayList<>();

    public void ajouterDocument(Document doc) {
        documents.add(doc);
        if (doc instanceof Livre) {
            exemplaires.add(new Exemplaire((Livre) doc, LocalDate.now()));
        }
    }

    public void ajouterAdherent(Adherent adherent) {
        adherents.add(adherent);
    }

    public boolean emprunterDocument(Adherent adherent, Document document) {
        if (!(document instanceof Livre)) {
            System.out.println("Les periodiques ne peuvent pas etre empruntes");
            return false;
        }

        if (adherent instanceof Etudiant && ((Etudiant) adherent).getAmende() > 0) {
            System.out.println("Emprunt impossible: amende impayee");
            return false;
        }

        long nbEmprunts = emprunts.stream()
            .filter(e -> e.getAdherent().equals(adherent))
            .count();
            
        if (nbEmprunts >= 3) {
            System.out.println("Emprunt impossible: quota atteint (3)");
            return false;
        }

        Exemplaire exemplaireDispo = exemplaires.stream()
            .filter(e -> e.getLivre().equals(document))
            .filter(e -> !estEmprunte(e))
            .findFirst()
            .orElse(null);

        if (exemplaireDispo == null) {
            System.out.println("Aucun exemplaire disponible");
            return false;
        }

        emprunts.add(new Emprunt(exemplaireDispo, adherent));
        return true;
    }

    private boolean estEmprunte(Exemplaire ex) {
        return emprunts.stream()
            .anyMatch(e -> e.getExemplaire().equals(ex));
    }

    public void restituerDocument(Exemplaire exemplaire) {
        Emprunt emprunt = emprunts.stream()
            .filter(e -> e.getExemplaire().equals(exemplaire))
            .findFirst()
            .orElse(null);

        if (emprunt != null) {
            int retard = emprunt.calculerRetard();
            
            if (retard > 0 && emprunt.getAdherent() instanceof Etudiant) {
                Etudiant etudiant = (Etudiant) emprunt.getAdherent();
                etudiant.appliquerAmende(retard);
                System.out.println("Amende appliquee: " + (retard * 15) + " MAD");
            }
            
            emprunts.remove(emprunt);
        }
    }

    public void afficherEtatAdherent(Adherent adherent) {
        System.out.println(adherent);
        if (adherent instanceof Etudiant) {
            double amende = ((Etudiant) adherent).getAmende();
            System.out.println("Statut: " + (amende > 0 ? "BLOQUE" : "ACTIF"));
            System.out.println("Amende actuelle: " + amende + " MAD");
        } else {
            System.out.println("Statut: ACTIF");
        }
    }

    public void afficherListeDocuments() {
        System.out.println("\n=== Documents de la bibliotheque ===");
        documents.forEach(System.out::println);
    }

    public void afficherListeAdherents() {
        System.out.println("\n=== Adherents de la bibliotheque ===");
        adherents.forEach(System.out::println);
    }

    public List<Exemplaire> getExemplaires() { return exemplaires; }
    public List<Emprunt> getEmprunts() { return emprunts; }

    
    public void ajouterCommande(Commande commande) {
        if(commande.getDemandeur() instanceof Enseignant) {
            commandes.add(commande);
        }
    }

    public List<Commande> getCommandesEnAttente() {
        return commandes.stream()
                .filter(c -> c.getStatut() == Commande.Statut.EN_ATTENTE)
                .collect(Collectors.toList());
    }

    public void validerCommande(Commande commande) {
        commande.setStatut(Commande.Statut.VALIDEE);
        for(Livre livre : commande.getLivres()) {
            exemplaires.add(new Exemplaire(livre, LocalDate.now()));
        }
    }

    public void refuserCommande(Commande commande) {
        commande.setStatut(Commande.Statut.REFUSEE);
    }
}
