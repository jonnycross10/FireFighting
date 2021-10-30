package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.*;

public class Helipad extends Fixed{
    Point location;
    int width = Game.getSmallDim() /10;
    int circleOffset;

    public Helipad(){
        int x = Game.getDispW()/2 - width/2;
        int y = Game.getDispH() - 2*width;
        location = new Point(x,y);
        circleOffset = width/5;
    }

    public void draw(Graphics g){
        int x = location.getX()+circleOffset/2;
        int y = location.getY()+circleOffset/2;
        int circWidth = width - circleOffset;
        g.setColor(ColorUtil.GRAY);
        g.drawRect(location.getX(), location.getY(), width, width, 5);
        g.drawArc(x, y, circWidth,circWidth,0,360);
    }

    public Point getLZ() {
        return new Point(location.getX() +width/2,
                location.getY() +width/2);
    }

    public boolean isUnderneath(Helicopter helicopter) {
        int deltaHelipad = GameWorld.squaredDistanceBetween(this.getLZ(),helicopter.getLocation());
        return(deltaHelipad <width * width);

    }
}

