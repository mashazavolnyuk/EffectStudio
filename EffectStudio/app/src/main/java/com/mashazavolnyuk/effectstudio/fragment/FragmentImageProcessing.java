package com.mashazavolnyuk.effectstudio.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.adapter.EffectsListAdapter;
import com.mashazavolnyuk.effectstudio.adapter.FiltersListAdapter;
import com.mashazavolnyuk.effectstudio.adapter.GradientsListAdapter;
import com.mashazavolnyuk.effectstudio.data.ImageStorage;
import com.mashazavolnyuk.effectstudio.interfaces.IObserveRecyclerTools;
import com.mashazavolnyuk.effectstudio.interfaces.IObserveWorkingImage;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by Dark Maleficent on 12.06.2016.
 */
public class FragmentImageProcessing extends Fragment implements IObserveWorkingImage, IObserveRecyclerTools {
    ImageView imageView;
    RecyclerView barToolsEffect;
    View v;
    EffectsListAdapter adapter;
    String[] SPINNERLIST = {"Effect", "Filters", "Gradient"};
    int positionBar = 0;
    ViewGroup group;
    String m_chosen;

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroyView() {
//        ViewGroup mContainer = (ViewGroup) getActivity().findViewById(R.id.mainContent);
//        mContainer.removeAllViewsInLayout();
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_image_processing, null);
        setHasOptionsMenu(true);
        barToolsEffect = (RecyclerView) v.findViewById(R.id.rcvToolsEffect);
        imageView = (ImageView) v.findViewById(R.id.workingImage);
        group = (ViewGroup) v.findViewById(R.id.workingSpace);
        ImageStorage.getInstance().setObserver(this);
        setToolsBar(positionBar);
        Bitmap bitmap = ImageStorage.getInstance().getBmp();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Effect");
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(), R.mipmap.cat);
            imageView.setImageBitmap(bitmap1);
            barToolsEffect.setEnabled(false);
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        final MaterialBetterSpinner materialDesignSpinner = (MaterialBetterSpinner)
                v.findViewById(R.id.material_design_spinner);
        materialDesignSpinner.setAdapter(arrayAdapter);
        materialDesignSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                setToolsBar(i);
                positionBar = i;
            }
        });
        return v;
    }

    private void setToolsBar(int position) {

        switch (position) {
            case 0:
                EffectsListAdapter effectsListAdapter = new EffectsListAdapter(getActivity());
                barToolsEffect.setAdapter(effectsListAdapter);
                break;
            case 1:
                FiltersListAdapter filtersListAdapter = new FiltersListAdapter(getActivity());
                barToolsEffect.setAdapter(filtersListAdapter);
                break;
            case 2:
                GradientsListAdapter gradientsListAdapter = new GradientsListAdapter(getActivity());
                barToolsEffect.setAdapter(gradientsListAdapter);
                break;

        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        MenuInflater menuInflater = (getActivity()).getMenuInflater();
        menuInflater.inflate(R.menu.choose_filters, menu);
        for (int j = 0; j < menu.size(); j++) {
            MenuItem item = menu.getItem(j);
            if (item.getItemId() == R.id.checkDone)
                item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
            item.setIcon(R.mipmap.ic_check_grey600_36dp);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                if (ImageStorage.getInstance().getBmp() != null)
                    share();
                break;
            case R.id.save:
                galleryAddPic();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void galleryAddPic() {

//        Bitmap bitmap = ImageStorage.getInstance().getBmp();
//        File root = Environment.getExternalStorageDirectory();
//        File file = new File(root.getAbsolutePath()+"/DCIM/Camera/img.jpg");
//        try
//        {
//            file.createNewFile();
//            FileOutputStream ostream = new FileOutputStream(file);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
//            ostream.close();
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        SimpleFileDialog FileOpenDialog =  new SimpleFileDialog(getActivity(), "FileOpen",
//                new SimpleFileDialog.SimpleFileDialogListener() {
//                    @Override
//                    public void onChosenDir(String chosenDir) {
//                        // The code in this function will be executed when the dialog OK button is pushed
//                        m_chosen = chosenDir;
//                        Toast.makeText(getActivity(), "Chosen FileOpenDialog File: " +
//                                m_chosen, Toast.LENGTH_LONG).show();
//                    }
//
//                });
//        FileOpenDialog.Default_File_Name = "";
//        FileOpenDialog.chooseFile_or_Dir();


//        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        File f = new File(m_chosen);
//        Uri contentUri = Uri.fromFile(f);
//        mediaScanIntent.setData(contentUri);
//        getActivity().sendBroadcast(mediaScanIntent);

        OutputStream outputStream;
        Bitmap source = ImageStorage.getInstance().getBmp();
        File extStorageDirectory = Environment.getExternalStorageDirectory();
        File dir = new File(extStorageDirectory.getAbsoluteFile() + "/DCIM/Camera/img.jpg");
        //File file = new File(root.getAbsolutePath()+"/DCIM/Camera/img.jpg");
        dir.mkdir();
       // File myBmp = new File(dir, source.toString() + ".jpg");

        try {
            dir.createNewFile();
            outputStream = new FileOutputStream(dir);
            source.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
            Snackbar.make(barToolsEffect,"Save to storage ",Snackbar.LENGTH_SHORT).show();

        } catch (Exception e) {

            e.printStackTrace();
        }


    }


    @Override
    public void newState(boolean state) {
        if (state) {
            Bitmap bitmap = ImageStorage.getInstance().getBmp();
            imageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public void updateRecycler(boolean flag) {
        if (flag) {
            adapter.notifyDataSetChanged();
        }
    }

    private void share() {
        Bitmap bmp = ImageStorage.getInstance().getBmp();
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
}
