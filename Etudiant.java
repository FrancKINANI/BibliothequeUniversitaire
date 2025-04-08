package BibliothequeUniversitaire;
import java.time.LocalDate;

class Etudiant extends Adherent {
    private String filiere;
    private int anneeEtude;
    private double amende;

    public Etudiant() {
        super();
    }

    public Etudiant(String nom, String prenom, LocalDate dateInscription, 
                   String filiere, int anneeEtude) {
        super(nom, prenom, dateInscription);
        this.filiere = filiere;
        this.anneeEtude = anneeEtude;
    }

    public void appliquerAmende(int joursRetard) {
        this.amende += joursRetard * 15;
    }
    
    public double getAmende() { return amende; }
    public void payerAmende(double montant) { 
        amende = Math.max(0, amende - montant); 
    }

    @Override
    public String toString() {
        return "Etudiant [" + super.toString() + ", Filiere: " + filiere 
               + ", Annee: " + anneeEtude + ", Amende: " + amende + "]";
    }
}
