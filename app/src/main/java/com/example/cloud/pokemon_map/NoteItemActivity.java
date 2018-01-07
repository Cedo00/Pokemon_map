package com.example.cloud.pokemon_map;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.cloud.pokemon_map.db.Notebook;

import org.litepal.crud.DataSupport;

import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class NoteItemActivity extends AppCompatActivity implements View.OnClickListener {

    public static void actionStart(Context context, String userName, int noteId) {
        Intent intent = new Intent(context, NoteItemActivity.class);
        intent.putExtra("userName", userName);
        intent.putExtra("noteId", noteId);
        context.startActivity(intent);
    }

    private String userName;
    private int noteId;

    private String note_et_title = "";
    private String note_et_date = "";
    private String note_et_content = "";
    private String note_image = "";

    private EditText et_note_date;
    private EditText et_note_title;
    private EditText et_note_content;
    private ImageView img_note_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_item_activity_layout);

        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        noteId = intent.getIntExtra("noteId", 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.note_item_activity_toolbar);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // 设置note信息
        et_note_date = (EditText) findViewById(R.id.note_item_activity_edittext_date);
        et_note_title = (EditText) findViewById(R.id.note_item_activity_edittext_title);
        et_note_content = (EditText) findViewById(R.id.note_item_activity_edittext_content);
        img_note_image = (ImageView) findViewById(R.id.note_item_activity_image);

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

        // 用于 add note
        if (noteId < 0) {
            Calendar c = Calendar.getInstance();
            note_et_date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DAY_OF_MONTH);
            et_note_date.setText(note_et_date);
            note_image = "default";
        }

        // button
        Button button_add_img = (Button) findViewById(R.id.note_item_activity_button_add_image);
        Button button_update = (Button) findViewById(R.id.note_item_activity_button_update);
        Button button_delete = (Button) findViewById(R.id.note_item_activity_button_delete);
        button_add_img.setOnClickListener(this);
        button_update.setOnClickListener(this);
        button_delete.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        String TAG = "NoteItemAc";
        switch (v.getId()) {
            case R.id.note_item_activity_button_add_image:
                break;

            case R.id.note_item_activity_button_update:
                note_et_date = et_note_date.getText().toString();
                note_et_title = et_note_title.getText().toString();
                note_et_content = et_note_content.getText().toString();

                Notebook newNote = new Notebook();
                newNote.setNote_username(userName);
                newNote.setNote_date(note_et_date);
                newNote.setNote_title(note_et_title);
                newNote.setNote_content(note_et_content);
                newNote.setNote_picture("default");

                Log.d(TAG, "button update hello");
                Log.d(TAG, "username = " + userName);
                Log.d(TAG, "date = " + note_et_date);
                Log.d(TAG, "title = " + note_et_title);
                Log.d(TAG, "content = " + note_et_content);
                Log.d(TAG, "img = " + note_image);
                if (noteId < 0) {
                    newNote.save();
                }
                else {
                    newNote.updateAll("id = ?", String.valueOf(noteId));
                }
                SelfInfoActivity.actionStart(NoteItemActivity.this, userName);
                finish();
                break;

            case R.id.note_item_activity_button_delete:
                if (noteId < 0) {
                    finish();
                }
                else {
                    DataSupport.deleteAll(Notebook.class, "id = ?", String.valueOf(noteId));
                    SelfInfoActivity.actionStart(NoteItemActivity.this, userName);
                    finish();
                }
                break;

            default:
                break;
        }
    }
}
