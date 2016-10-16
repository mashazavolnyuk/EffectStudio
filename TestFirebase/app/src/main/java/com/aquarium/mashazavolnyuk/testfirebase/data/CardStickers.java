package com.aquarium.mashazavolnyuk.testfirebase.data;

import com.aquarium.mashazavolnyuk.testfirebase.cardstickers.CardImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 29.09.2016.
 */
public class CardStickers {
    private List<CardImage> card = new ArrayList<>();
    private static CardStickers ourInstance = new CardStickers();

    public static CardStickers getInstance() {
        return ourInstance;
    }

    private CardStickers() {
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
