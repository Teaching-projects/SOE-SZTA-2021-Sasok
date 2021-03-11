import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        // Paraméterek bekérése Scanner-el
        Scanner sc = new Scanner(System.in);

        int unit1Hp = readIn(sc, "Első egység HP-ja: ");
        int unit1Dmg = readIn(sc, "Első egység DMG-je: ");
        int unit2Hp = readIn(sc, "Második egység HP-ja: ");
        int unit2Dmg = readIn(sc, "Második egység DMG-je: ");

        if (unit1Hp == 0 || unit2Hp == 0){
            System.out.println("\nA HP nem lehet 0!");
            System.exit(1);
        }

        // Két Unit létrehozása
        Unit unit1 = new Unit("Warrior", unit1Dmg, unit1Hp);
        Unit unit2 = new Unit("Shaman", unit2Dmg, unit2Hp);

        sc.close();

        // Csata elkezdése
        battle(unit1, unit2);
    }

    private static int readIn(Scanner sc, String msg) {
        int answer = 0;

        System.out.print(msg);
        if (sc.hasNextInt())
            answer = sc.nextInt();
        else{
            System.out.println(sc.next() + "\nNem érvényes értéket adott meg, adjon meg egy számot! ");
            System.exit(1);
        }

        return answer;
    }

    private static void battle(Unit unit1, Unit unit2) {
        if (unit1.getDMG() == 0 && unit2.getDMG() == 0){
            System.out.println("A csapatok visszavonultak, a harc dontetlennel vegzodott.");
            System.exit(1);
        }

        System.out.println(
                "\nA Csata elkezdődött! " + unit1.getName() + " kezdheti a támadást " + unit2.getName() + (" ellen."));
        int i = 1;
        while (unit1.isAlive() && unit2.isAlive()) {
            unit1.attack(unit2);
            System.out.println(i + ". körben " + unit1.getName() + " támad --- " + unit1.getName() + " élete: "
                    + unit1.getHP() + " || " + unit2.getName() + " élete: " + unit2.getHP());
            if (!unit2.isAlive()) {
                break;
            }
            unit2.attack(unit1);
            i++;
            System.out.println(i + ". körben " + unit2.getName() + " támad, ---" + unit1.getName() + " élete: "
                    + unit1.getHP() + " || " + unit2.getName() + " élete: " + unit2.getHP());

        }
        System.out.println(unit1.isAlive() ? "\n" + unit1.getName().toUpperCase() + " GYOZEDELMESKEDETT!"
                : "\n" + unit2.getName().toUpperCase() + " GYOZEDELMESKEDETT!");
    }
}