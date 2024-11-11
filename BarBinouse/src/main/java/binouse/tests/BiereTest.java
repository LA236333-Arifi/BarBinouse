package binouse.tests;

import binouse.biere.Biere;

/**
 * Classe de test pour la classe Biere.
 */

public class BiereTest {

    public static void main(String[] args)
    {
        Biere biere = new Biere("Blonde", "Bière blonde légère", 0.33f, 1, 1001, 50);
        System.out.println(biere);
    }
}