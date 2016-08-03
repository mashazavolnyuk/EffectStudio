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
    Bitmap temp;
    ImageView imgPreview;
    int id = 0;
    int value;

    @Override
    public void onDestroyView() {
        ViewGroup mContainer = (ViewGroup) getActivity().findViewById(R.id.mainContent);
        mContainer.removeAllViewsInLayout();
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_regulator, null);
        seekBarRugelator = (SeekBar) v.findViewById(R.id.seekBarRegulator);
        id = getArguments().getInt("idProperties");
        imgPreview = (ImageView) v.findViewById(R.id.imgPreview);
        setHasOptionsMenu(true);
        bitmap=ImageStorage.getInstance().getBmp();
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
                value=seekBar.getProgress();
                PropertyTask propertyTask = new PropertyTask(getActivity());
                propertyTask.execute();

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
            imgPreview.setImageBitmap(temp);
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
                temp = PropertyExecutor.getInstance().execute(id, bitmap, getActivity());

            } catch (Exception e) {
                e.printStackTrace();
            }
            return temp;
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
                ImageStorage.getInstance().setBmp(temp);
                break;


        }
        return super.onOptionsItemSelected(item);
    }

}
