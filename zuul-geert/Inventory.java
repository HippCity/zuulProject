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
              System.out.println(SL.getString("A(n) ") + SL.getString(inventoryList.get(i).getName()));
              System.out.println(SL.getString("weight: ") + inventoryList.get(i).getWeight());
              System.out.println(SL.getString("damage: ") + inventoryList.get(i).getDamage());
              System.out.println("");
              //System.out.println(inventoryList.get(i).getWeight());
            }
            
            //if (getEquipedItem() != null) {
                
            //}
            //System.out.println("");
            //System.out.println(SL.getString("You have ") + getIntInventorySpace() + SL.getString(" KG of space left"));
            //return inventoryList;
        }
        else {
            System.out.println(SL.getString("Your inventory currently contains no items"));
        }
        
        
    }
    
    public void getInventorySpace()
    {
        System.out.println(SL.getString("You have ") + getIntInventorySpace() + SL.getString(" KG of space left"));
    }
    
    /**
     * methode
     */
    public void addItem(Item item)
    {
        inventoryList.add(item);
        inventorySpace = inventorySpace + item.getWeight();
    }
    
    
    public void removeItem(Item item)
    {
        inventorySpace = inventorySpace - item.getWeight();
        inventoryList.remove(item);
        
    }
    
    public int getIntInventorySpace()
    {
        int integer = inventorySize - inventorySpace;
        return integer;
    }
    
    public Item getItem(String theName)
    {
        for (int i = 0; i < inventoryList.size(); i++) {
             String nameList = inventoryList.get(i).getName();
             if (nameList == theName){
                 return inventoryList.get(i);
                }
             //System.out.println(itemList.get(i));
        }
        //return itemList.get(0);
        return null;
    }
    
}
