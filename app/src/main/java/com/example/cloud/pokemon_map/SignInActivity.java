package com.example.cloud.pokemon_map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;

    private Button button_sign_in;

    private Button button_sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        button_sign_in = (Button) findViewById(R.id.Login_Button_sign_in);
        button_sign_up = (Button) findViewById(R.id.Login_Button_sign_up);
        button_sign_in.setOnClickListener(this);
        button_sign_up.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Login_Button_sign_in:
                break;

            case R.id.Login_Button_sign_up:
                intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
