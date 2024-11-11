package binouse.tests;

import binouse.stock.Stock;
import binouse.biere.Biere;

//@Test
public class StockTest {
    public void testAjouterEtLireBiere() {
        Stock stock = new Stock();
        Biere biere = new Biere("Blonde", "Bière blonde légère", 0.33f, 1, 1001, 50);
        stock.ajouterBiere(biere);

        System.out.println(stock);
    }
}
