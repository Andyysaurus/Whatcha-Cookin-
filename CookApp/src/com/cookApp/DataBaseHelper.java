package com.cookApp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBaseHelper extends SQLiteOpenHelper {
	
	public DataBaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}
	
	@Override
	public void onCreate(SQLiteDatabase _db) {
		//_db.execSQL(AddMoleAdapter.DATABASE_CREATE);
		_db.execSQL(PhotoAdapter.DATABASE_CREATE);
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase _db, int _oldversion, int _newversion) {
		Log.w("TaskDBAdapter", "Upgrading from version " + _oldversion + " to " + _newversion + ", which will destroy all old data");
		_db.execSQL("DROP TABLE IF EXISTS " + "MOLES");
		_db.execSQL("DROP TABLE IF EXISTS " + "PHOTOMOLES");
		onCreate(_db);
	}

}
