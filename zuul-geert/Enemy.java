
/**
 * class enemy - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Enemy
{
    // instance variables - vervang deze door jouw variabelen
    private int strength;
    private String name;
    private int level;
    private int health;

    /**
     * Constructor voor objects van class enemy
     */
    public Enemy(String name, int strength, int level, int health)
    {
        // geef de instance variables een beginwaarde
        this.name = name;
        this.strength = strength;
        this.level = level;
        this.health = health;
    }

    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     */
    public int getStrength()
    {
        return strength;
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     */
    public int getHealth()
    {
        return health;
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     */
    public int getLevel()
    {
        return level;
    }

}
