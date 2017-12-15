package com.sf.oarage.pentakillclient.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.sf.oarage.pentakillclient.Constants;
import com.sf.oarage.pentakillclient.R;
import com.sf.oarage.pentakillclient.utils.QrCodeUtil;

/**
 * Created by liushihan on 2017/12/15.
 */

public class QrCodeActivity extends AppCompatActivity {
    ImageView imageQrCode;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_for_wechat);
        imageQrCode = findViewById(R.id.image_qrcode);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String market_id = intent.getStringExtra("market_id");
        String url = Constants.HOST+"";
        imageQrCode.setImageBitmap(QrCodeUtil.createQRCodeWithLogo6(url, 500, drawableToBitmap(getResources().getDrawable(R.mipmap.qrcode_logo))));

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
}
