package com.gzcc.CodingGarfield.shopping;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gzcc.CodingGarfield.shopping.Temporary.newdatabase;

public class UpdateBusinessActivity extends Activity {
    TextView busname;
    TextView buspassword;
    TextView busnumber;
    TextView busemail;
    TextView busshopname;
    Button busupdate;
    Button busback;
    SharedPreferences sharedPreferences;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_business);
        busname=(TextView)this.findViewById(R.id.busname);
        buspassword=(TextView)this.findViewById(R.id.buspassword);
        busnumber=(TextView)this.findViewById(R.id.busnumber);
        busemail=(TextView)this.findViewById(R.id.busemail);
        busshopname=(TextView)this.findViewById(R.id.busshopname);
        busupdate=(Button)this.findViewById(R.id.busupdate);
        busback=(Button)this.findViewById(R.id.busback);
        sharedPreferences=this.getSharedPreferences("Countername", Context.MODE_PRIVATE);
        name=sharedPreferences.getString("username",null);

        newdatabase db=new newdatabase(this);
        SQLiteDatabase read=db.getReadableDatabase();

        String  selection="username=?";

        Cursor cursor=read.query("business",null,selection,new String[]{name},null,null,null);
        cursor.moveToNext();
        busname.setText(cursor.getString(1));
        buspassword.setText(cursor.getString(2));
        busnumber.setText(cursor.getString(3));
        busemail.setText(cursor.getString(4));
        busshopname.setText(cursor.getString(5));
        busback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back=new Intent(UpdateBusinessActivity.this,Maininterface2.class);
                startActivity(back);
                finish();
            }
        });
        busupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent update=new Intent(UpdateBusinessActivity.this,AlterBusinessInformation.class);
                startActivity(update);
                finish();
            }
        });

    }
}
