package com.example.cloud.pokemon_map;

import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.cloud.pokemon_map.db.Notebook;

import org.litepal.crud.DataSupport;

import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class NoteItemActivity extends AppCompatActivity implements View.OnClickListener {

    public static final int CHOOSE_PHOTO = 2;

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
    private byte[] note_image = "default".getBytes();

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
        String str_img = new String(note_image);
        if (Objects.equals(str_img, "default")) {
            Log.d("NoteItemActivity", "image do not change");
        }
        else {
            Glide.with(this).load(note_image).into(img_note_image);
        }

        // 用于 add note
        if (noteId < 0) {
            Calendar c = Calendar.getInstance();
            note_et_date = c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)+1) + "-" + c.get(Calendar.DAY_OF_MONTH);
            et_note_date.setText(note_et_date);
            note_image = "default".getBytes();
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
                if (ContextCompat.checkSelfPermission(NoteItemActivity.this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(NoteItemActivity.this, new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                }
                else {
                    openAlbum();
                }
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
                newNote.setNote_picture(note_image);

                Log.d(TAG, "button update hello");
                Log.d(TAG, "username = " + userName);
                Log.d(TAG, "date = " + note_et_date);
                Log.d(TAG, "title = " + note_et_title);
                Log.d(TAG, "content = " + note_et_content);
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

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                }
                else {
                    Toast.makeText(this, "You denied the permission", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CHOOSE_PHOTO:
                if (resultCode == RESULT_OK) {
                    if (Build.VERSION.SDK_INT >= 19) {
                        handleImageOnKitkkat(data);
                    }
                    else {
                        handleImageBeforeKitkat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitkkat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            String docId = DocumentsContract.getDocumentId(uri);

//            Log.d("MainActivity", "docId " + docId);

            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1];

//                Log.d("MainActivity", "id " + id);

                String selection = MediaStore.Images.Media._ID + "=" + id;

//                Log.d("MainActivity", "selection " + selection);

                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);

//                Log.d("MainActivity", "com.android.providers.media.documents " + imagePath);
            }
            else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);

//                Log.d("MainActivity", "com.android.providers.downloads.documents " + imagePath);
            }
        }
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            imagePath = getImagePath(uri, null);

//            Log.d("MainActivity", "content " + imagePath);
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            imagePath = uri.getPath();

//            Log.d("MainActivity", "file " + imagePath);
        }

        int lastDot = imagePath.lastIndexOf(".");
        String extention = imagePath.substring(lastDot+1).toUpperCase();

//        Log.d("MainActivity", "extention " + extention);

        displayImage(imagePath);
    }

    private void handleImageBeforeKitkat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }

//        Log.d("MainActivity", "getImagePath  " + path);

        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            img_note_image.setImageBitmap(bitmap);
            note_image = img_byte(bitmap);
            Glide.with(this).load(note_image).into(img_note_image);
        }
        else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    // 把图片转换为字节
    private byte[]img_byte(Bitmap bitmap){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }
}
