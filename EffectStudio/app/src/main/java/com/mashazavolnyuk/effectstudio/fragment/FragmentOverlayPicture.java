package com.mashazavolnyuk.effectstudio.fragment;

import android.content.Context;
import android.graphics.Bitmap;
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
import android.widget.Toast;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.data.ImageStorage;
import com.mashazavolnyuk.effectstudio.interfaces.INavigation;
import com.mashazavolnyuk.effectstudio.stickers.StickerView;

/**
 * Created by Dark Maleficent on 21.10.2016.
 */

public class FragmentOverlayPicture extends Fragment {
    SurfaceView surfaceView;
    float x, y;
    Bitmap bmp;
    StickerView stickerView;
    Drawable drawable;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_overlay_process, container, false);
        setHasOptionsMenu(true);
        stickerView = (StickerView) v.findViewById(R.id.sticker_view);
        Bitmap bitmap = ImageStorage.getInstance().getBmpOriginal();
        Bundle bundle=this.getArguments();
        Bitmap sticker=bundle.getParcelable("sticker");
        drawable = new BitmapDrawable(sticker);
        stickerView.setImageBitmap(bitmap);
        stickerView.addSticker(drawable);
        Drawable d = stickerView.getDrawable();
        bitmap = ((BitmapDrawable) d).getBitmap();
        Log.d("FragmentOverlayPicture", "" + bitmap.hashCode());
        ImageStorage.getInstance().setBmp(bitmap);
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
                        //surfaceView.updateDrawing();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.checkDone:
                Bitmap bmp=ImageStorage.getInstance().getBmpOriginal();
                bmp=stickerView.createBitmap();
                ImageStorage.getInstance().setBmp(bmp);
                Log.d("FragOver",""+bmp.hashCode());
                ((INavigation) getActivity()).toModifyImage();
                Toast.makeText(getActivity(), "apply", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
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
