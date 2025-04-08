package BibliothequeUniversitaire;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

class Livre extends Document {
    private List<String> auteurs;

    public Livre() {
        super();
        auteurs = new ArrayList<>();
    }

    public Livre(String titre, LocalDate datePublication, List<String> auteurs) {
        super(titre, datePublication);
        this.auteurs = auteurs;
    }

    @Override
    public String toString() {
        return "Livre [" + super.toString() + ", Auteurs: " + auteurs + "]";
    }
}
