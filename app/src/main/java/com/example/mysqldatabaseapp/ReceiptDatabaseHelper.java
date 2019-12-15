package com.example.mysqldatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ReceiptDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "receipts.db";
    private static final String TABLE_NAME = "receipts";
    private static int DATABASE_VERSION = 1; //Strictly positive

    private static final String COLUMN_RECEIPTID = "receipt_id";
    private static final String COLUMN_RECEIPTTITLE = "receipt_title";
    private static final String COLUMN_RECEIPTPRICE = "receipt_price";

    public ReceiptDatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createStatement = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_RECEIPTID + " INTEGER PRIMARY KEY, " +
                COLUMN_RECEIPTTITLE + " TEXT, " +
                COLUMN_RECEIPTPRICE + " TEXT)";

        db.execSQL(createStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        DATABASE_VERSION = newVersion;
        String updateQuery = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(updateQuery);
        onCreate(db);
    }

    public void insertReceipt(Receipt receipt) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_RECEIPTTITLE, receipt.getReceiptTitle());
        contentValues.put(COLUMN_RECEIPTPRICE, receipt.getReceiptPrice());

        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME, null, contentValues);
    }

    public ArrayList<Receipt> getReceipts() {
        SQLiteDatabase db = getReadableDatabase();

        String[] columns = {COLUMN_RECEIPTID, COLUMN_RECEIPTTITLE, COLUMN_RECEIPTPRICE};
        String[] where = {};

        Cursor cursor = db.query(TABLE_NAME, columns, "", where, "", "", "");

        ArrayList<Receipt> receipts = new ArrayList<>(cursor.getCount());

        int id = cursor.getColumnIndex(COLUMN_RECEIPTID);
        int title = cursor.getColumnIndex(COLUMN_RECEIPTTITLE);
        int price = cursor.getColumnIndex(COLUMN_RECEIPTPRICE);

        while(cursor.moveToNext()) {
            Receipt rec = new Receipt(cursor.getString(title), cursor.getString(price));
            rec.setReceiptID(cursor.getInt(id));
            receipts.add(rec);
        }

        cursor.close();
        return receipts;
    }

    public void resetDatabase() {
        SQLiteDatabase db = getWritableDatabase();
        //db.delete(TABLE_NAME, );
    }
}
