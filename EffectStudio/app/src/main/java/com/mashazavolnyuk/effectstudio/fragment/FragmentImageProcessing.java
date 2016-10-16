package com.mashazavolnyuk.effectstudio.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.adapter.EffectsListAdapter;
import com.mashazavolnyuk.effectstudio.adapter.FiltersListAdapter;
import com.mashazavolnyuk.effectstudio.adapter.GradientsListAdapter;
import com.mashazavolnyuk.effectstudio.data.ImageStorage;
import com.mashazavolnyuk.effectstudio.interfaces.INavigation;
import com.mashazavolnyuk.effectstudio.interfaces.IObrservableChangeTools;
import com.mashazavolnyuk.effectstudio.interfaces.IObserveRecyclerTools;
import com.mashazavolnyuk.effectstudio.interfaces.IObserveWorkingImage;
import com.mashazavolnyuk.effectstudio.interfaces.IObserverChangeTools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Dark Maleficent on 12.06.2016.
 */
public class FragmentImageProcessing extends Fragment implements IObserveWorkingImage, IObserveRecyclerTools,IObserverChangeTools {
    ImageView imageView;
    RecyclerView barToolsEffect;
    public static final int EFFECTS=0;
    public static final int FILTERS=1;
    public static final int GRADIENTS=3;
    View v;
    Handler handler;
    ProgressDialog progressDialog;
    boolean changeImage = false;
    final int STATUS_NONE = 0; // нет подключения
    final int STATUS_CONNECTING = 1; // подключаемся
    final int STATUS_CONNECTED = 2; // подключено
    Menu menu;
    MenuInflater menuInflater;
    IObrservableChangeTools iObrservableChangeTools = (IObrservableChangeTools) getActivity();


    @Override
    public void onStart() {
        super.onStart();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_image_processing, null);
        setHasOptionsMenu(true);
        barToolsEffect = (RecyclerView) v.findViewById(R.id.rcvToolsEffect);
        imageView = (ImageView) v.findViewById(R.id.workingImage);
        ImageStorage.getInstance().setObserver(this);
        Bitmap bitmap = ImageStorage.getInstance().getBmpOriginal();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Effect Studio");
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
        setToolsBar(EFFECTS);
        return v;
    }

    private void setToolsBar(int position) {
        switch (position) {
            case EFFECTS:
                EffectsListAdapter effectsListAdapter = new EffectsListAdapter(getActivity());
                effectsListAdapter.setObserver(this);
                barToolsEffect.setAdapter(effectsListAdapter);
                break;
            case FILTERS:
                FiltersListAdapter filtersListAdapter = new FiltersListAdapter(getActivity());
                filtersListAdapter.setObserver(this);
                barToolsEffect.setAdapter(filtersListAdapter);
                break;
            case GRADIENTS:
                GradientsListAdapter gradientsListAdapter = new GradientsListAdapter(getActivity());
                gradientsListAdapter.setObserver(this);
                barToolsEffect.setAdapter(gradientsListAdapter);
                break;

        }

    }

    private void resetImage() {
        Bitmap original = ImageStorage.getInstance().getBmpOriginal();
        if (original != null)
            imageView.setImageBitmap(original);
        changeImage = false;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        MenuInflater menuInflater = (getActivity()).getMenuInflater();
        menuInflater.inflate(R.menu.menu_image, menu);
        this.menuInflater = inflater;
        this.menu = menu;
        for (int j = 0; j < menu.size(); j++) {
            MenuItem item = menu.getItem(j);
            if (item.getItemId() == R.id.checkDone) {
                item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
                item.setIcon(R.mipmap.ic_check_white_36dp);
                if (changeImage)
                    item.setVisible(true);
                else
                    item.setVisible(false);
            }
            if (item.getItemId() == R.id.restore) {
                item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
                item.setIcon(R.mipmap.ic_restore_white_36dp);
                if (changeImage)
                    item.setVisible(true);
                else
                    item.setVisible(false);
            }

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.checkDone:
                newState(true);
                onCreateOptionsMenu(menu, menuInflater);
                Toast.makeText(getActivity(),"apply",Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                if (ImageStorage.getInstance().getBmpOriginal() != null)
                    share();
                break;
            case R.id.palette:
                ((INavigation) getActivity()).toPallete();
                break;
            case R.id.restore:
                resetImage();
                onCreateOptionsMenu(menu,menuInflater);
                Toast.makeText(getActivity(),"undo",Toast.LENGTH_SHORT).show();
                break;
            case R.id.save:
                handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case STATUS_CONNECTED:
                                Context context = getActivity();
                                progressDialog = new ProgressDialog(context);
                                progressDialog.setMessage("Please,waite");
                                progressDialog.setIndeterminate(false);
                                progressDialog.setMax(100);
                                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                progressDialog.setCancelable(true);
                                progressDialog.show();
                                Log.d("Handler ", "start");
                                break;
                            case STATUS_NONE:
                                progressDialog.dismiss();
                                Log.d("Handler ", "dismiss");
                                break;
                            case STATUS_CONNECTING:
                                progressDialog.setMessage("Wrote in DCIM folder");
                                break;
                        }
                    }
                };
                startSavePicture();
                break;
            case R.id.delete:
                ImageStorage.getInstance().setBmp(null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startSavePicture() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                handler.sendEmptyMessage(STATUS_CONNECTED);
                try {
                    TimeUnit.SECONDS.sleep(1);
                    Log.d("Thread ", "start");
                    save();
                    handler.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(1);
                    handler.sendEmptyMessage(STATUS_NONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }


    private void save() {
        String filename;
        Date date = new Date(0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        filename = sdf.format(date);
        try {
            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOut = null;
            File file = new File(path, "/DCIM/" + filename + ".jpg");
            fOut = new FileOutputStream(file);
            Bitmap mBitmap = ImageStorage.getInstance().getBmpOriginal();
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver()
                    , file.getAbsolutePath(), file.getName(), file.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void newState(boolean state) {
        if (state) {
            Bitmap bitmap = ImageStorage.getInstance().getBmpModify();
            ImageStorage.getInstance().setBmp(bitmap);
            imageView.setImageBitmap(bitmap);
            changeImage = false;


        }
    }


    private void share() {
        Bitmap bmp = ImageStorage.getInstance().getBmpOriginal();
        final Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temporary_file.jpg"));
        getActivity().startActivity(Intent.createChooser(share, "Share Image"));
    }


    @Override
    public void updatePicture(boolean flag) {
        changeImage = true;
        onCreateOptionsMenu(menu, menuInflater);
        Bitmap bmp = ImageStorage.getInstance().getBmpModify();
        imageView.setImageBitmap(bmp);
    }



    @Override
    public void changeTools(int tools) {
        changeImage = false;
        setToolsBar(tools);;
    }
}