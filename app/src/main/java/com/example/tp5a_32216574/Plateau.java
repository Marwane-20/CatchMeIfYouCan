package com.example.tp5a_32216574;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;


public class Plateau extends View {
   
    private Paint paint;
    private Paint paint_RED,paint_BLUE;
    private float[][] points;
    private float ray;
    private int red_loc = 0,blue_loc = 5;
    public Plateau(Context context) {
        super(context);
        paint = new Paint();
        paint_RED = new Paint();
        paint_RED.setColor(Color.RED);
        paint_BLUE = new Paint();
        paint_BLUE.setColor(Color.BLUE);
        paint.setColor(Color.BLACK);






    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        points = computeCenters();
        ray = computeRadius();
        canvas.drawLine(0,getHeight()/2,getWidth(),getHeight()/2,paint);
        for(int i = 0;i < points.length;i++){
            if(i == red_loc)
                canvas.drawCircle(points[i][0],points[i][1],ray,paint_RED);
            else if(i == blue_loc)
                canvas.drawCircle(points[i][0],points[i][1],ray,paint_BLUE);
            else
                canvas.drawCircle(points[i][0],points[i][1],ray,paint);
        }


    }
    private float[ ][ ] computeCenters() {
        float [ ][ ] centers = new float[10][2] ;
        float w = getWidth(),h=getHeight() ;
        float radius = Math.min(w,h)/4 ;
        float centerX = w/ 2 , centerY = h / 2;
        for ( int i = 0 ; i <10; i ++){
            double angle = Math . PI /2 + 2*i*Math . PI / 10;
            centers[i][0] = ( float) (centerX + Math .cos ( angle )* radius) ;
            centers[i][1] = ( float) (centerY - Math .sin ( angle )*radius ) ;
        }
        return centers ;
    }
    private float computeRadius () {
        return Math.min (getWidth ( ) , getHeight ( ) ) / 20;
    }
    @Override
    public boolean onTouchEvent (MotionEvent e) {
        switch ( e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                h = e.getY();
                if(h < getHeight()/2) {
                    red_loc++;
                    if (red_loc > 9)
                        red_loc = 0;
                    if(red_loc == blue_loc){
                        Toast.makeText(getContext(),"LE ROUGE A GAGNE !!!",Toast. LENGTH_SHORT).show();
                        red_loc =0;blue_loc=5;
                    }
                }
                else if(h > getHeight()/2){
                    blue_loc++;
                if(blue_loc > 9)
                    blue_loc = 0;
                if(blue_loc == red_loc){
                    Toast.makeText(getContext(),"LE BLEUE A GAGNE!!!",Toast. LENGTH_SHORT).show();
                    red_loc =0;blue_loc=5;
                }

                }
                case MotionEvent.ACTION_UP:
                    //w = e.getX();


            case MotionEvent.ACTION_MOVE:

        }
        invalidate();
        return true;
    }

}
