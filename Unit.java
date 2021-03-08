public class Unit {
    private final String name;
    private final int dmg;
    private int hp;
    // hány másodpercet kell várnia, hogy ütni tudjon?
    private int as;

    public Unit(String name, int dmg, int hp, int as) {
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

    public int getHP() {
        return this.hp;
    }

    public String getName() {
        return this.name;
    }

    public int getDMG() {
        return this.dmg;
    }

    public int getAs() {
        return this.as;
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

}