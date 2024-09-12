import java.util.Random;

public class Main {
    public static int bossHealth =650;
    public static int bossDamage = 50;
    public static int healing = 25;
    public static String bossDefence;
    public static int [] heroesHealth = {250, 200, 250, 50 };
    public static int [] heroesDamage = {10,15,20,0 };
    public static String [] heroesDamageType ={"Physical", "Magical", "Kinetic", "HEALER"};
    public static int roundNumber;


    public static void main(String[] args) {
        showStatistics();
        while (!isGameOver()){
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        bossAttacks();
        bossDefences();
        heroesAttacks();
        healingHeroes();
        showStatistics();
    }

    public static void healingHeroes() {
        int healerIndex = findHealerIndex();
        if (healerIndex != -1) {
            for (int i = 0; i <heroesHealth.length ; i++) {
                if(i != healerIndex && heroesHealth[i] > 0){
                    heroesHealth[i] += healing;
                    System.out.println(heroesDamageType[healerIndex] + " heals " + heroesDamageType[i]
                            + " for " + healing + " heath");
                }

            }
        }

    }

    public static int findHealerIndex() {
        for (int i = 0; i < heroesDamageType.length; i++) {
            if (heroesDamageType[i].equals("HEALER")) {
                return i;
            }
        }
        return -1;
    }

    public static void heroesAttacks() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = calculateDamage(i);
                if (bossHealth - bossDamage < 0){
                    bossHealth = 0;
                }else {
                    bossHealth -= damage;
                }
            }
        }

    }

    public static int calculateDamage(int heroIndex ) {
        int damage = heroesDamage [heroIndex];
        if (bossDefence == heroesDamageType[heroIndex]){
            Random random = new Random();
            int coefficient = random.nextInt(9)+2;
            damage *= coefficient;
            System.out.println("--------> CRITICAL DAMAGE -------" + damage);
        }
        return damage;
    }

    public static void bossDefences() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesDamage.length);
        bossDefence = heroesDamageType[randomIndex];
    }


    public static void bossAttacks() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0){
                    heroesHealth[i] = 0;
                }else {
                    heroesHealth[i] -= bossDamage;
                }
            }
        }
    }

    public static boolean isGameOver(){
        if (bossHealth <= 0){
            System.out.println("Heros won");
            return true;
        }
        boolean allHeroesDead = true;
        for (int health : heroesHealth){
            if (health > 0){
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("Boss won");
        }
        return allHeroesDead;

    }
    private static void showStatistics() {
        System.out.println("ROUND______>" + roundNumber);
        System.out.println("Boss Health-------->" + bossHealth + " boss damage------->" + bossDamage +
                " boss defence------->" + (bossDefence == null? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesDamageType[i] + "  health: " + heroesHealth[i]
                    + " damage: " + heroesDamage[i]);
        }


    }


}