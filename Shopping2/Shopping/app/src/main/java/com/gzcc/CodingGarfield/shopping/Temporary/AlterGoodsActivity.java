package com.gzcc.CodingGarfield.shopping.Temporary;

import android.content.ContentValues;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gzcc.CodingGarfield.shopping.Finaldb_dbclass.goods;
import com.gzcc.CodingGarfield.shopping.R;

import net.tsz.afinal.FinalDb;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AlterGoodsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_goods);


        //开始建DB
        FinalDb FDb=FinalDb.create(this);

        List<goods> list=FDb.findAll(goods.class);
        if(list.size()!=0) {
            FDb.deleteAll(goods.class);
        }

        goods goods=new goods();
        goods.setGoodID(2016061801);
        goods.setGoodDetails("Wicker约拍，完美释放你的梦想");
        goods.setGoodItem("服务");
        goods.setGoodTitle("Wicker约拍");
        goods.setPhone("110");
        goods.setGoodPicUri("http://images2015.cnblogs.com/blog/809500/201606/809500-20160618030359698-160201919.jpg");
        FDb.save(goods);

        FDb=FinalDb.create(this);
        goods=new goods();
        goods.setGoodID(2016061802);
        goods.setGoodDetails("静静糯米蛋，做不一样的美食");
        goods.setGoodItem("美食");
        goods.setGoodTitle("静静糯米蛋");
        goods.setPhone("156 2214 5443");
        goods.setGoodPicUri("http://images2015.cnblogs.com/blog/809500/201606/809500-20160618030322510-2078414388.png");
        FDb.save(goods);

        FDb=FinalDb.create(this);
        goods=new goods();
        goods.setGoodID(2016061803);
        goods.setGoodDetails("广商油条，经济好吃");
        goods.setGoodItem("美食");
        goods.setGoodTitle("广商油条");
        goods.setPhone("130");
        goods.setGoodPicUri("http://images2015.cnblogs.com/blog/809500/201606/809500-20160618030357448-2043985439.jpg");
        FDb.save(goods);

        FDb=FinalDb.create(this);
        goods=new goods();
        goods.setGoodID(2016061804);
        goods.setGoodDetails("广商充电器，比京东更实惠");
        goods.setGoodItem("电器");
        goods.setGoodTitle("广商充电器");
        goods.setPhone("150");
        goods.setGoodPicUri("http://images2015.cnblogs.com/blog/809500/201606/809500-20160618030415135-959259627.jpg");
        FDb.save(goods);

        FDb=FinalDb.create(this);
        goods=new goods();
        goods.setGoodID(2016061805);
        goods.setPhone("160");
        goods.setGoodDetails("广商衣橱，为你的美创造一个世界");
        goods.setGoodItem("家具");
        goods.setGoodTitle("广商衣橱");
        goods.setGoodPicUri("http://images2015.cnblogs.com/blog/809500/201606/809500-20160618030409604-432619147.jpg");
        FDb.save(goods);

        FDb=FinalDb.create(this);
        goods=new goods();
        goods.setGoodID(2016061806);
        goods.setGoodDetails("赶快加入我们，拿下人生第一桶金");
        goods.setGoodItem("招商");
        goods.setGoodTitle("盛大招商");
        goods.setPhone("170");
        goods.setGoodPicUri("http://images2015.cnblogs.com/blog/809500/201606/809500-20160618030422932-945084790.jpg");
        FDb.save(goods);
    }
}
