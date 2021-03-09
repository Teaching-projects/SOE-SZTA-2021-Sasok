public class Unit {
    private final String name;
    private final double dmg;
    private double hp;
    private double as;

    public Unit(String name, double dmg, double hp, double as) {
        this.name = name;
        this.dmg = dmg;
        this.hp = hp;
        this.as = as;
    }

    public void attack(Unit enemy) {
        if (enemy.isAlive() && this.isAlive()) {
            enemy.hp -= this.dmg;
        }
    }

    public double getHP() {
        return this.hp;
    }

    public String getName() {
        return this.name;
    }

    public double getDMG() {
        return this.dmg;
    }

    public double getAs() {
        return this.as;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

}