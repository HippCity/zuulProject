import java.util.ArrayList;

/**
 * class Item - defines what an item is
 *
 * @author Geert Perton
 * @version 2-13-2020
 */
public class Item
{
    // instance variables - vervang deze door jouw variabelen
    private String name;
    private int weight;
    boolean pickup;
    

    /**
     * Constructor voor objects van class Item
     */
    public Item(String name, int weight, boolean pickup)
    {
        this.name = name;
        this.weight = weight;
        this.pickup = pickup;
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public String getName()
    {
        // schrijf hier jouw code
        return name;
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public int getWeight()
    {
        // schrijf hier jouw code
        return weight;
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public boolean getPickup()
    {
        // schrijf hier jouw code
        return pickup;
    }

    
    


}
