package com.mashazavolnyuk.effectstudio.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 29.09.2016.
 */
public class CardStorage {
    private List<CardImage> card = new ArrayList<>();
    private static CardStorage ourInstance = new CardStorage();

    public static CardStorage getInstance() {
        return ourInstance;
    }

    private CardStorage() {
    }

    public void setCardImageList(List<CardImage> card) {
        this.card = card;

    }

    public void addCardImage(CardImage cardImage) {

        card.add(cardImage);
    }

    public List<CardImage> getCardImageList() {
        return card;

    }
}
