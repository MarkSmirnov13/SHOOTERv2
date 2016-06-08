package com.example.user.shooter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.ArrayList;

/**
 * Created by user on 6/6/2016.
 */
public class BG extends Thread {

    private boolean runFlag = false;
    private SurfaceHolder surfaceHolder;
    private Bitmap picture;
    public static NLO nlo;
    public static float H;
    public static float W;
    public static Bums babams;
    private float SPEED = 5.0f;
    private float i = 2.0f;
    private int coord = 0;
    private int z;
    public static Enemy enemy;
    ArrayList<Bums> babamsList = new ArrayList<Bums>();


    public BG(SurfaceHolder surfaceHolder, Resources resources){
        this.surfaceHolder = surfaceHolder;
        // загружаем картинку, которую будем отрисовывать
        H = 50.0f;
        W = 50.0f;
        picture = BitmapFactory.decodeResource(resources, R.drawable.bg);
        nlo = new NLO(resources);
        babams = new Bums();
        enemy = new Enemy();
    }

    public void setRunning(boolean run) {
        runFlag = run;
    }

    public void drawEnemy(Canvas canvas, int j){
        //int a = j;
        Paint p = new Paint();
        p.setColor(Color.GREEN);

        // for (a = 0; a > 0; a++){
        //for (b = 0; b < 10; b++){
        //while (j < 10 * a) {
        canvas.drawRect(enemy.getX() + j * i, enemy.getY() + j * i, enemy.getW() + j * i, enemy.getH() + j * i, p);
        //}
        //a++;
        //while (j < 10 * a) {
        //    canvas.drawRect(enemy.getX() - j * i, enemy.getY() - j * i, enemy.getW() - j * i, enemy.getH() - j * i, p);
        //}


    }
    @Override
    public void run() {
        Canvas canvas;
            while (runFlag) {

            canvas = null;
            try {
                // получаем объект Canvas и выполняем отрисовку
                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    boolean flag = true;

                    canvas.drawBitmap(picture,0,0,null);
                    //canvas.drawBitmap(nlo.getPicture(),nlo.getX(),nlo.getY(),null);
                    Paint p = new Paint();
                    p.setColor(Color.BLACK);
                    canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),p);
                    drawEnemy(canvas, coord);
                    p.setColor(Color.CYAN);
                    canvas.drawRect(nlo.getX(), nlo.getY(),nlo.getX()+W, nlo.getY()+H,p);
                    int j = coord;
                    int a = 0;
                    while (j > 0){
                        if (babamsList.size() <= a) {
                            babamsList.add(babams);
                            babamsList.set(a, new Bums());
                            babamsList.get(a).setX(nlo.getX());
                            babamsList.get(a).setY(nlo.getY());
                        }
                        p.setColor(Color.RED);
                        canvas.drawRect(babamsList.get(a).getX() + W/2 - babamsList.get(a).getW()/2, babamsList.get(a).getY()-SPEED * j, babamsList.get(a).getX() + babamsList.get(a).getW()/2 + W/2, babamsList.get(a).getY()+ babamsList.get(a).getH()- SPEED * j,p);
                        a++;
                        j -= 10;
                    }
                    coord++;
                }
            }
            finally {
                if (canvas != null) {
                    // отрисовка выполнена. выводим результат на экран
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }

}
