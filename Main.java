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

    private static void battle (Unit our, Unit enemy){
        int i = 1;
        while(our.isAlive() && enemy.isAlive()){
            our.attack(enemy);
            if(our.isAlive() && enemy.isAlive()){
                enemy.attack(our);
                if (our.hp < 0 || enemy.hp < 0)
                    System.out.println(i + ". körben a maradék életünk: " + our.hp + ", lenne az ellenség maradék élete: " + enemy.hp + " lenne");
                else
                    System.out.println(i + ". körben a maradék életünk: " + our.hp + ", az ellenség maradék élete: " + enemy.hp);
                i++;
            }
        }

        System.out.println((our.hp>enemy.hp) ? "\nNyertünk!" : "\nVesztettünk!");
    }
}