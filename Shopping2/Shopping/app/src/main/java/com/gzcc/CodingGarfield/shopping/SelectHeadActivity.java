package com.gzcc.CodingGarfield.shopping;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.view.View;

import android.widget.Button;
import android.widget.ImageView;

public class SelectHeadActivity extends Activity {

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
        String dateString = "/gzccShopping/head";
        return dateString;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_head);

        headImageView = (ImageView) findViewById(R.id.imageID);//找到要填充的imageView

        photo_button = (Button) findViewById(R.id.btn_01);// 相册按钮
        camera_button = (Button) findViewById(R.id.btn_02);// 拍照按钮


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

    }

    // 调用startActivityResult，返回之后的回调函数
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == NONE)
            return;

        // 通过照相机拍照的图片出理
        if (requestCode == PHOTO_CAMERA) {
            // 设置文件保存路径这里放在跟目录下
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
                photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 -
                // 100)压缩文件

                //设置图片显示内容
                headImageView.setImageBitmap(photo);
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
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
        intent.putExtra("outputX", 64);
        intent.putExtra("outputY", 64);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, PHOTO_RESOULT);
    }

}