package com.example.darkmaleficent.effectstudio;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.util.VKUtil;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, INavigation, IMenuImageGallery, IMenuGallety {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int CAMERA_REQUEST = 2;
    private String[] scope = new String[]{VKScope.WALL, VKScope.PHOTOS};
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] fingerprints = VKUtil.getCertificateFingerprint(this, this.getPackageName());
        System.out.print(Arrays.asList(fingerprints));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setHomeButtonEnabled(true);
        }
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setMainNavigationState(false);
        toGridView();

    }

    private void setMainNavigationState(boolean state) {
        if (state)
            setAsSecondaryScreen();//где надо back
        else
            setAsMainScreen();//где глав
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
    public void toGridView() {
        setMainNavigationState(false);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FragmentMainGallery fragment = new FragmentMainGallery();
        ft.add(R.id.maincontainer, fragment, "galllery");
        ft.addToBackStack("galllery");
        ft.commit();


    }

    @Override
    public void toModifyImage(Bitmap image) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        FragmentImageProcessing fragment = new FragmentImageProcessing();
        Bundle bundle = new Bundle();
        bundle.putParcelable("image", image);
        fragment.setArguments(bundle);
        ft.replace(R.id.maincontainer, fragment, "modify");
        setMainNavigationState(true);
        ft.addToBackStack("modify");
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
                ImageManagerLoader.getInstance().addImage(bitmap);
                toGridView();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            ImageManagerLoader.getInstance().addImage(photo);
            toGridView();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
            fm.beginTransaction();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            toGridView();
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
        if (id == R.id.action_settings) {
            return true;
        }

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
                loginVK();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loginVK() {

        VKSdk.login(this, scope);

//        VKRequest albums = new VKRequest("photos.getAlbums",
//                VKParameters.from(VKApiConst.OWNER_ID,1));
//
//        albums.setResponseParser(new VKParser() {
//            @Override
//            public Object createModel(JSONObject object) {
//                return new VKList<>(object, VKApiPhotoAlbum.class);
//            }
//        });
//        albums.executeWithListener(new VKRequest.VKRequestListener() {
//            @Override
//            public void onComplete(VKResponse response) {
//                Log.d("Albums: ", response.parsedModel.toString());
//               // updateAlbums((VKList) response.parsedModel);
//            }
        //    });

    }

    @Override
    public void createMenu(int position, View v) {
        ImageManagerLoader.getInstance().setWorkingPosition(position);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        DialogMenuImageFromGallery fragment = new DialogMenuImageFromGallery();
        ft.replace(R.id.maincontainer, fragment, "menu");
        ft.commit();
    }

    //TODO
    @Override
    public void toShare(Bitmap bmp) {
        final Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("image/jpg");
        final File photoFile = new File(getFilesDir(), "foo.jpg");
        shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(photoFile));
        startActivity(Intent.createChooser(shareIntent, "Share image using"));
    }
}
