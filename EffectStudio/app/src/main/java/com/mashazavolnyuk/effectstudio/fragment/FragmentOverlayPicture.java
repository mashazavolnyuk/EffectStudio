package com.mashazavolnyuk.effectstudio.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.data.ImageStorage;

/**
 * Created by Dark Maleficent on 21.10.2016.
 */

public class FragmentOverlayPicture extends Fragment {

    MySurfaceView surfaceView;
    float x, y;
    Bitmap bmp;
    LinearLayout linearLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_overlay_picture, container, false);
        setHasOptionsMenu(true);
        surfaceView = new MySurfaceView(getActivity());

        Drawable drawable=new BitmapDrawable(ImageStorage.getInstance().getBmpOriginal());
        linearLayout=(LinearLayout) v.findViewById(R.id.layoutOverlay);
        linearLayout.removeAllViews();
        surfaceView.setZOrderMediaOverlay(true);
        surfaceView.setZOrderOnTop(true);
        linearLayout.addView(surfaceView);
        surfaceView.setBackground(drawable);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        bmp= BitmapFactory.decodeResource(getActivity().getResources(),R.mipmap.gold);
        x = 0;
        y = 0;
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: // нажатие
                        x = (int) event.getX();
                        y = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_MOVE: // движение
                        x = (int) event.getX();
                        y = (int) event.getY();
                        break;
                    case MotionEvent.ACTION_UP: // отпускание
                        x = (int) event.getX();
                        y = (int) event.getY();
                        surfaceView.updateDrawing();
                        break;
                }
                return true;
            }
        });
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        MenuInflater menuInflater = (getActivity()).getMenuInflater();
        menuInflater.inflate(R.menu.menu_image, menu);
        for (int j = 0; j < menu.size(); j++) {
            MenuItem item = menu.getItem(j);
            if (item.getItemId() == R.id.checkDone) {
                item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
                item.setIcon(R.mipmap.ic_check_white_36dp);
            }
            if (item.getItemId() == R.id.restore) {
                item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
                item.setIcon(R.mipmap.ic_restore_white_36dp);
            }
        }
    }


    public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {

        private DrawThread drawThread;
        Canvas canvas;

        public MySurfaceView(Context context) {
            super(context);
            getHolder().addCallback(this);
            // deprecated setting, but required on Android versions prior to 3.0
            getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        }



        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);

            drawThread = new DrawThread(getHolder());
            drawThread.run();
            drawThread.start();
            // updateBall();

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {

        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

        public void updateDrawing() {
            try {
                canvas = null;
                canvas = getHolder().lockCanvas(null);
                synchronized (getHolder()) {
                    this.draw(canvas);

                }
            } finally {
                if (canvas != null) {
                    //this.onDraw(canvas);
                    getHolder().unlockCanvasAndPost(canvas);
                }
            }

        }
    }


    class DrawThread extends Thread {
        private SurfaceHolder surfaceHolder;
        private Canvas canvas;

        public DrawThread(SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
            surfaceHolder.setFormat(PixelFormat.TRANSPARENT);

        }

        @Override
        public void run() {
            try {
                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    Paint paint = new Paint();
                    canvas.getWidth();
                    canvas.getHeight();
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                    canvas.drawPaint(paint);
                    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
                    canvas.drawBitmap(bmp, x, y, null);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            } catch (Exception e) {
                Log.d("DrawThread", " " + e.toString());
            }
        }
    }
}
