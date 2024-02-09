package de.pascalschwab;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;

public class Scoreboard implements Disposable {
    private static final float HEIGHT = 100;
    private final Label label;
    private final BitmapFont font;
    private final float width, height;
    private String text;
    public Scoreboard(float x, float y){
        this.width = Gdx.graphics.getWidth();
        this.height = HEIGHT;
        this.text = "0 : 0";

        font = new BitmapFont();
        font.getData().setScale(2);
        label = new Label(text, new Label.LabelStyle(font, null));
        label.setAlignment(Align.center);
        label.setSize(this.width, this.height);
        label.setPosition(x,y);
    }

    @Override
    public void dispose() {
        font.dispose();
    }

    public Label getLabel() {
        return label;
    }

    public float getHeight() {
        return height;
    }

    public float getWidth() {
        return width;
    }

    public void updateScore(Player[] players){
        label.setText(players[0].getScore() + " : " + players[1].getScore());
    }
}
