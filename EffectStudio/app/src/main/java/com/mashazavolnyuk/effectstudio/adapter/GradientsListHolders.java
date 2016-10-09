package com.mashazavolnyuk.effectstudio.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.data.ImageStorage;
import com.mashazavolnyuk.effectstudio.effect.ImageChangerExecutor;
import com.mashazavolnyuk.effectstudio.interfaces.IObserveRecyclerTools;

/**
 * Created by Dark Maleficent on 07.09.2016.
 */
public class GradientsListHolders extends RecyclerView.ViewHolder {
    TextView description;
    ImageView effect;
    String imageChange;
    View.OnClickListener listener;
    int position;
    Bitmap bitmap = ImageStorage.getInstance().getBmpOriginal();
    Bitmap temp;
    IObserveRecyclerTools observer;


    public GradientsListHolders(View itemView) {
        super(itemView);
        description = (TextView) itemView.findViewById(R.id.tvDescriptionEffectTools);
        effect = (ImageView) itemView.findViewById(R.id.imgEffectTools);
        listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ExecuteGradientTask task = new ExecuteGradientTask(v.getContext());
                    task.execute();
                } catch (Exception e) {
                    Snackbar.make(v, "sorry,your phone can't does this operation", Snackbar.LENGTH_LONG).show();
                }
            }
        };
        itemView.setOnClickListener(listener);
    }

    class ExecuteGradientTask extends AsyncTask<Void, Void, Bitmap> {


        Context context;
        ProgressDialog progressDialog;

        public ExecuteGradientTask(Context context) {
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
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                temp = ImageStorage.getInstance().getBmpOriginal();
                temp = ImageChangerExecutor.getInstance().execute(imageChange, bitmap,context);
                ImageStorage.getInstance().setBmpModify(temp);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return temp;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            progressDialog.getProgress();
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ImageStorage.getInstance().setBmp(temp);
            observer.updatePicture(true);
            progressDialog.dismiss();
        }
    }


}
