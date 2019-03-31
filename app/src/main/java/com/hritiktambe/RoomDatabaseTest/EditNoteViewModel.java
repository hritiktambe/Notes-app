package com.hritiktambe.RoomDatabaseTest;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

public class EditNoteViewModel extends AndroidViewModel {

    private static final String TAG = "EditNoteViewModel";
    private NoteDao noteDao;
    private NoteRoomDatabase db;


    public EditNoteViewModel(@NonNull Application application) {
        super(application);
        Log.d(TAG, "EditNoteViewModel: called");
        db = NoteRoomDatabase.getDatabase(application);
        noteDao = db.noteDao();

    }

    //wrapper function for getting note

    LiveData<Note> getNote(String noteId)
    {
        return noteDao.getNote(noteId);
    }
}
