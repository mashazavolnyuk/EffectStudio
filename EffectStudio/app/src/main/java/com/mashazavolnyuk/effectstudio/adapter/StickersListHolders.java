package com.mashazavolnyuk.effectstudio.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.interfaces.INavigation;

/**
 * Created by Dark Maleficent on 31.10.2016.
 */
public class StickersListHolders extends RecyclerView.ViewHolder {
    ImageView imageView;
    Context context;

    public StickersListHolders(View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imgPreview);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable = imageView.getDrawable();
                Bitmap bmp= ((BitmapDrawable) drawable).getBitmap();
                ((INavigation) (context)).toViewOverlayProcess(bmp);
            }
        });
    }
}
