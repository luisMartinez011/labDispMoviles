package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "mobilesdb";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME_ORDERS = "ordenes";
    private static final String ID_COL_ORDERS = "id";
    private static final String NAME_COL_ORDERS = "nombre";
    private static final String DESCRIPTION_COL_ORDERS = "description";

    private static final String TABLE_NAME_USERS = "users";
    private static final String ID_COL_USERS = "id";
    private static final String NAME_COL_USERS = "nombre";
    private static final String EMAIL_COL_USERS = "email";
    private static final String PASSWORD_COL_USERS = "password";

    // creating a constructor for our database handler.
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryOrders = "CREATE TABLE " + TABLE_NAME_ORDERS + " ("
                + ID_COL_ORDERS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL_ORDERS + " TEXT,"
                + DESCRIPTION_COL_ORDERS + " TEXT)";
        db.execSQL(queryOrders);

        String queryUsers = "CREATE TABLE " + TABLE_NAME_USERS + " ("
                + ID_COL_USERS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL_USERS + " TEXT,"
                + EMAIL_COL_USERS + " TEXT,"
                + PASSWORD_COL_USERS + " TEXT)";
        db.execSQL(queryUsers);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ORDERS);
        onCreate(db);
    }

    public void addNewOrder(String orderName, String orderDesc ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL_ORDERS, orderName);
        values.put(DESCRIPTION_COL_ORDERS, orderDesc);
        db.insert(TABLE_NAME_ORDERS,null,values);
        db.close();
    }

    public void deleteOrder(int orderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = ID_COL_ORDERS + " = ?";
        String[] selectionArgs = { String.valueOf(orderId) };
        db.delete(TABLE_NAME_ORDERS, selection, selectionArgs);
        db.close();
    }

    public void addNewUser(String userName, String userPassword, String userEmail ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME_COL_USERS, userName);
        values.put(PASSWORD_COL_USERS, userPassword);
        values.put(EMAIL_COL_USERS, userEmail);
        db.insert(TABLE_NAME_USERS,null,values);
        db.close();
    }
    public void deleteUser(int userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = ID_COL_USERS + " = ?";
        String[] selectionArgs = { String.valueOf(userId) };
        db.delete(TABLE_NAME_USERS, selection, selectionArgs);
        db.close();
    }

}
