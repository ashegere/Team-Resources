package com.example.teamres.Utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.teamres.Model.AssignModel;
import com.example.teamres.Model.FoodModel;
import com.example.teamres.Model.ToDoModel;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NAME = "Resdb.db";
    private static final String TODO_TABLE = "todo";
    private static final String IMG_TABLE = "images";
    private static final String FSHIFT_TABLE = "fshift";
    private static final String SSHIFT_TABLE = "sshift";
    private static final String TSHIFT_TABLE = "tshift";
    private static final String BFAST_TABLE = "bfast";
    private static final String LUNCH_TABLE = "lunch";
    private static final String DINNER_TABLE = "dinner";
    private static final String ID = "id";
    private static final String TASK = "task";
    private static final String STATUS= "status";
    private static final String DATE = "date";
    private static final String ASSN = "assignment";
    private static final String FOOD = "foodItem";




    private static final String CREATE_TODO_TABLE = "CREATE TABLE " + TODO_TABLE + "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TASK + " TEXT, " + STATUS + " INTEGER, " + DATE + " TEXT)";

    private static final String CREATE_IMG_TABLE = "CREATE TABLE IF NOT EXISTS images (id INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, message TEXT, imagepath TEXT)";


    private static final String CREATE_FSHIFT_TABLE = "CREATE TABLE IF NOT EXISTS fshift (id INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, assignment TEXT, date TEXT, status INTEGER)";

    private static final String CREATE_SSHIFT_TABLE = "CREATE TABLE IF NOT EXISTS sshift (id INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, assignment TEXT, date TEXT, status INTEGER)";

    private static final String CREATE_TSHIFT_TABLE = "CREATE TABLE IF NOT EXISTS tshift (id INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, assignment TEXT, date TEXT, status INTEGER)";

    private static final String CREATE_BFAST_TABLE = "CREATE TABLE IF NOT EXISTS bfast (id INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, foodItem TEXT)";

    private static final String CREATE_LUNCH_TABLE = "CREATE TABLE IF NOT EXISTS lunch (id INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, foodItem TEXT)";

    private static final String CREATE_DINNER_TABLE = "CREATE TABLE IF NOT EXISTS dinner (id INTEGER PRIMARY KEY " +
            "AUTOINCREMENT, foodItem TEXT)";




    private SQLiteDatabase db;

    public DBHelper(Context ctc) {

        super(ctc, NAME , null, 2);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TODO_TABLE);
        db.execSQL(CREATE_IMG_TABLE);
        db.execSQL(CREATE_FSHIFT_TABLE);
        db.execSQL(CREATE_SSHIFT_TABLE);
        db.execSQL(CREATE_TSHIFT_TABLE);
        db.execSQL(CREATE_BFAST_TABLE);
        db.execSQL(CREATE_LUNCH_TABLE);
        db.execSQL(CREATE_DINNER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TODO_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + IMG_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + FSHIFT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + SSHIFT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TSHIFT_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + BFAST_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + LUNCH_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DINNER_TABLE);

        onCreate(db);
    }

    public void openDatabase() {

        db = this.getWritableDatabase();
    }


    // ---------------------------------------------------------------------------------------------------------------------- //

    // For Communications

    public void insertTask(ToDoModel task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task.getTask());
        cv.put(STATUS, 0);
        cv.put(DATE, task.getDate());
        db.insert(TODO_TABLE, null, cv);
    }

    public List<ToDoModel> getAllTasks() {
        List<ToDoModel> taskList = new ArrayList<>();
        Cursor c = null;
        db.beginTransaction();
         try {
             c = db.query(TODO_TABLE, null, null, null, null, null, null, null);
             if(c != null) {
                 if(c.moveToFirst()) {
                     do{
                         ToDoModel task = new ToDoModel();
                         task.setId(c.getInt(c.getColumnIndex(ID)));
                         task.setTask(c.getString(c.getColumnIndex(TASK)));
                         task.setStatus(c.getInt(c.getColumnIndex(STATUS)));
                         task.setDate(c.getString(c.getColumnIndex(DATE)));

                         taskList.add(task);

                     }while(c.moveToNext());
                 }
             }
         }
         finally {
             db.endTransaction();
             c.close();
         }
         return taskList;
    }

    public void updateTask(int id, String task) {
        ContentValues cv = new ContentValues();
        cv.put(TASK, task);
        db.update(TODO_TABLE, cv, ID + "=?", new String[] {String.valueOf(id)});
    }

    public void deleteTask(int id) {
        db.delete(TODO_TABLE, ID + "=?", new String[] {String.valueOf(id)});
    }



    // ---------------------------------------------------------------------------------------------------------------------- //

