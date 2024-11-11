package binouse.app;

import binouse.stock.Stock;
import binouse.biere.Biere;
import java.util.Scanner;

/**
* Classe avec une interface texte pour les opérations CRUD
*
* */
public class Application
{
    public static void main(String[] args) {
        Stock stock = new Stock(); // Nouveau stock de bieres
        Scanner scanner = new Scanner(System.in); // Classe pour gérer l'input

        // Boucle infinie pour le CRUD
        while (true) {
            System.out.println("\n1. Ajouter une bière");
            System.out.println("2. Lire une bière");
            System.out.println("3. Mettre à jour une bière");
            System.out.println("4. Supprimer une bière");
            System.out.println("5. Afficher le stock");
            System.out.println("6. Quitter l'application");
            System.out.print("Choisissez une option : ");

            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                {
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Description : ");
                    String description = scanner.nextLine();
                    System.out.print("Contenance : ");
                    float contenance = scanner.nextFloat();
                    System.out.print("Type : ");
                    int type = scanner.nextInt();
                    System.out.print("Brasserie : ");
                    int brasserie = scanner.nextInt();
                    System.out.print("Quantité : ");
                    int quantite = scanner.nextInt();

                    stock.ajouterBiere(new Biere(nom, description, contenance, type, brasserie, quantite));
                    break;
                }
                case 2:
                {
                    System.out.print("Quelle biere voulez vous lire ? ");
                    int ID = scanner.nextInt();
                    Biere BiereALire = stock.lireBiere(ID);
                    if (BiereALire != null)
                    {
                        System.out.println(BiereALire);
                    }
                    else
                    {
                        System.out.print("Aucune bière trouvée avec cet id.");
                    }
                    break;
                }
                case 3:
                {
                    System.out.print("Quelle biere voulez vous mettre à jour ? ");
                    int ID = scanner.nextInt();

                    System.out.println("Définissez la nouvelle bière : ");
                    System.out.print("Nom : ");
                    String nom = scanner.nextLine();
                    System.out.print("Description : ");
                    String description = scanner.nextLine();
                    System.out.print("Contenance : ");
                    float contenance = scanner.nextFloat();
                    System.out.print("Type : ");
                    int type = scanner.nextInt();
                    System.out.print("Brasserie : ");
                    int brasserie = scanner.nextInt();
                    System.out.print("Quantité : ");
                    int quantite = scanner.nextInt();

                    Biere biereUpdate = new Biere(nom, description, contenance, type, brasserie, quantite);
                    stock.mettreAJourBiere(ID, biereUpdate);
                    break;
                }
                case 4:
                {
                    System.out.print("Quelle biere voulez vous supprimer ? ");
                    int ID = scanner.nextInt();
                    stock.supprimerBiere(ID);

                    System.out.println("Bière supprimée");
                    break;
                }
                case 5:
                {
                    System.out.println(stock);
                    break;
                }
                case 6:
                {
                    System.exit(0);
                    break;
                }
            }
        }
    }
}
