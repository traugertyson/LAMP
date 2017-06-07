package com.david.lamp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.david.lamp.colors.Hue;
import com.david.lamp.colors.RGB;
import com.david.lamp.colors.Transition;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<RGB> colors = new ArrayList<>();
        ArrayList<Transition> transitions = new ArrayList<>();
        colors.add(new RGB(0, 100, 100));
        colors.add(new RGB(0, 50, 200));
        transitions.add(new Transition(Transition.TransitionType.FADE, 10));
        transitions.add(new Transition(Transition.TransitionType.FADE, 20));
        String name = "BlueGreen";
        Hue h = new Hue(name ,colors, transitions);

        HueView hueView = (HueView) findViewById(R.id.hueView);
        hueView.setHue(h);

    }


}
