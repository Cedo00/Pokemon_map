package com.example.cloud.pokemon_map;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Objects;

/**
 * Created by Cloud on 2018/1/6.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {

    private Context mContext;

    private List<NoteItem> mNoteList;


    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;
        ImageView noteImage;
        TextView noteTitle;
        TextView noteDate;

        public ViewHolder(View view) {

            super(view);

            cardView   = (CardView) view;
            noteImage = (ImageView) view.findViewById(R.id.item_note_image);
            noteTitle = (TextView) view.findViewById(R.id.item_note_title);
            noteDate  = (TextView) view.findViewById(R.id.item_note_date);
        }
    }


    public NoteAdapter(List<NoteItem> noteList) {

        mNoteList = noteList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null) {
            mContext = parent.getContext();
        }

        View view = LayoutInflater.from(mContext).inflate(R.layout.note_item, parent, false);

        final ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                NoteItem note = mNoteList.get(position);

                Log.d("noteAdapter", "go to item " + note.getNote_user() + note.getNote_id());

                NoteItemActivity.actionStart(mContext, note.getNote_user(), note.getNote_id());
            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        NoteItem note = mNoteList.get(position);

        Log.d("noteadapter", "title = " + note.getNote_title());
        holder.noteTitle.setText(note.getNote_title());
        holder.noteDate.setText(note.getNote_date());
        String str_img = new String(note.getNote_picture());
        if (Objects.equals(str_img, "default")) {
            Glide.with(mContext).load(R.drawable.default_note_image).into(holder.noteImage);
        }
        else {
            Glide.with(mContext).load(note.getNote_picture()).into(holder.noteImage);
        }
    }

    @Override
    public int getItemCount() {
        return mNoteList.size();
    }
}
