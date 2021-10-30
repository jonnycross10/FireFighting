package org.csc133.a2;

import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Point;
import org.csc133.a2.gameobjects.*;

import java.util.ArrayList;
import java.util.Random;

public class GameWorld{
    public static final int INIT_FUEL = 25000;
    River river;
    Helipad helipad;
    Helicopter helicopter;
    ArrayList<Fire> fires;
    GameObject go;

    public GameWorld(){
        init();
    }

    public static int randomRangedInt(int lowerBound, int upperBound) {
        return new Random().nextInt(upperBound-lowerBound) +lowerBound;
    }

    public static int squaredDistanceBetween(Point here, Point there) {
        int dx = here.getX() - there.getX();
        int dy = here.getY() - there.getY();
        return (dx * dx + dy *dy);
    }

    void init(){
        go = new GameObject();
        river = new River();
        helipad = new Helipad();
        Point p;
        helicopter = new Helicopter(helipad.getLZ());
        fires = new ArrayList<Fire>();
        Point fire1Loc = new Point(Game.getDispW()/5 +randomRangedInt(0,25),
                Game.getDispH()/25 + randomRangedInt(0,25));
        Point fire2Loc = new Point(Game.getDispW()-Game.getDispW()/5 - randomRangedInt(0,25),
                Game.getDispH()/25 - randomRangedInt(0,25));
        Point fire3Loc = new Point(Game.getDispW()/2 - randomRangedInt(0,25),
                Game.getDispH()/2+randomRangedInt(0,25));

        Fire fire1 = new Fire(fire1Loc);
        Fire fire2 = new Fire(fire2Loc);
        Fire fire3 = new Fire(fire3Loc);

        fires.add(fire1);
        fires.add(fire2);
        fires.add(fire3);
    }

    void accelerate(){
        helicopter.accelerate();
    }
    void brake(){
        helicopter.brake();
    }

    void left(){
        helicopter.turnLeft();
    }
    void right(){
        helicopter.turnRight();
    }
    void fight(){
        for(Fire fire: fires){
            if(helicopter.near(fire)){
                helicopter.fight(fire);
            }
        }
    }

    void drink(){
        if(river.underHelicopter(helicopter)){
            helicopter.drinkFrom(river);
        }
    }

    void quit(){
        Display.getInstance().exitApplication();
    }

    void tick(){
        boolean firesAreOut = true;

        helicopter.move();

        for(Fire fire : fires){

            if(randomRangedInt(0,10)==0){
                fire.grow();
            }
            if(fire.isStillBurning()){
                firesAreOut = false;
            }
        }

        if(helicopter.hasLandedAt(helipad) && firesAreOut){
            restartOrQuit(Dialog.show("Game over", "You won\n Score "+ helicopter.getFuel() +"Play again?",
                    "yes", "no"));

        }

        if(helicopter.getFuel() <= 0){
            restartOrQuit(Dialog.show("Game over", "You lost\n Play again?","Yes","No"));

        }
    }

    private void restartOrQuit(boolean restart){
        if(restart)
            this.init();
        else
            this.quit();
    }

    void draw(Graphics g) {
        g.clearRect(0,0,Game.getDispW(),Game.getDispH());
        river.draw(g);
        helipad.draw(g);
        for (Fire fire: fires){
            fire.draw(g);
        }

        helicopter.draw(g);
    }

    public int getTotalFireSize(){
        int sizeFires = 0;
        ArrayList<GameObject> gameObjectCollection =  getGameObjectCollection();
        for(GameObject go: gameObjectCollection){
            /*
            if(go instanceof Fire){
                Fire fire = (Fire) go;
                //to be implemented
            }

             */
        }
        return sizeFires;
    }

    public ArrayList<GameObject> getGameObjectCollection(){
        return new ArrayList<GameObject>();
    }
}

