package com.example.evolotl;

import android.app.Application;

public class GlobalHappiness extends Application {
    private int happiness;
    private int idCurr;

    public int getHappiness() {
        return happiness;
    }


    public void setHappiness(Integer happiness) {
        this.happiness = happiness;
    }

    public int getIdCurr() {
        return idCurr;
    }

    public void setIdCurr(Integer idCurr) {
        this.idCurr = idCurr;
    }
}

