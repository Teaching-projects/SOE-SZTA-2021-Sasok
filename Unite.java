public class Unite {
    int dmg;
    int hp;
    int def;
    
    public Unite (int dmg, int def, int hp){
        this.dmg=dmg;
        this.def=def;
        this.hp=hp;
    }

    public void attack(Unite enemy){
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