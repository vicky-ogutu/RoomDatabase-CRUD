package com.example.roomdatabasetutorial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class studentAdapter extends RecyclerView.Adapter<studentAdapter.studentViewHolder>{
    private List<student> _students = new ArrayList<>();


    @NonNull
    @Override
    public studentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_layout, parent, false);
        return new studentViewHolder(view);

            }

    @Override
    public void onBindViewHolder(@NonNull studentViewHolder holder, int position) {

        student currentStudent = _students.get(position);

        holder.id.setText(String.valueOf(currentStudent.getId()));
        holder.First_Name.setText(currentStudent.getFirst_name());
        holder.Last_Name.setText(currentStudent.getLast_name());

    }

    @Override
    public int getItemCount() {
        return _students.size();
    }

    public void set_students(List<student> _students){
        this._students = _students;

        notifyDataSetChanged();

    }

    class studentViewHolder extends RecyclerView.ViewHolder{
               TextView id, First_Name, Last_Name;

        public studentViewHolder(@NonNull View itemView) {
            super(itemView);
            id= itemView.findViewById(R.id.ID);
            First_Name = itemView.findViewById(R.id.FirstName);
            Last_Name= itemView.findViewById(R.id.LastName);

        }
    }


}
