package tyler.HealthAppV3;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RoutineDbHelper extends SQLiteOpenHelper {


    private static final String DB_NAME = "routine.db";
    private static final int DB_VERSION = 1;

    public RoutineDbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_ROUTINE_TABLE = "CREATE TABLE " +
                RoutineContract.RoutineEntry.TABLE_NAME + " ( " +
                RoutineContract.RoutineEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                RoutineContract.RoutineEntry.COLUMN_EXERCISE + " TEXT NOT NULL, " +
                RoutineContract.RoutineEntry.COLUMN_SETS + " INTEGER NOT NULL, "+
                RoutineContract.RoutineEntry.COLUMN_REPS + " INTEGER NOT NULL"+
                //RoutineContract.ToDoListEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";
        db.execSQL(CREATE_ROUTINE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + RoutineContract.RoutineEntry.TABLE_NAME);
        onCreate(db);

    }
}
