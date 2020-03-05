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
    private int damage;
    private ArrayList<Enemy> enemyList;
    private int health;
    private int enemyHealth;
    private Enemy currentEnemy;
    
    Random rand = new Random();

    /**
     * Constructor voor objects van class fight
     */
    public Fight(int health)
    {
        createEnemys();
        health = 20;
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
     * @param  y    deze method krijgt deze parameter mee in de aanroep
     * @return  deze method geeft de som van x en y terug
     */
    public void startFight(int level)
    {
        
        currentEnemy = pickEnemy();
        for (int i = 100; i > level; i = currentEnemy.getLevel()) {
             currentEnemy = pickEnemy();
        }
        System.out.println("You stumbled across a(n) " + currentEnemy.getName());
        enemyHealth = currentEnemy.getHealth();
        System.out.println("His health is  " + enemyHealth);
    }
    
    private Enemy pickEnemy()
    {
        
        //enemyList.get(rand.nextInt(enemyList.size()));
        
        //for (int i = 0; i < enemyList.size(); i++) {
        //  System.out.println(i);
        //}
        return enemyList.get(rand.nextInt(enemyList.size()));
    }
    
    private void printDamageDone()
    {
        System.out.println("Total damage done: " + damage + "hp");
    }
    
    private void printEnemyStatus()
    {
        if (enemyHealth <= 0) {
            enemyHealth = 0;
            System.out.println("You defeated the " + currentEnemy.getName());
        }
        else {
            System.out.println("Current health enemey: " + enemyHealth + "hp");
        }
    }
    
    public void punch()
    {
        damage = rand.nextInt(5) + 1;
        System.out.println("punched " + currentEnemy.getName());
        printDamageDone();
        enemyHealth = enemyHealth - damage;
        printEnemyStatus();
        
        
    }
    
    public void block()
    {
        System.out.println("blocked attack");
    }
    
    public void stab(Command command)
    {
        String direction = SL.getEnglishString(command.getSecondWord());
        if (direction == null) {
            System.out.println("Stab where?");
            System.out.println("You can stab the head or the chest");
        }
        else {
        System.out.println("stabbed enemy" + direction);
        }
    }

}
