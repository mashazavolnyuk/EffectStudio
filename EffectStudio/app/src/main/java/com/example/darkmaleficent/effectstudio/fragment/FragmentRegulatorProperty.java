package com.example.darkmaleficent.effectstudio.fragment;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.data.ImageStorage;
import com.example.darkmaleficent.effectstudio.property.PropertyExecutor;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public class FragmentRegulatorProperty extends Fragment {

    SeekBar seekBarRugelator;
    Bitmap bitmap;
    ImageView imgPreview;
    int id = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_regulator, null);
        seekBarRugelator = (SeekBar) v.findViewById(R.id.seekBarRegulator);
        id = getArguments().getInt("idProperties");
        imgPreview = (ImageView) v.findViewById(R.id.imgPreview);
        setHasOptionsMenu(true);
        int i=ImageStorage.getInstance().getWorkingPosition();
        bitmap=ImageStorage.getInstance().getWorkingBitmap();
        imgPreview.setImageBitmap(bitmap);
        seekBarRugelator.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                PropertyTask propertyTask = new PropertyTask(getActivity());
                propertyTask.execute();
                imgPreview.setImageBitmap(ImageStorage.getInstance().getWorkingBitmap());
            }
        });
        return v;
    }

    private class PropertyTask extends AsyncTask<Void, Bitmap, Bitmap> {

        Context context;
        ProgressDialog progressDialog;

        public PropertyTask(Context context)
        {
            this.context = context;
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Please,waite");
            progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCancelable(true);

        }

        @Override
        protected void onPreExecute() {
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageStorage.getInstance().setWorkingBitmap(bitmap);
            Bitmap bmp=ImageStorage.getInstance().getWorkingBitmap();
            imgPreview.setImageBitmap(bmp);
            progressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Bitmap... values) {
            super.onProgressUpdate(values);
            progressDialog.getProgress();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                Bitmap bmp = ImageStorage.getInstance().getWorkingBitmap();
                bitmap = PropertyExecutor.getInstance().execute(id, bmp, getActivity());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        MenuInflater menuInflater = (getActivity()).getMenuInflater();
        menuInflater.inflate(R.menu.choose_filters, menu);
        for (int j = 0; j < menu.size(); j++) {
            MenuItem item = menu.getItem(j);
            item.setShowAsActionFlags(MenuItem.SHOW_AS_ACTION_ALWAYS);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.checkDone:
                Toast.makeText(getActivity(), "qwwr", Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}
