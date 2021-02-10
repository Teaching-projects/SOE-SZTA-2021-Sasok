public class Unite {
    int dmg;
    int hp;
    
    public Unite (int dmg, int hp){
        this.dmg=dmg;
        this.hp=hp;

    }

    public void attack(Unite enemy){
        enemy.hp=enemy.hp-this.dmg;
    }
}