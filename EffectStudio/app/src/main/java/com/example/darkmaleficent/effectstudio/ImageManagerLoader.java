package com.example.darkmaleficent.effectstudio;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 09.06.2016.
 */
public class ImageManagerLoader implements IObservableWorkingImage {
    private static ImageManagerLoader intance;
    private List<Bitmap> bitmapList;
    private int workingPosition;
    private IObserveWorkingImage observeWorkingImage;
    private ImageManagerLoader() {
    }

    public static ImageManagerLoader getInstance() {
        if (intance == null)
            synchronized (ImageManagerLoader.class) {
                if (intance == null)
                    intance = new ImageManagerLoader();
            }
        return intance;
    }

    public boolean isLoadedImageList() {
        if (null == bitmapList || bitmapList.isEmpty())
            return false;
        else
            return true;
    }
    public Bitmap getWorkingBitmap(){
        return bitmapList.get(workingPosition);
    }
    public int getWorkingPosition(){
        return workingPosition;
    }
    public void setWorkingPosition(int position){
        this.workingPosition=position;
    }
    public void setWorkingBitmap(Bitmap bitmap){
        ReplaceImage(workingPosition, bitmap);
    }
    public void setCurrentWorkingImage(int position){
        workingPosition=position;
    }
    public void addImage(Bitmap obj) {
        if (!isLoadedImageList()) {
            bitmapList = new ArrayList<Bitmap>();
            bitmapList.add(obj);
        } else
            bitmapList.add(obj);
    }

    public List<Bitmap> getAllImage() {
        return bitmapList;
    }

    private void ReplaceImage(int index, Bitmap newObject) {
        bitmapList.set(index, newObject);
        observeWorkingImage.newState(true);
    }


    @Override
    public void setObserver(IObserveWorkingImage observer) {
        observeWorkingImage=observer;
    }
}
