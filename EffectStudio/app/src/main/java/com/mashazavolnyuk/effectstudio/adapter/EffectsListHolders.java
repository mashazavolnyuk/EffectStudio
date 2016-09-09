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

import com.mashazavolnyuk.effectstudio.data.ImageStorage;
import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.effect.EffectExecutor;


public class EffectsListHolders extends RecyclerView.ViewHolder {
    TextView description;
    ImageView effect;
    View.OnClickListener listener;
    int position;
    Bitmap bitmap = ImageStorage.getInstance().getBmp();
    Bitmap temp;
    ProgressDialog progressDialog;

    public EffectsListHolders(View itemView) {
        super(itemView);
        description = (TextView) itemView.findViewById(R.id.tvDescriptionEffectTools);
        effect = (ImageView) itemView.findViewById(R.id.imgEffectTools);
        listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ExecuteEffectTask task = new ExecuteEffectTask(v.getContext());
                    task.execute();
                    Snackbar.make(v, "well done", Snackbar.LENGTH_LONG).show();
                } catch (Exception e) {
                    Snackbar.make(v, "sorry,your phone can't does this operation", Snackbar.LENGTH_LONG).show();
                }
            }
        };
        itemView.setOnClickListener(listener);

    }

    class ExecuteEffectTask extends AsyncTask<Void, Void, Bitmap> {

        int i;
        Context context;
        ProgressDialog progressDialog;

        public ExecuteEffectTask(Context context) {
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
            i = (int) description.getTag();
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(Void... voids) {
            try {
                temp = ImageStorage.getInstance().getBmp();
                temp = EffectExecutor.getInstance().execute(i, bitmap, context);

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
            progressDialog.dismiss();
        }
    }
}




