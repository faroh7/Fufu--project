package com.example.carpoolingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RateTrip extends AppCompatActivity {

    RatingBar ratingBar;
    Trip rateTrip;
    Button button;
    DatabaseHelper helpa;

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_trip);
        db = new DatabaseHelper(getApplicationContext());

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //finding the specific RatingBar with its unique ID
        LayerDrawable stars=(LayerDrawable)ratingBar.getProgressDrawable();

        //Use for changing the color of RatingBar
        stars.getDrawable(2).setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_ATOP);

        button = findViewById(R.id.submitRating);

        Bundle bundle = getIntent().getExtras();
        System.out.println(bundle);
        String driver = bundle.getString("driver");
        String passenger = bundle.getString("passenger");
        String destination = bundle.getString("destination");
        String source = bundle.getString("source");
        System.out.println("On the other page");
        System.out.println(driver);
        System.out.println(passenger);
        System.out.println(destination);
        System.out.println(source);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean success =
                db.addRating(driver,passenger,source,destination,ratingBar.getRating());
                if(success){
                    System.out.println("Rating added successfully");
                    Toast.makeText(getApplicationContext(), "Rating added successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    System.out.println("Rating not added");
                    Toast.makeText(getApplicationContext(), "Rating not added", Toast.LENGTH_SHORT).show();
                }
                
            }
        });
//        if(bundle != null && bundle.containsKey("Tree")){
//            rateTrip = (Trip) bundle.getSerializable("Tree");
//        }
    }

    public void Call(View v)
    {
        // This function is called when button is clicked.
        // Display ratings, which is required to be converted into string first.
        TextView t = (TextView)findViewById(R.id.textView2);
        t.setText("You Rated :"+String.valueOf(ratingBar.getRating()));
        System.out.println();
    }
}