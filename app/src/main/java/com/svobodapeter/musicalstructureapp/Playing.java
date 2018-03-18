package com.svobodapeter.musicalstructureapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by pitrs on 13.03.2018.
 */

public class Playing extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playing);

        /**
         * Intent which will open MainActivity.class from Playing.class
         */
        ImageButton mainActivity = findViewById(R.id.main_activity);
        mainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                //Process after activation of event
                Intent mainIntentActivity = new Intent(Playing.this,MainActivity.class);
                startActivity(mainIntentActivity);
            }
        });

        getActivityIntent();

    }

    /**
     * Method will get data from MainActivity when this activity is called by Intent myIntent
     */
    public void getActivityIntent (){
        Intent prevIntent = getIntent();
        if (prevIntent != null) {
            String getArtistName = prevIntent.getStringExtra("Artist");
            String getSongName = prevIntent.getStringExtra("Song");
            TextView showArtist = findViewById(R.id.artist_name_playing);
            TextView showSong = findViewById(R.id.song_name_playing);
            showArtist.setText(getArtistName);
            showSong.setText(getSongName);
        }

    }
}