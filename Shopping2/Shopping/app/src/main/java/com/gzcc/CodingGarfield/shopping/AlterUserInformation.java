package com.gzcc.CodingGarfield.shopping;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AlterUserInformation extends Activity {
    TextView alterusername;
    EditText alteruserpassword;
    EditText alterusernumber;
    EditText alteruseremail;
    Button alteruserinformation;
    Button alteruserback;
    SharedPreferences sharedPreferences;
    String name;
    SQLiteDatabase read;
    SQLiteDatabase write;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_user_information);
        alterusername=(TextView) this.findViewById(R.id.alterusername);
        alteruserpassword=(EditText)this.findViewById(R.id.alteruserpassword);
        alterusernumber=(EditText)this.findViewById(R.id.alterusernumber);
        alteruseremail=(EditText)this.findViewById(R.id.alteruseremail);
        alteruserinformation=(Button)this.findViewById(R.id.alteruserinformation);
        alteruserback=(Button)this.findViewById(R.id.alteruserback);

        sharedPreferences=this.getSharedPreferences("Countername", Context.MODE_PRIVATE);
        name=sharedPreferences.getString("username",null);

        newdatabase db=new newdatabase(this);
        read=db.getReadableDatabase();
        write=db.getWritableDatabase();
        String  selection="username=?";
        Cursor cursor=read.query("user",null,selection,new String[]{name},null,null,null);
        cursor.moveToNext();
        alterusername.setText(cursor.getString(1));
        alteruserpassword.setText(cursor.getString(2));
        alterusernumber.setText(cursor.getString(3));
       alteruseremail.setText(cursor.getString(4));
        alteruserback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(AlterUserInformation.this,Main3Activity.class);
                startActivity(back);
                finish();
            }
        });
        alteruserinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }
    void save(){
        String str=alteruserpassword.getText().toString();
        if(str.length()<6){
            warning();

        }
        else{
            ContentValues values = new ContentValues();
           // values.put("username", alterusername.getText().toString());
            values.put("password", alteruserpassword.getText().toString());
            values.put("phone", alterusernumber.getText().toString());
            values.put("email",alteruseremail.getText().toString());
            long flag = write.update("user",values,"username=?",new String[]{name});
            if (flag > 0) {
                Toast.makeText(getApplicationContext(), "修改信息成功！", Toast.LENGTH_LONG).show();

            }
            else
                Toast.makeText(getApplicationContext(), "修改信息失败！", Toast.LENGTH_LONG).show();
        }

    }

    void warning(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage( "修改失败！密码不能少于六位！").setCancelable(false).setPositiveButton("确定",null).create().show();
    }
}
