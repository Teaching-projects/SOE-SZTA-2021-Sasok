public class Main {
    public static void main(String args[]) {
        Unit our = new Unit(5, 6, 8);
        Unit enemy = new Unit(8, 8, 10);
        our.attack(enemy);
        System.out.println((our.isAlive()) ? "Tuleltuk! :)" : "Meghaltunk! :(");
    }
}