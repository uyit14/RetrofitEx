package com.example.uytai.retrofit.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.uytai.retrofit.R;
import com.example.uytai.retrofit.adapter.MoviesAdapter;
import com.example.uytai.retrofit.model.Movie;
import com.example.uytai.retrofit.model.MovieResponse;
import com.example.uytai.retrofit.rest.ApiClient;
import com.example.uytai.retrofit.rest.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "7e8f60e325cd06e164799af1e317d7a7";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(API_KEY.isEmpty()){
            Toast.makeText(this, "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
            return;
        }

        //
        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<MovieResponse> call = apiService.getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext()));
                Log.d(TAG, "Number of movies received:" + movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });

        //


    }

    //Các bước
    //Bước 1: Add in dependencies and add INTERNET permissions
    //Bước 2: Tạo các package activity, adapter, rest, model
    //Bước 3: Trong package Model tạo class với các thuộc tính trong API
    //Bước 4: Tạo class ApiClient và ApiInterface trong rest
        //Path: Sử dụng URL 1 cách động dựa vào biến truyền vào
        //Query: Chúng ta có thể nối thêm paramater vào sau URL
        //Body: Sử dụng retrofit để trả về tất cả ở trong body
        //Header: Các thực để sử dụng username và password
    //Bước 5: code trong MainActivity, với API="your_key"
    //Bước 6: Tạo item cho RecycerView
    //Bước 7: Custom Adapter
        //1. Tạo Holder extends RecyclerView.ViewHolder
        //2. Extends cho lớp cha
}
