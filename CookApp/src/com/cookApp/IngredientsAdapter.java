package com.cookApp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class IngredientsAdapter {
	static final String DATABASE_NAME = "ingredients.db";
	static final int DATABASE_VERSION = 1;
	public static final int NAME_COLUMN = 1;
	
	static final String DATABASE_CREATE = "CREATE TABLE " + "INGREDIENTS" +
			"( " + "NAME text primary key, QUANTITY integer, MEASUREMENT text" + ")";
	
	
	public SQLiteDatabase db;
	private final Context context;
	private DataBaseHelper dbHelper;
	
	
	public IngredientsAdapter(Context _context) {
		context = _context;
		dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public IngredientsAdapter open() throws SQLException {
		db = dbHelper.getWritableDatabase();
		return this;
	}
	
	public void Close() {
		db.close();
	}
	
	public SQLiteDatabase getDatabaseInstance() {
		return db;
	}
	
	public void insertIngredientEntry(String name, Integer quantity, String measurement) {
		ContentValues newValues = new ContentValues();
		newValues.put("NAME", name);
		newValues.put("QUANTITY", quantity);
		newValues.put("MEASUREMENT", measurement);
		db.insert("INGREDIENTS", null, newValues);
	}
	
	/*public int countPhotos(String area, String location) {
		Cursor c = db.rawQuery("SELECT LOCATION FROM MOLEPHOTOS WHERE AREA = \"" + area + "\"" + " AND LOCATION = \"" + location + "\"", null);
		int count = c.getCount();
		c.close();
		return count;
	}
	
	public String[] photoNames(String area, String location) {
	Cursor c = db.rawQuery("SELECT PHOTO FROM MOLEPHOTOS WHERE AREA = \"" + area + "\"" + " AND LOCATION = \"" + location + "\"", null);
	int count = c.getCount();
	String arr[]=new String[count];
	int i=0;
	c.moveToFirst();
	while (c.isAfterLast() == false) 
	{
	    arr[i]  = c.getString(0);
	    i++;
	    c.moveToNext();
	}
	return arr;
}*
	
	/*public int deleteEntry(String mlocation) {
		String where = "LOCATION=?";
		int numberOfEntriesDeleted = db.delete("MOLES", where, new String[]{mlocation});
		return numberOfEntriesDeleted;
	}*/
	
	public String[] getIngredients(String name) {
		String[] ingredients = new String[2]; 
		Cursor cursor = db.query("INGREDIENTS", null, "NAME=?", new String[]{name}, null, null, null);
		if ( cursor.getCount()<1 ) {
			cursor.close();
			return null;
		}
		cursor.moveToFirst();
		ingredients[0] = cursor.getString(cursor.getColumnIndex("QUANTITY"));
		ingredients[1] = cursor.getString(cursor.getColumnIndex("MEASUREMENT"));
		cursor.close();
		return ingredients;
	}
	
	/*public Boolean checkLocations(String location) {
		Cursor c = db.rawQuery("SELECT LOCATION FROM MOLES WHERE LOCATION = \"" + location + "\"", null);
		int count = c.getCount();
		if (count>0) {
			return true; //meaning false
		} else {
			return false;
		}		
		
	}
	
	public void updateEntry(String mlocation, String area, String photo) {
		ContentValues updatedValues = new ContentValues();
		updatedValues.put("LOCATION", mlocation);
		updatedValues.put("AREA", area);
		updatedValues.put("PHOTO", photo);
		
		String where = "LOCATION=?";
		db.update("PHOTOMOLES", updatedValues, where, new String[]{mlocation});
	}*/


}
