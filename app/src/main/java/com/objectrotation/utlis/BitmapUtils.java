package com.objectrotation.utlis;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;

public class BitmapUtils {

    public static Bitmap drawSquare(int side) {
        Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = Bitmap.createBitmap(side, side, bitmapConfig);
        bitmap = bitmap.copy(bitmapConfig, true);

        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        int colorGreen = Color.rgb(22, 160, 133);
        Shader shader = new LinearGradient(0, 0, bitmap.getWidth(), 0,
                colorGreen, Color.BLACK, Shader.TileMode.CLAMP);
        paint.setShader(shader);

        canvas.drawRect(0, 0, bitmap.getWidth(), bitmap.getHeight(), paint);

        return bitmap;
    }
}
