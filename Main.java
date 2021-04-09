import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String args[]) {
        
        if(args.length==2){
            battle(JsonToPlayer(args[0]), JsonToUnit(args[1]));

        }else{
            Scanner sc = new Scanner(System.in);

            int unit1Hp = (int) readIn(sc, "Player HP-ja: ");
            int unit1Dmg = (int) readIn(sc, "Player DMG-je: ");
            double unit1As = readIn(sc, "Player AS-je: ");
            int playerdmgpl = (int) readIn(sc, "Player DMG növekedése: ");
            int playerhppl = (int) readIn(sc, "Player HP növekedése: ");
            float playeraspl = (int) readIn(sc, "Player AS szorzoja: ");
            int playerxp = (int) readIn(sc, "Player Szintlépéshez szükséges xp mennyisége: ");
            int unit2Hp = (int) readIn(sc, "Második egység HP-ja: ");
            int unit2Dmg = (int) readIn(sc, "Második egység DMG-je: ");
            double unit2As = readIn(sc, "Második egység AS-je: ");

            if (unit1Hp == 0 || unit2Hp == 0) {
                System.out.println("\nA HP nem lehet 0!");
                return;
            } else if (unit1Hp == -1 || unit2Hp == -1 || unit1Dmg == -1 || unit2Dmg == -1) {
                System.out.println(sc.next() + "\nNem érvényes értéket adott meg, adjon meg egy számot! ");
                return;
            }
            if (unit1As <= 0 || unit2As <= 0) {
                System.out.println("\nAz attack speed nem lehet egyenlő vagy kisebb mint 0!");
                return;
            }

            // Két Unit létrehozása
            Player player1 = new Player("Harcos", unit1Dmg, unit1Hp, unit1As,playerxp,playerdmgpl,playerhppl,playeraspl);
            Unit unit2 = new Unit("Sámán", unit2Dmg, unit2Hp, unit2As);

            sc.close();
            battle(player1, unit2);

        }
        
    }

    private static double readIn(Scanner sc, String msg) {
        double answer = 0;

        System.out.print(msg);
        if (sc.hasNextFloat()) {
            answer = sc.nextFloat();
            return answer;
        } else {
            // Akkor tér vissza vele, ha pl. betű van megadva
            answer = -1;
            return answer;
        }
    }

    private static void battle(Player unit, Unit unit2) {
        if (unit.getDMG() == 0 && unit2.getDMG() == 0) {
            System.out.println("A csapatok visszavonultak, a harc dontetlennel vegzodott.");
            return;
        }

        String name1 = unit.getName();
        String name2 = unit2.getName();
        boolean bothAlive = true; // mindkettő él
        boolean isFirstRound = true; // első kör
        double defaultAs = unit.getAs();
        double defaultAs2 = unit2.getAs();
        while (bothAlive) {
            // Ha első kör akkor egyszerre megütik egymást 1. ütés!
            if (isFirstRound) {
                isFirstRound = false;
                unit.attack(unit2);
                unit2.attack(unit);
                System.out.println("\nA Csata elkezdődött! " + name1 + " és " + name2 + " megtámadták egymást. életük: "
                        + name1 + " " + unit.getHp() + ", " + name2 + " " + unit2.getHp());
            }
            // Attack speed számolása, melyiké kisebb --> az üthet elősször
            double lowestAs = Math.min(unit.getAs(), unit2.getAs());
            unit.setAs(unit.getAs() - lowestAs);
            unit2.setAs(unit2.getAs() - lowestAs);
            // Ütések
            if (unit.getAs() == 0) {
                unit.attack(unit2);
                unit.setAs(defaultAs);
                System.out.println(name1 + " megtámadta " + name2 + ", így " + name2 + " élete - " + unit2.getHp());
            }
            if (unit2.getAs() == 0) {
                unit2.attack(unit);
                unit2.setAs(defaultAs2);
                System.out.println(name2 + " megtámadta " + name1 + ", így " + name1 + " élete - " + unit.getHp());
            }
            // Éltek még?
            if (unit.isAlive() == false || unit2.isAlive() == false) {
                bothAlive = false;
            }
        }
        // Első ütésnél meghalt e vagy nem...
        if (isFirstRound)
            System.out.println(unit.isAlive() ? "\n" + unit.getName() + " EGY CSAPASSAL GYOZOTT"
                    : "\n" + unit2.getName() + " EGY CSAPASSAL GYOZOTT!");
        else
            System.out.println(unit.isAlive() ? "\n" + unit.getName() + " GYOZEDELMESKEDETT!"
                    : "\n" + unit2.getName() + " GYOZEDELMESKEDETT!");
    }
    public static Unit JsonToUnit(String arg){
        String first = arg;
        try {
            String contents = new String((Files.readAllBytes(Paths.get(first))));
            JSONObject o = new JSONObject(contents);
            String unitjsonNAME = o.getString("NAME");
            int unitjsonHP = o.getInt("HP");
            int unitjsonDMG = o.getInt("DMG");
            double unitjsonAS = o.getDouble("AS");
            Unit unit = new Unit(unitjsonNAME, unitjsonDMG, unitjsonHP, unitjsonAS);
            return unit;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public static Player JsonToPlayer(String arg){
        String first = arg;
        try {
            String contents = new String((Files.readAllBytes(Paths.get(first))));
            JSONObject o = new JSONObject(contents);
            String playerjsonNAME = o.getString("NAME");
            int playerjsonHP = o.getInt("HP");
            int playerjsonDMG = o.getInt("DMG");
            double playerjsonAS = o.getDouble("AS");
            int playerXP = o.getInt("XP");
            int playerjsonDMGPL = o.getInt("DMGPL");
            int playerjsonHPPL = o.getInt("HPPL");
            float playerASPL = o.getFloat("ASPL");
            Player player = new Player(playerjsonNAME, playerjsonDMG, playerjsonHP, playerjsonAS, playerXP, playerjsonDMG, playerjsonHPPL, playerASPL);
            return player;
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}