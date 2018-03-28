package com.workspaceit.photoclubbingme.backend.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.workspaceit.photoclubbingme.backend.entity.OauthCredential;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pmc-android";
    private static final String TABLE_CONTACTS = "OauthCredential";

    private static final String KEY_access_token= "access_token";
    private static final String KEY_token_type = "token_type";
    private static final String KEY_refresh_token = "refresh_token";
    private static final String KEY_expires_in = "expires_in";
    private static final String KEY_scope = "scope";

    public DatabaseHandler(Context context) { super(context, DATABASE_NAME, null, DATABASE_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_access_token + " TEXT,"
                + KEY_token_type + " TEXT,"
                + KEY_refresh_token + " TEXT,"
                + KEY_expires_in + " TEXT,"
                + KEY_scope + " TEXT"
                + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public void reOpen() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null);
    }

    public void addCountry(OauthCredential oauth) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_access_token, oauth.getAccess_token());
        values.put(KEY_token_type, oauth.getToken_type());
        values.put(KEY_refresh_token, oauth.getRefresh_token());
        values.put(KEY_expires_in, oauth.getExpires_in());
        values.put(KEY_scope, oauth.getScope());

        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public ArrayList<OauthCredential> getOauthCredential() {
        ArrayList<OauthCredential> cl=new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                OauthCredential c = new OauthCredential();
                c.setAccess_token(cursor.getString(0));
                c.setToken_type(cursor.getString(1));
                c.setRefresh_token(cursor.getString(2));
                c.setExpires_in(cursor.getInt(3));
                c.setScope(cursor.getString(4));
                cl.add(c);
            } while (cursor.moveToNext());
        }
        return cl;
    }
}