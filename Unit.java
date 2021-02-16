public class Unit {
    final int dmg;
    int hp;
    
    public Unit (int dmg, int hp){
        this.dmg=dmg;
        this.hp=hp;
    }

    public void attack(Unit enemy){
        if(enemy.isAlive() && this.isAlive()){
                enemy.hp -= this.dmg;  
        }
    }

    public boolean isAlive(){
        return this.hp > 0;
    }
}