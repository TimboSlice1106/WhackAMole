package com.example.whackamole;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whackamole.R;

public class ImageActivity extends AppCompatActivity {

    private RadioButton cowButton;
    private RadioButton moleButton;
    private RadioButton frogButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        cowButton = findViewById(R.id.cowButton);
        moleButton = findViewById(R.id.moleButton);
        frogButton = findViewById(R.id.frogButton);
    }

    @Override
    public void onBackPressed() {
        int image;
        if(moleButton.isChecked())
            image = 1;
        else if(cowButton.isChecked())
            image = 2;
        else
            image = 3;
        Intent i = new Intent();
        i.putExtra("IMAGE", image);
        setResult(RESULT_OK, i);
        finish();
    }
}
