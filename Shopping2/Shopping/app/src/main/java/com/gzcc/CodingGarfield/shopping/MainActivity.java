package com.gzcc.CodingGarfield.shopping;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText user;
    EditText password;
    RadioButton customer;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user=(EditText)this.findViewById(R.id.username);
        password=(EditText)this.findViewById(R.id.secret);
        customer=(RadioButton) this.findViewById(R.id.customer);
    }
    void login(View view){
        if(customer.isChecked()){
            newdatabase db=new newdatabase(this);
            SQLiteDatabase read=db.getReadableDatabase();
            String[] column=new String[]{"username","password"};
            String  selection="username=? and password=?";
            String[] selectionargs=new String[]{user.getText().toString(),password.getText().toString()};
            Cursor cursor=read.query("user",column,selection,selectionargs,null,null,null);
            if(cursor.moveToNext()){
                sharedPreferences=this.getSharedPreferences("Countername", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.putString("username",user.getText().toString());
                editor.commit();
                Intent intent=new Intent(this,Main3Activity.class);
                startActivity(intent);

            }
            else
                Toast.makeText(getApplicationContext(),"登录失败！请重新输入！",Toast.LENGTH_LONG).show();

            cursor.close();
            read.close();
            db.close();

        }
        else{
            newdatabase db=new newdatabase(this);
            SQLiteDatabase read=db.getReadableDatabase();
            String[] column=new String[]{"username","password"};
            String  selection="username=? and password=?";
            String[] selectionargs=new String[]{user.getText().toString(),password.getText().toString()};
            Cursor cursor=read.query("business",column,selection,selectionargs,null,null,null);
            if(cursor.moveToNext()){
                sharedPreferences=this.getSharedPreferences("Countername", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.putString("username",user.getText().toString());
                editor.commit();
                Intent intent=new Intent(this,Maininterface2.class);
                startActivity(intent);

            }
            else
                Toast.makeText(getApplicationContext(),"登录失败！请重新输入！",Toast.LENGTH_LONG).show();
            cursor.close();
            read.close();
            db.close();



        }

    }
   void register(View view){
       if(customer.isChecked()) {
           Intent register = new Intent(MainActivity.this, RegisterActivity.class);
           startActivity(register);
           this.finish();
       }
       else{
           Intent register = new Intent(MainActivity.this, Register2Activity.class);
           startActivity(register);
           this.finish();
       }


    }
}
