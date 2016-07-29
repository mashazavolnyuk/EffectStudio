package com.example.darkmaleficent.effectstudio.interfaces;

/**
 * Created by Dark Maleficent on 10.06.2016.
 */
public interface IListener {
    void setObserver(IObserve observer);

    /* @param true, если состояние изменилось
     */
    void newState(boolean newState);
}