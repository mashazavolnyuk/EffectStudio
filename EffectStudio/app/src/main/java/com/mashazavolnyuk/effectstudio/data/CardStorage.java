package com.mashazavolnyuk.effectstudio.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dark Maleficent on 29.09.2016.
 */
public class CardStorage {
    private List<CardImage> card = new ArrayList<>();
    private static CardStorage intance = new CardStorage();

    public static CardStorage getInstance() {
        if (intance == null)
            synchronized (CardStorage.class) {
                if (intance == null)
                    intance = new CardStorage();
            }
        return intance;
    }

    private CardStorage() {
    }

    public void addCardImage(CardImage cardImage) {

        card.add(cardImage);
    }

    public List<CardImage> getCardImageList() {
        return card;

    }
    public int getsize(){
        return  card.size();
    }
}
