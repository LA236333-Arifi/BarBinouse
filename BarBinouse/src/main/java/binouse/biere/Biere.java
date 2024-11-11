package binouse.biere;
public class Biere
{
    private String nom;
    private String description;
    private float contenance;
    private int type;
    private int brasserie;
    private int quantite;

    /**
     * Constructeur pour instancer une Biere.
     *
     * @param nom Le nom de la bière
     * @param description La description de la bière
     * @param contenance La contenance en litres
     * @param type Le type de bière (int pour l'exemple)
     * @param brasserie L'identifiant de la brasserie
     * @param quantite La quantité en stock
     */
    public Biere(String nom, String description, float contenance, int type, int brasserie, int quantite) {
        this.nom = nom;
        this.description = description;
        this.contenance = contenance;
        this.type = type;
        this.brasserie = brasserie;
        this.quantite = quantite;
    }

    // Getters et setters
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public float getContenance() { return contenance; }
    public void setContenance(float contenance) { this.contenance = contenance; }

    public int getType() { return type; }
    public void setType(int type) { this.type = type; }

    public int getBrasserie() { return brasserie; }
    public void setBrasserie(int brasserie) { this.brasserie = brasserie; }

    public int getQuantite() { return quantite; }
    public void setQuantite(int quantite) { this.quantite = quantite; }

    @Override
    public String toString() {
        return "Biere : { " +
                "nom= '" + nom + '\'' +
                ", description= '" + description + '\'' +
                ", contenance= " + contenance +
                ", type= " + type +
                ", brasserie= " + brasserie +
                ", quantite= " + quantite +
                " }";
    }
}