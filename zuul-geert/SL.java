import java.util.HashMap;


/**
 * class SL - geef hier een beschrijving van deze class
 *
 * @author Geert Perton
 * @version 07-02-2020
 */
public class SL
{
    // instance variables - vervang deze door jouw variabelen
    private int x;
    private static String string;
    private static HashMap<String, String> NL = new HashMap<String, String>();
    private static HashMap<String, String> EN = new HashMap<String, String>();
    private static HashMap<String, String> languages = new HashMap<String, String>();
    
    
    /**
     * Constructor voor objects van class SL
     */
    public static void initialize()
    {
        //NL.put();
        
        languages.put("EN", "EN");
        languages.put("NL", "NL");
        
        //SL.getString(
        
        EN.put("noord", "north");
        EN.put("oost", "east");
        EN.put("zuid", "south");
        EN.put("west", "west");
        EN.put("omhoog", "up");
        EN.put("omlaag", "down");
        EN.put("ga", "go");
        EN.put("stop", "quit");
        EN.put("help", "help");
        EN.put("terug", "back");
        EN.put("over", "about");
        EN.put("inventaris", "inventory");
        EN.put("taal", "language");
        
        NL.put("outside the main entrance of the Hawkins laboratory", "buiten de hoofdingang van het Hawkins laboratorium");
        NL.put("in the reception area", "in de receptie");
        NL.put("in the waiting room", "in de wacht kamer");
        NL.put("in the hallway", "in de gang");
        NL.put("in the bathroom", "in het toilet");
        NL.put("You are ", "Je bent ");
        NL.put("in the lab", "in het lab");
        NL.put("in the behavioral lab", "in het gedrag onderzoek lab");
        NL.put("in the biocontainment room", "in de biocontainment kamer");
        NL.put("in the office", "in het kantoor");
        NL.put("in the storage room", "in de opslag ruimte");
        NL.put("in the basement", "in de kelder");
        NL.put("Thank you for playing.  Good bye.", "Bedankt voor het spelen. Tot ziens.");
        NL.put("Welcome to Hawkins laboratory!", "Welcome bij het Hawkins lab!");
        NL.put("Hawkins laboratory is a new, incredibly boring adventure game.", "");
        NL.put("Type '", "Typ '");
        NL.put("' if you need help.", "'  als je hulp nodig hebt");
        NL.put("I don't know what you mean...", "Ik weet niet wat je bedoelt..");
        NL.put("You are lost. You are alone. You wander", "Je bent de weg kwijt. Je bent alleen. Je dwaalt");
        NL.put("around at the Hawkins laboratory.", "bij het Hawkins laboratorium");
        NL.put("Your command words are:", "Je commando's zijn:");
        NL.put("Go where?", "Waarheen gaan?");
        NL.put("There is no door!", "Daar is geen deur!");
        NL.put("What language?", "Welke taal?");
        NL.put("You have already selected this language!", "Je hebt deze taal al geselecteerd!");
        NL.put("The language now is: ", "De taal is nu: ");
        NL.put("That language is not available, the available languages are: ", "Die taal is niet beschikbaar, de beschikbare talen zijn: ");
        NL.put("English EN", "Engels EN");
        NL.put("Dutch NL", "Nederlands NL");
        NL.put("Quit what?", "Waarmee stoppen?");
        NL.put("Exits:", "Uitgangen:");
        
        //command words
        for (String translation : EN.keySet()){
            String translated = EN.get(translation);
            NL.put(translated, translation);
        }
        //NL.put("north", "noord"); 
        //NL.put("east", "oost");
        //NL.put("south", "zuid");
        //NL.put("west", "west");
        //NL.put("up", "omhoog");
        //NL.put("down", "omlaag");
        //NL.put("go", "ga");
        //NL.put("quit", "stop");
        //NL.put("help", "help");
        //NL.put("back", "terug");
        //NL.put("about", "over");
        //NL.put("inventory", "inventaris");
        //NL.put("language", "taal");
        
        
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
        //NL.put(, "");
    }

    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  string    deze method krijgt deze parameter mee in de aanroep
     * @return    deze method geeft de som van x en y terug
     */
    public static String getString(String key)
    {
            if (Game.language == "NL") {
            string = NL.get(key);
            //System.out.println("the key is " + key);
            //System.out.println("the string is " + string);
            return string;
        }
        else {
            return key;
        }
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public static String getLanguage(String lang)
    {
        return languages.get(lang);
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public static String getEnglishString(String key)
    {
            if (Game.language == "NL") {
            string = EN.get(key);
            //System.out.println("the string is " + string);
            return string;
        }
        else {
            return key;
        }
    }


}
