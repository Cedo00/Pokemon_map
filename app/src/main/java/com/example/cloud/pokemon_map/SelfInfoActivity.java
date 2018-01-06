package com.example.cloud.pokemon_map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.cloud.pokemon_map.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class SelfInfoActivity extends AppCompatActivity {

    public static void actionStart(Context context, String userName) {
        Intent intent = new Intent(context, SelfInfoActivity.class);
        intent.putExtra("userName", userName);
        context.startActivity(intent);
    }

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.self_info_activity_layout);

        Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");

        Toolbar toolbar = (Toolbar) findViewById(R.id.self_info_toolbar);
        setSupportActionBar(toolbar);

        // 设置用户信息
        CircleImageView circle_selfInfo_headshot = (CircleImageView) findViewById(R.id.self_info_circle_headshot);
        TextView text_name = (TextView) findViewById(R.id.self_info_textview_username);
        TextView text_id = (TextView) findViewById(R.id.self_info_textview_userid);

        String name = "";
        String id = "";
        String headshot = "";
        boolean isHeadShotDefault = true;

        List<User> users = DataSupport.where("user_name = ?", userName).find(User.class);
        for (User user: users) {
            Log.d("SelfInfoActivity", "user id is " + user.getId());

            name = user.getUser_name();
            id = String.valueOf(user.getId());

            text_name.setText(name);
            text_id.setText(id);

            // 设置头像
            if (!Objects.equals(user.getUser_headshot(), "default")) {
                isHeadShotDefault = false;
                headshot = user.getUser_headshot();
                Log.d("SelfInfoActivity", "headshot not default");
            }
        }

        // 呼出滑动菜单
        mDrawerLayout = (DrawerLayout) findViewById(R.id.self_info_drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        // 对滑动菜单中的内容进行更改
        NavigationView navView = (NavigationView) findViewById(R.id.self_info_nav_view);
        View header = navView.getHeaderView(0);
        TextView menu_name = (TextView) header.findViewById(R.id.drawer_menu_textview_username);
        TextView menu_id = (TextView) header.findViewById(R.id.drawer_menu_textview_userid);
        CircleImageView circle_menu_headshot = (CircleImageView) header.findViewById(R.id.drawer_menu_headshot);
        menu_id.setText("UserId: 00" + id);
        menu_name.setText("UserName: " + name);
        if (!isHeadShotDefault) {
            Log.d("SelfInfoActivity", "headshot not default");
        }

        // 滑动菜单项点击事件
        navView.setCheckedItem(R.id.drawer_menu_profile);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Intent intent;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.drawer_menu_profile:
                        break;

                    case R.id.drawer_menu_handbook:
                        PmHandbookActivity.actionStart(SelfInfoActivity.this, userName);
                        break;

                    case R.id.drawer_menu_notbook:
                        break;

                    case R.id.drawer_menu_update_handbook:
                        break;

                    default:
                        break;
                }
                return true;
            }
        });
    }

    // 让导航按钮起作用
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
