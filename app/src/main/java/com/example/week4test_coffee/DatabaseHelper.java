package com.example.week4test_coffee;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import static com.example.week4test_coffee.DatabaseContract.DATABASE_NAME;
import static com.example.week4test_coffee.DatabaseContract.DATABASE_VERSION;
import static com.example.week4test_coffee.DatabaseContract.FIELD_COFFEE_NAME;
import static com.example.week4test_coffee.DatabaseContract.FIELD_DESC;
import static com.example.week4test_coffee.DatabaseContract.FIELD_ID;
import static com.example.week4test_coffee.DatabaseContract.FIELD_IMAGE;
import static com.example.week4test_coffee.DatabaseContract.TABLE_NAME;

public class DatabaseHelper extends SQLiteOpenHelper {


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.getCreateTableStatement());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertCoffee(Coffee coffeeToInsert){
        // Create content value which holds key value pairs, key being the column in the db and
        // value being the value associated with the column
        ContentValues contentValues = new ContentValues();
        contentValues.put(FIELD_DESC, coffeeToInsert.getDesc());
        contentValues.put(FIELD_IMAGE, coffeeToInsert.getImageUrl());
        contentValues.put(FIELD_ID, coffeeToInsert.getId());
        contentValues.put(FIELD_COFFEE_NAME, coffeeToInsert.getName());

        // Need to get a writable database for this object
        SQLiteDatabase writeableDatabase = this.getWritableDatabase();

        // insert into the database
        writeableDatabase.insert(TABLE_NAME, null, contentValues);
        writeableDatabase.close();
    }

    public Coffee queryForOneId(String id) {
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Coffee returnCoffee = null;

        Cursor cursor = readableDatabase.rawQuery(DatabaseContract.getSelectedIdItem(id), null);

        // Check if we do get something back from the query
        if(cursor.moveToFirst()) {
            String descFromDb = cursor.getString(cursor.getColumnIndex(FIELD_DESC));
            String imageFromDb = cursor.getString(cursor.getColumnIndex(FIELD_IMAGE));
            String idFromDb = cursor.getString(cursor.getColumnIndex(FIELD_IMAGE));
            String nameFromDb = cursor.getString(cursor.getColumnIndex(FIELD_COFFEE_NAME));
        }
        readableDatabase.close();
        return returnCoffee;
    }

    public ArrayList<Coffee> queryForAllCoffeeItems() {
        ArrayList<Coffee> returnCoffeeList = null;
        SQLiteDatabase readableDatabase = this.getReadableDatabase();
        Cursor cursor = readableDatabase.rawQuery(DatabaseContract.getSelectedAllCoffeeItems(), null);

        // check if we do get something back from the query
        if(cursor.moveToFirst()) {
            returnCoffeeList = new ArrayList<>();
            do {
                Coffee returnCoffee = null;
                String descFromDb = cursor.getString(cursor.getColumnIndex(FIELD_DESC));
                String imageFromDb = cursor.getString(cursor.getColumnIndex(FIELD_IMAGE));
                String idFromDb = cursor.getString(cursor.getColumnIndex(FIELD_IMAGE));
                String nameFromDb = cursor.getString(cursor.getColumnIndex(FIELD_COFFEE_NAME));
                returnCoffee = new Coffee(descFromDb, imageFromDb, idFromDb, nameFromDb);
                returnCoffeeList.add(returnCoffee);
            }while(cursor.moveToNext());
        }
        readableDatabase.close();
        return returnCoffeeList;
    }
}
