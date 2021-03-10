import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        // Paraméterek bekérése Scanner-el
        Scanner sc = new Scanner(System.in);

        int unit1Hp = (int) readIn(sc, "Első egység HP-ja: ");
        int unit1Dmg = (int) readIn(sc, "Első egység DMG-je: ");
        double unit1As = readIn(sc, "Első egység AS-je: ");
        int unit2Hp = (int) readIn(sc, "Második egység HP-ja: ");
        int unit2Dmg = (int) readIn(sc, "Második egység DMG-je: ");
        double unit2As = readIn(sc, "Második egység AS-je: ");

        // Két Unit létrehozása
        Unit unit1 = new Unit("Harcos", unit1Dmg, unit1Hp, unit1As);
        Unit unit2 = new Unit("Sámán", unit2Dmg, unit2Hp, unit2As);

        sc.close();

        // Csata elkezdése
        battle(unit1, unit2);
    }

    private static double readIn(Scanner sc, String msg) {
        boolean acceptable = true;
        double answer = 0;
        do {
            System.out.print(msg);
            if (sc.hasNextFloat()) {
                answer = sc.nextFloat();
                acceptable = false;
            } else {
                System.out.println(sc.next() + " nem érvényes értéket adott meg, adjon meg egy számot! ");
            }
        } while (acceptable);
        return answer;
    }

    private static void battle(Unit unit1, Unit unit2) {
        String name1 = unit1.getName();
        String name2 = unit2.getName();
        boolean bothAlive = true; // mindkettő él
        boolean isFirstRound = true; // első kör
        double defaultAs = unit1.getAs();
        double defaultAs2 = unit2.getAs();
        while (bothAlive) {
            // Ha első kör akkor egyszerre megütik egymást 1. ütés!
            if (isFirstRound) {
                isFirstRound = false;
                unit1.attack(unit2);
                unit2.attack(unit1);
                System.out.println("\nA Csata elkezdődött! " + name1 + " és " + name2 + " megtámadták egymást. Életük: "
                        + name1 + " " + unit1.getHP() + ", " + name2 + " " + unit2.getHP());
            }
            //Attack speed számolása, melyiké kisebb --> az üthet elősször
            double lowestAs = Math.min(unit1.getAs(), unit2.getAs());
            unit1.setAs(unit1.getAs()-lowestAs);
            unit2.setAs(unit2.getAs()-lowestAs);
            //Ütések
            if (unit1.getAs() == 0) {
                unit1.attack(unit2);
                unit1.setAs(defaultAs);
                System.out.println(name1 + " megtámadta " + name2 + ", így " + name2 + " élete - " + unit2.getHP());
            }
            if (unit2.getAs() == 0) {
                unit2.attack(unit1);
                unit2.setAs(defaultAs2);
                System.out.println(name2 + " megtámadta " + name1 + ", így " + name1 + " élete - " + unit1.getHP());
            }
            // Éltek még?
            if (unit1.isAlive() == false || unit2.isAlive() == false) {
                bothAlive = false;
            }
        }
        // Első ütésnél meghalt e vagy nem...
        if (isFirstRound)
            System.out.println(unit1.isAlive() ? "\n" + unit1.getName().toUpperCase() + " EGY CSAPÁSSAL GYŐZÖTT"
                    : "\n" + unit2.getName().toUpperCase() + " EGY CSAPÁSSAL GYŐZÖTT!");
        else
            System.out.println(unit1.isAlive() ? "\n" + unit1.getName().toUpperCase() + " GYŐZEDELMESKEDETT!"
                    : "\n" + unit2.getName().toUpperCase() + " GYŐZEDELMESKEDETT!");
    }
}