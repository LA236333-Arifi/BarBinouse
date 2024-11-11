package binouse.stock;

import binouse.db.DbConnexion;
import binouse.biere.Biere;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe Stock contenant une Map de bières avec un ID incrementé par entrée.
 */
public class Stock {
    private Map<Integer, Biere> bieres;

    public Stock() {
        bieres = new HashMap<>();
    }

    /**
     * Ajoute une nouvelle bière dans la base de données et la stocke dans le map des bières.
     *
     * @param biere L'objet Biere contenant les informations de la bière à ajouter.
     * @return L'ID généré pour la bière ajoutée.
     */
    public int ajouterBiere(Biere biere)
    {
        int ID = DbConnexion.ajouterBiere(biere);
        bieres.put(ID, biere);
        return ID;
    }

    /**
     * Lit une bière en fonction de son ID. Si elle est présente dans le map local, elle est retournée directement.
     * Sinon, elle est récupérée depuis la base de données et ajoutée au map si elle existe.
     *
     * @param id L'identifiant de la bière à lire.
     * @return L'objet Biere correspondant à l'ID, ou null si aucune bière n'est trouvée.
     */
    public Biere lireBiere(int id) {
        // On retourne la bière si elle est déjà dans le map
        Biere biere = bieres.get(id);
        if (biere != null)
        {
            return biere;
        }

        // Sinon on va la chercher dans la DB et voir si elle existe la bas.
        biere = DbConnexion.getBiereById(id);
        if (biere != null)
        {
            bieres.put(id, biere);
        }

        return biere;
    }

    /**
     * Met à jour les informations d'une bière spécifique dans la base de données et le map local.
     *
     * @param id L'identifiant de la bière à mettre à jour.
     * @param biere L'objet Biere contenant les nouvelles informations de la bière.
     */
    public void mettreAJourBiere(int id, Biere biere) {
        if (DbConnexion.mettreAJourBiere(id, biere))
        {
            bieres.put(id, biere);
        }
    }

    /**
     * Supprime une bière de la base de données et du map local.
     *
     * @param id L'identifiant de la bière à supprimer.
     */
    public void supprimerBiere(int id) {
        bieres.remove(id);
        DbConnexion.deleteBiere(id);
    }

    public void ajouterStock(int id, int quantite) {
        Biere biere = bieres.get(id);
        if (biere != null)
        {
            biere.setQuantite(biere.getQuantite() + quantite);
        }
    }

    public void enleverStock(int id, int quantite) {
        Biere biere = bieres.get(id);
        if (biere != null)
        {
            int nouvelleQuantite = biere.getQuantite() - quantite;
            if (nouvelleQuantite < 0)
            {
                nouvelleQuantite = 0;
            }
            biere.setQuantite(nouvelleQuantite);
            checkStock(id);
        }
    }

    private void checkStock(int id)
    {
        Biere biere = bieres.get(id);
        if (biere != null)
        {
            if (biere.getQuantite() <= 0)
            {
                bieres.remove(id);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Catalogue des bières :\n");
        for (Map.Entry<Integer, Biere> entry : bieres.entrySet()) {
            sb.append("ID: ").append(entry.getKey())
                    .append(" - ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }
}