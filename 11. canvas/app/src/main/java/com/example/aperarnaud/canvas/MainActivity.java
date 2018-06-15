package com.example.aperarnaud.canvas;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends Activity {

    DrawingView dv ;
    private Paint mPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawingView(this));
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
    }

    public class DrawingView extends View {
        ArrayList<Point> PointList = new ArrayList<Point>();

        public DrawingView(Context c) {
            super(c);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            for(Point point : PointList) {
                canvas.drawCircle(point.x, point.y, 100, mPaint);
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            float x = event.getX();
            float y = event.getY();

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    PointList.add(new Point((int)x, (int)y));
                    invalidate();
                    invalidate();
                    break;
            }
            return true;
        }
    }
}