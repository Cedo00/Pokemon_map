package com.example.cloud.pokemon_map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cloud.pokemon_map.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Objects;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {

    private Intent intent;

    EditText ET_userName;
    EditText ET_passward;

    private String userName = "*";
    private String passward = "*";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity_layout);

        ET_userName = (EditText) findViewById(R.id.Login_EditText_username);
        ET_passward = (EditText) findViewById(R.id.Login_EditText_password);

        Button button_sign_in = (Button) findViewById(R.id.Login_Button_sign_in);
        Button button_sign_up = (Button) findViewById(R.id.Login_Button_sign_up);
        button_sign_in.setOnClickListener(this);
        button_sign_up.setOnClickListener(this);

        Button dbtest = (Button) findViewById(R.id.dbtest);
        dbtest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        userName = ET_userName.getText().toString();
        passward = ET_passward.getText().toString();

        switch (v.getId()) {
            case R.id.Login_Button_sign_in:
                Log.d("SignIn", "userName = " + userName);
                Log.d("SignIn", "passward = " + passward);
                if (isCorrectSignIn()) {
                    SelfInfoActivity.actionStart(SignInActivity.this, userName);
                }
                else {
                    Toast.makeText(SignInActivity.this, "错误的用户名或密码.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.Login_Button_sign_up:
                intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
                break;

            case R.id.dbtest:
                intent = new Intent(SignInActivity.this, dbtest.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    private boolean isCorrectSignIn() {
        Log.d("SignIn", "isCorrectSignIn()");

        List<User> users = DataSupport.select("user_Passward")
                                     .where("user_Name = ?", userName)
                                     .find(User.class);

        if (users != null) {
            for (User user : users) {
                if (Objects.equals(passward, user.getUser_passward())) {
                    return true;
                }
                Log.d("SignIn", "passward = " + user.getUser_passward());
            }
        }
        return false;
    }
}
