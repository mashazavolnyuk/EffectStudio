package com.mashazavolnyuk.effectstudio.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 29.09.2016.
 */
public class CardStorage {
    private List<CardImage> card = new ArrayList<>();
    private static volatile CardStorage instance = new CardStorage();

    public static CardStorage getInstance() {
        if (instance == null)
            synchronized (CardStorage.class) {
                if (instance == null)
                    instance = new CardStorage();
            }
        return instance;
    }

    private CardStorage() {
    }

    public void setCards(List<CardImage> newList){
        card = newList;
    }

    public void addCardImage(CardImage cardImage) {

        card.add(cardImage);
    }

    public void clean() {
        card.clear();
    }

    public List<CardImage> getCardImageList() {
        return card;

    }

    public int getsize() {
        return card.size();
    }
}
