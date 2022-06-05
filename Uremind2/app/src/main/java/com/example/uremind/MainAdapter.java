package com.example.uremind;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    // Inisialisasi
    private List<MainData> dataList;
    private Activity context;
    private RoomDB database;

    // constructor
    public MainAdapter(Activity context, List<MainData> dataList){
        this.context = context;
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inisialisasi
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_task,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    //Inisialisasi main data
        MainData data = dataList.get(position);

        //Inisialisasi database
        database = RoomDB.getInstance(context);
        //Set text on text view
        holder.textTitle.setText(data.getTitletask());
        holder.textNote.setText(data.getNotetask());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Inisialisasi main data
                MainData d = dataList.get(holder.getAdapterPosition());
                //Get id
                int sID = d.getID();
                //Get text
                String sText = d.getTitletask();
                String sNote = d.getNotetask();

                // Dialog
                Dialog dialog = new Dialog(context);
                //Content view
                dialog.setContentView(R.layout.task_update);

                //Inisialisasi lebar&tinggi
                int width = WindowManager.LayoutParams.MATCH_PARENT;
                int height = WindowManager.LayoutParams.MATCH_PARENT;
                //Set Layout
                dialog.getWindow().setLayout(width,height);
                //Show Dialog
                dialog.show();

                //Inisialisasi dan assign variabel
                EditText editText = dialog.findViewById(R.id.Tasktitle);
                EditText editNote = dialog.findViewById(R.id.Tasknotes);
                Button btUpdate = dialog.findViewById(R.id.btn_update);

                //Set text on edit text
                editText.setText(sText);
                editNote.setText(sNote);

                btUpdate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //Dismiss dialog
                        dialog.dismiss();
                        //Get update text from edit text
                        String uText = editText.getText().toString().trim();
                        String uNote = editNote.getText().toString().trim();

                        //Update text in database
                        database.mainDao().updatetitle(sID,uText);
                        database.mainDao().updatenote(sID,uNote);

                        //Notify when data is updated
                        dataList.clear();
                        dataList.addAll(database.mainDao().getAll());
                        notifyDataSetChanged();
                    }
                });
            }
        });

        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Inisialisasi main data
                MainData d = dataList.get(holder.getAdapterPosition());
                //Delete text from database
                database.mainDao().delete(d);
                // NOtify when data's deletede
                int position = holder.getAdapterPosition();
                dataList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,dataList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        //Inisialisasi
        TextView textTitle;
        TextView textNote;
        ImageView btEdit, btDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Asign variabel
            textTitle = itemView.findViewById(R.id.taskOutput);
            textNote = itemView.findViewById(R.id.noteOutput);
            btEdit = itemView.findViewById(R.id.btn_edit);
            btDelete = itemView.findViewById(R.id.btn_delete);


        }
    }
}
