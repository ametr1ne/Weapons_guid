package com.example.weapons_guide;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Text_Content_Activity extends AppCompatActivity {

    private SharedPreferences def_pref;
    private ActionBar actionBar;
    private Typeface face1;
    private TextView text_content;
    private ImageView i_content;
    private int category;
    private int position;

    private int[] array_guns = {R.string.five_seven, R.string.usp};
    private int[] array_rifles = {R.string.ak47, R.string.m4a1, R.string.famas};
    private int[] array_weights = {R.string.negev};
    private int[] array_other = {R.string.knife};

    private int[] array_image_guns = {R.drawable.five_seven, R.drawable.usp};
    private int[] array_image_rifles = {R.drawable.ak47, R.drawable.m4a1, R.drawable.famas};
    private int[] array_image_weights = {R.drawable.negev};
    private int[] array_image_other = {R.drawable.knife};

    private String[] array_title_guns = {"Five-seven", "USP-S"};
    private String[] array_title_rifles = {"AK-47", "M4A1", "Famas"};
    private String[] array_title_weights = {"Negev"};
    private String[] array_title_other = {"Knife"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_content);
        init();
        receiveIntent();
    }
    private void receiveIntent() {
        Intent i = getIntent();
        category = i.getIntExtra("category", 0);
        position = i.getIntExtra("position", 0);
        if (i != null) {
            switch (category) {
                case 0:
                    actionBar.setTitle(array_title_guns[position]);
                    i_content.setImageResource(array_image_guns[position]);
                    text_content.setText(array_guns[position]);
                    break;
                case 1:
                    actionBar.setTitle(array_title_rifles[position]);
                    i_content.setImageResource(array_image_rifles[position]);
                    text_content.setText(array_rifles[position]);
                    break;
                case 2:
                    actionBar.setTitle(array_title_weights[position]);
                    i_content.setImageResource(array_image_weights[position]);
                    text_content.setText(array_weights[position]);
                    break;
                case 3:
                    actionBar.setTitle(array_title_other[position]);
                    i_content.setImageResource(array_image_other[position]);
                    text_content.setText(array_other[position]);
                    break;
            }
        }
    }

    private void init() {

        def_pref = PreferenceManager.getDefaultSharedPreferences(this);
        text_content = findViewById(R.id.text_main_content);
        i_content = findViewById(R.id.image_content);
        face1 = Typeface.createFromAsset(this.getAssets(), "fonts/Lexend-Regular.ttf");
        text_content.setTypeface(face1);
        actionBar = getSupportActionBar();
        String text = def_pref.getString("list_preference_1", "Middle");
        if (text != null) {
            switch (text) {
                case "Big":
                    text_content.setTextSize(24);
                    break;
                case "Middle":
                    text_content.setTextSize(18);
                    break;
                case "Small":
                    text_content.setTextSize(14);
                    break;
            }
        }
    }
}
