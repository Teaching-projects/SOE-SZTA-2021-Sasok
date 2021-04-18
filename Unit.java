public class Unit {
    private final String name;
    private int dmg;
    private int hp;
    private double as;

    public Unit(String name, int dmg, int hp, double as) {
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

    public boolean isAlive() {
        return this.hp > 0;
    }

    // Getters

    public int getHp() {
        return this.hp;
    }

    public String getName() {
        return this.name;
    }

    public int getDMG() {
        return this.dmg;
    }

    public double getAs() {
        return this.as;
    }

    // Setters

    protected void setAs(double as) {
        this.as = as;
    }

    public void setHP(int hp){
        this.hp=hp;
    }

    protected void setDMG(int dmg){
        this.dmg=dmg;
    }

}