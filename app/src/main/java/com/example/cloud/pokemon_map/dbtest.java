package com.example.cloud.pokemon_map;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.cloud.pokemon_map.db.Notebook;
import com.example.cloud.pokemon_map.db.Pokemon;
import com.example.cloud.pokemon_map.db.User;

import org.litepal.crud.DataSupport;

import java.util.List;

public class dbtest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbtest_activity_layout);

        changePokemonDB();
        changeNote();

        String TAG = "dbtest";

        Log.d(TAG, "db User ");
        List<User> users = DataSupport.findAll(User.class);
        for (User user:users) {
            Log.d(TAG, "user name is " + user.getUser_name());
        }

        Log.d(TAG, "");
        Log.d(TAG, "");

        Log.d(TAG, "db Pokemon ");
        List<Pokemon> pokemons = DataSupport.findAll(Pokemon.class);
        for (Pokemon pokemon:pokemons) {
            Log.d(TAG, "pokemon name is " + pokemon.getPokemon_name());
            Log.d(TAG, "pokemon id is " + pokemon.getPokemon_id());
            Log.d(TAG, "pokemon picture is " + pokemon.getPokemon_picture_id());
        }

        Log.d(TAG, "");
        Log.d(TAG, "");

        Log.d(TAG, "db Notebook ");
        List<Notebook> Notebooks = DataSupport.findAll(Notebook.class);
        for (Notebook notebook:Notebooks) {
            Log.d(TAG, "note id is " + notebook.getId());
            Log.d(TAG, "note title is " + notebook.getNote_title());
        }
    }

    private void changePokemonDB() {

        DataSupport.deleteAll(Pokemon.class);

        Pokemon addPokemon;

        addPokemon = new Pokemon();
        addPokemon.setPokemon_id(1);
        addPokemon.setPokemon_name("妙蛙种子");
        addPokemon.setPokemon_picture_id(R.drawable.pm_001);
        addPokemon.setPokemon_intro("会看到它在太阳底下睡午觉的样子。在沐浴了充足的阳光后，它背上的种子就会茁壮成长。");
        addPokemon.setPokemon_type("草、毒");
        addPokemon.save();

        addPokemon = new Pokemon();
        addPokemon.setPokemon_id(2);
        addPokemon.setPokemon_name("妙蛙草");
        addPokemon.setPokemon_picture_id(R.drawable.pm_002);
        addPokemon.setPokemon_intro("它的腰腿会为了支撑背上的花苞而变强。如果待在太阳底下一动不动的时间变长了，就表示大花即将盛开。");
        addPokemon.setPokemon_type("草、毒");
        addPokemon.save();

        addPokemon = new Pokemon();
        addPokemon.setPokemon_id(3);
        addPokemon.setPokemon_name("妙蛙花");
        addPokemon.setPokemon_picture_id(R.drawable.pm_003);
        addPokemon.setPokemon_intro("据说充足的营养和阳光会让花儿的颜色变得更加鲜艳。花香会治愈人心。");
        addPokemon.setPokemon_type("草、毒");
        addPokemon.save();

        addPokemon = new Pokemon();
        addPokemon.setPokemon_id(4);
        addPokemon.setPokemon_name("小火龙");
        addPokemon.setPokemon_picture_id(R.drawable.pm_004);
        addPokemon.setPokemon_intro("尾巴上的火焰代表着它的心情。当它开心时，火焰会摇曳晃动。而当它生气时，火焰就会剧烈燃烧。");
        addPokemon.setPokemon_type("火");
        addPokemon.save();

        addPokemon = new Pokemon();
        addPokemon.setPokemon_id(5);
        addPokemon.setPokemon_name("火恐龙");
        addPokemon.setPokemon_picture_id(R.drawable.pm_005);
        addPokemon.setPokemon_intro("它会用锋利的爪子无情地打倒对手。如果遇到强敌，它就会兴奋不已，尾巴上会燃起蓝白色的火焰。");
        addPokemon.setPokemon_type("火");
        addPokemon.save();

        addPokemon = new Pokemon();
        addPokemon.setPokemon_id(6);
        addPokemon.setPokemon_name("喷火龙");
        addPokemon.setPokemon_picture_id(R.drawable.pm_006);
        addPokemon.setPokemon_intro("会在空中飞来飞去寻找强大的对手。对于比它弱小的对手，它不会吐出那可熔化一切的高温火焰。");
        addPokemon.setPokemon_type("火、飞行");
        addPokemon.save();

        addPokemon = new Pokemon();
        addPokemon.setPokemon_id(7);
        addPokemon.setPokemon_name("杰尼龟");
        addPokemon.setPokemon_picture_id(R.drawable.pm_007);
        addPokemon.setPokemon_intro("甲壳的作用不仅仅是用来保护自己。它圆润的外形和表面的沟槽会减小水的阻力，使它能快速地游动");
        addPokemon.setPokemon_type("水");
        addPokemon.save();

        addPokemon = new Pokemon();
        addPokemon.setPokemon_id(8);
        addPokemon.setPokemon_name("卡咪龟");
        addPokemon.setPokemon_picture_id(R.drawable.pm_008);
        addPokemon.setPokemon_intro("长满蓬松毛发的大尾巴的颜色，会随着年龄的增长而越变越深。甲壳上的伤痕是强者的证明。");
        addPokemon.setPokemon_type("水");
        addPokemon.save();

        addPokemon = new Pokemon();
        addPokemon.setPokemon_id(9);
        addPokemon.setPokemon_name("水箭龟");
        addPokemon.setPokemon_picture_id(R.drawable.pm_009);
        addPokemon.setPokemon_intro("甲壳上的喷射口能够精准地瞄准目标。打出的水弹可以命中５０米外的空罐子。");
        addPokemon.setPokemon_type("水");
        addPokemon.save();
    }

    private void changeNote() {
        DataSupport.deleteAll(Notebook.class);

        Notebook addNote;

        addNote = new Notebook();
        addNote.setNote_username("Ash");
        addNote.setNote_date("2018-1-1");
        addNote.setNote_picture("default".getBytes());
        addNote.setNote_title("第一篇");
        addNote.setNote_content("1111 好麻烦");
        addNote.save();

        addNote = new Notebook();
        addNote.setNote_username("Ash");
        addNote.setNote_date("2018-1-2");
        addNote.setNote_picture("default".getBytes());
        addNote.setNote_title("第二篇");
        addNote.setNote_content("2222 不想写");
        addNote.save();

        addNote = new Notebook();
        addNote.setNote_username("Ash");
        addNote.setNote_date("2018-1-3");
        addNote.setNote_picture("default".getBytes());
        addNote.setNote_title("第三篇");
        addNote.setNote_content("3333 放弃了");
        addNote.save();

        addNote = new Notebook();
        addNote.setNote_username("Ash");
        addNote.setNote_date("2018-1-4");
        addNote.setNote_picture("default".getBytes());
        addNote.setNote_title("第四篇");
        addNote.setNote_content("4444 哈哈哈哈");
        addNote.save();

        addNote = new Notebook();
        addNote.setNote_username("Ash");
        addNote.setNote_date("2018-1-5");
        addNote.setNote_picture("default".getBytes());
        addNote.setNote_title("第五篇");
        addNote.setNote_content("5555 再见再见");
        addNote.save();

        addNote = new Notebook();
        addNote.setNote_username("Asuka");
        addNote.setNote_date("2018-1-1");
        addNote.setNote_picture("default".getBytes());
        addNote.setNote_title("asuka title 1");
        addNote.setNote_content("dgfagfgliufgliagbaidhfalgb");
        addNote.save();
    }
}
