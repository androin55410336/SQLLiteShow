package app.buusk.resume_55410336;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class myDBClass extends SQLiteOpenHelper {
	
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "mydatabase";
	private static final String TABLE_MEMBER = "members";

	public myDBClass(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+ TABLE_MEMBER +
				"(MemberID INTEGER PRIMARY KEY AUTOINCREMENT," +
				" Name TEXT(100)," +
				" University TEXT(100)," +
				" Faculty TEXT(100));");
		
		Log.d("CREATE TABLE", "Create Table Sucsessfully.");
		
	}
	
	public long InsertData(String strName, String strUnv, String strFac) {
		try {
			SQLiteDatabase db;
			db =  this.getWritableDatabase(); // Write Data
			
			ContentValues Val = new ContentValues(); //Create Table
			Val.put("Name", strName); // "Name" >> Table Name
			Val.put("University", strUnv);
			Val.put("Faculty", strFac);
			
			long rows = db.insert(TABLE_MEMBER, null, Val);
			db.close();
			return rows;
			
		}catch (Exception e){
			return -1;
		}
		
		
	}
	public String [] SelectData(String Name){
		try
		{
			String arrData[] = null;
			SQLiteDatabase db;
			db = this.getReadableDatabase();
			
			Cursor cursor = db.query(TABLE_MEMBER, new String[] { "*" }, "Name=?", 
					new String[] { String.valueOf(Name) }, null, null, null, null);
			if (cursor != null)
			{
				if(cursor.moveToFirst())
				{
					arrData = new String[cursor.getColumnCount()];
					
					arrData[0] = cursor.getString(0);
					arrData[1] = cursor.getString(1);
					arrData[2] = cursor.getString(2);
				}
			}
			cursor.close();
			db.close();
			return arrData;
			}catch (Exception e) {
				return null;
			}
	}

	public ArrayList<HashMap<String, String>> SelectAllData()
	{
		try
		{
			ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;
			
			SQLiteDatabase db;
			db = this.getReadableDatabase();
			
			String strSQL = "SELECT * FROM " + TABLE_MEMBER;
			Cursor cursor = db.rawQuery(strSQL, null);
			if (cursor != null)
			{
				if (cursor.moveToFirst()){
					do{
						map = new HashMap<String, String>();
						map.put("Name", cursor.getString(0));
						map.put("University", cursor.getString(1));
						map.put("Faculty", cursor.getString(2));
						MyArrList.add(map);
					} while (cursor.moveToNext());
				}
				
			}
			cursor.close();
			db.close();
			return MyArrList;
		}catch (Exception e) {
			// TODO: handle exception
			return null;
		}
			
			
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
		onCreate(db);
			
	}

}

