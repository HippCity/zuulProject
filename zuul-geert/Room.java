import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Room 
{
    private String description;
    private int level;
    private int key;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> itemList;
    private static ArrayList<String> names;
    Random rand = new Random();
    private int randomeInteger;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        itemList = new ArrayList<Item>();
        setRandomCountItem();
        key = 0;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }
    
    public int getKey()
    {
        return key;
    }
    
    public void setKey(int key)
    {
        this.key = key;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return SL.getString("You are ") + SL.getString(description) + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = SL.getString("Exits:");
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + SL.getString(exit);
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return  deze method geeft de som van x en y terug
     */
    public void setItem(String name, int weight, boolean yes, int damage)
    {
        Item couch;
        couch = new Item(name, weight, yes, damage);
        itemList.add(couch);
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return  deze method geeft de som van x en y terug
     */
    public void printItem()
    {
        if (itemList.size() != 0){
            System.out.println(SL.getString("This room contains:"));
            for (int i = 0; i < itemList.size(); i++) {
                if (itemList.get(i).getPickup() == true) {
                  System.out.println(SL.getString("A(n) ") + SL.getString(itemList.get(i).getName())
                  + SL.getString(", weight: ")
                  + itemList.get(i).getWeight() + " Kg");
                }
                else {
                  System.out.println(SL.getString("A(n) ") + SL.getString(itemList.get(i).getName())
                  + SL.getString(", weighing too much to pick up"));
                }
            }
        }
        else {
            System.out.println(SL.getString("This room does not contain any items"));
        }
        
        //System.out.println(itemList.get(0).getName());
        //System.out.println(itemList.get(0).getWeight());
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return  deze method geeft de som van x en y terug
     */
    public String getName(Item name)
    {
        return name.getName();
    }
    
    public int getWeight(Item name)
    {
        return name.getWeight();
    }
    
    public boolean getBoolean(Item name)
    {
        return name.getPickup();
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return  deze method geeft de som van x en y terug
     */
    public void setRandomItem()
    {
        createItems();
        //String itemName = names.get(randomeInteger);
        if (itemList.size() >= 1) {
            randomeInteger = rand.nextInt(names.size());
            Item item2 = new Item(names.get(randomeInteger), rand.nextInt(6) + 1, true, rand.nextInt(5));
            itemList.add(item2);
        }
        
        if (itemList.size() == 0) {
            randomeInteger = rand.nextInt(names.size());
            Item item1 = new Item(names.get(randomeInteger), rand.nextInt(6) + 1, true, rand.nextInt(5));
            itemList.add(item1);
        }
    }
    
    public void addItem(Item item)
    {
        itemList.add(item);
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return  deze method geeft de som van x en y terug
     */
    public void setRandomCountItem()
    {
        int integer = rand.nextInt(2);
        //System.out.println("The randome integer is " + integer);
        for (int i = 0; i < integer; i++) {
          setRandomItem();
        }
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return  deze method geeft de som van x en y terug
     */
    public static void createItems()
    {
        names = new ArrayList<String>();
        names.add("axe");
        //names.add("key");
        names.add("stick");
        names.add("screw");
        names.add("hammer");
        names.add("crowbar");
        names.add("ductape");
        names.add("knife");
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return  deze method geeft de som van x en y terug
     */
    public Item getItem(String theName)
    {
        for (int i = 0; i < itemList.size(); i++) {
             String nameList = itemList.get(i).getName();
             if (nameList == theName){
                 return itemList.get(i);
                }
             //System.out.println(itemList.get(i));
        }
        //return itemList.get(0);
        return null;
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public String getThatName()
    {
        // schrijf hier jouw code
        return "item1";
    }
    
    public void newItem(int level, String enemyName)
    {
        
        //createItems();
        randomeInteger = rand.nextInt(names.size());
        Item item1 = new Item(names.get(randomeInteger), rand.nextInt(6) + 1, true, rand.nextInt(5 + level));
        addItem(item1);
        System.out.println(SL.getString("The ") + enemyName + SL.getString(" dropped a(n) ") + item1.getName());
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public void removeItem(Item item)
    {
        itemList.remove(item);
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public void setLevel(int level)
    {
        this.level = level;
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public int getLevel()
    {
        return level;
    }


    



}

