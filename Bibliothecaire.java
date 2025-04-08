package BibliothequeUniversitaire;

public class Bibliothecaire {
	private static int nextId = 1;
	private String id;
    private String nom;
    private Bibliotheque bibliotheque;

    public Bibliothecaire() {
    	this.id = "BIBLIO" + nextId++;
    }
    public Bibliothecaire(String nom, String identifiant, Bibliotheque bibliotheque) {
    	this();
        this.nom = nom;
        this.bibliotheque = bibliotheque;
    }

    public void traiterCommande(Commande commande, boolean valider) {
        if(valider) {
            bibliotheque.validerCommande(commande);
            System.out.println("Commande " + commande.getId() + " validée !");
        } else {
            bibliotheque.refuserCommande(commande);
            System.out.println("Commande " + commande.getId() + " refusée.");
        }
    }
    
    public String getNom() { return nom; }
    
    @Override
    public String toString() {
    	return "ID: " + id + ", Nom: " + nom + " " + ", Bibliotheque: " + bibliotheque;
    }
    
}
