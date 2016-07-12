package com.example.darkmaleficent.effectstudio;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class DialogMenuImageFromGallery extends DialogFragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog_menu_image_from_gallery, null);
        return v;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.textShare:
                onShareItem();
                break;
        }
    }
    private void onShareItem() {
        Bitmap bmp=ImageManagerLoader.getInstance().getWorkingBitmap();
        ((IMenuGallety)getActivity()).toShare(bmp);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }
}
