public class Unit {
    String name;
    final int dmg;
    int hp;

    public Unit(String name, int dmg, int hp) {
        this.name = name;
        this.dmg = dmg;
        this.hp = hp;
    }

    public void attack(Unit enemy) {
        if (enemy.isAlive() && this.isAlive()) {
            enemy.hp -= this.dmg;
        }
    }

    public boolean isAlive() {
        return this.hp > 0;
    }

}