package com.example.restfulapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restfulapi.restapi.Apiget;
import com.example.restfulapi.restapi.modelclass;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textViewresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewresult = findViewById(R.id.texviewdata);

        String Baseurl = "https://simplifiedcoding.net/demos/";
        // String feed="marvel";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(Baseurl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Apiget myhelperclass = retrofit.create(Apiget.class);

        Call<List<modelclass>> call = myhelperclass.getusuer();

        call.enqueue(new Callback<List<modelclass>>() {
            @Override
            public void onResponse(Call<List<modelclass>> call, Response<List<modelclass>> response) {
                if (!response.isSuccessful()) {
                    textViewresult.setText("Code: " + response.code());
                    return;
                }
                List<modelclass> modelclassList = response.body();


                for (modelclass user : modelclassList)
                {
                    String contenet = "";
                    contenet += "Name:" + user.getName() + "\n";
                    contenet += "RealName:" + user.getRealname() + "\n";
                    contenet += "Bio:" + user.getBio() + "\n";
                    contenet += "Image:" + user.getImageurl() + "\n\n";
                    textViewresult.append(contenet);
                }
            }

            @Override
            public void onFailure(Call<List<modelclass>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}