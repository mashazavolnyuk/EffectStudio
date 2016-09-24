package com.mashazavolnyuk.effectstudio;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
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
import android.widget.TextView;

import com.mashazavolnyuk.effectstudio.data.ImageStorage;
import com.mashazavolnyuk.effectstudio.fragment.FragmentImageProcessing;
import com.mashazavolnyuk.effectstudio.fragment.FragmentRegulatorProperty;
import com.mashazavolnyuk.effectstudio.interfaces.INavigation;
import com.mashazavolnyuk.effectstudio.interfaces.ISwitchCanvas;
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
    private static Context context;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    private List<VKApiPhoto> photos;
    CanvasView v;
    float x, y;
    Bitmap ball;
    Bitmap bmpDraw;
    Intent receivedIntent;


    static {
        System.loadLibrary("NativeImageProcessor");
    }

    public static Context getContext() {

        return context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        context = this;
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Get intent, action and MIME type
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        }
        toModifyImage();


    }


    private void handleSendImage(Intent intent) {
        Uri imageUri = (Uri) intent.getParcelableExtra(Intent.EXTRA_STREAM);
        if (imageUri != null) {
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bmp = shrinkBitmap(bitmap);
            ImageStorage.getInstance().setBmp(bmp);
        }
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
        Typeface type = Typeface.createFromAsset(context.getAssets(),"Roboto-BlackItalic.ttf");
        TextView view=new TextView(this);
        view.setText("Effect Studio");
        view.setTypeface(type);
        final ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setCustomView(view);

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
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //Var #2
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
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
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap bmp = shrinkBitmap(bitmap);
            ImageStorage.getInstance().setBmp(bmp);
            toModifyImage();
        }
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            Bitmap bmp = shrinkBitmap(photo);
            ImageStorage.getInstance().setBmp(bmp);
            toModifyImage();
        }


    }


    private Bitmap shrinkBitmap(Bitmap bmp) {
        int newWidth = 1024;
        int newHeight = 600;
        float scaleWidth = 1;
        float scaleHeight = 1;

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Log.d("w", "= " + width);
        Log.d("h", "= " + height);
        if ((width > 1024 && height > 600) || (height > 1024 && width > 600)) {
            scaleWidth = ((float) newWidth) / width;
            scaleHeight = ((float) newHeight) / height;
        }
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(bmp, 0, 0,
                width, height, matrix, true);
        Log.d("new w", "= " + resizedBitmap.getWidth());
        Log.d("new h", "= " + resizedBitmap.getHeight());
        return resizedBitmap;
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
            case R.id.nav_share:
                shareLink();
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

    private void shareLink() {
        ShareCompat.IntentBuilder
                .from(this) // getActivity() or activity field if within Fragment
                .setText("https://play.google.com/store/apps/details?id=com.mashazavolnyuk.effectstudio")
                .setType("text/plain") // most general text sharing MIME type
                .setChooserTitle("Effect Studio")
                .startChooser();
    }

    @Override
    public void switchOnCanvas(boolean flag, Bitmap bmp, ViewGroup group) {
        if (flag) {

            bmpDraw = ImageStorage.getInstance().getBmp();
            v = new CanvasView(this);
            Drawable d = new BitmapDrawable(getResources(), bmpDraw);
            group.removeAllViewsInLayout();
            group.addView(v);
            // v.setBackground(d);
            //v.setBackgroundColor(Color.BLUE);
            v.setZOrderMediaOverlay(true);
            // v.setZOrderOnTop(true);
            // v.setZOrderMediaOverlay(true);
            //v.setKeepScreenOn(true);
            v.getHolder().setFormat(PixelFormat.TRANSPARENT);
            // v.getHolder().setFormat(PixelFormat.RGBA_8888);
            ball = BitmapFactory.decodeResource(getResources(), R.mipmap.monster);
            x = y = 0;
        }


    }

    public class CanvasView extends SurfaceView implements SurfaceHolder.Callback {
        private DrawThread drawThread;
        Canvas canvas;
        Bitmap working;
        Bitmap original = ImageStorage.getInstance().getBmp();
        Paint transparentPaint;


        public CanvasView(Context context) {
            super(context);
            getHolder().addCallback(this);
            working = original.copy(Bitmap.Config.ARGB_8888, true);
            transparentPaint = new Paint();
            transparentPaint.setColor(getResources().getColor(android.R.color.transparent));
            transparentPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            transparentPaint.setAntiAlias(true);

        }

        @Override
        public void draw(Canvas canvas) {
            super.draw(canvas);
            drawThread = new DrawThread(getHolder());
            drawThread.run();
            drawThread.start();

        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
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
                    updateBall();
                    break;
            }
            return true;

        }

        @Override
        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);

//            bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
//            bitmap.eraseColor(Color.TRANSPARENT);
//            temp = new Canvas(bitmap);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
//            drawThread = new DrawThread(getHolder(),canvas);
//            drawThread.run();
//            drawThread.start();
//            temp.drawColor(Color.argb(80, 0, 0, 0));
//            temp.drawCircle(centerPosX, centerPosY, 200, transparentPaint);
//            canvas.drawBitmap(bitmap, 0, 0, null);

        }

        @Override
        public void surfaceCreated(SurfaceHolder surfaceHolder) {
            canvas = new Canvas(working);

        }

        @Override
        public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder surfaceHolder) {

        }

        private void updateBall() {
            try {

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

        class DrawThread extends Thread {
            private SurfaceHolder surfaceHolder;

            public DrawThread(SurfaceHolder surfaceHolder) {
                this.surfaceHolder = surfaceHolder;
                surfaceHolder.setFormat(PixelFormat.TRANSPARENT);

            }

            @Override
            public void run() {
                Canvas canvas = new Canvas(working);
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
                        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                        canvas.setBitmap(working);
                        canvas.drawBitmap(ball, x, y, paint);
                        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
                        surfaceHolder.unlockCanvasAndPost(canvas);

                    }
                } catch (Exception e) {
                    Log.d("DrawThread", " " + e.toString());
                }
            }
        }


    }

//    public void test()
//    {
//
//
//         ArrayList<FacebookAlbum> alFBAlbum = new ArrayList<>();
///*make API call*/
//        new GraphRequest(
//                AccessToken.getCurrentAccessToken(),  //your fb AccessToken
//                "/" + AccessToken.getCurrentAccessToken().getUserId() + "/albums",//user id of login user
//                null,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
//                        Log.d("TAG", "Facebook Albums: " + response.toString());
//                        try {
//                            if (response.getError() == null) {
//                                JSONObject joMain = response.getJSONObject(); //convert GraphResponse response to JSONObject
//                                if (joMain.has("data")) {
//                                    JSONArray jaData = joMain.optJSONArray("data"); //find JSONArray from JSONObject
//                                    alFBAlbum = new ArrayList<>();
//                                    for (int i = 0; i < jaData.length(); i++) {//find no. of album using jaData.length()
//                                        JSONObject joAlbum = jaData.getJSONObject(i); //convert perticular album into JSONObject
//                                        GetFacebookImages(joAlbum.optString("id")); //find Album ID and get All Images from album
//                                    }
//                                }
//                            } else {
//                                Log.d("Test", response.getError().toString());
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//        ).executeAsync();
//
//
//
//}

}//Main Avtivity
