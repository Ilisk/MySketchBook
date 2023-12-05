package com.example.mysketchbook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PaintArea extends View implements View.OnTouchListener {

    Paint paint = new Paint();
    List<Point> pointArrayList = new ArrayList<>();
    private int currentColor;


    public PaintArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        //this.currentColor = currentColor;
        this.setOnTouchListener(this);
        setupPaint();

    }
    private void setupPaint() {
        paint.setColor(Color.BLACK);  // Цвет по умолчанию
        paint.setAntiAlias(true);
        paint.setStrokeWidth(0.5f);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void setPaintColor(int color) {
        currentColor = color;
    }


    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        Point dot = new Point(motionEvent.getX(), motionEvent.getY(), currentColor);
        pointArrayList.add(dot);
        invalidate();
        return true;
    }

    @Override
    public void onDraw(Canvas canvas) {
        for (Point p : pointArrayList) {
            paint.setColor(p.color);
            canvas.drawCircle(p.x, p.y, 8, paint);
        }
    }


    public void clearPaintArea(){
        pointArrayList.clear();
        invalidate();
    }

}

