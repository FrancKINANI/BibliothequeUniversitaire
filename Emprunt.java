package BibliothequeUniversitaire;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

class Emprunt {
    private Exemplaire exemplaire;
    private Adherent adherent;
    private LocalDate dateEmprunt;
    private LocalDate dateRetour;

    public Emprunt() {}

    public Emprunt(Exemplaire exemplaire, Adherent adherent) {
        this.exemplaire = exemplaire;
        this.adherent = adherent;
        this.dateEmprunt = LocalDate.now();
        this.dateRetour = adherent instanceof Etudiant ? dateEmprunt.plusDays(15) : dateEmprunt.plusDays(30);
    }

    public int calculerRetard() {
        if (LocalDate.now().isAfter(dateRetour)) {
            return (int) ChronoUnit.DAYS.between(dateRetour, LocalDate.now());
        }
        return 0;
    }
    
    public Exemplaire getExemplaire() { return exemplaire; }
    public Adherent getAdherent() { return adherent; }
    public void setDateEmprunt(LocalDate date) { this.dateEmprunt = date; }

    @Override
    public String toString() {
        return "Emprunt [" + exemplaire + ", Adherent: " + adherent 
               + ", Retour prevu: " + dateRetour + "]";
    }
}