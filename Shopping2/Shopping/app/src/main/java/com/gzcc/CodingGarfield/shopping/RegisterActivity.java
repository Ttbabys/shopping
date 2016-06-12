package com.gzcc.CodingGarfield.shopping;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText user;
    EditText password;
    EditText phone;
    EditText email;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user=(EditText)this.findViewById(R.id.username2);
        password=(EditText)this.findViewById(R.id.password);
        phone=(EditText)this.findViewById(R.id.phonenumber);
        email=(EditText)this.findViewById(R.id.email);


    }
    void login(View view){

            if (user.getText().toString().length() == 0 || password.getText().toString().length() == 0) {
                Toast.makeText(getApplicationContext(), "注册失败！用户名和密码不能为空！", Toast.LENGTH_LONG).show();
            } else {

                newdatabase db = new newdatabase(this);
                SQLiteDatabase read = db.getReadableDatabase();
                SQLiteDatabase write = db.getWritableDatabase();
                Cursor cursor = read.query("user", new String[]{"username"}, "username=?", new String[]{user.getText().toString()}, null, null, null);
                if (cursor.moveToNext() == true) {
                    Toast.makeText(getApplicationContext(), "注册失败！用户名已被使用！", Toast.LENGTH_LONG).show();
                    cursor.close();
                    write.close();
                    read.close();
                    db.close();
                } else {
                    ContentValues values = new ContentValues();
                    values.put("username", user.getText().toString());
                    values.put("password", password.getText().toString());
                    values.put("phone", phone.getText().toString());
                    values.put("email", email.getText().toString());
                    if (password.getText().toString().length() < 6) {
                        Toast.makeText(getApplicationContext(), "注册失败！密码不能少于六位！", Toast.LENGTH_LONG).show();
                    } else {

                        long flag = write.insert("user", null, values);
                        if (flag > 0) {
                            Toast.makeText(getApplicationContext(), "注册成功！", Toast.LENGTH_LONG).show();
                        }

                    }
                    cursor.close();
                    write.close();
                    read.close();
                    db.close();
                }
            }





        }



    void back(View view){
        Intent back=new Intent(RegisterActivity.this,MainActivity.class);
        startActivity(back);
        this.finish();
    }
    void exit(View view){
       this.finish();
    }
}
