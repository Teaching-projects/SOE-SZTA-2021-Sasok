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
        enemy.hp=enemy.hp+enemy.def-this.dmg;
    }

    public boolean isAlive(){
        if(this.hp > 0){
            return true;
        }
        return false;
    }
}