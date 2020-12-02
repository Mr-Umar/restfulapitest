package com.example.restfulapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restfulapi.restapi.Api;
import com.example.restfulapi.restapi.helperuser;

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

        Api myhelperclass = retrofit.create(Api.class);
        Call<List<helperuser>> call = myhelperclass.getusuer();

        call.enqueue(new Callback<List<helperuser>>() {
            @Override
            public void onResponse(Call<List<helperuser>> call, Response<List<helperuser>> response) {
                if (!response.isSuccessful()) {
                    textViewresult.setText("Code: " + response.code());
                    return;
                }
                List<helperuser> helperuserList = response.body();
                for (helperuser user : helperuserList)
                {
                    String contenet = "";
                    contenet += "Name:" + user.getName() + "\n";
                    contenet += "RealName:" + user.getRealname() + "\n";
                    contenet += "Bio:" + user.getBio() + "\n";
                    contenet += "Image:" + user.getFirstappearance() + "\n\n";
                    textViewresult.append(contenet);
                }
            }

            @Override
            public void onFailure(Call<List<helperuser>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}