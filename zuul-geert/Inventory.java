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
    private int x;
    private ArrayList<Item> inventoryList = new ArrayList<Item>();
    public String test = "de test string";
    
    /**
     * Constructor voor objects van class Inventory
     */
    public Inventory()
    {
        // hier moet nog een hele hoop gebeuren
        x = 0;
    }

    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return    deze method geeft de som van x en y terug
     */
    public ArrayList getInventory()
    {
        return inventoryList;
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
