package com.example.rental_app;

import android.widget.TextView;

public class Upddate {
    String name;
    String day;


    public Upddate(String name, String day) {
        this.name = name;
        this.day = day;
    }

    public String getName() {
        return name;
    }

    public String getDays() {
        return day;
    }
}
