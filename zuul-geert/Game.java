import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
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
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
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

        receptionArea.setExit("north", hallway);
        receptionArea.setExit("south", outside);
        receptionArea.setExit("west", waitingRoom);
        
        waitingRoom.setExit("east", receptionArea);

        hallway.setExit("north", mainLab);
        hallway.setExit("east", office);
        hallway.setExit("south", receptionArea);
        hallway.setExit("west", bathroom);
        
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
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to Hawkins laboratory!");
        System.out.println("Hawkins laboratory is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
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
                System.out.println("I don't know what you mean...");
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
                about();
                break;
                
            case INVENTORY:
                inventory();
                break;
                
            //case inspect:
            //    inspect(item);
            //    break;
        }
        return wantToQuit;
    }

    // implementations of user commands:
    
    /**
     * Print out what items the inventory holds.
     */
    private void pickUp() 
    {
       //under construction
       System.out.println("*pick up the item*");
    }
    
    /**
     * Print out what items the inventory holds.
     */
    private void inspect(Item item) 
    {
        //under construction
        System.out.println("*insert information about set item*");
    }
    
    /**
     * Print out what items the inventory holds.
     */
    private void inventory() 
    {
        //under construction
        System.out.println("Your inventory currently contains:");
        //System.out.println(getTest);
    }
    
    /**
     * Print out some information about the current room.
     * Like the name of the room, exits, items, etc.
     */
    private void about() 
    {
       System.out.println("*insert information about the current room, or about the game*");
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
            System.out.println("You need to move at least one room before being able to use the command back");
        }
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the Hawkins laboratory.");
        System.out.println();
        System.out.println("Your command words are:");
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
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
            roomList.add(currentRoom);
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
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
