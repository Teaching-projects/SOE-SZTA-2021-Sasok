public class Player extends Unit{
    private final int xp_per_level;
    private final int dmg_increase_per_level;
    private final int hp_increase_per_level;
    private final float cooldown_multiplier_per_level;
    private int xp;
    private int lvl;
    private int maxHP;
    private int xCoordinate;
    private int yCoordinate;

    public Player( String name, int dmg, int hp, double as, int xppl, int dmgipl, int hpipl, float cmpl) {
        super(name,dmg,hp,as);
        this.xp_per_level=xppl;
        this.dmg_increase_per_level=dmgipl;
        this.hp_increase_per_level=hpipl;
        this.cooldown_multiplier_per_level=cmpl;
        this.xp=0;
        this.lvl=1;
        this.maxHP=hp;
        this.xCoordinate = 0;
        this.yCoordinate = 0;
    }

    public int attack(Unit enemy) {
        this.xp+=super.attack(enemy);
        this.lvlup();
        return 0;
    }
    public void move(String move) {
        if (move == "right") {
            this.xCoordinate++;
        }
        if (move == "left") {
            this.xCoordinate--;
        }
        if (move == "up") {
            this.yCoordinate++;
        }
        if (move == "down") {
            this.yCoordinate--;
        }
    }
    public boolean isAlive() {
        return super.isAlive();
    }

    public void lvlup(){
        while(this.xp>=this.xp_per_level){
            this.xp-=this.xp_per_level;
            this.maxHP+=this.hp_increase_per_level;
            this.hp=this.maxHP;
            this.dmg+=this.dmg_increase_per_level;
            this.as*=cooldown_multiplier_per_level;
            this.lvl++;
            System.out.println(super.getName()+" a(z)"+this.getLvl()+". szintre lépett");
        }
    }

    public int getLvl(){
        return this.lvl;
    }
}