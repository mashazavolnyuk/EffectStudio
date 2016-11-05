package com.mashazavolnyuk.effectstudio.fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mashazavolnyuk.effectstudio.R;
import com.mashazavolnyuk.effectstudio.adapter.OverlayImageListAdapter;
import com.mashazavolnyuk.effectstudio.data.CardImage;
import com.mashazavolnyuk.effectstudio.data.CardStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 18.10.2016.
 */

public class FragmentStickers extends Fragment {
    private RecyclerView recyclerView;
    private OverlayImageListAdapter adapter;
    private String[] metricsDpi = {"MEDIUM", "HIGH", "XHIGH", "XXHIGH", "XXXHIGH"};
    private String dpi;
    DisplayMetrics metrics;

    public FragmentStickers() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stickers, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.rcvStickersFrames);
       // recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new OverlayImageListAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        AsyncTaskLoadJsonModel loadJsonModel = new AsyncTaskLoadJsonModel();
        loadJsonModel.execute();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    private static String AssetJSONFile(String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }

    private String getDataJson() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("jsonTest.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private String checkDPI() {
        metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        switch (metrics.densityDpi) {
            case DisplayMetrics.DENSITY_MEDIUM://160
                return metricsDpi[0];
            case DisplayMetrics.DENSITY_HIGH://240
                return metricsDpi[1];
            case DisplayMetrics.DENSITY_XHIGH://320
                return metricsDpi[2];
            case DisplayMetrics.DENSITY_XXHIGH://480
                return metricsDpi[3];
            case DisplayMetrics.DENSITY_XXXHIGH://640
                return metricsDpi[4];

        }
        return null;
    }

    private String fullCheckDPI() {
        int dpi = metrics.densityDpi;
        if(dpi>DisplayMetrics.DENSITY_XXHIGH||dpi<=DisplayMetrics.DENSITY_XXXHIGH)
            return metricsDpi[4];
        return null;
    }

    private void parseJson(JSONObject jsonObject) {
        if (jsonObject != null) {
            List<CardImage> newList = new ArrayList<>();
            try {
                dpi = checkDPI();
                if (dpi == null)
                    dpi = fullCheckDPI();
                JSONArray array = jsonObject.getJSONArray("categories");
                for (int index = 0; index < array.length(); index++) {
                    Log.d("loop", "index" + index);
                    newList.add(new CardImage(array.getJSONObject(index), dpi));
                }
                CardStorage.getInstance().setCards(newList);
            } catch (JSONException e) {
                e.printStackTrace();

            }
        }
    }

    private boolean checkOutConnection() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }

    private class AsyncTaskLoadJsonModel extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                String s = getDataJson();
                JSONObject jsonObject = new JSONObject(s);
                if (jsonObject != null)
                    parseJson(jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            adapter.update();
        }
    }

}