// ---------------------------------------------------------------------------------------------------------------------- //

    //For Job Assignments

    public void insertAssignment(AssignModel asmt, int tb) {
        ContentValues cv = new ContentValues();
        cv.put(ASSN, asmt.getAssn());
        cv.put(STATUS, asmt.getStatus());
        cv.put(DATE, asmt.getDate());

        if(tb == 1) {
            db.insert(FSHIFT_TABLE, null, cv);
        }
        else if(tb == 2){
            db.insert(SSHIFT_TABLE, null, cv);
        }
        else if(tb == 3){
            db.insert(TSHIFT_TABLE, null, cv);
        }
    }

    public void updateDate(AssignModel asmt, int id, int tb) {
        ContentValues cv = new ContentValues();
        cv.put(DATE, asmt.getDate());


        if(tb == 1)
            db.update(FSHIFT_TABLE ,cv,ID + "=?", new String[]{String.valueOf(id)});
        if(tb == 2)
            db.update(SSHIFT_TABLE ,cv,ID + "=?", new String[]{String.valueOf(id)});
        if(tb == 3)
            db.update(TSHIFT_TABLE ,cv,ID + "=?", new String[]{String.valueOf(id)});
    }

    public void updateStatus(AssignModel asmt, int id, int tb) {
        ContentValues cv = new ContentValues();
        cv.put(STATUS, asmt.getStatus());

        if(tb == 1)
            db.update(FSHIFT_TABLE ,cv,ID + "=?", new String[]{String.valueOf(id)});
        if(tb == 2)
            db.update(SSHIFT_TABLE ,cv,ID + "=?", new String[]{String.valueOf(id)});
        if(tb == 3)
            db.update(TSHIFT_TABLE ,cv,ID + "=?", new String[]{String.valueOf(id)});
    }

    public List<AssignModel> getAllAssignments(int tb) {
        List<AssignModel> asmtList = new ArrayList<>();
        Cursor c = null;
        db.beginTransaction();

        try {

            if(tb == 1) {
                c = db.query(FSHIFT_TABLE, null, null, null, null, null, null, null);

            }

            else if (tb == 2) {
                c = db.query(SSHIFT_TABLE, null, null, null, null, null, null, null);
            }
            else if (tb == 3){
                c = db.query(TSHIFT_TABLE, null, null, null, null, null, null, null);
            }
            else
                c = null;

            if(c != null) {
                if(c.moveToFirst()) {
                    do{
                        AssignModel asmt = new AssignModel();

                        asmt.setId(c.getInt(c.getColumnIndex(ID)));
                        asmt.setAssn(c.getString(c.getColumnIndex(ASSN)));

                        asmtList.add(asmt);

                    }while(c.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            c.close();
        }
        return asmtList;
    }

    public void deleteAssignment(int id, int tb) {

        if(tb == 1) {
            db.delete(FSHIFT_TABLE, ID + "=?", new String[]{String.valueOf(id)});
        }

        if(tb == 2){
            db.delete(SSHIFT_TABLE, ID + "=?", new String[]{String.valueOf(id)});
        }
        if(tb == 3){
            db.delete(TSHIFT_TABLE, ID + "=?", new String[]{String.valueOf(id)});
        }
    }


    public boolean statusTrue(int id, int tb) {
        Cursor c = null;
        String where = ID+"="+id;
        String from[] = {STATUS};
        //ID + "=?", new String[] {String.valueOf(id)}
        if(tb == 1) {
            c = db.query(true, FSHIFT_TABLE, from, where, null,
                    null, null, null, null);
        }

        if(tb == 2){
            c = db.query(true, SSHIFT_TABLE, from, where, null,
                    null, null, null, null);
        }
        if(tb == 3){
            c = db.query(true, TSHIFT_TABLE, from, where, null,
                    null, null, null, null);
        }

        c.moveToFirst();

        if(c.getInt(c.getColumnIndex(STATUS)) == 1)
            return true;

        else
            return false;
    }

    public String getDate(int id, int tb) {
        Cursor c = null;
        String where = ID+"="+id;
        String from[] = {DATE};
        //ID + "=?", new String[] {String.valueOf(id)}
        if(tb == 1) {
            c = db.query(true, FSHIFT_TABLE, from, where, null,
                    null, null, null, null);
        }

        if(tb == 2){
            c = db.query(true, SSHIFT_TABLE, from, where, null,
                    null, null, null, null);
        }
        if(tb == 3){
            c = db.query(true, TSHIFT_TABLE, from, where, null,
                    null, null, null, null);
        }

        c.moveToFirst();
        return c.getString(c.getColumnIndex(DATE));

    }

    // ---------------------------------------------------------------------------------------------------------------------- //


        //Food
    // -----------------------------------------------------------------------------------------------------------------


    public void insertFood(FoodModel food, int ft) {
        ContentValues cv = new ContentValues();
        cv.put(FOOD, food.getFoodName());

        if(ft == 1) {
            db.insert(BFAST_TABLE, null, cv);
        }
        else if(ft == 2){
            db.insert(LUNCH_TABLE, null, cv);
        }
        else if(ft == 3){
            db.insert(DINNER_TABLE, null, cv);
        }
    }

    public void deleteFood(int id, int ft) {

        if(ft == 1) {
            db.delete(BFAST_TABLE, ID + "=?", new String[]{String.valueOf(id)});
        }

        if(ft == 2){
            db.delete(LUNCH_TABLE, ID + "=?", new String[]{String.valueOf(id)});
        }
        if(ft == 3){
            db.delete(DINNER_TABLE, ID + "=?", new String[]{String.valueOf(id)});
        }
    }

    public List<FoodModel> getAllFoods(int ft) {
        List<FoodModel> foodsList = new ArrayList<>();
        Cursor c = null;
        db.beginTransaction();

        try {

            if(ft == 1) {
                c = db.query(BFAST_TABLE, null, null, null, null, null, null, null);

            }

            else if (ft == 2) {
                c = db.query(LUNCH_TABLE, null, null, null, null, null, null, null);
            }
            else if (ft == 3){
                c = db.query(DINNER_TABLE, null, null, null, null, null, null, null);
            }
            else
                c = null;

            if(c != null) {
                if(c.moveToFirst()) {
                    do{
                        FoodModel food = new FoodModel();

                        food.setId(c.getInt(c.getColumnIndex(ID)));
                        food.setFoodName(c.getString(c.getColumnIndex(FOOD)));

                        foodsList.add(food);

                    }while(c.moveToNext());
                }
            }
        }
        finally {
            db.endTransaction();
            c.close();
        }
        return foodsList;
    }

    public void updateFood(FoodModel food, int id, int ft) {
        ContentValues cv = new ContentValues();
        cv.put(FOOD, food.getFoodName());

        if(ft == 1)
            db.update(BFAST_TABLE ,cv,ID + "=?", new String[]{String.valueOf(id)});
        if(ft == 2)
            db.update(LUNCH_TABLE ,cv,ID + "=?", new String[]{String.valueOf(id)});
        if(ft == 3)
            db.update(DINNER_TABLE ,cv,ID + "=?", new String[]{String.valueOf(id)});
    }

}
