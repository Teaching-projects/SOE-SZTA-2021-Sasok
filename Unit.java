public class Unit {
    private final String name;
    protected int dmg;
    protected int hp;
    protected double as;

    public Unit(String name, int dmg, int hp, double as) {
        this.name = name;
        this.dmg = dmg;
        this.hp = hp;
        this.as = as;
    }

    public int attack(Unit enemy) {
        if (enemy.isAlive() && this.isAlive()) {
            if(enemy.hp<this.dmg){
                int xp = enemy.hp;
                enemy.hp=0;
                return xp;
            } else {
                enemy.hp-=this.dmg;
                return this.dmg;
            }
        }
        return 0;
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

    protected void setHP(int hp){
        this.hp=hp;
    }

    protected void setDMG(int dmg){
        this.dmg=dmg;
    }

}