package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "mobilesdb";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME_ORDERS = "ordenes";
    private static final String ID_COL_ORDERS = "id_orders";
    public static final String NAME_COL_ORDERS = "nombre";
    public static final String DESCRIPTION_COL_ORDERS = "description";

    private static final String TABLE_NAME_USERS = "users";
    private static final String ID_COL_USERS = "id";
    private static final String NAME_COL_USERS = "nombre";
    public static final String EMAIL_COL_USERS = "email";
    public static final String PASSWORD_COL_USERS = "password";

    // ** Articulos
    private static final String TABLE_NAME_ARTICULOS = "articulos";
    private static final String ID_COL_ARTICULOS = "id_articulos";
    public static final String SERIE_COL_ARTICULOS= "numero_serie";
    public static final String ALIAS_COL_ARTICULOS = "alias";
    public static final String DESCRIPCION_COL_ARTICULOS = "descripcion";
    public static final String IMAGEN_COL_ARTICULOS = "imagen";

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

        String queryArticulos = "CREATE TABLE " + TABLE_NAME_ARTICULOS + " ("
                + ID_COL_ARTICULOS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SERIE_COL_ARTICULOS + " TEXT,"
                + ALIAS_COL_ARTICULOS + " TEXT,"
                + DESCRIPCION_COL_ARTICULOS + " TEXT,"
                + IMAGEN_COL_ARTICULOS + " TEXT,"
                + ID_COL_ORDERS + " INTEGER,"
                + "FOREIGN KEY(" + ID_COL_ORDERS + ") references "
                + TABLE_NAME_ORDERS + "(" + ID_COL_ORDERS + "))";
        db.execSQL(queryArticulos);
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

    public Cursor getUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = EMAIL_COL_USERS + " = ?";
        String[] selectionArgs = { email };
        return db.query(TABLE_NAME_USERS, null, selection, selectionArgs, null, null, null);
    }

    public void initAdminUser() {
        String adminName = "Admin";
        String adminPassword = "admin123"; // Please use a secure password in production
        String adminEmail = "admin@admin.dev";

        addNewUser(adminName, adminPassword, adminEmail);
    }


    public Cursor getAllOrders() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME_ORDERS, null, null, null, null, null, null);
    }


    //Obtiene todos los articulos que le corresponden a cierta orden
    public Cursor getAllArticulosByOrder(int orderId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = ID_COL_ORDERS + " = ?";
        String[] selectionArgs = { String.valueOf(orderId) };
        Cursor resultado = db.query(TABLE_NAME_ARTICULOS, null, null,  null, null, null, null);
        return resultado;
    }

    public void addNewArticulo(String numeroSerie, String alias, String descripcion, int orderId){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(SERIE_COL_ARTICULOS, numeroSerie);
        values.put(ALIAS_COL_ARTICULOS, numeroSerie);
        values.put(DESCRIPCION_COL_ARTICULOS, descripcion);
        db.insert(TABLE_NAME_ARTICULOS,null,values);
        db.close();
    }
}
