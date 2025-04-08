package BibliothequeUniversitaire;

import java.time.LocalDate;
import java.util.List;

public class Gestion {
    public static void main(String[] args) {
        Bibliotheque biblio = new Bibliotheque();

        Livre livre1 = new Livre("Algorithmes", LocalDate.of(2020, 1, 1), List.of("Cormen"));
        Periodique periodique1 = new Periodique("Science Today", LocalDate.of(2024, 1, 1), Periodique.Frequence.MENSUELLE);
        
        biblio.ajouterDocument(livre1);
        biblio.ajouterDocument(periodique1);

        Etudiant etudiant = new Etudiant("Dupont", "Jean", LocalDate.now().minusMonths(6), "Informatique", 2);
        Enseignant enseignant = new Enseignant("Martin", "Pierre", LocalDate.now().minusYears(1), "Mathematiques", "Professeur");
        
        biblio.ajouterAdherent(etudiant);
        biblio.ajouterAdherent(enseignant);

        System.out.println("\n--- Scenario etudiant ---");
        biblio.emprunterDocument(etudiant, livre1);
        
        Emprunt empruntEtudiant = biblio.getEmprunts().get(0);
        empruntEtudiant.setDateEmprunt(LocalDate.now().minusDays(20));
        
        biblio.restituerDocument(empruntEtudiant.getExemplaire());
        biblio.afficherEtatAdherent(etudiant);

        System.out.println("\n--- Scenario enseignant ---");
        biblio.emprunterDocument(enseignant, periodique1);

        System.out.println("\n--- Paiement amende ---");
        ((Etudiant) etudiant).payerAmende(75);
        biblio.afficherEtatAdherent(etudiant);

        biblio.afficherListeDocuments();
        biblio.afficherListeAdherents();
        
        
        System.out.println("\n--- Gestion de la commande par le Bibliothecaire ---");
        Bibliotheque bibliotheque = new Bibliotheque();
        Bibliothecaire bibliothecaire = new Bibliothecaire("Pierre", "BIB123", bibliotheque);

        List<Livre> livresCommandes = List.of(
            new Livre("Clean Code", LocalDate.of(2008, 1, 1), List.of("Robert C. Martin")),
            new Livre("Design Patterns", LocalDate.of(1994, 1, 1), List.of("Gamma", "Helm"))
        );
        
        Commande commande = new Commande(enseignant, livresCommandes);
        bibliotheque.ajouterCommande(commande);

        List<Commande> enAttente = bibliotheque.getCommandesEnAttente();
        if(!enAttente.isEmpty()) {
            Commande premiereCommande = enAttente.get(0);
            
            bibliothecaire.traiterCommande(premiereCommande, true);
            
            System.out.println("Statut commande: " + premiereCommande.getStatut());
            System.out.println("Exemplaires ajoutes: " + bibliotheque.getExemplaires().size());
        }
    }
}
