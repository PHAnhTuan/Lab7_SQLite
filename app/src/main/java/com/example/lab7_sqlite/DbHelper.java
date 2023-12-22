package com.example.lab7_sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context){
        super(context, "TodoDatabase", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tạo câu lệnh, tạo bảng
        String sql = "CREATE TABLE TODO(ID INTEGER PRIMARY KEY AUTOINCREMENT,"+"TITLE TEXT, CONTENT TEXT, DATE TEXT, TYPE TEXT, STATUS INTEGER)";
        sqLiteDatabase.execSQL(sql);

        //Tạo câu lệnh thêm dữ liệu vào database
        String data = "INSERT INTO TODO VALUES(1, 'Toán', 'Đại số và hình học', '23/12/2023', 'Toán', 1)," +
                "(2, 'Văn', 'Tập làm văn', '24/12/2023', 'Văn', 0)," +
                "(3, 'Anh văn', 'Listening', '25/12/2023', 'Anh văn', 0)";
        sqLiteDatabase.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Khi update database ta cần kiểm tra version hiện tại version khi ta upgrade có khác nhau hay không
        //Nếu có thực hiện câu lệnh xóa bảng và khởi tạo lại
        if(i != i1){
            //Xóa bảng nếu tồn tại
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS TODO");
            //Gọi lại hàm onCreate
            onCreate(sqLiteDatabase);
        }
    }
}
