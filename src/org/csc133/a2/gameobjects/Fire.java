package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.*;

public class Fire{
    Point location;
    int width;

    public Fire(Point p){
        width = GameWorld.randomRangedInt(Game.getDispW()/20, Game.getDispW()/5);
        location = p;
    }

    public boolean isStillBurning(){
        return width >0;
    }

    public void draw(Graphics g){
        g.setColor(ColorUtil.MAGENTA);

        if(isStillBurning()){
            g.fillArc(location.getX(), location.getY(), width, width, 0,360);
            g.drawString(String.valueOf(width), location.getX()+width, location.getY()+width);
        }
    }

    public Point centerOf(){
        return new Point(location.getX()+width/2, location.getY()+width/2);
    }

    public boolean near(Helicopter helicopter){
        double sqDistance = Math.max(width/1.5 * width/1.5, Math.pow(Game.getSmallDim()/20,2));
        return GameWorld.squaredDistanceBetween(helicopter.getLocation(), location)< sqDistance;
    }

    public void shrink(int water) {
        int sizeChange = water / 5;
        if (width > 0) {
            width -= sizeChange;
            location.setX(location.getX() + sizeChange / 2);
            location.setY(location.getY() + sizeChange / 2);
        }
    }

    public void grow(){
        if(width>0){
            width += 2;
            location.setX(location.getX()-1);
            location.setY(location.getY()-1);
        }
    }

}
