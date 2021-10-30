package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Font;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;

import static com.codename1.ui.CN.*;
import org.csc133.a2.*;

public class Helicopter{
    public Point getLocation;
    private Point location;
    private int heading;
    private int speed;
    private int fuel;
    private int water;
    private int width;
    private int height;

    final static int MAX_SPEED = 10;
    final static int MAX_WATER = 1000;

    public Helicopter(Point lz){
        width = Game.getSmallDim()/50;
        height = width *2;
        heading = 180;
        water = 0;
        fuel = GameWorld.INIT_FUEL;
        speed = 0;

        location = lz;
        location.setX(location.getX()-width/2);

    }

    public void accelerate(){
        speed = Math.min(++speed, MAX_SPEED);
    }
    public void brake(){
        speed = Math.max(--speed, 0);
    }

    public void turnLeft(){
        heading = heading + 15;
    }

    public void turnRight(){
        heading = heading - 15;
    }

    public void move(){
        int radius = speed *5;
        double angle = Math.toRadians(90 - heading);
        int deltaX = (int)(radius * Math.cos(angle));
        int deltaY = (int)(radius * Math.sin(angle));

        if(fuel>0){
            location.setX(location.getX()+deltaX);
            location.setY(location.getY()+deltaY);
            fuel = Math.max(fuel-(speed*speed+5), 0);
        }
    }

    public void draw(Graphics g){
        double angle = Math.toRadians(90-heading);
        int eX = (int)(height * Math.cos(angle));
        int eY = (int)(height * Math.sin(angle));
        int oX = location.getX() + width/2;
        int oY = location.getY() + width/2;


        g.setColor(ColorUtil.YELLOW);
        g.fillArc(location.getX(), location.getY(), width, width, 0 , 360);
        g.drawLine(oX,oY, oX+eX,oY+eY);

        g.setFont(Font.createSystemFont(FACE_MONOSPACE, STYLE_BOLD, SIZE_LARGE));
        g.drawString("F: " + fuel, location.getX(), location.getY() + height*2);
        g.drawString("W: " + water, location.getX(), location.getY()+height *3);
    }

    public void drinkFrom(River river) {
        if(canDrinkFrom(river)&& speed <= 2){
            water = Math.min(100+water, MAX_WATER);
        }
    }

    public boolean canDrinkFrom(River river){
        return river.underHelicopter(this);
    }

    public boolean near(Fire fire) {
        return fire.near(this);
    }

    public void fight(Fire fire){
        if(fire.near(this)){
            fire.shrink(water);
            dumpWater();
        }
    }

    public void dumpWater(){
        water = 0;
    }

    public boolean hasLandedAt(Helipad helipad) {
        return(speed ==0 && helipad.isUnderneath(this));
    }

    public Point getLocation(){
        return(location);
    }

    public int getFuel() {
        return (fuel);
    }
}
