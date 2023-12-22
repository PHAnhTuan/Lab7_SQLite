package com.example.lab7_sqlite;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ToDoAdapter  extends ArrayAdapter<ToDo> {
    private Context context;
    private List<ToDo> tasks;
    public ToDoAdapter(Context context, List<ToDo> tasks) {
        super(context, 0, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ToDo task = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_todo, parent, false);

        }
        TextView taskTitle = convertView.findViewById(R.id.task_title);
        TextView taskTContent = convertView.findViewById(R.id.task_content);
        TextView taskDate = convertView.findViewById(R.id.task_date);
        TextView taskType = convertView.findViewById(R.id.task_type);

        taskTitle.setText(task.getTitle());
        taskTContent.setText(task.getContent());
        taskDate.setText(task.getDate());
        taskType.setText(task.getType());

        return convertView;
    }

}
