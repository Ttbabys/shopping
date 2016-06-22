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

import com.gzcc.CodingGarfield.shopping.Temporary.newdatabase;

public class Register2Activity extends AppCompatActivity {
    EditText businessname;
    EditText businesspassword;
    EditText businessphone;
    EditText businessemail;
    EditText businesstradename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register2);
        businessname=(EditText)this.findViewById(R.id.businessname);
        businesspassword=(EditText)this.findViewById(R.id.businesspassword);
        businessphone=(EditText)this.findViewById(R.id.businessphone);
        businessemail=(EditText)this.findViewById(R.id.businessemail);
        businesstradename=(EditText)this.findViewById(R.id.tradename);
    }

    void back(View view){
        Intent intent=new Intent(this,LoginIntoActivity.class);
        startActivity(intent);
        this.finish();
    }
    void exit(View view){
        this.finish();
    }
    void register(View view){
        if (businessname.getText().toString().length() == 0 || businesspassword.getText().toString().length() == 0||businesstradename.getText().toString().length()==0) {
            Toast.makeText(getApplicationContext(), "注册失败！用户名和密码以及商店名称不能为空！", Toast.LENGTH_LONG).show();
        } else {

            newdatabase db = new newdatabase(this);
            SQLiteDatabase read = db.getReadableDatabase();
            SQLiteDatabase write = db.getWritableDatabase();
            Cursor cursor = read.query("business", new String[]{"username"}, "username=?", new String[]{businessname.getText().toString()}, null, null, null);
            if (cursor.moveToNext() == true) {
                Toast.makeText(getApplicationContext(), "注册失败！用户名已被使用！", Toast.LENGTH_LONG).show();
                cursor.close();
                write.close();
                read.close();
                db.close();
            } else {
                ContentValues values = new ContentValues();
                values.put("username",businessname.getText().toString());
                values.put("password",businesspassword.getText().toString());
                values.put("phone", businessphone.getText().toString());
                values.put("email",businessemail.getText().toString());
                values.put("tradename",businesstradename.getText().toString());
                if (businesspassword.getText().toString().length() < 6) {
                    Toast.makeText(getApplicationContext(), "注册失败！密码不能少于六位！", Toast.LENGTH_LONG).show();
                } else {

                    long flag = write.insert("business", null, values);
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
}
