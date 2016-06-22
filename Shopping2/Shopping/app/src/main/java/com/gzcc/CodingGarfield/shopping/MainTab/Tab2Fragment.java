package com.gzcc.CodingGarfield.shopping.MainTab;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.gzcc.CodingGarfield.shopping.Finaldb_dbclass.goods;
import com.gzcc.CodingGarfield.shopping.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalDb;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab2Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab2Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab2Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private String[] mListStr;
    private String[] mListTitle;

    PullToRefreshListView lv;
    ArrayList<Map<String, Object>> mData = new ArrayList<Map<String, Object>>();
    FinalDb FDb;
    private SearchView sv;

    private OnFragmentInteractionListener mListener;

    public Tab2Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab2Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab2Fragment newInstance(String param1, String param2) {
        Tab2Fragment fragment = new Tab2Fragment();
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

    private String[] mListUri=
            {
                    "http://pic.cnblogs.com/avatar/809500/20150910145711.png",
                    "http://pic.cnblogs.com/avatar/809500/20150910145711.png",
                    "http://pic.cnblogs.com/avatar/809500/20150910145711.png",
                    "http://pic.cnblogs.com/avatar/809500/20150910145711.png",
                    "http://pic.cnblogs.com/avatar/809500/20150910145711.png",
            };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view2=inflater.inflate(R.layout.fragment_tab2, container, false);

        FDb=FinalDb.create(view2.getContext());

        // 获取 PullToRefreshListView View
        lv = (PullToRefreshListView) view2.findViewById(R.id.PullFresh2);



        sv=(SearchView) view2.findViewById(R.id.searchView);
        sv.setIconifiedByDefault(true);
        sv.onActionViewExpanded();
        sv.setFocusable(false);
        sv.clearFocus();
        int textid=sv.getContext().getResources().getIdentifier("android:id/search_src_text",null,null);
        TextView tv=(TextView)sv.findViewById(textid);
        tv.setTextColor(Color.BLUE);

        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Toast.makeText(getActivity().getApplicationContext(),query, Toast.LENGTH_LONG).show();
                List<goods> listsvgoods=FDb.findAllByWhere(goods.class,"goodTitle LIKE '%"+query+"%' ");
                mListStr=new String[listsvgoods.size()];
                mListTitle=new String[listsvgoods.size()];
                if (listsvgoods.size()>0) {
                    for (int i = 0; i < listsvgoods.size(); i++) {
                        mListTitle[i] = listsvgoods.get(i).getGoodName();
                        mListStr[i] = listsvgoods.get(i).getGoodDetails();
                    }


                    // ....
                    int lengh = mListTitle.length;
                    for (int i = 0; i < lengh; i++) {
                        Map<String, Object> item = new HashMap<String, Object>();
                        item.put("name", mListTitle[i]);
                        item.put("info", mListStr[i]);
                        mData.add(item);
                    }
                    // ...
                    SimpleAdapter adapter1 = new SimpleAdapter(getActivity(), mData, R.layout.item,
                            new String[]{"name", "info", "img"}, new int[]{R.id.name, R.id.info, R.id.img});
                    // 将 SimpleAdapter 设置到 PullToRefreshListView
                    lv.setAdapter(adapter1);
                    // 刷新事件监听
                    lv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ListView>() {
                        @Override
                        public void onRefresh(PullToRefreshBase<ListView> refreshView) {
                            // ...
                            new AsyncTask<String, Void, Void>() {

                                @Override
                                protected Void doInBackground(String... params) {

                                    Looper.prepare();
                                    // 处理刷新任务
                                    try {
                                        Thread.sleep(3000);

                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    return null;
                                }

                                protected void onPostExecute(Void reslst) {
                                    // 更行内容，通知 PullToRefresh 刷新结束
                                    lv.onRefreshComplete();
                                }
                            }.execute();
                        }
                    });
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(),"没找到"+query, Toast.LENGTH_LONG).show();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return view2;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
}
