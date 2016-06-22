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
import android.widget.TextView;
import android.widget.Toast;

import com.gzcc.CodingGarfield.shopping.Finaldb_dbclass.Login;
import com.gzcc.CodingGarfield.shopping.Temporary.newdatabase;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.annotation.view.ViewInject;

import java.util.Date;
import java.util.List;

public class LoginIntoActivity extends AppCompatActivity {
    EditText user;
    EditText password;
    RadioButton customer;
    SharedPreferences sharedPreferences;

    @ViewInject(id=R.id.textView)
    TextView textView;

    Login lg;


    FinalDb finalDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_into);
        user=(EditText)this.findViewById(R.id.username);
        password=(EditText)this.findViewById(R.id.secret);
        customer=(RadioButton) this.findViewById(R.id.customer);

        finalDb = FinalDb.create(this);
//        finalDb = FinalDb.create(this);
//        lg = new Login();
//        lg.setStatus("online");
//        lg.setLoginDate(new Date());
//        finalDb.save(lg);

    }
    void login(View view){



        if(customer.isChecked()){
            newdatabase db1=new newdatabase(this);
            SQLiteDatabase read=db1.getReadableDatabase();
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



                List<Login> list=finalDb.findAll(Login.class);
                Login lg = new Login();
                lg.setStatus("online");
                lg.setLoginDate(new Date());
                if (list.size()!=0)
                {
                    finalDb.deleteAll(Login.class);
                }
                finalDb.save(lg);

                Intent intent=new Intent(this,MainShoppingActivity.class);
                startActivity(intent);
                this.finish();

            }
            else
                Toast.makeText(getApplicationContext(),"登录失败！请重新输入！",Toast.LENGTH_LONG).show();

            cursor.close();
            read.close();
            db1.close();

        }
        else{
            newdatabase db1=new newdatabase(this);
            SQLiteDatabase read=db1.getReadableDatabase();
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

                this.finish();
            }
            else
                Toast.makeText(getApplicationContext(),"登录失败！请重新输入！",Toast.LENGTH_LONG).show();
            cursor.close();
            read.close();
            db1.close();

        }

    }
   void register(View view){
       if(customer.isChecked()) {
           Intent register = new Intent(LoginIntoActivity.this, RegisterActivity.class);
           startActivity(register);
           this.finish();
       }
       else{
           Intent register = new Intent(LoginIntoActivity.this, Register2Activity.class);
           startActivity(register);
           this.finish();
       }


    }
}
