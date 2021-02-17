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
        System.out.println("Warrior élete és sebzése: "+ warrior.hp +", "+ warrior.dmg + " || Shaman élete és sebzése: " + shaman.hp + ", " + shaman.dmg);
    } 
}