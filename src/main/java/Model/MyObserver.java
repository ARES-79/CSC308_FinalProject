package Model;

import Model.MyObservable;

/**
 * Assignment 03
 * @author Mitashi Parikh
 * @version 1.0
 * Ass3.Model.MyObserver Interface - an interface which is realized by the various concrete observers which is implement update
 */
public interface MyObserver {
    public abstract void update(MyObservable ob);
}
