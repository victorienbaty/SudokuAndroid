package com.example.thibaut.mysudoku;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dessin);
    }
    */

public class Dessin extends View implements View.OnTouchListener {

    int screenWidth = Resources.getSystem().getDisplayMetrics().widthPixels;
    int screenHeight = Resources.getSystem().getDisplayMetrics().heightPixels;
    int min = screenWidth < screenHeight ? screenWidth : screenHeight;
    int step = min / 9;
    private List<coordinate> coordinates = new ArrayList<coordinate>();
    private int coordX = 100;
    private int coordY = 100;
    private int selectedValue = 0;

    public Dessin(Context context) {
        super(context);
        this.setOnTouchListener(this);
    }

    public class coordinate{
        public int X;
        public int Y;
        public int value;

        //Constructor
        public coordinate(int x,int y){
            this.X=x;
            this.Y=y;
        }
    }

    private coordinate getCoordinate(int x , int y){
        if ( y > min)
            return null;
        else
            return new coordinate((x / step) +1,(y / step) +1);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(step - 10);

        //Draw sudoku grid
        for (int i = 0; i < 9; i++) {
            canvas.drawLine(0, i * step, min, i * step, paint);
            canvas.drawLine(0, i * step + 1, min, i * step + 1, paint);

            canvas.drawLine(i * step, 0, i * step, min, paint);
            canvas.drawLine(i * step + 1, 0, i * step + 1, min, paint);

        }
        canvas.drawLine(0, 9 * step + 1, min, 9 * step + 1, paint);

        //Draw number grid
        for (int i = 0; i < 9; i++) {
            canvas.drawLine(i * step + 1, 11 * step + 1, i * step + 1, 12 * step + 1, paint);
            canvas.drawText(String.valueOf(i + 1), i * step + 20, 12 * step - 15, paint);
        }
        canvas.drawLine(0, 11 * step + 1, min, 11 * step + 1, paint);
        canvas.drawLine(0, 12 * step + 1, min, 12 * step + 1, paint);

        if (selectedValue != 0)
            canvas.drawText(String.valueOf(selectedValue), coordX, coordY, paint);

        for (coordinate c:coordinates) {
            canvas.drawText(String.valueOf(c.value), (c.X-1) * step + 20, c.Y * step - 15, paint);
        }
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        coordX = (int) motionEvent.getX();
        coordY = (int) motionEvent.getY();
        Log.d("TAG", "coord: " + coordX + " " + coordY);
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                selectedValue= getSelectedNumber(coordX,coordY);
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if (selectedValue != 0){
                    coordinate myCoordinate = getCoordinate(coordX,coordY);
                    if (myCoordinate != null) {
                        coordinates.remove(myCoordinate);
                        myCoordinate.value = selectedValue;
                        coordinates.add(myCoordinate);
                    }
                }
                selectedValue = 0;
                break;
        }
        this.invalidate();
        return true;
    }
    private int getSelectedNumber( int x, int y){
        if (y < 11 * step + 1 || y > 12 * step + 1 )
            return 0;
        else
            return  (x / step) +1;
    }
}