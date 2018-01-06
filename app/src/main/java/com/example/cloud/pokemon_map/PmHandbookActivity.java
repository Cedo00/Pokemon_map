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

import com.example.cloud.pokemon_map.db.Pokemon;
import com.example.cloud.pokemon_map.db.User;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class PmHandbookActivity extends AppCompatActivity {

    private String TAG = "PmHangbook";

    public static void actionStart(Context context, String userName) {
        Intent intent = new Intent(context, PmHandbookActivity.class);
        intent.putExtra("userName", userName);
        context.startActivity(intent);
    }

    private DrawerLayout mDrawerLayout;

    List<Pokemon> pokemons = DataSupport.findAll(Pokemon.class);
    private List<PokemonItem> pokemonList = new ArrayList<>();
    private PokemonAdapter pokemonAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pm_handbook_activity_layout);

        Intent intent = getIntent();
        final String userName = intent.getStringExtra("userName");

        Toolbar toolbar = (Toolbar) findViewById(R.id.pm_handbook_toolbar);
        setSupportActionBar(toolbar);

        // 设置用户信息
        String name = "";
        String id = "";
        String headshot = "";
        boolean isHeadShotDefault = true;

        List<User> users = DataSupport.where("user_name = ?", userName).find(User.class);
        for (User user: users) {
            Log.d("SelfInfoActivity", "user id is " + user.getId());

            name = user.getUser_name();
            id = String.valueOf(user.getId());

            // 设置头像
            if (!Objects.equals(user.getUser_headshot(), "default")) {
                isHeadShotDefault = false;
                headshot = user.getUser_headshot();
                Log.d("SelfInfoActivity", "headshot not default");
            }
        }

        // 呼出滑动菜单
        mDrawerLayout = (DrawerLayout) findViewById(R.id.pm_handbook_drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }

        // 对滑动菜单中的内容进行更改
        NavigationView navView = (NavigationView) findViewById(R.id.pm_handbook_nav_view);
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
        navView.setCheckedItem(R.id.drawer_menu_handbook);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            Intent intent;

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();

                switch (item.getItemId()) {
                    case R.id.drawer_menu_profile:
                        SelfInfoActivity.actionStart(PmHandbookActivity.this, userName);
                        break;

                    case R.id.drawer_menu_handbook:
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

        // 设置RecyclerView
        initPokemons();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.pm_handbook_recyclerview);
        GridLayoutManager layoutManger = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManger);
        pokemonAdapter = new PokemonAdapter(pokemonList);
        recyclerView.setAdapter(pokemonAdapter);
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

    private void initPokemons() {
        pokemonList.clear();
        pokemons.toArray();
        for (int i = 0; i < 50; i ++) {
            Random random = new Random();
            int index = random.nextInt(pokemons.size());

            Pokemon temp_pokemon = pokemons.get(index);
            PokemonItem item = new PokemonItem(temp_pokemon.getPokemon_id(),
                                               temp_pokemon.getPokemon_name(),
                                               temp_pokemon.getPokemon_picture_id(),
                                               temp_pokemon.getPokemon_intro(),
                                               temp_pokemon.getPokemon_type());
            pokemonList.add(item);
        }
    }
}
