package com.example.uytai.retrofit.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uytai.retrofit.R;
import com.example.uytai.retrofit.model.Movie;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    TextView tvTitle, tvRating, tvDescription, tvDate;
    ImageView imgPoster_path;
    Integer Id;
    Double rating;
    String title, poster_path, description, date;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        AnhXa();
        GetInformation();
        SetInformation();

        toolbar = findViewById(R.id.toolbar);
        ActionToolbar();
    }

    private void ActionToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void SetInformation() {
        tvTitle.setText(title);
        tvDescription.setText(description);
        tvRating.setText(rating+"");

        //
        String date_str = date;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date2 = format.parse(date_str);
            SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
            String finalString = newFormat.format(date2);
            tvDate.setText(finalString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String BASE_URL = "http://image.tmdb.org/t/p/w185/";

        Picasso.with(getApplicationContext()).load(BASE_URL+poster_path)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.error_image)
                .into(imgPoster_path);
    }

    private void GetInformation() {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            title = bundle.getString("Title");
            description = bundle.getString("Description");
            rating = bundle.getDouble("Rating");
            poster_path = bundle.getString("Poster");
            date = bundle.getString("Date");
        }


    }

    private void AnhXa() {
        tvTitle = findViewById(R.id.title);
        tvRating = findViewById(R.id.rating);
        tvDescription = findViewById(R.id.description);
        imgPoster_path = findViewById(R.id.poster_path);
        tvDate = findViewById(R.id.subtitle);
    }
}
