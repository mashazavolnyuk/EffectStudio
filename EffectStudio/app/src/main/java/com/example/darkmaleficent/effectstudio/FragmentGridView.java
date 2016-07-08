package com.example.darkmaleficent.effectstudio;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.GridView;

import com.vk.sdk.VKScope;
import com.vk.sdk.api.model.VKApiPhotoAlbum;
import com.vk.sdk.api.model.VKList;

;

/**
 * Created by Dark Maleficent on 07.05.2016.
 */
public class FragmentGridView extends Fragment implements IObserve {

    private static final String TAG = "FragmentGridView";
    private FloatingActionButton fabPlus;
    private FloatingActionButton fabLoadImageFromCamera;
    private FloatingActionButton fabLoadImageFromGallery;
    private ImageAdapter imageAdapter;
    private Animation button_up;
    private String[]scope=new String[]{VKScope.WALL,VKScope.PHOTOS};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_grid_image, null);
        GridView gridView = (GridView) v.findViewById(R.id.gridView);
        imageAdapter = new ImageAdapter(getActivity(), (INavigation) getActivity());
        imageAdapter.setObserver(this);
        gridView.setAdapter(imageAdapter);
        //fabPlus = (FloatingActionButton) v.findViewById(R.id.btnFab);
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
//            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) fabPlus.getLayoutParams();
//            p.setMargins(0, 0, dpToPx(getActivity(), 8), 0); // get rid of margins since shadow area is now the margin
//            fabPlus.setLayoutParams(p);
//        }
        fabLoadImageFromCamera=(FloatingActionButton) v.findViewById(R.id.btnFabCamera);
        fabLoadImageFromGallery=(FloatingActionButton) v.findViewById(R.id.btnFabGallery);

        fabLoadImageFromCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((INavigation) getActivity()).loadImagefromCamera();
            }
        });
        fabLoadImageFromGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               ((INavigation) getActivity()).loadImagefromGallery();
//                VKSdk.login(getActivity(),scope);
//                VKRequest request=new VKRequest("photos.getOwnerPhotoUploadServer",VKParameters.from(VKApiConst.PHOTOS,4));
//                request.setResponseParser(new VKParser() {
//                    @Override
//                    public Object createModel(JSONObject object) {
//                        return new VKList<>(object, VKApiPhotoAlbum.class);
//                    }
//                });
//                request.executeWithListener(new VKRequest.VKRequestListener() {
//                    @Override
//                    public void onComplete(VKResponse response) {
//                        Log.d("Albums: ", response.parsedModel.toString());
//                    }
//                });
//                VKRequest albums = new VKRequest("photos.getAlbums",
//                        VKParameters.from(VKApiConst.PHOTO_SIZES,1));
//
//                albums.setResponseParser(new VKParser() {
//                    @Override
//                    public Object createModel(JSONObject object) {
//                        return new VKList<>(object, VKApiPhotoAlbum.class);
//                    }
//                });
//                albums.executeWithListener(new VKRequest.VKRequestListener() {
//                    @Override
//                    public void onComplete(VKResponse response) {
//                        Log.d("Albums: ", response.parsedModel.toString());
//                        updateAlbums((VKList)response.parsedModel);
//                    }
//            });

            }
        });
//        fabPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fabLoadImageFromCamera.setVisibility(View.VISIBLE);
//                fabLoadImageFromGallery.setVisibility(View.VISIBLE);
////                animationButtonUp(fabLoadImageFromCamera, 60);
////                animationButtonUp(fabLoadImageFromGallery, 120);
//
//
//            }
//        });
        return v;
    }

    public void updateAlbums(VKList albums){

        for(Object obj : albums){
            if(obj instanceof VKApiPhotoAlbum){
                VKApiPhotoAlbum album = (VKApiPhotoAlbum)obj;


            }
        }
    }

    public void animationButtonUp(View viewToAnimate, int translateY){

        float absTranslateY = translateY * getResources().getDisplayMetrics().density;

        // start 20 pixels down and with 0.2 alpha
//        viewToAnimate.setTranslationY(0);
        viewToAnimate.setAlpha(0f);
// animate up and to full alpha.
        viewToAnimate.animate().translationY(-absTranslateY).alpha(1).setDuration(1000).start();

//        AnimatorSet set = new AnimatorSet();
//        ObjectAnimator mover = ObjectAnimator.ofFloat(viewToAnimate, "translationY", 0, -absTranslateY);
//        ObjectAnimator alpher = ObjectAnimator.ofFloat(viewToAnimate, "alpha", 0, 1);
//        set.play(mover);
//        set.play(alpher);
//        set.start();

//        AnimationSet replaceAnimation = new AnimationSet(false);
//        // animations should be applied on the finish line
//        replaceAnimation.setFillAfter(true);
//        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
//        alpha.setDuration(900);
//
//        TranslateAnimation trans = new TranslateAnimation( TranslateAnimation.ABSOLUTE, 0,
//                TranslateAnimation.ABSOLUTE, 0,
//                TranslateAnimation.ABSOLUTE, 0,
//                TranslateAnimation.ABSOLUTE, -absTranslateY);
//        trans.setDuration(1000);
//        replaceAnimation.addAnimation(alpha);
//        replaceAnimation.addAnimation(trans);
//        viewToAnimate.startAnimation(replaceAnimation);
    }

    public IListener getImageAdapter() {
        return imageAdapter;
    }


}
