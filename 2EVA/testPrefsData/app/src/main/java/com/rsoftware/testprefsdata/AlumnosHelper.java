package com.rsoftware.testprefsdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class AlumnosHelper extends SQLiteOpenHelper {
    private Context mContext;
    public AlumnosHelper(@Nullable Context context) {
        super(context, "AlumnosBBDD.db", null, 1);
        mContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Toast.makeText(mContext,"Base de datos creada",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
