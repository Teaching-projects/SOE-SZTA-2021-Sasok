public class Player extends Unit{
    private int xp_per_level;
    private int dmg_increase_per_level;
    private int hp_increase_per_level;
    private float cooldown_multiplier_per_level;
    private int xp;
    private int lvl;

    public Player( String name, int dmg, int hp, double as, int xppl, int dmgipl, int hpipl, float cmpl) {
        super(name,dmg,hp,as);
        this.xp_per_level=xppl;
        this.dmg_increase_per_level=dmgipl;
        this.hp_increase_per_level=hpipl;
        this.cooldown_multiplier_per_level=cmpl;
        this.xp=0;
        this.lvl=1;
    }

    public void attack(Unit enemy) {
        super.attack(enemy);
        this.xp = this.xp+super.getDMG();
        this.lvlup();
    }

    public boolean isAlive() {
        return super.isAlive();
    }

    public void lvlup(){
        if(this.xp>this.xp_per_level){
            this.xp=0;
            super.setHP(super.getHp()+hp_increase_per_level);
            super.setDMG(super.getDMG()+dmg_increase_per_level);
            super.setAs(super.getAs()*cooldown_multiplier_per_level);
            this.lvl=this.lvl+1;
            System.out.println(super.getName()+" a(z)"+this.getLvl()+". szintre lépett");
        }
    }

    public int getLvl(){
        return this.lvl;
    }
}