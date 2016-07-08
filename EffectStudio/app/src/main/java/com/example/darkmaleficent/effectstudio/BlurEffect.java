package com.example.darkmaleficent.effectstudio;

import android.content.Context;
import android.graphics.Bitmap;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

/**
 * Created by Dark Maleficent on 17.06.2016.
 */
public class BlurEffect extends SingleImageEffect {

    public BlurEffect() {
        super(2, "Blur", R.mipmap.ic_add_a_photo_white_36dp,TypeEffect.Filter);
    }

    @Override
    public Bitmap apply(Context context, Bitmap bitmap) {
        //define this only once if blurring multiple times
        RenderScript rs = RenderScript.create(context);
        //this will blur the bitmapOriginal with a radius of 8 and save it in bitmapOriginal
        final Allocation input = Allocation.createFromBitmap(rs, bitmap); //use this constructor for best performance, because it uses USAGE_SHARED mode which reuses memory
        final Allocation output = Allocation.createTyped(rs, input.getType());
        final ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setRadius(25f);
        script.setInput(input);
        script.forEach(output);
        output.copyTo(bitmap);
        return bitmap;
    }
}
