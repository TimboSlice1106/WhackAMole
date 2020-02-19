package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.gridlayout.widget.GridLayout;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private GridLayout grid;
    private Drawable moleImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    private Handler handler; //used to make moles move
    private Button button;
    private boolean on;
    public moveMole moveMole;
    public int count;
    private TextView Score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        imageViews = new ImageView[16];
        rand = new Random();
        moleLocation = rand.nextInt(16);
        on = false;
        handler = new Handler();
        moveMole = new moveMole();
        button = findViewById(R.id.button);
        count = 0;
        Score = findViewById(R.id.Score);

        for (int i = 0; i < 16; i++) {
            imageViews[i] = (ImageView) getLayoutInflater().inflate(R.layout.mole_view, null);
            imageViews[i].setMinimumWidth(270);
            imageViews[i].setMinimumHeight(270);
            if (i == moleLocation) imageViews[i].setImageDrawable(moleImage);
            grid.addView(imageViews[i]);
        }
    }

    public void startPressed(View v) {
        if (on) {
            on = false;
            handler.removeCallbacks(moveMole);
        } else {
            on = true;
            handler.postDelayed(moveMole, 1000);
        }


    }

    private class moveMole implements Runnable {
        @Override
        public void run() {
            imageViews[moleLocation].setImageDrawable(null);
            moleLocation = rand.nextInt(16);
            imageViews[moleLocation].setImageDrawable(moleImage);
            handler.postDelayed(moveMole, 1000);
        }
    }

    public void points(View v){
        if (v == imageViews[moleLocation]) {
            Score.setText(""+count++);
        }
    }

}