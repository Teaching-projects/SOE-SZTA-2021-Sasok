import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Unit {
    private final int xp_per_level;
    private final int dmg_increase_per_level;
    private final int hp_increase_per_level;
    private final float cooldown_multiplier_per_level;
    private int xp;
    private int lvl;
    private int maxHP;
    private int xCoordinate;
    private int yCoordinate;
    private BufferedImage img;

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
        img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/hero.png"));
        } catch (IOException e) {
        }
    }

    public int attack(Unit enemy) {
        this.xp+=super.attack(enemy);
        this.lvlup();
        return 0;
    }
    public void move(String move) {
        if (move == "right") {
            this.yCoordinate++;
        }
        if (move == "left") {
            this.yCoordinate--;
        }
        if (move == "up") {
            this.xCoordinate--;
        }
        if (move == "down") {
            this.xCoordinate++;
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

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getDmg_increase_per_level() {
        return dmg_increase_per_level;
    }

    public int getHp_increase_per_level() {
        return hp_increase_per_level;
    }

    public float getCooldown_multiplier_per_level() {
        return cooldown_multiplier_per_level;
    }

    public int getLvl() {
        return lvl;
    }

    public void setLvl(int lvl) {
        this.lvl = lvl;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

}