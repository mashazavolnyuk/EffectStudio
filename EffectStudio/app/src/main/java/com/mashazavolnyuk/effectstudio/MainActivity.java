package com.mashazavolnyuk.effectstudio;

import android.Manifest;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Typeface;
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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mashazavolnyuk.effectstudio.data.ImageStorage;
import com.mashazavolnyuk.effectstudio.fragment.FragmentAddSticker;
import com.mashazavolnyuk.effectstudio.fragment.FragmentImageProcessing;
import com.mashazavolnyuk.effectstudio.fragment.FragmentOverlayPicture;
import com.mashazavolnyuk.effectstudio.fragment.FragmentRegulatorProperty;
import com.mashazavolnyuk.effectstudio.fragment.FragmentShowPalette;
import com.mashazavolnyuk.effectstudio.fragment.FragmentStartScreen;
import com.mashazavolnyuk.effectstudio.interfaces.INavigation;
import com.mashazavolnyuk.effectstudio.interfaces.IObrservableChangeTools;
import com.mashazavolnyuk.effectstudio.interfaces.IObserverChangeTools;
import com.vk.sdk.VKScope;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKParser;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.model.VKApiPhotoAlbum;
import com.vk.sdk.api.model.VKList;

import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, INavigation, IObrservableChangeTools {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private String[] scope = new String[]{VKScope.WALL, VKScope.PHOTOS};
    private static Context context;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    IObserverChangeTools observerChangeTools;

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
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if (type.startsWith("image/")) {
                handleSendImage(intent); // Handle single image being sent
            }
        }
        toStartScreen();
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
        Typeface type = Typeface.createFromAsset(context.getAssets(), "Roboto-BlackItalic.ttf");
        TextView view = new TextView(this);
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
        FragmentImageProcessing fragment = new FragmentImageProcessing();
        observerChangeTools = fragment;
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContent, fragment)
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("effects")
                .commit();
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
    public void toPallete() {

        setMainNavigationState(true);
        FragmentShowPalette fragment = new FragmentShowPalette();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContent, fragment)
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("pallete")
                .commit();


    }

    @Override
    public void toStartScreen() {
        setMainNavigationState(true);
        setMainNavigationState(true);
        FragmentStartScreen fragment = new FragmentStartScreen();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContent, fragment)
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("start screen")
                .commit();

    }

    @Override
    public void toStickersAndFrames() {
        setMainNavigationState(false);
        FragmentAddSticker f = new FragmentAddSticker();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContent, f)
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("stickers and frames")
                .commit();
    }

    @Override
    public void toViewOverlayProcess() {
        setMainNavigationState(true);
        FragmentOverlayPicture f = new FragmentOverlayPicture();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContent, f)
                .setTransition(android.support.v4.app.FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack("overlay picture")
                .commit();
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
        android.support.v4.app.FragmentManager fm = getSupportFragmentManager();
        //FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStackImmediate();
            fm.beginTransaction().commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            toStartScreen();
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
            case R.id.nav_import:
                toStartScreen();
                break;
            case R.id.nav_effects:
                setTools(FragmentImageProcessing.EFFECTS);
                break;
            case R.id.nav_filters:
                setTools(FragmentImageProcessing.FILTERS);
                break;
            case R.id.nav_gradients:
                setTools(FragmentImageProcessing.GRADIENTS);
                break;
            case R.id.nav_share:
                shareLink();
                break;
            case R.id.nav_stickers:
                toStickersAndFrames();
                break;

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setTools(int tools) {
        if (ImageStorage.getInstance().getBmpOriginal() != null)
            observerChangeTools.changeTools(tools);
        else {
            Toast.makeText(getContext(), "Photo is absent", Toast.LENGTH_SHORT).show();
            toStartScreen();
        }

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
    public void setObserver(IObserverChangeTools observer) {
        this.observerChangeTools = observer;

    }


}

