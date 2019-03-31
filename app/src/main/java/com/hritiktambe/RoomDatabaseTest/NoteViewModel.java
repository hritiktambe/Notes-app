package com.hritiktambe.RoomDatabaseTest;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private final String TAG = this.getClass().getSimpleName();
    private NoteDao noteDao;
    private NoteRoomDatabase noteDb;
    private LiveData<List<Note>> mAllNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        //instance of database
        noteDb = NoteRoomDatabase.getDatabase(application);
        //DAO object
        noteDao = noteDb.noteDao();
        mAllNotes = noteDao.getAllNotes();

    }

    //Wrapper method to insert notes

    public void insert (Note note){

        new InsertAsyncTask(noteDao).execute(note);

    }

    //wrapper function to get notes
    LiveData<List<Note>> getAllNotes(){

        return mAllNotes;
    }

    //wrapper function to update view

    public void update(Note note){

        new UpdateAsyncTask(noteDao).execute(note);
    }
    //wrapper function to delete
    public void delete(Note note){

        new DeleteAsyncTask(noteDao).execute(note);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.i(TAG, "ViewModel Destroyed");

    }

    private class InsertAsyncTask extends AsyncTask<Note,Void,Void>{


        NoteDao mNoteDao;

        public InsertAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            mNoteDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateAsyncTask extends AsyncTask<Note,Void,Void>{

        NoteDao mNoteDao;

        public UpdateAsyncTask(NoteDao noteDao) {
            this.mNoteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {

            mNoteDao.update(notes[0]);
            return null;

        }
    }

    private class DeleteAsyncTask extends AsyncTask<Note,Void,Void>{

        NoteDao mNoteDao;

        public DeleteAsyncTask(NoteDao noteDao) {
        }

        @Override
        protected Void doInBackground(Note... notes) {

            mNoteDao.delete(notes[0]);
            return null;
        }
    }
}
