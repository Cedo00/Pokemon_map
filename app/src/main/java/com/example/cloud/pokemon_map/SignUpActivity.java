package com.example.cloud.pokemon_map;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cloud.pokemon_map.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Objects;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    Intent intent;

    EditText ET_userName;
    EditText ET_passward;

    private String userName;
    private String passward;
    private boolean gender = false;
    private boolean isAdmin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_activity_layout);

        Button button_submit = (Button) findViewById(R.id.SignUp_Button_submit);
        Button button_return = (Button) findViewById(R.id.SignUp_Button_return);
        button_submit.setOnClickListener(this);
        button_return.setOnClickListener(this);

        ET_userName = (EditText) findViewById(R.id.SIgnUp_EditText_username);
        ET_passward = (EditText) findViewById(R.id.SignUp_EditText_password);

        RadioGroup radioGroup_gender = (RadioGroup) findViewById(R.id.SignUp_radioGroup_gender);
        radioGroup_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                String result = radioButton.getText().toString();

                if (Objects.equals("male", result)) {
                    gender = true;
                }

                Log.d("SignUp", "gender = " + result);
            }
        });
        RadioGroup radioGroup_isAdmin = (RadioGroup) findViewById(R.id.SignUp_radioGroup_isAdmin);
        radioGroup_isAdmin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = (RadioButton) group.findViewById(checkedId);
                String result = radioButton.getText().toString();

                if (Objects.equals("true", result)) {
                    isAdmin = true;
                }

                Log.d("SignUp", "isAdmin = " + result);
            }
        });
    }

    @Override
    public void onClick(View v) {
        userName = ET_userName.getText().toString();
        passward = ET_passward.getText().toString();

        switch (v.getId()) {
            case R.id.SignUp_Button_submit:
                Log.d("SignUp", "userName = " + userName);
                Log.d("SignUp", "passward = " + passward);
                Log.d("SignUp", "Gender = " + gender);
                Log.d("SignUp", "isAdmin = " + isAdmin);

                if (isCorrectSignUp()) {
                    User user = new User();
                    user.setUser_name(userName);
                    user.setUser_passward(passward);
                    user.setUser_gender(gender);
                    user.setUser_admin(isAdmin);
                    user.setUser_headshot("default");
                    user.save();
                    finish();
                }
                else {
                    Toast.makeText(SignUpActivity.this, "用户名已存在.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.SignUp_Button_return:
                Log.d("SignUp", "return");
                intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
    }

    private boolean isCorrectSignUp() {
        Log.d("SignUp", "isCorrectSignUp");

        List<User> users = DataSupport.select("user_Passward")
                .where("user_name = ?", userName)
                .find(User.class);

        for (User user: users) {
            Log.d("SignUp", "user: " + user.getUser_passward());

            return false;
        }
        
        return true;
    }
}
