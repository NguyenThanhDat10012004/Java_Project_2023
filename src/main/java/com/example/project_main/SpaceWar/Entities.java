package com.example.project_main.SpaceWar;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public abstract class Entities {
    private double cornerX;
    private double cornerY;
    private double lengthX;
    private double lengthY;

    private ImageView sprite;
    private Rectangle rect;

    public Entities() {
    }

    public Entities(double cornerX, double cornerY) {
        this.cornerX = cornerX;
        this.cornerY = cornerY;
    }

    public Entities(double cornerX, double cornerY, double lengthX, double lengthY) {
        this.cornerX = cornerX;
        this.cornerY = cornerY;
        this.lengthX = lengthX;
        this.lengthY = lengthY;
    }

    public double getcornerX() {
        return cornerX;
    }

    public void setcornerX(double cornerX) {
        this.cornerX = cornerX;
    }

    public double getcornerY() {
        return cornerY;
    }

    public void setcornerY(double cornerY) {
        this.cornerY = cornerY;
    }

    public double getLengthX() {
        return lengthX;
    }

    public void setLengthX(double lengthX) {
        this.lengthX = lengthX;
    }

    public double getLengthY() {
        return lengthY;
    }

    public void setLengthY(double lengthY) {
        this.lengthY = lengthY;
    }

    public ImageView getSprite() {
        return sprite;
    }

    public void setSprite(ImageView sprite) {
        this.sprite = sprite;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }

    public boolean checkCollision(Rectangle other) {
        return rect.getBoundsInParent().intersects(other.getBoundsInParent());
    }
}
