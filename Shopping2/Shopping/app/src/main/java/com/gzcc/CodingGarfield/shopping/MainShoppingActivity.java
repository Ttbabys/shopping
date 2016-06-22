package com.gzcc.CodingGarfield.shopping;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.gzcc.CodingGarfield.shopping.Finaldb_dbclass.Login;
import com.gzcc.CodingGarfield.shopping.MainTab.Tab1Fragment;
import com.gzcc.CodingGarfield.shopping.MainTab.Tab2Fragment;
import com.gzcc.CodingGarfield.shopping.MainTab.Tab3Fragment;
import com.gzcc.CodingGarfield.shopping.Temporary.AlterGoodsActivity;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;
import net.tsz.afinal.annotation.view.ViewInject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.Controller;
import me.majiajie.pagerbottomtabstrip.PagerBottomTabLayout;
import me.majiajie.pagerbottomtabstrip.TabLayoutMode;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectListener;

public class MainShoppingActivity extends AppCompatActivity
        implements Tab1Fragment.MyListener,NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, Tab1Fragment.OnFragmentInteractionListener {


    public static Long getId() {
        return id;
    }

    public static void setId(Long id) {
        MainShoppingActivity.id = id;
    }

    static Long id;

    private Tab1Fragment POF;
    private Tab2Fragment POF2;
    private Tab3Fragment POF3;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;



    String headimagefile = "/gzccShopping/head.jpg";

    Controller CON;

    OnTabItemSelectListener TISL=new OnTabItemSelectListener() {
        @Override
        public void onSelected(int index, Object tag) {
            FragmentManager fm = getFragmentManager();
            // 开启Fragment事务
            FragmentTransaction transaction = fm.beginTransaction();
            switch (CON.getSelected())
            {
                case 0:
                   // Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_LONG).show();
                    transaction.replace(R.id.id_content,POF);
                    break;
                case 1:
                   // Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_LONG).show();
                    POF2=new Tab2Fragment();
                    transaction.replace(R.id.id_content,POF2);
                    break;
                case 2:
                  //  Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_LONG).show();

                    POF3=new Tab3Fragment();
                    transaction.replace(R.id.id_content,POF3);
                    break;
            }
            transaction.commit();
        }

        @Override
        public void onRepeatClick(int index, Object tag) {

        }
    };







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_shopping);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        finalDb = FinalDb.create(this);

        setDefaultFrament();

        PagerBottomTabLayout bottomTabLayout = (PagerBottomTabLayout) findViewById(R.id.tab);
        CON = bottomTabLayout.builder()
                .setMode(TabLayoutMode.HIDE_TEXT | TabLayoutMode.CHANGE_BACKGROUND_COLOR)
                .addTabItem(android.R.drawable.ic_menu_view, "看看", Color.rgb(2,104,175))
                .addTabItem(android.R.drawable.ic_menu_search, "搜索", Color.rgb(255, 130, 150))
                .addTabItem(android.R.drawable.ic_menu_edit, "", Color.rgb(255, 255, 255))
                .build();


        CON.addTabItemClickListener(TISL);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();





        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);






        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void setDefaultFrament() {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction Transaction = fm.beginTransaction();
        POF = new Tab1Fragment();
        Transaction.replace(R.id.id_content, POF);
        Transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main3, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            Intent setting = new Intent(this,AboutActivity.class);
            startActivity(setting);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    FinalDb finalDb;
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_usermenu) {
            Intent intent=new Intent(this, AlterGoodsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_set) {
            Intent intent=new Intent(this,SettingsActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_sql) {
            Login lg = new Login();
            lg.setStatus("offline");
            lg.setLoginDate(new Date());
            List<Login> list=finalDb.findAll(Login.class);
            if(list.size()!=0) {
                finalDb.deleteAll(Login.class);
            }
            finalDb.save(lg);
            Intent intent=new Intent(this,LoginIntoActivity.class);
            startActivity(intent);
            this.finish();
//            Intent setting = new Intent(this,newdatabase.class);
//            startActivity(setting);
        } else if (id == R.id.nav_share) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main3 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.gzcc.CodingGarfield.shopping/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);

    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main3 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.gzcc.CodingGarfield.shopping/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }



    public void showMessage(Long index,int i){
        if(i==1){
            Toast.makeText(this.getApplicationContext(),"\nid:"+index, Toast.LENGTH_LONG).show();
            Intent newgood = new Intent(this,good.class);
            Bundle b = new Bundle();
            b.putLong("Pid",index);
            newgood.putExtras(b);
            startActivity(newgood);
        }
    }


}
