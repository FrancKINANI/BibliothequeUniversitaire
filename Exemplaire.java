package BibliothequeUniversitaire;
import java.time.LocalDate;

class Exemplaire {
    private static int nextId = 1;
    private String idUnique;
    private String statut;
    private LocalDate dateAchat;
    private Livre livre;

    public Exemplaire() {
        this.idUnique = "EX" + nextId++;
        this.statut = "Neuf";
    }

    public Exemplaire(Livre livre, LocalDate dateAchat) {
        this();
        this.livre = livre;
        this.dateAchat = dateAchat;
    }
    
    public Livre getLivre() { return livre;}

    @Override
    public String toString() {
        return "Exemplaire [ID: " + idUnique + ", Statut: " + statut 
               + ", Date achat: " + dateAchat + ", " + livre + "]";
    }
}
