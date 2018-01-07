package com.example.cloud.pokemon_map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cloud.pokemon_map.db.Notebook;

import org.litepal.crud.DataSupport;

import java.util.List;
import java.util.Objects;

public class NoteItemActivity extends AppCompatActivity {

    public static void actionStart(Context context, String userName, int noteId) {
        Intent intent = new Intent(context, NoteItemActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("noteId", noteId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_item_activity_layout);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("userName");
        int noteId = intent.getIntExtra("noteId", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.note_item_activity_toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // 设置note信息
        EditText et_note_date = (EditText) findViewById(R.id.note_item_activity_edittext_date);
        EditText et_note_title = (EditText) findViewById(R.id.note_item_activity_edittext_title);
        EditText et_note_content = (EditText) findViewById(R.id.note_item_activity_edittext_content);
        ImageView img_note_image = (ImageView) findViewById(R.id.note_item_activity_image);

        String note_et_title = "";
        String note_et_date = "";
        String note_et_content = "";
        String note_image = "";

        List<Notebook> notebooks = DataSupport.where("id = ?", String.valueOf(noteId)).find(Notebook.class);
        for (Notebook notebook: notebooks) {
            Log.d("NoteItemActivity", "Note id is " + notebook.getId());

            note_et_title = notebook.getNote_title();
            note_et_date = notebook.getNote_date();
            note_et_content = notebook.getNote_content();
            note_image = notebook.getNote_picture();
        }

        et_note_date.setText(note_et_date);
        et_note_title.setText(note_et_title);
        et_note_content.setText(note_et_content);
        if (Objects.equals(note_image, "default")) {
            Log.d("NoteItemActivity", "image do not change");
        }
        else {
            Glide.with(this).load(R.drawable.default_headshot).into(img_note_image);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
