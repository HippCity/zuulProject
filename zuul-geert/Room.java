import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

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
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ArrayList<Item> itemList;

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
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public void setItem(String name, int weight)
    {
        Item bijl;
        bijl = new Item(name, weight);
        itemList.add(bijl);
        
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public void printItem()
    {
        if (itemList.size() != 0){
            System.out.println(SL.getString("This room contains:"));
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println(SL.getString("An ") + SL.getString(itemList.get(0).getName())
                + SL.getString(", weighing ")
                + itemList.get(0).getWeight() + " Kg");
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
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public String getName()
    {
        return itemList.get(0).getName();
    }
    
    public int getWeight()
    {
        return itemList.get(0).getWeight();
    }



}

