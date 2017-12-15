package com.sf.oarage.pentakillclient.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.sf.oarage.pentakillclient.Constants;
import com.sf.oarage.pentakillclient.R;
import com.sf.oarage.pentakillclient.utils.QrCodeUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by liushihan on 2017/12/15.
 */

public class QrCodeActivity extends AppCompatActivity {
    ImageView imageQrCode;
    Button buttonSave;

    String url="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_for_wechat);
        imageQrCode = findViewById(R.id.image_qrcode);
        buttonSave = findViewById(R.id.btn_save_qrcode);
        Intent intent = getIntent();
        long id = intent.getLongExtra("id", 0);
        long market_id = intent.getLongExtra("market_id", 0);
        url = Constants.HOST + "/qc/" + id + "/" + market_id;
        imageQrCode.setImageBitmap(QrCodeUtil.createQRCodeWithLogo6(url, 650, drawableToBitmap(getResources().getDrawable(R.mipmap.qrcode_logo))));
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveImage(QrCodeUtil.createQRCodeWithLogo6(url, 650, drawableToBitmap(getResources().getDrawable(R.mipmap.qrcode_logo))));
            }
        });
    }

    public static Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static void saveImage(Bitmap bmp) {
        File appDir = new File(Environment.getExternalStorageDirectory(), "Qrcode");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
