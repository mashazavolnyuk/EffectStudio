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
    private List<String> imageUrls=new ArrayList<>();
    private String[] metricsDpi = {"MEDIUM", "HIGH", "XHIGH", "XXHIGH", "XXXHIGH"};
    private String dpi;


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


    public CardImage(JSONObject json, String Dpi) throws JSONException {
        this.dpi = Dpi;
        try {
            id = json.getInt("id");
            switch (dpi){
                case "MEDIUM":
                    imgUrl = json.getString("cover(m)");
                    break;
                case "HIGH":
                    imgUrl = json.getString("cover(h)");
                    break;
                case "XHIGH":
                    imgUrl = json.getString("cover(x)");
                    break;
                case "XXHIGH":
                    imgUrl = json.getString("cover(xx)");
                    break;
                case "XXXHIGH":
                    imgUrl = json.getString("cover(xxx)");
                    break;
                default:
                    imgUrl = json.getString("cover(xxx)");
                    break;

            }

            name = json.getString("name");

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
