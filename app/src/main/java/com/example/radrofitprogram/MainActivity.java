package com.example.radrofitprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.radrofitprogram.databinding.ActivityMainBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,R.layout.activity_main);

        binding.reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name",binding.name.getText().toString().trim());
                    jsonObject.put("mobile",binding.phone.getText().toString().trim());
                    jsonObject.put("email",binding.email.getText().toString().trim());
                    jsonObject.put("pswrd",binding.password.getText().toString().trim());
                    jsonObject.put("baction","register_user");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://androindian.com/apps/example_app/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                RetroInterface retroInterface=retrofit.create(RetroInterface.class);

                JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

                Call<RegResponse> regResponseCall=retroInterface.createUser(object);

                regResponseCall.enqueue(new Callback<RegResponse>() {
                    @Override
                    public void onResponse(Call<RegResponse> call, Response<RegResponse> response) {
                        String res=response.body().getResponse();

                        if(res.equalsIgnoreCase("failed")){
                            Toast.makeText(MainActivity.this, ""+response.body().getUser(), Toast.LENGTH_SHORT).show();
                            binding.message.setText(response.body().getUser());

                        }else if(res.equalsIgnoreCase("success")){
                            Toast.makeText(MainActivity.this, ""+response.body().getUser(), Toast.LENGTH_SHORT).show();
                            binding.message.setText(response.body().getUser());

                            binding.name.setText("");
                            binding.email.setText("");
                            binding.password.setText("");
                            binding.phone.setText("");


                        }else{
                            Toast.makeText(MainActivity.this, ""+response.body().getUser(), Toast.LENGTH_SHORT).show();
                            binding.message.setText(response.body().getUser());
                        }
                    }

                    @Override
                    public void onFailure(Call<RegResponse> call, Throwable t) {

                        Toast.makeText(MainActivity.this, ""+t.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });



    }
}
