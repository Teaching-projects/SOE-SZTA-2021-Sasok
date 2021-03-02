import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        initialize();
    }

    private static void battle(Unit unit1, Unit unit2) {
        System.out.println("A Csata elkezdődött! " + unit1.getName() + " kezdheti a támadást " + unit2.getName() + (" ellen."));
        int i = 1;
        while (unit1.isAlive() && unit2.isAlive()) {
            unit1.attack(unit2);
            System.out.println(i + ". körben " + unit1.getName() + " támad --- " + unit1.getName() + " élete: " + unit1.getHP()
                    + " || " + unit2.getName() + " élete: " + unit2.getHP());
            if (!unit2.isAlive()) {
                break;
            }
            unit2.attack(unit1);
            i++;
            System.out.println(i + ". körben " + unit2.getName() + " támad, ---" + unit1.getName() + " élete: " + unit1.getHP()
                    + " || " + unit2.getName() + " élete: " + unit2.getHP());

        }
        System.out.println(unit1.isAlive() ? "\n" + unit1.getName().toUpperCase() + " GYŐZEDELMESKEDETT!"
                : "\n" + unit2.getName().toUpperCase() + " GYŐZEDELMESKEDETT!");
    }

    private static void initialize() {
        Scanner sc = new Scanner(System.in);
        // Első Unit
        System.out.println("Első egység neve: ");
        String unit1Name = sc.next();
        System.out.println(unit1Name + " élete:");
        int unit1Hp = sc.nextInt();
        System.out.println(unit1Name + " sebzése: ");
        int unit1Dmg = sc.nextInt();
        // Második Unit
        System.out.println("Második egység neve: ");
        String unit2Name = sc.next();
        System.out.println(unit2Name + " élete:");
        int unit2Hp = sc.nextInt();
        System.out.println(unit2Name + " sebzése: ");
        int unit2Dmg = sc.nextInt();
        // Két Unit létrehozása
        Unit unit1 = new Unit(unit1Name, unit1Dmg, unit1Hp);
        Unit unit2 = new Unit(unit2Name, unit2Dmg, unit2Hp);
        sc.close();
        battle(unit1, unit2);
    }
}