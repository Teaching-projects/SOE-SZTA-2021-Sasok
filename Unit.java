public class Unit {
    int dmg;
    int hp;
    int def;
    
    public Unit (int dmg, int def, int hp){
        this.dmg=dmg;
        this.def=def;
        this.hp=hp;
    }

    public void attack(Unit enemy){
        if(enemy.isAlive()){
            if(enemy.def<this.dmg){
                enemy.hp=enemy.hp+enemy.def-this.dmg;
            }
        }
    }

    public boolean isAlive(){
        return (this.hp > 0) ? true : false;
    }
}