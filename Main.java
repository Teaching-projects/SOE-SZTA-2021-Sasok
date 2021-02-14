public class Main {
    public static void main(String args[]) {
        Unite our = new Unite(5, 6, 8);
        Unite enemy = new Unite(8, 8, 10);
        our.attack(enemy);
        System.out.println((our.isAlive()) ? "Tuleltuk! :)" : "Meghaltunk! :(");
    }
}