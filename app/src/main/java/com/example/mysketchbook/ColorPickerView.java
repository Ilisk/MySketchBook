package com.example.mysketchbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.MotionEvent;
import android.view.View;
import android.graphics.Bitmap;

public class ColorPickerView extends View {

    private Paint paint;
    private OnColorChangedListener onColorChangedListener;

    interface OnColorChangedListener {
        void onColorChanged(int color);
    }

    public ColorPickerView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    public void setOnColorChangedListener(OnColorChangedListener listener) {
        this.onColorChangedListener = listener;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Create a linear gradient from red to violet
        @SuppressLint("DrawAllocation") Shader shader = new LinearGradient(0, 0, getWidth(), 0,
                new int[]{Color.RED, Color.YELLOW, Color.GREEN, Color.CYAN, Color.BLUE, Color.MAGENTA, Color.RED},
                null, Shader.TileMode.CLAMP);

        paint.setShader(shader);
        canvas.drawRect(0, 0, getWidth(), getHeight(), paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int pixel = getPixelColor(x, y);
            if (onColorChangedListener != null) {
                onColorChangedListener.onColorChanged(pixel);
            }
            invalidate();
        }

        return true;
    }

    private int getPixelColor(int x, int y) {
        // Obtain the color of the touched pixel
        int[] pixels = new int[1];
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        onDraw(canvas);
        bitmap.getPixels(pixels, 0, 1, x, y, 1, 1);
        return pixels[0];
    }
}
