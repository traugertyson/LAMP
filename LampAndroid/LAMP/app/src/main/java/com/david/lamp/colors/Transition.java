package com.david.lamp.colors;

/**
 * Created by david on 6/7/17.
 */

public class Transition {

    public TransitionType type;
    public int interval;

    public enum TransitionType {
        SOLID, FADE, JUMP

    }

    public Transition(TransitionType type, int interval) {
        this.type = type;
        this.interval = interval;
    }

}
