package BibliothequeUniversitaire;
import java.time.LocalDate;

abstract class Adherent {
    private static int nextId = 1;
    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateInscription;

    public Adherent() {
        this.id = "ADH" + nextId++;
        this.dateInscription = LocalDate.now();
    }

    public Adherent(String nom, String prenom, LocalDate dateInscription) {
        this();
        this.nom = nom;
        this.prenom = prenom;
        this.dateInscription = dateInscription;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nom: " + nom + " " + prenom 
               + ", Inscription: " + dateInscription;
    }
}
