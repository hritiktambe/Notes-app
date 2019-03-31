package com.hritiktambe.RoomDatabaseTest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.NoteViewHolder> {

    public interface OnDeleteClickListener{

        void OnDeleteClickListener(Note myNote);
    }

    private List <Note> mNotes;
    private Context context;
    OnDeleteClickListener onDeleteClickListener;


    public NoteListAdapter(Context context,OnDeleteClickListener listener) {

        this.context = context;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View view = (View) LayoutInflater.from(context).inflate(R.layout.recycler_list_item,parent,false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i) {


        if (mNotes!=null){
            Note note = mNotes.get(i);
            noteViewHolder.setData(note.getNote(),i);
            noteViewHolder.setOnClickListeners();
        } else {
            noteViewHolder.noteItemView.setText(R.string.no_note);
        }

    }

    @Override
    public int getItemCount() {

        if(mNotes != null){

        return mNotes.size();
        }
        else
            return 0;
    }

    public void setNotes(List<Note> notes){

        mNotes = notes;
        notifyDataSetChanged();

    }


    public class NoteViewHolder extends RecyclerView.ViewHolder {

         private TextView noteItemView;
         private ImageView imgDelete;
         private ImageView edit;
         private int mPosition;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            noteItemView = itemView.findViewById(R.id.txvNote);
            imgDelete = itemView.findViewById(R.id.ivRowDelete);
            edit = itemView.findViewById(R.id.ivRowEdit);
        }

        public void setData(String note, int position){

            noteItemView.setText(note);
            mPosition = position;

        }

        public void setOnClickListeners() {

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context,EditNoteActivity.class);
                    intent.putExtra("note_id",mNotes.get(mPosition).getId());
                    ((Activity)context).startActivityForResult(intent,MainActivity.UPDATE_NOTE_ACTIVITY_REQUEST_CODE);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onDeleteClickListener.OnDeleteClickListener(mNotes.get(mPosition));

                }
            });

        }
    }


}
