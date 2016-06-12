package com.gzcc.CodingGarfield.shopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Maininterface extends AppCompatActivity {
    Button userinformation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maininterface);
        userinformation=(Button)this.findViewById(R.id.userinformation);
        userinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Maininterface.this,AlterUserInformation.class);
                startActivity(intent);
            }
        });
    }
}
