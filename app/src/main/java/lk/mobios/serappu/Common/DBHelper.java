package lk.mobios.serappu.Common;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Sereppu.db";
    public static final String USER_TABLE_NAME = "users";
    public static final String USER_COLUMN_ID = "id";
    public static final String USER_COLUMN_NAME = "name";
    public static final String USER_COLUMN_EMAIL = "email";
    public static final String USER_COLUMN_USERNAME = "username";
    public static final String USER_COLUMN_PASSWORD = "password";

    public static final String PROMO_COLUMN_TITLE= "title";
    public static final String PROMO_COLUMN_DESCRIPTION= "description";

    public static final String FB_COLUMN_NAME= "name";
    public static final String FB_COLUMN_FEEDBACK= "title";

    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table users " +
                        "(id integer primary key, name text,email text,username text, password text, address text, tel text)"
        );

        sqLiteDatabase.execSQL(
                "create table promos " +
                        "(id integer primary key, title text,description text)"
        );

        sqLiteDatabase.execSQL(
                "create table feedbacks " +
                        "(id integer primary key, name text,feedback text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // TODO Auto-generated method stub
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(sqLiteDatabase);
    }

    public boolean insertNewUser(String name, String email, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert("users", null, contentValues);
        return true;
    }

    public boolean insertNewPromo(String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        db.insert("promos", null, contentValues);
        return true;
    }

    public boolean insertNewFeedback(String name, String feedback) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("feedback", feedback);
        db.insert("feedbacks", null, contentValues);
        return true;
    }

    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from promos where id="+id+"", null );
        return res;
    }

    public Cursor getDataFB(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from feedbacks where id="+id+"", null );
        return res;
    }

    public Cursor getUserPWD(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from users where username='"+username+"'", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, USER_TABLE_NAME);
        return numRows;
    }

    public boolean updatePromo(Integer id, String title, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("description", description);
        db.update("promos", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deletePromos (Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("promos",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllPromos() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from promos", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(PROMO_COLUMN_TITLE)));
            res.moveToNext();
        }
        return array_list;
    }

    public ArrayList<String> getAllFeedbacks() {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from feedbacks", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(FB_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }

    public int numberOfRowsFB(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, "feedbacks");
        return numRows;
    }
}
