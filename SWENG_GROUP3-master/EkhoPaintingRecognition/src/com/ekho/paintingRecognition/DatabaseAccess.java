package com.ekho.paintingRecognition;

        import android.content.Context;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import java.util.ArrayList;
        import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<String> getData(String paintingID) {
        List<String> list = new ArrayList<>();
        String id = "ID: ";
        String name = "Name: ";
        String year = "Year: ";
        String type = "Type: ";
        String artist = "Artist: ";
        String description  = "Description: ";


        // SQL command to select all data from table 'Art' where value Art_ID is equal to the paintingID passed as parameter.
        Cursor cursor = database.rawQuery("SELECT * FROM NewDatabase2 WHERE ID = '" + paintingID + "'", null);

        // Append data to appropriate strings.
        cursor.moveToFirst();
        list.add(id + cursor.getString(0));
        list.add(name + cursor.getString(1));
        list.add(year + cursor.getString(2));
        list.add(type + cursor.getString(3));
        list.add(artist + cursor.getString(4));
        list.add(description + cursor.getString(5));
        cursor.close();

        return list;
    }


    public String imageName (String paintingID){
        Cursor cursor = database.rawQuery("SELECT * FROM NewDatabase2 WHERE ID = '" + paintingID + "'", null);
        cursor.moveToFirst();
        String s = cursor.getString(6);
        cursor.close();
        return s;
    }

}