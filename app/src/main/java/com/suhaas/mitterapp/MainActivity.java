package com.suhaas.mitterapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText et_value;
    Button btn_post;
    TextView tv_response;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_value = (EditText) findViewById(R.id.et_value);
        btn_post = (Button) findViewById(R.id.btn_post);
        tv_response = (TextView) findViewById(R.id.tv_response);

        et_value.setFilters(new InputFilter[] {
                new InputFilter.AllCaps() {
                    @Override
                    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                        return String.valueOf(source).toLowerCase().replace(" ", "");
                    }
                }
        });

        apiService = ApiClient.createService(ApiService.class);
        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                apiService.submitPost(String.valueOf(et_value.getText().toString())).enqueue(new Callback<MitterResponse>() {
                    @Override
                    public void onResponse(Call<MitterResponse> call, Response<MitterResponse> response) {
                        int statusCode = response.code();
                        Log.d("Main", "onResponse: " + statusCode);
                        Log.d("Main", "rawResponse: " + response.raw().toString());
                        if (statusCode == 200){
                            tv_response.setText("Value : " + response.body().getMsg());
                        } else if (statusCode == 404){
                            tv_response.setText("Value : No value available");
                        } else if (statusCode == 500){
                            tv_response.setText("Value : Please try again later");
                        } else if (statusCode == 403){
                            tv_response.setText("Value : You don't have clearance!");
                        }
                        et_value.setText(null);
                    }

                    @Override
                    public void onFailure(Call<MitterResponse> call, Throwable t) {
                        Log.d("Main", "onFailure: " + t.getMessage());
                    }
                });
            }
        });

    }
}
