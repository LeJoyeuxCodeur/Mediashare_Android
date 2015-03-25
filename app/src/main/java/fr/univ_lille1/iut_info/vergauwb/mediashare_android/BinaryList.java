package fr.univ_lille1.iut_info.vergauwb.mediashare_android;

import android.graphics.drawable.Drawable;

public class BinaryList {
    private String pseudo, description;
    private Drawable image;

    public BinaryList(String pseudo, String description, Drawable image) {
        this.pseudo = pseudo;
        this.description = description;
        this.image = image;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
