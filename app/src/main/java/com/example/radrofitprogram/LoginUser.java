package com.example.radrofitprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.radrofitprogram.databinding.ActivityLoginUserBinding;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginUser extends AppCompatActivity {

    ActivityLoginUserBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       binding = DataBindingUtil.setContentView(LoginUser.this,R.layout.activity_login_user);

       binding.Reg.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(LoginUser.this,MainActivity.class);
               startActivity(intent);
           }
       });

       binding.login.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               JSONObject jsonObject = new JSONObject();
               try {
                   jsonObject.put("mobile",binding.phone.getText().toString().trim());
                   jsonObject.put("pswrd",binding.password.getText().toString().trim());
                   jsonObject.put("baction","login_user");
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               Retrofit retrofit = new Retrofit.Builder()
                       .baseUrl("http://androindian.com/apps/example_app/")
                       .addConverterFactory(GsonConverterFactory.create())
                       .build();

               RetroInterface retroInterface=retrofit.create(RetroInterface.class);

               JsonObject object=new JsonParser().parse(jsonObject.toString()).getAsJsonObject();

               Call<LoginResponse> regResponseCall=retroInterface.LoginUser(object);

              regResponseCall.enqueue(new Callback<LoginResponse>() {
                  @Override
                  public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                      String res = response.body().getResponse();

                      if (res.equalsIgnoreCase("success")) {
                          Toast.makeText(LoginUser.this, "success"+ response.body().getData(), Toast.LENGTH_SHORT).show();

                          Intent intent = new Intent(LoginUser.this,ViewPage.class);
                          startActivity(intent);

                      } else if (res.equalsIgnoreCase("failed")) {
                          Toast.makeText(LoginUser.this, "Failed", Toast.LENGTH_SHORT).show();

                      } else {
                          Toast.makeText(LoginUser.this, "Error", Toast.LENGTH_SHORT).show();
                      }
                  }

                  @Override
                  public void onFailure(Call<LoginResponse> call, Throwable t) {

                      Toast.makeText(LoginUser.this, "" + t.toString(), Toast.LENGTH_SHORT).show();
                  }
              });

           }
       });
    }
}
