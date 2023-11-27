package com.example.project_main.SpaceWar;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Meteorites extends Entities {
    private final String image = getClass().getResource("/Image/meteorite.png").toString();
    private Text text;
    private static final double fallspeed = 4;

    public Meteorites(double cornerX, double cornerY, double lengthX, double lengthY, String word) {
        super(cornerX, cornerY, lengthX, lengthY);

        Rectangle tmpR = new Rectangle(lengthX, lengthY);
        tmpR.setTranslateX(cornerX);
        tmpR.setTranslateY(cornerY);
        tmpR.setVisible(false);
        setRect(tmpR);

        Image tmpI = new Image(image, lengthX, lengthY, false, false);
        ImageView tmpV = new ImageView(tmpI);
        tmpV.setTranslateX(cornerX);
        tmpV.setTranslateY(cornerY);
        setSprite(tmpV);

        text = new Text(word);
        text.setTranslateX(cornerX + lengthX/4);
        text.setTranslateY(cornerY + 6* lengthY/7);
        text.setStyle("-fx-font-size: 20; -fx-align: center");
        text.setFill(Color.YELLOW);
        text.setWrappingWidth(100);
        text.setTextAlignment(TextAlignment.CENTER);
    }

    public Text getContent() {
        return text;
    }

    public void setText(String newText) {
        text.setText(newText);
    }

    public boolean falldown(double HEIGHT) {
        getRect().setTranslateY(getRect().getTranslateY() + fallspeed);
        // System.out.println(getRect().getTranslateY());
        getSprite().setTranslateY(getRect().getTranslateY());
        text.setTranslateY(getRect().getTranslateY() + 6* getLengthY()/7);

        if (getRect().getTranslateY() >= HEIGHT) {
            return true;
        }
        return false;
    }
}
