package com.mashazavolnyuk.effectstudio.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.ListPopupWindow;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.data.ImageStorage;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public class OptionsAdapter extends BaseAdapter {

    protected  ListPopupWindow mPopupWindow;
    Context c;
    int position;

    public OptionsAdapter(ListPopupWindow popupWindow, Context context, int position) {
        c = context;
        mPopupWindow = popupWindow;
        this.position = position;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.pop_up_menu_gallery, viewGroup, false);

        v.findViewById(R.id.imgShare).setOnClickListener(listener);

        return v;
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.imgShare:
                    share();
                    break;
            }
            mPopupWindow.dismiss();
        }

    };

    private void share() {
        Bitmap bmp=ImageStorage.getInstance().getBmpOriginal();
        final Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/jpg");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File f = new File(Environment.getExternalStorageDirectory() + File.separator + "temporary_file.jpg");
        try {
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
        }
        share.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/temporary_file.jpg"));
        c.startActivity(Intent.createChooser(share, "Share Image"));
    }


}
