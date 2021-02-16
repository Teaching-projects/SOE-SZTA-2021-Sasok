public class Unit {
    final int DMG;
    int hp;
    
    public Unit (int DMG, int hp){
        this.DMG=DMG;
        this.hp=hp;
    }

    public void attack(Unit enemy){
        if(enemy.isAlive() && this.isAlive()){
                enemy.hp=enemy.hp-this.DMG;
            
        }
    }

    public boolean isAlive(){
        return (this.hp > 0) ? true : false;
    }
}