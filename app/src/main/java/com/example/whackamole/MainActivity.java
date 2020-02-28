package com.example.whackamole;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
    private Drawable cowImage;
    private Drawable frogImage;
    private ImageView[] imageViews;
    private int moleLocation;
    private Random rand;
    private Handler handler; //used to make moles move
    private Button animalbutton;
    private boolean on;
    public moveMole moveMole;
    public int count;
    private TextView Score;
    private int image;
    private int done;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        grid = findViewById(R.id.gridLayout);
        moleImage = getDrawable(R.drawable.mole);
        frogImage = getDrawable(R.drawable.frog);
        cowImage = getDrawable(R.drawable.cow);
        imageViews = new ImageView[16];
        rand = new Random();
        moleLocation = rand.nextInt(16);
        on = false;
        handler = new Handler();
        moveMole = new moveMole();
        animalbutton = findViewById(R.id.button);
        count = 0;
        Score = findViewById(R.id.Score);
        image = 1;
        done = moleLocation;

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

    public void imagePressed(View v) {
        Intent i = new Intent(this, ImageActivity.class);
        i.putExtra("IMAGE", image);
        startActivityForResult(i, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        image = data.getIntExtra("IMAGE", 1);
    }

    private class moveMole implements Runnable {
        @Override
        public void run() {
            int done;
            done = moleLocation;
            moleLocation = rand.nextInt(16);
            if (image == 1) {
                imageViews[moleLocation].setImageDrawable(moleImage);
            } else if (image == 2) {
                imageViews[moleLocation].setImageDrawable(cowImage);
            } else {
                imageViews[moleLocation].setImageDrawable(frogImage);
            }
            imageViews[done].setImageDrawable(null);
            handler.postDelayed(moveMole, 1000);
        }
    }

    public void points(View v){
        if (v == imageViews[moleLocation]) {
            Score.setText(""+count++);
        }
    }

}