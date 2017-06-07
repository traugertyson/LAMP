package com.david.lamp.colors;

import java.util.ArrayList;

/**
 * Created by david on 6/7/17.
 */

public class Hue {

    public ArrayList<RGB> colors;
    public ArrayList<Transition> transitions;
    public boolean current;
    public String name;

    public Hue(String name, ArrayList<RGB> colors, ArrayList<Transition> transitions) {
        this.name = name;
        this.colors = colors;
        this.transitions = transitions;
    }





}
