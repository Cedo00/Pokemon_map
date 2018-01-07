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
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.cloud.pokemon_map.db.Notebook;
import com.example.cloud.pokemon_map.db.User;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class NoteActivity extends AppCompatActivity {

    public static void actionStart(Context context, String userName) {
        Intent intent = new Intent(context, NoteActivity.class);
        intent.putExtra("userName", userName);
        context.startActivity(intent);
    }

    private DrawerLayout mDrawerLayout;

    List<Notebook> notebooks;
    private List<NoteItem> noteList = new ArrayList<>();
    private NoteAdapter noteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_activity_layout);

        Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");

        Toolbar toolbar = (Toolbar) findViewById(R.id.note_toolbar);
        setSupportActionBar(toolbar);

        // 设置用户信息
        String name = "";
        String id = "";
        String headshot = "";
        boolean isHeadShotDefault = true;

        List<User> users = DataSupport.where("user_name = ?", userName).find(User.class);
        for (User user: users) {
            Log.d("NoteActivity", "user id is " + user.getId());

            name = user.getUser_name();
            id = String.valueOf(user.getId());

            // 设置头像
            if (!Objects.equals(user.getUser_headshot(), "default")) {
                isHeadShotDefault = false;
                headshot = user.getUser_headshot();
                Log.d("NoteActivity", "headshot not default");
            }
        }

        // 呼出滑动菜单
        mDrawerLayout = (DrawerLayout) findViewById(R.id.note_drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        // 对滑动菜单中的内容进行更改
        NavigationView navView = (NavigationView) findViewById(R.id.note_nav_view);
        View header = navView.getHeaderView(0);
        TextView menu_name = (TextView) header.findViewById(R.id.drawer_menu_textview_username);
        TextView menu_id = (TextView) header.findViewById(R.id.drawer_menu_textview_userid);
        CircleImageView circle_menu_headshot = (CircleImageView) header.findViewById(R.id.drawer_menu_headshot);
        menu_id.setText("UserId: 00" + id);
        menu_name.setText("UserName: " + name);
        if (!isHeadShotDefault) {
            Log.d("NoteActivity", "headshot not default");
        }

        // 滑动菜单项点击事件
        navView.setCheckedItem(R.id.drawer_menu_notbook);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.drawer_menu_profile:
                        SelfInfoActivity.actionStart(NoteActivity.this, userName);
                        break;

                    case R.id.drawer_menu_handbook:
                        PmHandbookActivity.actionStart(NoteActivity.this, userName);
                        break;

                    case R.id.drawer_menu_notbook:
                        break;

                    default:
                        break;
                }
                return true;
            }
        });

        // 设置用户的记事本
        notebooks = DataSupport.where("note_username = ?", userName)
                               .find(Notebook.class);

        // 设置RecyclerView
        initNotes();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.note_recyclerview);
        GridLayoutManager layoutManger = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManger);
        noteAdapter = new NoteAdapter(noteList);
        recyclerView.setAdapter(noteAdapter);
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

    // 随机设置一些记录
    private void initNotes() {
        noteList.clear();
        for (int i = 0; i < 50; i ++) {
            Random random = new Random();
            int index = random.nextInt(notebooks.size());

            Notebook temp_note = notebooks.get(index);
            NoteItem item = new NoteItem(temp_note.getNote_username(),
                                         temp_note.getId(),
                                         temp_note.getNote_date(),
                                         temp_note.getNote_title(),
                                         temp_note.getNote_content(),
                                         temp_note.getNote_picture());
            noteList.add(item);
        }
    }
}
