package com.gzcc.CodingGarfield.shopping.MainTab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.wallet.wobs.UriData;
import com.gzcc.CodingGarfield.shopping.R;
import com.gzcc.CodingGarfield.shopping.good;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.annotation.view.ViewInject;
import net.tsz.afinal.bitmap.core.DiskCache;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab1Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab1Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private ViewPager mViewPaper;
    private List<ImageView> images;
    private List<View> dots;
    private int currentItem;



    //记录上一次点的位置
    private int oldPosition = 0;
    //存放图片的id
    private int[] imageIds = new int[]{
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };
    //存放图片的标题
    private String[]  titles = new String[]{
            "静静糯米蛋",
            "广商油条",
            "广商充电器",
            "广商衣橱",
            "广告招商"
    };

    private Handler mHandler = new Handler(){

        public void handleMessage(android.os.Message msg) {
            mViewPaper.setCurrentItem(currentItem);
        };
    };

    private class ViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup view, int position, Object object) {
            // TODO Auto-generated method stub
            view.removeView(images.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup view, int position) {
            // TODO Auto-generated method stub
            view.addView(images.get(position));
            return images.get(position);
        }

    }

    private class ViewPageTask implements Runnable{

        @Override
        public void run() {
            currentItem = (currentItem + 1) % imageIds.length;
            mHandler.sendEmptyMessage(0);
        }
    }
    public void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleWithFixedDelay(
                new ViewPageTask(),
                2,
                2,
                TimeUnit.SECONDS);
    }
    @Override
    public void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if(scheduledExecutorService != null){
            scheduledExecutorService.shutdown();
            scheduledExecutorService = null;
        }
    }
    private TextView title;
    private ViewPagerAdapter adapter;
    private ScheduledExecutorService scheduledExecutorService;

    public Tab1Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab1Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab1Fragment newInstance(String param1, String param2) {
        Tab1Fragment fragment = new Tab1Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    public Bitmap getBitmapFromUrl(String urlString)
    {
        InputStream is = null;
        Bitmap bitmap;
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
            //Thread.sleep(1000);
            return bitmap;
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally
        {
            try {
                is.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return null;
    }


    boolean PullView=true,
            LunView=true;
  //  ImageView ivtest;
    FinalBitmap fb1;
    Bitmap bpic;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab1, container, false);
        mViewPaper = (ViewPager) view.findViewById(R.id.vp);


        fb1=FinalBitmap.create(view.getContext());


        if (PullView) {
            // 获取 PullToRefreshListView View
            lv = (PullToRefreshListView) view.findViewById(R.id.PullFresh);
            // ....
            int lengh = mListTitle.length;
            for (int i = 0; i < lengh; i++) {
                Map<String, Object> item = new HashMap<String, Object>();
                item.put("name", mListTitle[i]);
                item.put("info", mListStr[i]);
                item.put("img",mimageIds[i]);
                mData.add(item);
            }
            // ...
            SimpleAdapter adapter1 = new SimpleAdapter(getActivity(), mData, R.layout.item,
                    new String[]{"name", "info","img"}, new int[]{R.id.name, R.id.info,R.id.img});
            // 将 SimpleAdapter 设置到 PullToRefreshListView
            lv.setAdapter(adapter1);
            // 刷新事件监听
            lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                @Override
                public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                    // ...
                    new AsyncTask<String,Void,Void>() {

                        @Override
                        protected Void doInBackground(String... params) {

                            Looper.prepare();
                            // 处理刷新任务
                            try {
                                Thread.sleep(3000);
                                for (int i = 0; i < mListTitle.length; i++) {
                                    Map<String, Object> item = new HashMap<String, Object>();
                                    item.put("name", mListTitle[i]);
                                    item.put("info", mListStr[i]);

                                    bpic= BitmapFactory.decodeFile(mListUri[1]);
                                    item.put("img",bpic);
                                    mData.add(item);
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            Looper.loop();
                            return null;
                        }

                        protected void onPostExecute(Void reslst) {
                            // 更行内容，通知 PullToRefresh 刷新结束
                            lv.onRefreshComplete();
                        }
                    }.execute();
                }
            });
            lv.setOnItemClickListener(
                    new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            myListener.showMessage(id,1);

                            Toast.makeText(view.getContext().getApplicationContext(), "position:"+position+"\nid:"+id, Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }


        if (LunView) {
            //显示的图片
            images = new ArrayList<ImageView>();
            for (int i = 0; i < imageIds.length; i++) {
                ImageView imageView = new ImageView(this.getActivity());
                imageView.setBackgroundResource(imageIds[i]);
                images.add(imageView);
            }
            //显示的小点
            dots = new ArrayList<View>();
            dots.add(view.findViewById(R.id.dot_0));
            dots.add(view.findViewById(R.id.dot_1));
            dots.add(view.findViewById(R.id.dot_2));
            dots.add(view.findViewById(R.id.dot_3));
            dots.add(view.findViewById(R.id.dot_4));

            title = (TextView) view.findViewById(R.id.title);
            title.setText(titles[0]);

            adapter = new ViewPagerAdapter();
            mViewPaper.setAdapter(adapter);

            mViewPaper.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {


                @Override
                public void onPageSelected(int position) {
                    title.setText(titles[position]);
                    dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                    dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);

                    oldPosition = position;
                    currentItem = position;
                }

                @Override
                public void onPageScrolled(int arg0, float arg1, int arg2) {

                }

                @Override
                public void onPageScrollStateChanged(int arg0) {

                }
            });
        }
        // Inflate the layout for this fragment
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        myListener = (MyListener) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }



    PullToRefreshListView lv;
    private String[] mListTitle = {"约拍", "美食", "美食", "电子产品", "日用品"};
    private String[] mListStr = {"wicker", "静静糯米蛋", "广商油条", "广商充电器","广商衣橱"};
    private int[] mimageIds = new int[]{
            R.drawable.wicker,
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d
    };
    private String[] mListUri=
            {
                    "http://pic.cnblogs.com/avatar/809500/20150910145711.png",
                    "http://pic.cnblogs.com/avatar/809500/20150910145711.png",
                    "http://pic.cnblogs.com/avatar/809500/20150910145711.png",
                    "http://pic.cnblogs.com/avatar/809500/20150910145711.png",
                    "http://pic.cnblogs.com/avatar/809500/20150910145711.png",
            };
    ListView mListView = null;
    ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();


    private MyListener myListener;

    public interface MyListener
    {

        public void showMessage(Long index,int i);
    }
}
