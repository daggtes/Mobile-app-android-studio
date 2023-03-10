package com.example.rental_app.model;

import android.widget.TextView;

public class Update {
    private String name;

    public Update(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
