package BibliothequeUniversitaire;
import java.time.LocalDate;

class Periodique extends Document {
    public enum Frequence { MENSUELLE, TRIMESTRIELLE, ANNUELLE }
    private Frequence frequence;

    public Periodique() {
        super();
    }

    public Periodique(String titre, LocalDate datePublication, Frequence frequence) {
        super(titre, datePublication);
        this.frequence = frequence;
    }

    @Override
    public String toString() {
        return "Periodique [" + super.toString() + ", Frequence: " + frequence + "]";
    }
}