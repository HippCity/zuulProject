import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Something" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Geert Perton
 * @version 2020.02.06
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList<Room> roomList = new ArrayList<Room>();
    private int index;
    private Room previousRoom;
    private Inventory inventory;
    private Fight fight;
    public static String language;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        SL.initialize();
        Room.createItems();
        language = "EN";
        parser = new Parser();
        inventory = new Inventory(20);
        fight = new Fight();
        //inventory.Inventory();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, receptionArea, waitingRoom, hallway, bathroom, mainLab, behavioralLab, biocontainmentRoom, office, storage, basement;
      
        // create the rooms
        outside = new Room("outside the main entrance of the Hawkins laboratory");
        receptionArea = new Room("in the reception area");
        waitingRoom = new Room("in the waiting room");
        hallway = new Room("in the hallway");
        bathroom = new Room("in the bathroom");
        mainLab = new Room("in the lab");
        behavioralLab = new Room("in the behavioral lab");
        biocontainmentRoom = new Room("in the biocontainment room");
        office = new Room("in the office");
        storage = new Room("in the storage room");
        basement = new Room("in the basement");
        
        // initialise room exits
        outside.setExit("north", receptionArea);
        //outside.setItem("axe", 1, true);

        receptionArea.setExit("north", hallway);
        receptionArea.setExit("south", outside);
        receptionArea.setExit("west", waitingRoom);
        //receptionArea.setItem("axe", 2, true);
        //receptionArea.setRandomItem();
        
        
        waitingRoom.setExit("east", receptionArea);
        //waitingRoom.setItem("axe", 13, true);

        hallway.setExit("north", mainLab);
        hallway.setExit("east", office);
        hallway.setExit("south", receptionArea);
        hallway.setExit("west", bathroom);
        //hallway.setRandomItem();
        
        bathroom.setExit("east", hallway);
        
        office.setExit("east", storage);
        office.setExit("west", hallway);
        
        storage.setExit("west", office);
        storage.setExit("down", basement);
        
        basement.setExit("up", storage);

        mainLab.setExit("north", behavioralLab);
        mainLab.setExit("east", biocontainmentRoom);
        mainLab.setExit("south", hallway);
        
        biocontainmentRoom.setExit("west", mainLab);
        behavioralLab.setExit("south", mainLab);

        currentRoom = outside;  // start game outside
        roomList.add(currentRoom);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over. tekst
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println(SL.getString("Thank you for playing.  Good bye."));
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println(SL.getString("Welcome to Hawkins laboratory!"));
        System.out.println(SL.getString("Hawkins laboratory is a new, incredibly boring adventure game."));
        System.out.println(SL.getString("Type '") + CommandWord.HELP + SL.getString("' if you need help."));
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println(SL.getString("I don't know what you mean..."));
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case BACK:
                goBack();
                break;
                
            case ABOUT:
                fightTester();
                break;
                
            case INVENTORY:
                inventory();
                break;
                
            case LANGUAGE:
                setLanguage(command);
                break;
            
            case PICKUP:
                pickUp(command);
                break;
                
            //case inspect:
            //    inspect(command);
            //    break;
        }
        return wantToQuit;
    }

    // implementations of user commands:
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public void fightTester()
    {
        fight.startFight(1);
    }

    
    /**
     * Picks up the item.
     */
    private void pickUp(Command command) 
    {
       //under construction
       if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(SL.getString("What item?"));
            return;
        }

       //String item = SL.getEnglishString(command.getSecondWord());

       // Try to leave current room.
       String itemString = SL.getItemString(command.getSecondWord());
       Item item = currentRoom.getItem(itemString);

       if (item == null) {
            System.out.println(SL.getString("That is not an item!"));
        }
        else if (currentRoom.getBoolean(item) == true) {
            if ((inventory.getInventorySpace() - currentRoom.getWeight(item)) >= 0) {
                //currentRoom = nextRoom;
            //System.out.println(currentRoom.getName(item));
            //System.out.println(currentRoom.getWeight(item));
            
            inventory.addItem(item);
            System.out.println(SL.getString("Picked up ") + currentRoom.getName(item));
            currentRoom.removeItem(item);
            
            }
            else {
                System.out.println(SL.getString("You do not have enough inventory space to pick up that item!"));
            }
        }
        else {
            System.out.println(SL.getString("You can not pick up that item!"));
        }
       //System.out.println("*insert information about set item*");
    }
    
    /**
     * Print out what items the inventory holds.
     */
    private void inspect(Command command) 
    {
        //under construction
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(SL.getString("What item?"));
            return;
        }

        //String item = SL.getEnglishString(command.getSecondWord());

        // Try to leave current room.
        String itemString = SL.getItemString(command.getSecondWord());
        Item item = currentRoom.getItem(itemString);

        if (item == null) {
            System.out.println(SL.getString("That is not an item!"));
        }
        else {
            //currentRoom = nextRoom;
            System.out.println(currentRoom.getName(item));
            System.out.println(currentRoom.getWeight(item));
        }
        //System.out.println("*insert information about set item*");
    }
    
    /**
     * Print out what items the inventory holds.
     */
    private void inventory() 
    {
        //under construction
        inventory.getInventory();
    }
    
    /**
     * Print out some information about the current room.
     * Like the name of the room, exits, items, etc.
     */
    private void about() 
    {
       currentRoom.printItem();
       //System.out.println("*insert information about the current room, or about the game*");
    }
    
    /**
     * Print out some back information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void goBack() 
    {
            if(roomList.size() >= 2){
            index = roomList.size() -2;
            previousRoom = roomList.get(index);
            currentRoom = previousRoom;
            System.out.println(currentRoom.getLongDescription());
            roomList.remove(index);
        }
        else {
            System.out.println(SL.getString("You need to move at least one room before being able to use the command back"));
        }
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        
        System.out.println(SL.getString("You are lost. You are alone. You wander"));
        System.out.println(SL.getString("around at the Hawkins laboratory."));
        System.out.println();
        System.out.println(SL.getString("Your command words are:"));
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(SL.getString("Go where?"));
            return;
        }

        String direction = SL.getEnglishString(command.getSecondWord());

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println(SL.getString("There is no door!"));
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            roomList.add(currentRoom);
        }
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void setLanguage(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(SL.getString("What language?"));
            return ;
        }

        String lang = command.getSecondWord();
        String taal = SL.getLanguage(lang);
        //String taal = "EN";

        if (language == taal) {
            System.out.println(SL.getString("You have already selected this language!"));
        }
        else {
            if (taal != null) {
                //System.out.println("The current language is: " + language);
                //System.out.println("The language is being set to: " + taal);
                language = taal;
                //System.out.println("Succesfully changed the language");
                System.out.println(SL.getString("The language now is: ") + language);
            }
            else {
                System.out.println(SL.getString("That language is not available, the available languages are: "));
                System.out.println(SL.getString("English EN"));
                System.out.println(SL.getString("Dutch NL"));
            }
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println(SL.getString("Quit what?"));
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
}
