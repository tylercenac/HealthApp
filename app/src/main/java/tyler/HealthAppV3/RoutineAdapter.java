package tyler.HealthAppV3;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RoutineAdapter extends RecyclerView.Adapter<RoutineAdapter.ToDoViewHolder> {

    private Context mContext;
    private Cursor cursor;
    private ListItemClickListener listItemClickListener;

    public RoutineAdapter(Context mContext, Cursor cursor, ListItemClickListener listItemClickListener){
        this.mContext = mContext;
        this.cursor = cursor;
        this.listItemClickListener = listItemClickListener;
    }


    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item, viewGroup, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder toDoViewHolder, int i) {

        if(!cursor.moveToPosition(i)){
            return;
        }

        String exercise = cursor.getString(cursor.getColumnIndex(RoutineContract.RoutineEntry.COLUMN_EXERCISE));
        int sets = cursor.getInt(cursor.getColumnIndex(RoutineContract.RoutineEntry.COLUMN_SETS));
        int reps = cursor.getInt(cursor.getColumnIndex(RoutineContract.RoutineEntry.COLUMN_REPS));
        //String timestamp = cursor.getString(cursor.getColumnIndex(RoutineContract.ToDoListEntry.COLUMN_TIMESTAMP));

        long id = cursor.getLong(cursor.getColumnIndex(RoutineContract.RoutineEntry._ID));

        toDoViewHolder.itemView.setTag(id);


        toDoViewHolder.exercise_text.setText(exercise);
        toDoViewHolder.sets_text.setText(String.valueOf(sets));
        toDoViewHolder.reps_text.setText(String.valueOf(reps));
        //toDoViewHolder.timestamp_text.setText(timestamp);

    }

    @Override
    public int getItemCount() {
        return cursor.getCount();
    }

    public class ToDoViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

        TextView exercise_text, sets_text, reps_text, timestamp_text;



        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);

            exercise_text = itemView.findViewById(R.id.exercise_text);
            sets_text = itemView.findViewById(R.id.sets_text);
            reps_text = itemView.findViewById(R.id.reps_text);
            //timestamp_text = itemView.findViewById(R.id.timestamp_text);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v){

            listItemClickListener.onClick(v, getAdapterPosition());

        }


    }

    public void swapCursor(Cursor newCursor){

        if(cursor != null){
            cursor.close();
        }

        cursor = newCursor;
        if(newCursor != null){
            this.notifyDataSetChanged();
        }

    }
}
