package com.example.project_main.SpaceWar;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bullets extends Entities {
    // private Player player;
    private String text;
    private double cnt = 0;
    
    public Bullets(Player player, double lengthX, double lengthY) {
        Rectangle tmpR = new Rectangle(lengthX, lengthY);
        tmpR.setTranslateX(player.getRect().getTranslateX() + player.getLengthX()/2 - lengthX/2);
        tmpR.setTranslateY(player.getRect().getTranslateY() - lengthY/2);

        tmpR.setFill(Color.WHITE);
        setRect(tmpR);

        text = player.getContent().getText();
    }

    public String getWord() {
        return text;
    } 

    public void setWord(String text) {
        this.text = text;
    }

    public double fire() {
        getRect().setTranslateY(getRect().getTranslateY() - 10);
        cnt+=10;
        return cnt;

    }
}
