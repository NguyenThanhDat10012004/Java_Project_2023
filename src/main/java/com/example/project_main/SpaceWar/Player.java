package com.example.project_main.SpaceWar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class Player extends Entities {
    private int point = 0;
    private int pos;
    private int hp = 3;
    private final String image = getClass().getResource("/Image/image.png").toString();
    private Text text;

    public Player(double cornerX, double cornerY, double lengthX, double lengthY, String word) {
        super(cornerX, cornerX, lengthX, lengthY);
        Rectangle tmpR = new Rectangle(lengthX, lengthY);
        tmpR.setTranslateX(cornerX);
        tmpR.setTranslateY(cornerY);
        tmpR.setVisible(false);
        setRect(tmpR);

        Image tmpI = new Image(image, lengthX, lengthY, false, false);
        ImageView tmpV = new ImageView(tmpI);
        tmpV.setTranslateX(cornerX);
        tmpV.setTranslateY(cornerY);
        tmpV.setTranslateZ(3);
        setSprite(tmpV);

        text = new Text(word);
        text.setTranslateX(cornerX - 20);
        text.setTranslateY(cornerY - 20);
        text.setStyle("-fx-font-size: 20; -fx-align: center");
        text.setFill(Color.YELLOW);
        text.setWrappingWidth(100);
        text.setTextAlignment(TextAlignment.CENTER);

        this.pos = 0;
    }

    public void moveLeft(double WIDTH) {
        if (pos > 0) {
            pos--;
            getRect().setTranslateX(getcornerX() + pos * WIDTH / 4);

            getSprite().setTranslateX(getRect().getTranslateX());

            text.setTranslateX(getRect().getTranslateX() - 20);
            text.setTranslateY(getRect().getTranslateY() - 20);
        }
    }

    public void moveRight(double WIDTH) {
        if (pos < 3) {
            pos++;
            getRect().setTranslateX(getcornerX() + pos * WIDTH / 4);

            getSprite().setTranslateX(getRect().getTranslateX());

            text.setTranslateX(getRect().getTranslateX() - 20);
            text.setTranslateY(getRect().getTranslateY() - 20);
        }
    }

    public void resetPos(double WIDTH) {
        pos = 0;
        getRect().setTranslateX(getcornerX() + pos * WIDTH / 4);

        getSprite().setTranslateX(getRect().getTranslateX());

        text.setTranslateX(getRect().getTranslateX() - 20);
        text.setTranslateY(getRect().getTranslateY() - 20);
    }

    public Text getContent() {
        return text;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setText(String newText) {
        text.setText(newText);
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        if (hp >= 0) {
            this.hp = hp;
        }
    }
}
