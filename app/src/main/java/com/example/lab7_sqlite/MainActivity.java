package com.example.lab7_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView inputTitle, inputContent, inputDate, inputType;
    Button addToDo, updateToDo, deleteToDo;
    ListView todoListView;
    ToDoDAO toDoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        todoListView = findViewById(R.id.todoListView);
        ToDoDAO toDoDAO = new ToDoDAO(this);
        ArrayList<ToDo> listToDo = toDoDAO.getListTodo();
        ToDoAdapter toDoAdapter = new ToDoAdapter(this, listToDo);
        todoListView.setAdapter(toDoAdapter);

        inputTitle = findViewById(R.id.inputTitle);
        inputContent = findViewById(R.id.inputContent);
        inputDate = findViewById(R.id.inputDate);
        inputType = findViewById(R.id.inputType);
        addToDo = findViewById(R.id.addToDo);
        updateToDo = findViewById(R.id.updateToDo);
        deleteToDo = findViewById(R.id.deleteToDo);

        addToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToDo();
            }
        });


        updateToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //updateToDo();
            }
        });

        deleteToDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deleteToDo();
            }
        });
    }

    private void addToDo() {
        String title = inputTitle.getText().toString();
        String content = inputContent.getText().toString();
        String date = inputDate.getText().toString();
        String type = inputType.getText().toString();

        // Kiểm tra xem title không được rỗng
        // Kiểm tra xem có dữ liệu đủ để thêm không
        if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content) &&
                !TextUtils.isEmpty(date) && !TextUtils.isEmpty(type)) {

            ToDo newToDo = new ToDo(0, title, content, date, type, 0);

            // Sử dụng ToDoAo để thêm công việc vào cơ sở dữ liệu
            ToDoAo toDoAo = new ToDoAo(MainActivity.this);
            boolean isSuccess = toDoAo.addToDo(newToDo);

            if (isSuccess) {
                Toast.makeText(MainActivity.this, "Thêm công việc thành công", Toast.LENGTH_SHORT).show();
                inputTitle.setText("");
                inputContent.setText("");
                inputDate.setText("");
                inputType.setText("");
                loadToDoList();
            } else {
                Toast.makeText(MainActivity.this, "Thêm công việc thất bại", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(MainActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }
    private void loadToDoList() {
        // Sử dụng ToDoAo để lấy danh sách công việc từ cơ sở dữ liệu
        ToDoAo toDoAo = new ToDoAo(MainActivity.this);
        ArrayList<ToDo> listToDo = toDoDAO.getListTodo();

        // Cập nhật dữ liệu lên ListView
        ToDoAdapter toDoAdapter = new ToDoAdapter(this, listToDo);
        todoListView.setAdapter(toDoAdapter);
    }
}