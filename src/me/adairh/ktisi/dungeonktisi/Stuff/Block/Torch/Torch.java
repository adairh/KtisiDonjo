package me.adairh.ktisi.dungeonktisi.Stuff.Block.Torch;

import javafx.animation.KeyFrame;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.util.Duration;
import me.adairh.ktisi.dungeonktisi.Utilities.ObjectImage;

public class Torch {

    /*Label test = new Label();
    setStyle(test);
    Image img   = new Image("assets/torch.png");
    ObjectImage object = new ObjectImage(img, 15, 4, 5);

        test.setGraphic(object);

        test.setId("mainButton");
    //==================
        test.setMinHeight(90);
        test.setAlignment(Pos.CENTER);
        test.setMinWidth(570);
        test.setTranslateY(430);*/

    private int x, y, posx, posy, wall;
    private int stage;

    public Torch(int wall, int place, int height, int width) {

        /* NORTH */
        if (wall == 0) {
            x = place;
            y = 0;
            posx = x;
            posy = y + 1;
            stage = wall*15;
        } /* SOUTH */
        if (wall == 1) {
            x = place;
            y = height - 1;
            posx = x;
            posy = y - 1;
            stage = wall*15;
        } /* EAST */
        if (wall == 2) {
            x = width - 1;
            y = place;
            posx = x - 1;
            posy = y;
            stage = wall*15;
        } /* WEST */
        if (wall == 3) {
            x = 0;
            y = place;
            posx = x + 1;
            posy = y;
            stage = wall*15;
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public int getWall() {
        return wall;
    }

    private boolean up = true;

    public KeyFrame changeStage(){
        return new KeyFrame( Duration.millis(200), event -> {
            if (up) stage ++;
            else stage --;
            if (stage >= (wall+1)*15) up = false;
            else if (stage <= (wall)*15) up = true;

        });
//
//        timeline.setCycleCount(timeline.INDEFINITE);
//        timeline.getKeyFrames().add(frame); //This was the offending line.
//        timeline.play();
    }

    public int getStage() {
        return stage;
    }
}
