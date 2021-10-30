package org.csc133.a2.gameobjects;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.*;

public class River extends Fixed{
    Point location;
    int riverWidth;

    public River(){
        riverWidth = Game.getDispW() /10;
        location = new Point(0,Game.getDispH()/5);
    }

    public void draw(Graphics g){
        g.setColor(ColorUtil.BLUE);
        g.drawRect(location.getX(), location.getY(), Game.getDispW(), riverWidth);

    }

    public boolean underHelicopter(Helicopter helicopter) {
        return(helicopter.getLocation().getY()>location.getY()
                && helicopter.getLocation().getY()< location.getY() + riverWidth);
    }
}
