package com.mashazavolnyuk.effectstudio.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 28.09.2016.
 */

public class CardImage {

    private int id;
    private String name;
    private String imgUrl;
    private List<String> imageUrls;

    public String getName() {
        return name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public int getId() {
        return id;
    }


    public CardImage(int id, String name, String imgUrl) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        imageUrls = new ArrayList<>();
    }



    public CardImage(JSONObject json) {
        try {
            id = json.getInt("id");
            name = json.getString("name");
            imgUrl = json.getString("url");
            JSONArray imageJsonArray = json.getJSONArray("imageUrls");
            if (imageJsonArray != null && imageJsonArray.length() > 0)
                for (int i = 0; i < imageJsonArray.length(); i++) {
                    imageUrls.add(imageJsonArray.getJSONObject(i).getString("url"));
                }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
