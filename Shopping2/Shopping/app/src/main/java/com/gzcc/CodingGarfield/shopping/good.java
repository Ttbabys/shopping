package com.gzcc.CodingGarfield.shopping;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gzcc.CodingGarfield.shopping.Finaldb_dbclass.goods;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class good extends AppCompatActivity {

    View view;
    int position;

    int id=1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good);



        id= (int) getIntent().getExtras().getLong("Pid");


        Toast.makeText(getApplicationContext(),String.valueOf(id), Toast.LENGTH_LONG).show();

        FinalDb finalDb=FinalDb.create(this);
        List<goods> goodsList=finalDb.findAll(goods.class);

        ImageView goodIv=(ImageView)this.findViewById(R.id.imageGood);
//        Toast.makeText(getApplicationContext(),goodsList.get((int) id+1).getGoodPicUri() , Toast.LENGTH_LONG).show();
//        goodIv.setImageBitmap(getBitmapFromUrl(goodsList.get((int) id).getGoodPicUri()));
        FinalBitmap finalBitmap=FinalBitmap.create(this);
        finalBitmap.display(goodIv,goodsList.get(id).getGoodPicUri());

        TextView textViewTitle=(TextView)this.findViewById(R.id.textGoodName);
        textViewTitle.setText(goodsList.get(id).getGoodTitle());

        TextView textViewDetail=(TextView)this.findViewById(R.id.textGoodDetail);
        textViewDetail.setText(goodsList.get(id).getGoodDetails());

        TextView textViewPhone=(TextView)this.findViewById(R.id.textGoodPhone);
        textViewPhone.setText(goodsList.get(id).getPhone());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }
}
