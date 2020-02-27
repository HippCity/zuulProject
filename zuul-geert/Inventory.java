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
    //public String test;
    private int inventorySize;
    private int inventorySpace;
    
    /**
     * Constructor voor objects van class Inventory
     */
    public Inventory(int size)
    {
        // hier moet nog een hele hoop gebeuren
        inventorySize = size;
        inventorySpace = 0;
        inventoryList = new ArrayList<Item>();
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
              System.out.println(SL.getString("A(n) ") + SL.getString(inventoryList.get(i).getName())
               + SL.getString(", weighing ") + inventoryList.get(i).getWeight());
               
              //System.out.println(inventoryList.get(i).getWeight());
            }
            System.out.println("");
            System.out.println(SL.getString("You have ") + getInventorySpace() + SL.getString(" KG of space left"));
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
        inventorySpace = inventorySpace + item.getWeight();
    }
    
    public int getInventorySpace()
    {
        int integer = inventorySize - inventorySpace;
        return integer;
    }
    
}
