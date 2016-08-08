package com.example.darkmaleficent.effectstudio;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import com.example.darkmaleficent.effectstudio.data.ImageStorage;
import com.example.darkmaleficent.effectstudio.fragment.FragmentImageProcessing;
import com.example.darkmaleficent.effectstudio.fragment.FragmentRegulatorProperty;
import com.example.darkmaleficent.effectstudio.interfaces.INavigation;
import com.example.darkmaleficent.effectstudio.interfaces.ISwitchCanvas;
import com.vk.sdk.VKScope;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKParser;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhoto;
import com.vk.sdk.api.model.VKApiPhotoAlbum;
import com.vk.sdk.api.model.VKList;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, INavigation, ISwitchCanvas {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private String[] scope = new String[]{VKScope.WALL, VKScope.PHOTOS};
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    private List<VKApiPhoto> photos;
    CanvasView v;
    float x, y;
    Bitmap ball;
    Bitmap bmpDraw;

    static {
        System.loadLibrary("NativeImageProcessor");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toModifyImage();


    }


    private void setMainNavigationState(boolean state) {
        if (state)
            setAsMainScreen();
        else
            setAsSecondaryScreen();
    }

    private void setAsSecondaryScreen() {
        enableHomeButton();
        disableNavigationDrawer();
    }

    private void disableNavigationDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    private void enableHomeButton() {
        final ActionBar supportActionBar = getSupportActionBar();
        toggle.setDrawerIndicatorEnabled(false);
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);
        }
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setAsMainScreen() {
        disableHomeButton();
        enableNavigationDrawer();
    }

    private void enableNavigationDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    private void disableHomeButton() {
        final ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(false);
            supportActionBar.setHomeButtonEnabled(false);
        }
        toggle.setDrawerIndicatorEnabled(true);
        toggle.setToolbarNavigationClickListener(null);
    }


    @Override
    public void toModifyImage() {
        setMainNavigationState(true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FragmentImageProcessing fragment = new FragmentImageProcessing();
        ft.add(R.id.mainContent, fragment, "modify");
        ft.addToBackStack("modify");
        ft.commit();


    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void toRegulationProperty(Bitmap image, int idProperties) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FragmentRegulatorProperty fragment = new FragmentRegulatorProperty();
        Bundle bundle = new Bundle();
        bundle.putParcelable("image", image);
        bundle.putInt("idProperties", idProperties);
        fragment.setArguments(bundle);
        ft.replace(R.id.mainContent, fragment, "regulation");
        setMainNavigationState(false);
        ft.addToBackStack("regulation");
        ft.commit();
    }

    @Override
    public void loadImagefromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    @Override
    public void loadImagefromCamera() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    MainActivity.CAMERA_REQUEST);
        } else {
            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(cameraIntent, CAMERA_REQUEST);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                ImageStorage.getInstance().setBmp(bitmap);
                toModifyImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageStorage.getInstance().setBmp(photo);
            toModifyImage();
        }

    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate();
            fm.beginTransaction().commit();

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            toModifyImage();
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                loadImagefromCamera();
                break;
            case R.id.nav_gallery:
                loadImagefromGallery();
                break;
            case R.id.nav_vk:
                getAlbumsVK();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void getAlbumsVK() {
        int id = getIntent().getIntExtra("need_covers", 1);
        VKRequest albums = new VKRequest("photos.getAlbums",
                VKParameters.from(VKApiConst.ALBUM_ID, id));

        albums.setResponseParser(new VKParser() {
            @Override
            public Object createModel(JSONObject object) {
                return new VKList<>(object, VKApiPhotoAlbum.class);
            }
        });
        albums.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onComplete(VKResponse response) {
                Log.d("Albums: ", response.parsedModel.toString());
            }
        });


    }


    @Override
    public void switchOnCanvas(boolean flag, Bitmap bmp, ViewGroup group) {
        if (flag) {
            bmpDraw = ImageStorage.getInstance().getBmp();
            v = new CanvasView(this);
            Drawable d = new BitmapDrawable(getResources(), bmpDraw);
            group.removeAllViewsInLayout();
            group.addView(v);
            v.setBackground(d);
            v.setZOrderOnTop(true);
            ball = BitmapFactory.decodeResource(getResources(), R.mipmap.monster);
            x = y = 0;
        }


    }

    public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {
        private DrawThread drawThread;
        Canvas canvas;


        public CanvasView(Context context) {
            super(context);
            getHolder().addCallback(this);
        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            drawThread =new DrawThread(getHolder());
            drawThread.run();
            drawThread.start();
         // updateBall();

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            switch (event.getAction()){

                case MotionEvent.ACTION_DOWN: // нажатие
                    x = (int) event.getX();
                    y = (int) event.getY();
                    break;
                case MotionEvent.ACTION_MOVE: // движение
                    x = (int) event.getX();
                    y = (int) event.getY();
                    updateBall();
                    break;
                case MotionEvent.ACTION_UP: // отпускание
                    x = (int) event.getX();
                    y = (int) event.getY();
                    break;
            }
//            x = (int) event.getX();
//            y = (int) event.getY();
           // updateBall();
            return true;

        }

        @Override
        protected void onDraw(Canvas canvas) {
//            drawThread =new DrawThread(getHolder());
//            drawThread.run();
//            drawThread.start();
            //canvas.drawBitmap(ball, x, y, null);
        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {


        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }

        private void updateBall() {
            try {
                canvas=null;
                canvas = getHolder().lockCanvas(null);
                synchronized (getHolder()) {
                    this.draw(canvas);
                }
            } finally {
                if (canvas != null) {
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

                    canvas.drawBitmap(ball, x, y, null);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            } catch (Exception e) {
                Log.d("DrawThread", " " + e.toString());
            }
        }


    }
}
