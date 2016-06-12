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

public class AlterBusinessInformation extends Activity {
    TextView alterbusinessrname;
    EditText alterbusinesspassword;
    EditText alterbusinessnumber;
    EditText alterbusinessemail;
    EditText alterbusinessshopname;
    Button alterbusinessinformation;
    Button alterbusinessback;
    SharedPreferences sharedPreferences;
    String name;
    SQLiteDatabase read;
    SQLiteDatabase write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_business_information);
        alterbusinessrname=(TextView)this.findViewById(R.id.alterbusinessname);
        alterbusinesspassword=(EditText)this.findViewById(R.id.alterbusinesspassword);
        alterbusinessnumber=(EditText)this.findViewById(R.id.alterbusinessnumber);
        alterbusinessemail=(EditText)this.findViewById(R.id.alterbusinessemail);
        alterbusinessshopname=(EditText)this.findViewById(R.id.alterbusinessshopname);
        alterbusinessback=(Button)this.findViewById(R.id.alterbusinessback);
        alterbusinessinformation=(Button)findViewById(R.id.alterbusinessinformation);

        sharedPreferences=this.getSharedPreferences("Countername", Context.MODE_PRIVATE);
        name=sharedPreferences.getString("username",null);

        newdatabase db=new newdatabase(this);
        read=db.getReadableDatabase();
        write=db.getWritableDatabase();
        String  selection="username=?";
        Cursor cursor=read.query("business",null,selection,new String[]{name},null,null,null);
        cursor.moveToNext();
        alterbusinessrname.setText(cursor.getString(1));
        alterbusinesspassword.setText(cursor.getString(2));
        alterbusinessnumber.setText(cursor.getString(3));
        alterbusinessemail.setText(cursor.getString(4));
        alterbusinessshopname.setText(cursor.getString(5));

        alterbusinessback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(AlterBusinessInformation.this,UpdateBusinessActivity.class);
                startActivity(back);
                finish();
            }
        });
        alterbusinessinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }
    void save(){
        String str=alterbusinesspassword.getText().toString();
        if(str.length()<6){
            warning();

        }
        else {
            ContentValues values = new ContentValues();
            values.put("password", alterbusinesspassword.getText().toString());
            values.put("phone", alterbusinessnumber.getText().toString());
            values.put("email",alterbusinessemail.getText().toString());
            values.put("tradename",alterbusinessshopname.getText().toString());
            int flag = write.update("business",values,"username=?",new String[]{name});
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
