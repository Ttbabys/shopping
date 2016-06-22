package com.gzcc.CodingGarfield.shopping.MainTab;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gzcc.CodingGarfield.shopping.R;
import com.gzcc.CodingGarfield.shopping.Temporary.newdatabase;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Tab3Fragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Tab3Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Tab3Fragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    private String path=Environment.getExternalStorageDirectory()+"/gzccShopping/head.jpg";//图像路径


    public Tab3Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Tab3Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Tab3Fragment newInstance(String param1, String param2) {
        Tab3Fragment fragment = new Tab3Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    TextView alterusername;
    EditText alteruserpassword;
    EditText alterusernumber;
    EditText alteruseremail;
    Button alteruserinformation;
    Button alteruserback;
    SharedPreferences sharedPreferences;
    String name;
    SQLiteDatabase read;
    SQLiteDatabase write;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_tab3, container, false);
        // Inflate the layout for this fragment
        //super.onCreate(savedInstanceState);
        //getActivity().setContentView(R.layout.fragment_tab3);

        headImageView = (ImageView) view.findViewById(R.id.imageID);//找到要填充的imageView

        photo_button = (Button) view.findViewById(R.id.btn01);// 相册按钮
        camera_button = (Button) view.findViewById(R.id.btn02);// 拍照按钮


        File mFile=new File(path);
        //若该文件存在
        if (mFile.exists()) {
            Bitmap bitmap= BitmapFactory.decodeFile(path);
            headImageView.setImageBitmap(bitmap);
        }

        //开启系统的相册
        photo_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 设置调用系统相册的意图(隐式意图)
                Intent intent = new Intent();

                //设置值活动//android.intent.action.PICK
                intent.setAction(Intent.ACTION_PICK);

                //设置类型和数据
                intent.setDataAndType(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        "image/*");

                // 开启系统的相册
                startActivityForResult(intent, PHOTO_COMPILE);
            }
        });




        //开启系统摄像头
        camera_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //设置图片的名称
                ImageName = "/" + getStringToday() + ".jpg";

                // 设置调用系统摄像头的意图(隐式意图)
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //设置照片的输出路径和文件名

                File file=    new File(Environment.getExternalStorageDirectory(), ImageName);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                //开启摄像头
                startActivityForResult(intent, PHOTO_CAMERA);
            }
        });

        alterusername=(TextView) view.findViewById(R.id.alterusername);
        alteruserpassword=(EditText)view.findViewById(R.id.alteruserpassword);
        alterusernumber=(EditText)view.findViewById(R.id.alterusernumber);
        alteruseremail=(EditText)view.findViewById(R.id.alteruseremail);
        alteruserinformation=(Button)view.findViewById(R.id.alteruserinformation);

        sharedPreferences=getActivity().getSharedPreferences("Countername", Context.MODE_PRIVATE);
        name=sharedPreferences.getString("username",null);

        newdatabase db=new newdatabase(getActivity());
        read=db.getReadableDatabase();
        write=db.getWritableDatabase();
        String  selection="username=?";
        Cursor cursor=read.query("user",null,selection,new String[]{name},null,null,null);
        cursor.moveToNext();
        alterusername.setText(cursor.getString(1));
        alteruserpassword.setText(cursor.getString(2));
        alterusernumber.setText(cursor.getString(3));
        alteruseremail.setText(cursor.getString(4));

        alteruserinformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        return view;
    }

    void save(){
        String str=alteruserpassword.getText().toString();
        if(str.length()<6){
            warning();

        }
        else{
            ContentValues values = new ContentValues();
            // values.put("username", alterusername.getText().toString());
            values.put("password", alteruserpassword.getText().toString());
            values.put("phone", alterusernumber.getText().toString());
            values.put("email",alteruseremail.getText().toString());
            long flag = write.update("user",values,"username=?",new String[]{name});
            if (flag > 0) {
                Toast.makeText(getActivity().getApplicationContext(), "修改信息成功！", Toast.LENGTH_LONG).show();

            }
            else
                Toast.makeText(getActivity().getApplicationContext(), "修改信息失败！", Toast.LENGTH_LONG).show();
        }

    }

    void warning(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this.getActivity());
        builder.setMessage( "修改失败！密码不能少于六位！").setCancelable(false).setPositiveButton("确定",null).create().show();
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

    // 调用startActivityResult，返回之后的回调函数
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == NONE)
            return;

        // 通过照相机拍照的图片出理
        if (requestCode == PHOTO_CAMERA) {
            // 设置文件保存路径这里放在跟目录下
            int REQUEST_EXTERNAL_STORAGE = 1;
            String[] PERMISSIONS_STORAGE = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };
            int permission = ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                        getActivity(),
                        PERMISSIONS_STORAGE,
                        REQUEST_EXTERNAL_STORAGE
                );
            }
            File picture = new File(Environment.getExternalStorageDirectory()
                    + ImageName);
            //裁剪图片
            startPhotoZoom(Uri.fromFile(picture));
        }

        if (data == null)
            return;

        // 读取相册裁剪图片
        if (requestCode == PHOTO_COMPILE) {
            //裁剪图片
            startPhotoZoom(data.getData());

        }


        // 裁剪照片的处理结果
        if (requestCode == PHOTO_RESOULT) {

            Bundle extras = data.getExtras();

            if (extras != null) {

                Bitmap photo = extras.getParcelable("data");

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                photo.compress(Bitmap.CompressFormat.JPEG,90, stream);// (0 -
                // 100)压缩文件


                saveMyBitmap("head",photo);

                //设置图片显示内容
                headImageView.setImageBitmap(photo);
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void saveMyBitmap(String bitName,Bitmap mBitmap){

        @SuppressLint("SdCardPath")
        File f = new File(Environment.getExternalStorageDirectory()+"/gzccShopping/" + bitName + ".jpg");
        try {
            f.createNewFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
           // DebugMessage.put("在保存图片时出错："+e.toString());
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(f);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        mBitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
        try {
            fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**调用系统的裁剪图片功能
     *uri图片的路径
     *
     */
    public void startPhotoZoom(Uri uri) {

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 200);
        intent.putExtra("outputY", 200);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, PHOTO_RESOULT);
    }

    public static final int NONE = 0;
    public static final int PHOTO_CAMERA = 1;// 相机拍照
    public static final int PHOTO_COMPILE = 2; // 编辑图片
    public static final int PHOTO_RESOULT = 3;// 结果

    ImageView headImageView = null;
    Button photo_button = null;
    Button camera_button = null;
    private String ImageName;

    public static String getStringToday() {
        //Date currentTime = new Date();
        // SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        // String dateString = "/gzccShopping/"+formatter.format(currentTime);
        String dateString ="/gzccShopping/head";
        return dateString;
    }
}
