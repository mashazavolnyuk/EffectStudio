package com.mashazavolnyuk.effectstudio.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.data.ImageStorage;
import com.mashazavolnyuk.effectstudio.interfaces.INavigation;

/**
 * Created by Dark Maleficent on 31.07.2016.
 */
public class PropertiesHolders  extends RecyclerView.ViewHolder {
    TextView description;
    Context c;
    ImageView effect;
    View.OnClickListener listener;
    int position;
    Bitmap bitmap;

    public PropertiesHolders(View itemView) {
        super(itemView);
        description = (TextView) itemView.findViewById(R.id.tvDescriptionEffectTools);
        effect = (ImageView) itemView.findViewById(R.id.imgEffectTools);
        listener =new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{

                    int i = (int) description.getTag();
                    bitmap = ImageStorage.getInstance().getBmp();
                    ((INavigation) c).toRegulationProperty(bitmap, i);

                }catch(Exception e){

                }
            }
        };
        itemView.setOnClickListener(listener);
    }


}
