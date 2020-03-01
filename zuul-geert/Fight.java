import java.util.ArrayList;
import java.util.Random;


/**
 * class fight - geef hier een beschrijving van deze class
 *
 * @author (jouw naam)
 * @version (versie nummer of datum)
 */
public class Fight
{
    // instance variables - vervang deze door jouw variabelen
    private int x;
    private ArrayList<Enemy> enemyList;
    Random rand = new Random();

    /**
     * Constructor voor objects van class fight
     */
    public Fight()
    {
        createEnemys();
        
    }

    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return    deze method geeft de som van x en y terug
     */
    private void createEnemys()
    {
        enemyList = new ArrayList<Enemy>();
        
        Enemy scientist = new Enemy("Evil Scientist", 5, 2, 5);
        enemyList.add(scientist);
        Enemy guard = new Enemy("Guard", 3, 1, 5);
        enemyList.add(guard);
        Enemy hazmat = new Enemy("Hazmat Scientist", 8, 3, 8);
        enemyList.add(hazmat);
        Enemy agent = new Enemy("Secret Agent", 10, 3, 8);
        enemyList.add(agent);
        Enemy catogorgon = new Enemy("Catogorgon", 7, 4, 6);
        enemyList.add(catogorgon);
        Enemy demogorgon = new Enemy("Demogorgon", 15, 5, 15);
        enemyList.add(demogorgon);
        Enemy portal = new Enemy("Tentacle Hydra Portal", 13, 6, 40);
        enemyList.add(portal);
        
    }
    
    /**
     * Voorbeeld van een method - schrijf hier jouw comment
     *
     * @param  y	deze method krijgt deze parameter mee in de aanroep
     * @return	deze method geeft de som van x en y terug
     */
    public void startFight(int level)
    {
        Enemy currentEnemy = pickEnemy();
        System.out.println(currentEnemy.getName());
    }
    
    private Enemy pickEnemy()
    {
        
        //enemyList.get(rand.nextInt(enemyList.size()));
        
        //for (int i = 0; i < enemyList.size(); i++) {
        //  System.out.println(i);
        //}
        return enemyList.get(rand.nextInt(enemyList.size()));
    }

}
