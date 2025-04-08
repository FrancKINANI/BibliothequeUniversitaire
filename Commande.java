package BibliothequeUniversitaire;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

class Commande {
    public enum Statut { EN_ATTENTE, VALIDEE, REFUSEE }
    
    private static int nextId = 1;
    private String id;
    private Enseignant demandeur;
    private List<Livre> livres;
    private Statut statut;
    private LocalDate dateCommande;

    public Commande() {
        this.id = "CMD" + nextId++;
        this.statut = Statut.EN_ATTENTE;
    }

    public Commande(Enseignant demandeur, List<Livre> livres) {
        this();
        this.demandeur = demandeur;
        this.livres = new ArrayList<>(livres);
        this.demandeur = demandeur;
        this.dateCommande = LocalDate.now();
    }

    public String getId() { return id; }
    public Enseignant getDemandeur() { return demandeur; }
    public List<Livre> getLivres() { return new ArrayList<>(livres); }
    public Statut getStatut() { return statut; }
    public LocalDate getDateCommande() { return dateCommande; }

    protected void setStatut(Statut statut) { 
        this.statut = statut; 
    }

    @Override
    public String toString() {
        return "Commande " + id + " - " + statut + 
               "\nDemandeur: " + demandeur +
               "\nLivres: " + livres;
    }
}

