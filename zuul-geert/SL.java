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
    private static HashMap<String, String> languages = new HashMap<String, String>();
    
    /**
     * Constructor voor objects van class SL
     */
    public static void initialize()
    {
        //NL.put();
        
        languages.put("EN", "EN");
        languages.put("NL", "NL");
        
        
        NL.put("Welcome to Hawkins laboratory!", "Welcome bij het Hawkins lab!");
        NL.put("What language?", "Welke taal?");
        NL.put("outside the main entrance of the Hawkins laboratory", "buiten de hoofdingang van het Hawkins lab");
        NL.put("in the reception area", "");
        NL.put("in the waiting room", "");
        NL.put("in the hallway", "");
        NL.put("in the bathroom", "");
        NL.put("in the lab", "");
        NL.put("in the behavioral lab", "");
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
    public int getLanguages(int y)
    {
        // schrijf hier jouw code
        return x + y;
    }


}
