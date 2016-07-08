package com.example.darkmaleficent.effectstudio;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Dark Maleficent on 14.06.2016.
 */
public class RecyclerViewHolders extends RecyclerView.ViewHolder {
    TextView description;
    ImageView effect;
    View.OnClickListener listener;
    int position;

    public RecyclerViewHolders(View itemView) {
        super(itemView);

        description = (TextView) itemView.findViewById(R.id.tvDescriptionEffectTools);
        effect = (ImageView) itemView.findViewById(R.id.imgEffectTools);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bmp = ImageManagerLoader.getInstance().getWorkingBitmap();
                int i = (int) description.getTag();
                Bitmap bitmap = EffectExecutor.getInstance().executeEffect(i, bmp, v.getContext());
                ImageManagerLoader.getInstance().setWorkingBitmap(bitmap);
            }
        };
        itemView.setOnClickListener(listener);
    }

}

