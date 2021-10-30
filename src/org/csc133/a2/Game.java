package org.csc133.a2;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.util.UITimer;

public class Game extends Form implements Runnable{
    GameWorld gw;

    public Game() {
        gw = new GameWorld();

        addKeyListener(-91, (evt) -> gw.accelerate());
        addKeyListener(-92, (evt) -> gw.brake());
        addKeyListener(-93, (evt) -> gw.left());
        addKeyListener(-94, (evt) -> gw.right());

        addKeyListener('f', (evt) -> gw.fight());
        addKeyListener('d', (evt) -> gw.drink());
        addKeyListener('Q', (evt) -> gw.quit());

        UITimer timer = new UITimer(this);
        timer.schedule(100,true,this);

        this.getAllStyles().setBgColor(ColorUtil.BLACK);
        this.show();
    }

    public static int getDispW(){
        return Display.getInstance().getDisplayWidth();
    }

    public static int getDispH(){
        return Display.getInstance().getDisplayHeight();
    }

    public static int getSmallDim(){
        return Math.min(getDispH(),getDispW());
    }

    public void paint(Graphics g){
        super.paint(g);
        gw.draw(g);
    }

    @Override
    public void run(){
        gw.tick();
        repaint();
    }
}
