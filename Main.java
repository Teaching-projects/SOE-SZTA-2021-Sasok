import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        // Paraméterek bekérése Scanner-el
        Scanner sc = new Scanner(System.in);

        int unit1Hp = readIn(sc, "Első egység HP-ja: ");
        int unit1Dmg = readIn(sc, "Első egység DMG-je: ");
        int unit1As = readIn(sc, "Első egység AS-je: ");
        int unit2Hp = readIn(sc, "Második egység HP-ja: ");
        int unit2Dmg = readIn(sc, "Második egység DMG-je: ");
        int unit2As = readIn(sc, "Második egység AS-je: ");

        // Két Unit létrehozása
        Unit unit1 = new Unit("Warrior", unit1Dmg, unit1Hp, unit1As);
        Unit unit2 = new Unit("Shaman", unit2Dmg, unit2Hp, unit2As);

        sc.close();

        // Csata elkezdése
        battle(unit1, unit2);
    }

    private static int readIn(Scanner sc, String msg) {
        boolean acceptable = true;
        int answer = 0;
        do {
            System.out.print(msg);
            if (sc.hasNextInt()) {
                answer = sc.nextInt();
                acceptable = false;
            } else {
                System.out.println(sc.next() + " nem érvényes értéket adott meg, adjon meg egy számot! ");
            }
        } while (acceptable);
        return answer;
    }

    private static void battle(Unit unit1, Unit unit2) {
        int as1 = unit1.getAs();
        int as2 = unit2.getAs();
        String name1 = unit1.getName();
        String name2 = unit2.getName();
        boolean bothAlive = true;
        // Mindkettő megüti egymást elősször
        unit1.attack(unit2);
        unit2.attack(unit1);
        System.out.println("\nA Csata elkezdődött! " + name1 + " és " + name2 + " megtámadták egymást. Életük: " + name1
                + " " + unit1.getHP() + ", " + name2 + " " + unit2.getHP());
        // Amíg mindkettő él
        int i = 0;
        while (bothAlive) {
            i++;
            if (i % as1 == 0) {
                unit1.attack(unit2);
                System.out.println(name1 + " megtámadta " + name2 + ", így " + name2 + " élete - " + unit2.getHP());
            }
            if (i % as2 == 0) {
                unit2.attack(unit1);
                System.out.println(name2 + " megtámadta " + name1 + ", így " + name1 + " élete - " + unit1.getHP());
            }
            if (unit1.isAlive() == false || unit2.isAlive() == false) {
                bothAlive = false;
            }
        }
        System.out.println(unit1.isAlive() ? "\n" + unit1.getName().toUpperCase() + " GYŐZEDELMESKEDETT!"
                : "\n" + unit2.getName().toUpperCase() + " GYŐZEDELMESKEDETT!");
    }
}