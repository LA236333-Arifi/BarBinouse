package binouse.db;

import binouse.biere.Biere;
import java.sql.*;

/**
 * Classe pour gérer la connexion à la base de données SQL Server.
 * */
public class DbConnexion
{
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=BiereDB;encrypt=false;user=sa;password=1234";

    /**
     * Retourne la connexion à la DB.
     *
     * @return Connection vers la base de données SQL Server
     * @throws SQLException Si une erreur de connexion se produit
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static int ajouterBiere(Biere biere)
    {
        String sql = "INSERT INTO Bieres (nom, description, contenance, type, brasserie, quantite) VALUES (?, ?, ?, ?, ?, ?)";
        int GenID = -1;

        try {
            // On se connecte à la DB et on met en place la requete
            Connection conn = getConnection();
            PreparedStatement Statement = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            // On met en place les paramètres de la requête
            Statement.setString(1, biere.getNom());
            Statement.setString(2, biere.getDescription());
            Statement.setFloat(3, biere.getContenance());
            Statement.setInt(4, biere.getType());
            Statement.setInt(5, biere.getBrasserie());
            Statement.setInt(6, biere.getQuantite());

            // Exécuter l'insertion
            Statement.executeUpdate();

            // Récupérer l'ID généré avec un ResultSet
            ResultSet generatedKeys = Statement.getGeneratedKeys();
            if (generatedKeys.next())
            {
                GenID = generatedKeys.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return GenID;
    }

    /**
     * Récupère une bière de la base de données à partir de son ID.
     *
     * @param id L'identifiant de la bière à récupérer.
     * @return Un objet Biere avec les informations récupérées, ou null si aucune bière n'est trouvée.
     */
    public static Biere getBiereById(int id) {
        String sql = "SELECT nom, description, contenance, type, brasserie, quantite FROM Bieres WHERE id = ?";

        try {
            // On se connecte à la DB et on met en place la requete
            Connection conn = DriverManager.getConnection(URL);
            PreparedStatement Statement = conn.prepareStatement(sql);

            // Définit l'ID de la bière à récupérer
            Statement.setInt(1, id);

            // On recupere les résultats
            ResultSet rs = Statement.executeQuery();

            // Si un résultat est trouvé, on crée et on retourne l'objet Biere
            if (rs.next()) {
                // On recupère chaque attribut pour créer un object Biere
                String nom = rs.getString("nom");
                String description = rs.getString("description");
                float contenance = rs.getFloat("contenance");
                int type = rs.getInt("type");
                int brasserie = rs.getInt("brasserie");
                int quantite = rs.getInt("quantite");

                return new Biere(nom, description, contenance, type, brasserie, quantite);
            }
            else
            {
                System.out.println("Aucune bière trouvée avec l'ID " + id);
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la récupération de la bière : " + e.getMessage());
            return null;
        }
    }

    /**
     * Met à jour les informations d'une bière dans la base de données.
     *
     * @param id    L'identifiant de la bière à mettre à jour.
     * @param biere Un objet Biere contenant les nouvelles informations.
     */
    public static boolean mettreAJourBiere(int id, Biere biere) {
        String sql = "UPDATE Bieres SET nom = ?, description = ?, contenance = ?, type = ?, brasserie = ?, quantite = ? WHERE id = ?";
        boolean ret = false;
        try {
            // On se connecte à la DB et on met en place la requete
            Connection conn = DriverManager.getConnection(URL);
            PreparedStatement Statement = conn.prepareStatement(sql);

            // Définit les paramètres pour la requête
            Statement.setString(1, biere.getNom());
            Statement.setString(2, biere.getDescription());
            Statement.setFloat(3, biere.getContenance());
            Statement.setInt(4, biere.getType());
            Statement.setInt(5, biere.getBrasserie());
            Statement.setInt(6, biere.getQuantite());
            Statement.setInt(7, id);

            // Exécute la mise à jour
            int rowsUpdated = Statement.executeUpdate();
            if (rowsUpdated > 0)
            {
                System.out.println("La bière avec l'ID " + id + " a été mise à jour avec succès.");
                ret = true;
            }
            else
            {
                System.out.println("Aucune bière trouvée avec l'ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la mise à jour de la bière : " + e.getMessage());
            return false;
        }

        return ret;
    }

    /**
     * Supprime une bière de la base de données à partir de son ID.
     *
     * @param id L'identifiant de la bière à supprimer.
     */
    public static void deleteBiere(int id){
        String sql = "DELETE FROM Bieres WHERE id = ?";

        try {
            // On se connecte à la DB et on met en place la requete
            Connection conn = DriverManager.getConnection(URL);
            PreparedStatement Statement = conn.prepareStatement(sql);

            // Définit l'ID de la bière à supprimer
            Statement.setInt(1, id);

            // on execute le statement (ici, on va supprimer la biere)
            int rowsDeleted = Statement.executeUpdate();
            if (rowsDeleted > 0)
            {
                System.out.println("La bière avec l'ID " + id + " a été supprimée avec succès.");
            }
            else
            {
                System.out.println("Aucune bière trouvée avec l'ID " + id);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la bière : " + e.getMessage());
        }
    }
}
