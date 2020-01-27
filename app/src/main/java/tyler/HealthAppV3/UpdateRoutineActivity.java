package tyler.HealthAppV3;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdateRoutineActivity extends AppCompatActivity {

    private EditText edit_task;
    private Spinner spinner;
    private Spinner spinner2;
    private Button btn_add;

    private Integer[] priority = {1, 2, 3, 4, 5};
    private Integer[] reps = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};

    SQLiteDatabase mDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_routine);


       final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Add");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }



        edit_task = findViewById(R.id.edit_exercise);
        spinner = findViewById(R.id.spinner);
        spinner2 = findViewById(R.id.spinner2);
        btn_add = findViewById(R.id.btn_add);

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, priority);
        spinner.setAdapter(arrayAdapter);

        ArrayAdapter<Integer> arrayAdapter2 = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, reps);
        spinner2.setAdapter(arrayAdapter2);

        RoutineDbHelper dbHelper = new RoutineDbHelper(this);
        mDB = dbHelper.getWritableDatabase();

        Intent intent = getIntent();

        if(intent != null && intent.hasExtra("id")){
            long id = intent.getLongExtra("id", 1);

            getTask(id);

            actionBar.setTitle("Update");
            btn_add.setText("Update");



        }

        btn_add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if (edit_task.getText().length() == 0) {
                    return;
                }


                String task = edit_task.getText().toString();
                int priority = (int) spinner.getSelectedItem();
                int reps = (int) spinner2.getSelectedItem();
                addNewTask(task, priority, reps);



               /* if (actionBar != null && actionBar.getTitle().equals("Add")) {
                    String task = edit_task.getText().toString();
                    int priority = (int) spinner.getSelectedItem();
                    int reps = (int) spinner2.getSelectedItem();
                    addNewTask(task, priority, reps);
                } else {

                    Intent intent = getIntent();
                    long id = intent.getLongExtra("id", 1);
                    updateTask(id);
                }*///FIX THIS SECTION TO IMPLEMENT UPDATING EXERCISES




            }
        });

    }

    private void getTask(long id) {

        Cursor cursor = mDB.query(false,
                RoutineContract.RoutineEntry.TABLE_NAME,
                null,
                RoutineContract.RoutineEntry._ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        cursor.moveToFirst();

        String task = cursor.getString(cursor.getColumnIndex(RoutineContract.RoutineEntry.COLUMN_EXERCISE));
        int priority = cursor.getInt(cursor.getColumnIndex(RoutineContract.RoutineEntry.COLUMN_SETS));
        int reps = cursor.getInt(cursor.getColumnIndex(RoutineContract.RoutineEntry.COLUMN_REPS));

        edit_task.setText(task);
        spinner.setSelection(priority);
        spinner2.setSelection(reps);


    }

    private void addNewTask(String task, int priority, int reps) {

        ContentValues cv = new ContentValues();
        cv.put(RoutineContract.RoutineEntry.COLUMN_EXERCISE, task);
        cv.put(RoutineContract.RoutineEntry.COLUMN_SETS, priority);
        cv.put(RoutineContract.RoutineEntry.COLUMN_REPS, reps);

        mDB.insert(RoutineContract.RoutineEntry.TABLE_NAME, null, cv);

        Toast.makeText(this, "Exercise added!", Toast.LENGTH_SHORT).show();

    }

    private void updateTask(long id){

        String task = edit_task.getText().toString();
        int priority = (int) spinner.getSelectedItem();
        int reps = (int) spinner2.getSelectedItem();

        ContentValues cv = new ContentValues();
        cv.put(RoutineContract.RoutineEntry.COLUMN_EXERCISE, task);
        cv.put(RoutineContract.RoutineEntry.COLUMN_SETS, priority);
        cv.put(RoutineContract.RoutineEntry.COLUMN_REPS, reps);

        mDB.update(RoutineContract.RoutineEntry.TABLE_NAME,
                cv,
                RoutineContract.RoutineEntry._ID + "=?",
                new String[]{String.valueOf(id)});

    }


}
