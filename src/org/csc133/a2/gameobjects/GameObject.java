package org.csc133.a2.gameobjects;

import com.codename1.ui.geom.Point;

import java.util.ArrayList;
import java.util.Arrays;

public class GameObject {
    Point p = new Point(0,0);
    Fire fire = new Fire(p);
    ArrayList<Fire> fList = new ArrayList<>(Arrays.asList(fire));


    public GameObject(){

    }
    public ArrayList<Fire> getGameObject(){
        return fList;
    }
}
