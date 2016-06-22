package com.gzcc.CodingGarfield.shopping.Temporary;

import android.content.Intent;
import android.os.Bundle;

import com.gzcc.CodingGarfield.shopping.Finaldb_dbclass.Login;
import com.gzcc.CodingGarfield.shopping.MainShoppingActivity;
import com.gzcc.CodingGarfield.shopping.R;

import net.tsz.afinal.*;

public class finaldb extends FinalActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finaldb);

        FinalDb fdb=FinalDb.create(this);
        Login lg=new Login();
        lg.setStatus("online");
        fdb.update(lg);
        Intent intent=new Intent(this,MainShoppingActivity.class);
        startActivity(intent);
        this.finish();
    }

}
