package me.adairh.ktisi.dungeonktisi.Utilities;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.Random;

public class ObjectImage extends ImageView {

    private Rectangle2D[] clips;
    private double  width,height;

    Random random = new Random();

    public ObjectImage(Image image, int columns, int rows){

        width = image.getWidth()/columns;
        height = image.getHeight()/rows;

        clips = new Rectangle2D[rows*columns];
        int count=0;
        for(int row =0;row < rows;row++ )
            for(int column = 0 ; column < columns; column++,count++)
                clips[count] = new Rectangle2D(width * column, height * row,width,height);

        setImage(image);
        //setViewport(clips[random.nextInt((walls+1)*15 - (walls * 15)) + (walls * 15)]);
    }

    public Rectangle2D[] getClips() {
        return clips;
    }
}