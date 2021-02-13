public class Main {
    public static void main(String args[]) {
        Unite our = new Unite(5, 6, 8);
        Unite enemy = new Unite(8, 8, 10);
        our.attack(enemy);
        if (our.isAlive()){
            System.out.println("Tuleltuk! :)");
        } else {
            System.out.println("Meghaltunk. :(");
        }
    }
}