package com.mashazavolnyuk.effectstudio.fragment;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
import com.mashazavolnyuk.effectstudio.data.CardImage;
import com.mashazavolnyuk.effectstudio.data.CardStorage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Dark Maleficent on 18.10.2016.
 */

public class FragmentStickers extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private String[] metrics = {"MEDIUM", "HIGH", "XHIGH", "XXHIGH", "XXXHIGH"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_stickers, null);
        recyclerView = (RecyclerView) v.findViewById(R.id.rcvStickersFrames);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        fillData();
        return v;
    }


    private static String AssetJSONFile(String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }

    private void fillData() {
        try {
            String jsonLocation = AssetJSONFile("jsonTest.json", getActivity());
            JSONObject formArray = (new JSONObject()).getJSONObject("formules");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private void checkDPI() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        switch (metrics.densityDpi) {
            case DisplayMetrics.DENSITY_MEDIUM:
                break;
            case DisplayMetrics.DENSITY_HIGH:
                break;
            case DisplayMetrics.DENSITY_XXXHIGH:
                break;
            case DisplayMetrics.DENSITY_XXHIGH:
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                break;
        }
    }

    private void parseJson(JSONObject jsonObject) {

        Log.d("json", "name == null");
        if (jsonObject != null) {
            Log.d("json", "name" + jsonObject.toString());
            try {

                JSONArray array = jsonObject.getJSONArray("categories");
                for (int index = 0; index < array.length(); index++) {
                    Log.d("loop", "index" + index);
                    CardStorage.getInstance().addCardImage(new CardImage(array.getJSONObject(index)));
                }
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
}
