package com.example.lab7_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ToDoAo {
    private final DbHelper dbHelper;

    public ToDoAo(Context context) {
        dbHelper = new DbHelper(context);
    }
    public boolean addToDo(ToDo toDo){

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        database.beginTransaction();
        //Ta sẽ sử dụng ContentValues để đưa dữ liệu vào database
        ContentValues values = new ContentValues();
        //Ở đây id là giá trị tự động tăng nên chúng ta không cần thêm vào
        values.put("TITLE", toDo.getTitle());
        values.put("CONTENT", toDo.getContent());
        values.put("DATE", toDo.getDate());
        values.put("TYPE", toDo.getType());
        values.put("STATUS", toDo.getStatus());
        //Nếu thành công sẽ trả vê giá trị tương ứng số hàng mà dữ liệu được add trong bảng
        long check = database.insert("TODO", null, values);
        return check != -1;
    }
}
