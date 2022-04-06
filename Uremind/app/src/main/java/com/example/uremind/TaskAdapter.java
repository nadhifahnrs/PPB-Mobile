package com.example.uremind;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder>{

    Context context;
    RealmResults<DataTask> taskList;

    public TaskAdapter(Context context, RealmResults<DataTask> taskList){
        this.context = context;
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskHolder(LayoutInflater.from(context).inflate(R.layout.task_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        DataTask task = taskList.get(position);
        holder.taskoutput.setText(task.getTitle());
        holder.notesoutput.setText(task.getNotes());

        String TimeFormat = DateFormat.getDateTimeInstance().format(task.ncreatedTime);
        holder.timeoutput.setText(TimeFormat);
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public class TaskHolder extends RecyclerView.ViewHolder{

        TextView taskoutput;
        TextView notesoutput;
        TextView timeoutput;

        public TaskHolder(@NonNull View itemView) {
            super(itemView);
            taskoutput = itemView.findViewById(R.id.TaskOutput);
            notesoutput = itemView.findViewById(R.id.NotesOutput);
            timeoutput = itemView.findViewById(R.id.TimeOutput);
        }
    }
}
