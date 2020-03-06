import java.util.Random;
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
    private ArrayList<Item> equipedItem = new ArrayList<Item>();
    private int index;
    private Room previousRoom;
    private Inventory inventory;
    private Fight fight;
    private boolean wantToQuit;
    private int chance = 0;
    public static String language;

    Random rand = new Random();

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
        fight = new Fight(20);
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
        outside.setLevel(1);
        //outside.setItem("axe", 1, true);

        receptionArea.setExit("north", hallway);
        receptionArea.setExit("south", outside);
        receptionArea.setExit("west", waitingRoom);
        receptionArea.setLevel(1);
        //receptionArea.setItem("axe", 2, true);
        //receptionArea.setRandomItem();

        waitingRoom.setExit("east", receptionArea);
        waitingRoom.setLevel(1);
        //waitingRoom.setItem("axe", 13, true);

        hallway.setExit("north", mainLab);
        hallway.setExit("east", office);
        hallway.setExit("south", receptionArea);
        hallway.setExit("west", bathroom);
        hallway.setLevel(2);
        //hallway.setRandomItem();

        bathroom.setExit("east", hallway);
        bathroom.setLevel(2);

        office.setExit("east", storage);
        office.setExit("west", hallway);
        office.setLevel(2);
        office.setItem("key", 1, true, 1);

        storage.setExit("west", office);
        storage.setExit("down", basement);
        storage.setLevel(2);

        basement.setExit("up", storage);
        basement.setLevel(6);
        basement.setKey(1);

        mainLab.setExit("north", behavioralLab);
        mainLab.setExit("east", biocontainmentRoom);
        mainLab.setExit("south", hallway);
        mainLab.setLevel(3);

        biocontainmentRoom.setExit("west", mainLab);
        biocontainmentRoom.setLevel(4);

        behavioralLab.setExit("south", mainLab);
        behavioralLab.setLevel(5);
        behavioralLab.setItem("grenade", 1, true, 1);

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
        System.out.println(SL.getString("Your objective is to find and close the portal to the upside down "));
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
        wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();
        if (fight.inFight == false) {
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
                about();
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

                case EQUIP:
                equip(command);
                break;

                case DROP:
                drop(command);
                break;

                default:
                System.out.println(SL.getString("You need to be in a fight to use that command"));

                //case inspect:
                //    inspect(command);
                //    break;

            }
        }
        else {
            switch (commandWord) {

                case UNKNOWN:
                System.out.println(SL.getString("I don't know what you mean..."));
                break;

                case HELP:
                printFightHelp();
                break;

                case QUIT:
                wantToQuit = quit(command);
                break;

                case EQUIP:
                equip(command);
                break;

                case STAB:
                stab(command);
                break;

                case BLOCK:
                block(command);
                break;

                case PUNCH:
                punch(command);
                break;

                default:
                System.out.println(SL.getString("You can't use that command when in a fight"));

            }
        }
        return wantToQuit;

    }

    // implementations of user commands:

    private void temporary()
    {
        //currentRoom.newItem(1);
    }

    private void drop(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(SL.getString("What item?"));
            return;
        }

        //String item = SL.getEnglishString(command.getSecondWord());

        // Try to leave current room.
        String itemString = SL.getItemString(command.getSecondWord());
        Item item = inventory.getItem(itemString);

        if (item == null) {
            System.out.println(SL.getString("That is not an item!"));
        }
        else {
            currentRoom.addItem(item);
            System.out.println(SL.getString("Dropped: ") + SL.getString(currentRoom.getName(item)));
            inventory.removeItem(item);
        }
        //else {
        //    System.out.println(SL.getString("You can not pick up that item!"));
        //}
    }

    private void equip(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println(SL.getString("What item?"));
            return;
        }

        String itemString = SL.getItemString(command.getSecondWord());
        Item item = inventory.getItem(itemString);

        if (item == null) {
            System.out.println(SL.getString("That is not an item!"));
        }
        else if (equipedItem.size() < 1) {
            //currentRoom = nextRoom;
            //System.out.println(currentRoom.getName(item));
            //System.out.println(currentRoom.getWeight(item));

            //inventory.addItem(item);
            System.out.println(SL.getString("Current weapon: ") + SL.getString(item.getName()));
            equipedItem.add(item);
            //currentRoom.removeItem(item);

        }
        else {
            System.out.println(SL.getString("Currenet weapon: ") + SL.getString(item.getName()));
            equipedItem.set(0, item);
            //System.out.println(SL.getString("you already have an item equiped"));
        }
    }

    private void checkItemDrop()
    {
        if (fight.itemDrop == true) {
            currentRoom.newItem(fight.getEnemyName().getLevel(), fight.getEnemyName().getName());
            fight.itemDrop = false;
        }
    }

    private void checkQuit(Command command)
    {
        if (fight.quit == true) {
            wantToQuit = quit(command);
        }
    }

    private void punch(Command command)
    {
        checkGrenade();
        fight.punch();
        checkItemDrop();
        checkQuit(command);
    }

    private void block(Command command)
    {
        checkGrenade();
        fight.block();
        checkItemDrop();
        checkQuit(command);
    }

    private void stab(Command command)
    {
        checkGrenade();
        if (equipedItem.size() == 1) {
            int damage = equipedItem.get(0).getDamage();
            fight.stab(command, damage);
        }
        else {
            System.out.println(SL.getString("You need to equip an item to be able to stab"));
        }
    }

    private void fightCheck()
    {
        if (fight.inFight == true) {
            fight.inFight = false;
        }
        else {
            fight.inFight = true;
        }
    }

    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return  deze method geeft de som van x en y terug
     */
    private void startFight()
    {
        fightCheck();

        fight.startFight(currentRoom.getLevel());
        System.out.println(SL.getString("Type help for help"));
        //while (fightCheck == true) {
        //    if (fight.checkFight() == false) {
        //        fightCheck();
        //    }
        //}

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
            if ((inventory.getIntInventorySpace() - currentRoom.getWeight(item)) >= 0) {
                //currentRoom = nextRoom;
                //System.out.println(currentRoom.getName(item));
                //System.out.println(currentRoom.getWeight(item));

                inventory.addItem(item);
                System.out.println(SL.getString("Picked up: ") + SL.getString(currentRoom.getName(item)));
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
        //if (inventory.getIntInventorySpace() < 20) {
        if (equipedItem.size() == 1) {
            System.out.println(SL.getString("Current weapon: ") + SL.getString(getEquipedItem().getName()));
        }
        //}

        inventory.getInventorySpace();
    }

    public Item getEquipedItem()
    {
        return equipedItem.get(0);
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
        System.out.println(SL.getString("Your objective is to find and close the portal to the upside down "));
        System.out.println();
        System.out.println(SL.getString("Your command words are:"));
        parser.showCommands();
    }

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printFightHelp() 
    {

        System.out.println(SL.getString("You are in a fight"));
        System.out.println(SL.getString("Your command words are:"));
        parser.showFightCommands();
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
            if (nextRoom.getKey() == 0) {
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                roomList.add(currentRoom);

                chance = rand.nextInt(3);
                if (chance == 2) {
                    startFight();
                }
            }
            else if (inventory.getItem("key") == null) {
                System.out.println(SL.getString("You need a key to open that door!"));
            }
            else {
                System.out.println(SL.getString("*Opened the door with the key*"));
                currentRoom = nextRoom;
                System.out.println(currentRoom.getLongDescription());
                roomList.add(currentRoom);
                startFight();
            }
        }
    }

    private void checkGrenade()
    {
        if (inventory.getItem("grenade") != null) {
            fight.setGrenade(true);
        }
        else {
            fight.setGrenade(false);
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
