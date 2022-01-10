package me.adairh.ktisi.dungeonktisi.Entity;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.GameHandle;

public class Actions {

    private GameHandle handle;

    public Actions(Pane root, GameHandle handle) {
        this.handle = handle;
        death(root, handle);
        win(root, handle);
    }

    public void refresh(Pane root){
        death(root, handle);
        win(root, handle);
    }

    private void death(Pane root, GameHandle handle) {

        Character character = handle.getMainCharacter();
        if (character.isDead()) {
            Image dead_screen = new Image("assets/gui/dead_screen.png");
            ImageView iV = new ImageView();
            iV.setFitHeight(handle.size * 11);
            iV.setFitWidth(handle.size * 11);
            iV.setImage(dead_screen);
            iV.setX(handle.spacing);
            iV.setY(50);
            Label again = new Label("Exit!");
            setStyle(again);
            again.setId("mainButton");
            again.styleProperty().bind(Bindings.when(again.hoverProperty())
                    .then("-fx-text-fill: #ffffff; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 30;")
                    .otherwise("-fx-text-fill: #ffffff; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 20;"));
            again.setOnMouseClicked((mouseEvent) -> {
                Platform.exit();
                System.exit(0);
            });
            VBox v = new VBox();
            v.setAlignment(Pos.CENTER);

            v.setMinWidth(352);
            v.setMaxWidth(352);

            v.setTranslateX(1980 / 2 -  v.getMinWidth() / 2 - 50);
            v.setTranslateY(50 + 64 * 7);

            v.setBackground(Background.EMPTY);
            v.getChildren().addAll(again);

            root.getChildren().add(iV);
            root.getChildren().add(v);

            handle.getTimeline().stop();
        }
    }

    private void win(Pane root, GameHandle handle) {

        Character character = handle.getMainCharacter();
        if (character.isWin()) {
            Image dead_screen = new Image("assets/Victory.png");
            ImageView iV = new ImageView();
            iV.setFitHeight(handle.size * 11);
            iV.setFitWidth(handle.size * 11);
            iV.setImage(dead_screen);
            iV.setX(handle.spacing);
            iV.setY(50);

            root.getChildren().add(iV);
            handle.getTimeline().stop();
        }
    }
    private void setStyle(Label label) {
        label.setStyle("-fx-text-fill: #000000; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 40;");
        label.setPadding(new Insets(10, 10, 10,10));
    }
}