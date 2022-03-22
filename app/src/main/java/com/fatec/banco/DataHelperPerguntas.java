package com.fatec.banco;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class DataHelperPerguntas {
	private static final String DATABASE_NAME = "shewbaca.db";
	   private static final int DATABASE_VERSION = 1;
	   private static final String TABLE_NAME = "perguntas";

	   private Context context;
	   private SQLiteDatabase db;

	   private SQLiteStatement insertStmt;
	   private static final String INSERT = "insert into " + TABLE_NAME + "(pergunta, alter1, alter2, alter3, alter4, resp) values (?,?,?,?,?,?)";

	   public DataHelperPerguntas(Context context) {
	      this.context = context;
	      OpenHelper openHelper = new OpenHelper(this.context);
	      this.db = openHelper.getWritableDatabase();
	      this.insertStmt = this.db.compileStatement(INSERT);
	   }

	   public long insert(String pergunta, String alter1, String alter2, String alter3, String alter4, String resp) {
	      this.insertStmt.bindString(1, pergunta);
	      this.insertStmt.bindString(2, alter1);
	      this.insertStmt.bindString(3, alter2);
	      this.insertStmt.bindString(4, alter3);
	      this.insertStmt.bindString(5, alter4);
	      this.insertStmt.bindString(6, resp);
	      return this.insertStmt.executeInsert();
	   }

	   public void deleteAll() {
	      this.db.delete(TABLE_NAME, null, null);
	   }

	   public List<String> selectOne(int id) {
	      List<String> list = new ArrayList<String>();
	      Cursor cursor = this.db.query(TABLE_NAME, new String[] { "pergunta","Alter1","Alter2","Alter3","Alter4","resp" },
	        "id=?", new String[]{String.valueOf(id)}, null, null, "pergunta");
	      if (cursor.moveToFirst()) {
	         do {
	        	 String texto =cursor.getString(0)+"_"+cursor.getString(1)+"_"+cursor.getString(2)+"_"+cursor.getString(3)+"_"+cursor.getString(4)+"_"+cursor.getString(5);
	            list.add(texto);
	         } while (cursor.moveToNext());
	      }
	      if (cursor != null && !cursor.isClosed()) {
	         cursor.close();
	      }
	      return list;
	  }
	   public int selectQtd() {
		      int qtd=0;
		      String queryString = "SELECT count(*) AS qtd FROM " + TABLE_NAME;
		      Cursor cursor = this.db.rawQuery(queryString, null);
		      if (cursor.moveToFirst()) {
		         do {
		        	String texto =cursor.getString(0);
		            qtd= Integer.parseInt(texto);
		         } while (cursor.moveToNext());
		      }
		      if (cursor != null && !cursor.isClosed()) {
		         cursor.close();
		      }
		      return qtd;
		  }
	   public List<String> selectAll() {
		      List<String> list = new ArrayList<String>();
		      Cursor cursor = this.db.query(TABLE_NAME, new String[] { "pergunta","Alter1","Alter2","Alter3","Alter4","resp" },
		        null, null, null, null, "pergunta");
		      if (cursor.moveToFirst()) {
		         do {
		        	 String texto =cursor.getString(0)+"|"+cursor.getString(1)+"|"+cursor.getString(2)+"|"+cursor.getString(3)+"|"+cursor.getString(4)+"|"+cursor.getString(5);
		            list.add(texto);
		         } while (cursor.moveToNext());
		      }
		      if (cursor != null && !cursor.isClosed()) {
		         cursor.close();
		      }
		      return list;
		  }
	   private static class OpenHelper extends SQLiteOpenHelper {

		      OpenHelper(Context context) {
		         super(context, DATABASE_NAME, null, DATABASE_VERSION);
		      }

		      @Override
		      public void onCreate(SQLiteDatabase db) {
		         db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, pergunta TEXT, alter1 TEXT, alter2 TEXT, alter3 TEXT, alter4 TEXT, resp TEX)");
		      }

		      @Override
		      public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		         Log.w("Example", "Upgrading database, this will drop tables and recreate.");
		         db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		         onCreate(db);
		      }
		   } 
}
