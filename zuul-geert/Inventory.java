import java.util.ArrayList;

/**
 * class Inventory - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Inventory
{
    // instance variables - vervang deze door jouw variabelen
    private ArrayList<Item> inventoryList;
    public String test;
    
    /**
     * Constructor voor objects van class Inventory
     */
    public Inventory()
    {
        // hier moet nog een hele hoop gebeuren
        inventoryList = new ArrayList<Item>();
        test = "de test string";
    }

    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return    deze method geeft de som van x en y terug
     */
    public void getInventory()
    {
        if (inventoryList.size() > 0) {
            System.out.println(SL.getString("Your inventory currently contains:"));
            for (int i = 0; i < inventoryList.size(); i++) {
              System.out.println(SL.getString("A(n) ") + SL.getString(inventoryList.get(i).getName()));
              //System.out.println(inventoryList.get(i).getWeight());
            }
            //return inventoryList;
        }
        else {
            System.out.println(SL.getString("Your inventory currently contains no items"));
        }
        
        
    }
    
    /**
     * methode
     */
    public void addItem(Item item)
    {
        inventoryList.add(item);
    }
    
    public String getTest()
    {
        return test;
    }
}
