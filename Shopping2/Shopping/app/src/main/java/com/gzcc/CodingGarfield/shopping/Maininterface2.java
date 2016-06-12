package com.gzcc.CodingGarfield.shopping;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Maininterface2 extends Activity {
    Button updatebusiness;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maininterface2);
        updatebusiness=(Button)this.findViewById(R.id.updatebusiness);
        updatebusiness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Maininterface2.this,UpdateBusinessActivity.class);
                startActivity(intent);
            }
        });
    }
}
