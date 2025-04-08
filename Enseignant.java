package BibliothequeUniversitaire;
import java.time.LocalDate;

class Enseignant extends Adherent {
    private String departement;
    private String titreAcademique;

    public Enseignant() {
        super();
    }

    public Enseignant(String nom, String prenom, LocalDate dateInscription, 
                     String departement, String titreAcademique) {
        super(nom, prenom, dateInscription);
        this.departement = departement;
        this.titreAcademique = titreAcademique;
    }

    @Override
    public String toString() {
        return "Enseignant [" + super.toString() + ", Departement: " + departement 
               + ", Titre: " + titreAcademique + "]";
    }
}