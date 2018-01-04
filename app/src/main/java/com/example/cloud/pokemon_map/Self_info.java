package com.example.cloud.pokemon_map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.cloud.pokemon_map.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class Self_info extends AppCompatActivity {

    public static void actionStart(Context context, String userName) {
        Intent intent = new Intent(context, Self_info.class);
        intent.putExtra("userName", userName);
        context.startActivity(intent);
    }

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_info_activity_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.self_info_toolbar);
        setSupportActionBar(toolbar);

        CircleImageView circle_headshot = (CircleImageView) findViewById(R.id.self_info_circle_headshot);
        TextView text_name = (TextView) findViewById(R.id.self_info_textview_username);
        TextView text_id = (TextView) findViewById(R.id.self_info_textview_userid);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");

        List<User> users = DataSupport.where("user_name = ?", userName).find(User.class);
        for (User user: users) {
            Log.d("Self_info", "user id is " + user.getId());

            text_name.setText(user.getUser_name());
            text_id.setText(String.valueOf(user.getId()));

            if (!Objects.equals(user.getUser_headshot(), "default")) {
                Log.d("Self_info", "headshot not default");
            }
        }

        // 呼出滑动菜单
        mDrawerLayout = (DrawerLayout) findViewById(R.id.self_info_drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;

            default:
                break;
        }
        return true;
    }
}
