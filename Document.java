package BibliothequeUniversitaire;
import java.time.LocalDate;

abstract class Document {
    private String titre;
    private LocalDate datePublication;

    public Document() {}

    public Document(String titre, LocalDate datePublication) {
        this.titre = titre;
        this.datePublication = datePublication;
    }

    @Override
    public String toString() {
        return "Titre: " + titre + ", Date publication: " + datePublication;
    }
}