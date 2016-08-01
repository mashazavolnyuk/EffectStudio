package com.example.darkmaleficent.effectstudio.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.darkmaleficent.effectstudio.R;
import com.example.darkmaleficent.effectstudio.SerializationUtil;
import com.example.darkmaleficent.effectstudio.data.ImageStorage;
import com.example.darkmaleficent.effectstudio.interfaces.IListener;
import com.example.darkmaleficent.effectstudio.interfaces.IMenuImageGallery;
import com.example.darkmaleficent.effectstudio.interfaces.INavigation;
import com.example.darkmaleficent.effectstudio.interfaces.IObserve;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dark Maleficent on 18.05.2016.
 */
public class GalleryImageAdapter extends BaseAdapter implements IListener {

    private Context context;
    private INavigation navigation;
    private List<Bitmap> imagelist;
    private ImageView imageMenu;
    ImageStorage imageStorage;

    private IObserve _observer;

    public GalleryImageAdapter(Context c, INavigation navigation) {
        context = c;
        this.navigation = navigation;
        //TODO serialize gallery
        try {
            ImageStorage imageStorage= (ImageStorage) SerializationUtil.deserialize("ImageStore.ser");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (ImageStorage.getInstance().isLoadedImageList())
            imagelist = ImageStorage.getInstance().getAllImage();
        else
            imagelist = new LinkedList<Bitmap>();

    }

    @Override
    public int getCount() {
        return imagelist.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ImageView imageView;

        View grid;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(context);
            grid = inflater.inflate(R.layout.item_image_gallery, null);

            grid.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int)(200 *
            context.getResources().getDisplayMetrics().density)));
           // convertView = LayoutInflater.from(context).inflate(R.layout.item_image_gallery, null);
            imageView = (ImageView) grid.findViewById(R.id.imgGallery);
            imageMenu=(ImageView) grid.findViewById(R.id.settingImgGallery);
            Bitmap obj = imagelist.get(position);
            imageView.setImageBitmap(obj);
            imageMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((IMenuImageGallery)context).createMenu(position,v);

                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageStorage.getInstance().setCurrentWorkingImage(position);
                    navigation.toModifyImage(((BitmapDrawable) imageView.getDrawable()).getBitmap());
                }
            });
        } else {
            grid = (View) convertView;
        }

        return grid;
    }

    @Override
    public void setObserver(IObserve observer) {
        _observer = observer;
        if (observer == null)
            Toast.makeText(context, "нет никого", Toast.LENGTH_LONG).show();
    }

    @Override
    public void newState(boolean newState) {
        if (newState)
            notifyDataSetChanged();
    }
}
