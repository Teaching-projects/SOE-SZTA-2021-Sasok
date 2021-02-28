import java.util.Scanner;

public class Main {
    public static void main(String args[]) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Harcosok élete:");
        int warriorHp = sc.nextInt();
        System.out.println("Harcosok sebzése: ");
        int  warriorDmg = sc.nextInt();
        System.out.println("Sámánok élete:");
        int shamanHp = sc.nextInt();
        System.out.println("Sámánok sebzése: ");
        int  shamanDmg = sc.nextInt();
        Unit warrior = new Unit(warriorDmg, warriorHp);
        Unit shaman = new Unit(shamanDmg, shamanHp);
        System.out.println("Warrior élete és sebzése: "+ warrior.hp +", "+ warrior.dmg + " || Shaman élete és sebzése: " + shaman.hp + ", " + shaman.dmg + '\n');
        battle(warrior,shaman);
        sc.close();
    } 

    private static void battle (Unit unit1, Unit unit2){
        int i = 1;
        while(unit1.isAlive() && unit2.isAlive()){
            unit1.attack(unit2);
            System.out.println(i + ". körben az első egység élete: " + unit1.hp + ", a második egység élete: " + unit2.hp);
            i++;
            unit2.attack(unit1);
            System.out.println(i + ". körben az első egység élete: " + unit1.hp + ", a második egység élete: " + unit2.hp);
            i++;
        }
        System.out.println(unit1.isAlive() ? unit1 + " nyert" : unit2 +" nyert");
    }
}