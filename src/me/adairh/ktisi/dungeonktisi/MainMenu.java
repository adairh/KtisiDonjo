package me.adairh.ktisi.dungeonktisi;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import me.adairh.ktisi.dungeonktisi.Utilities.ObjectImage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;

public class MainMenu {

    public static Scene getMainScene(Stage primaryStage, DungeonKtisi dungeonktisi) {

        Group group = new Group();




        Label start = new Label("Start");
        setStyle(start);
        start.setOnMouseClicked((mouseEvent) -> {
            try {
                primaryStage.setScene(dungeonktisi.gameStart(primaryStage));
                primaryStage.setMaximized(true);
                primaryStage.setResizable(false);
            } catch (IOException | InvocationTargetException | NoSuchMethodException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        start.setId("mainButton");
        start.styleProperty().bind(Bindings.when(start.hoverProperty())
                .then("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 50;")
                .otherwise("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 40;"));
        //==================
        start.setMinHeight(90);
        start.setAlignment(Pos.CENTER);
        start.setMinWidth(570);
        start.setTranslateY(250);
        //==================

        Label info = new Label("Info");
        setStyle(info);
        info.setOnMouseClicked((mouseEvent) -> {
            primaryStage.setScene(getInfo(primaryStage, dungeonktisi));
        });
        info.setId("mainButton");
        info.styleProperty().bind(Bindings.when(info.hoverProperty())
                .then("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 50;")
                .otherwise("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 40;"));
        //==================
        info.setMinHeight(90);
        info.setAlignment(Pos.CENTER);
        info.setMinWidth(570);
        info.setTranslateY(340);
        //==================

        Label end = new Label("Exit");
        setStyle(end);

        end.setOnMouseClicked((mouseEvent) -> {
            Platform.exit();
            System.exit(0);
        });

        end.setId("mainButton");
        end.styleProperty().bind(Bindings.when(end.hoverProperty())
                .then("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 50;")
                .otherwise("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 40;"));
        //==================
        end.setMinHeight(90);
        end.setAlignment(Pos.CENTER);
        end.setMinWidth(570);
        end.setTranslateY(430);
        //==================


        /*
        VBox vbox = new VBox();
        vbox.setBackground(Background.EMPTY);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMinWidth(570);
        vbox.setTranslateY(250);
        vbox.setSpacing(10);
        vbox.getChildren().addAll(start,info,end);
        */
        //group.getChildren().addAll(vbox);
        group.getChildren().addAll(start,info,end);

        Scene scene = new Scene(group,570,540);

        scene.getStylesheets().addAll("https://fonts.googleapis.com/css2?family=Shadows+Into+Light&display=swap",
                Objects.requireNonNull(DungeonKtisi.class.getResource("/mainmenu.css")).toExternalForm());

        scene.setFill(new ImagePattern(new Image("assets/main.jpg")));
        return scene;
    }

    private static void setStyle(Label label) {
        label.setStyle("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 40;");
        label.setPadding(new Insets(10, 10, 10,10));
    }

    private static void setContentStyle(Label label) {
        label.setStyle("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Fuzzy Bubbles', cursive; -fx-font-size: 20");
        label.setPadding(new Insets(10, 10, 10,10));
    }

    private static void setTitleStyle(Label label) {
        label.setStyle("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Kaushan Script', cursive; -fx-font-size: 30");
        label.setPadding(new Insets(10, 10, 10,10));
    }

    private static Scene getInfo(Stage stage, DungeonKtisi dungeonktisi){

        Group group = new Group();

        Label title = new Label("Information");
        setTitleStyle(title);

        Label htp = new Label("→");
        setStyle(htp);
        htp.setOnMouseClicked((mouseEvent) -> {
            stage.setScene(getHTP(stage, dungeonktisi));
        });
        htp.setId("mainButton");
        htp.styleProperty().bind(Bindings.when(htp.hoverProperty())
                .then("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 50;")
                .otherwise("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 40;"));
        //============================
        htp.setMinHeight(90);
        htp.setAlignment(Pos.TOP_CENTER);
        htp.setMinWidth(460);
        htp.setTranslateY(30);
        //============================
        HBox hbox = new HBox();
        hbox.setMinWidth(570);
        hbox.setAlignment(Pos.TOP_LEFT);
        hbox.setBackground(Background.EMPTY);
        hbox.getChildren().addAll(title);
        hbox.setPadding(new Insets(10));

        Label contents = new Label(
                "Viết bởi adairh đến từ Ktisi Team\n" +
                "Trò chơi được viết bằng Java\n" +
                        "" +
                        "Đây là trò chơi đầu tiên của mình, nên có thể sẽ có một vài lỗi\n" +
                        "Mong mọi người yêu thích <3\n" +
                        "Enjoy~!\n"

        );
        contents.setWrapText(true);
        contents.setTextAlignment(TextAlignment.JUSTIFY);
        setContentStyle(contents);


        Label back = new Label("Back");

        back.setOnMouseClicked((mouseEvent) -> {
            stage.setScene(getMainScene(stage, dungeonktisi));
        });
        setStyle(back);
        back.setId("mainButton");
        back.styleProperty().bind(Bindings.when(back.hoverProperty())
                .then("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 50;")
                .otherwise("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 40;"));
        //============================
        back.setMinHeight(90);
        back.setAlignment(Pos.CENTER);
        back.setMinWidth(570);
        back.setTranslateY(430);
        //============================

        VBox vbox = new VBox();
        vbox.setBackground(Background.EMPTY);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxWidth(570);
        vbox.setTranslateY(20);
        vbox.setSpacing(25);
        vbox.getChildren().addAll(hbox, contents);

        vbox.setPadding(new Insets(10));

        group.getChildren().addAll(vbox, htp, back);

        Scene scene = new Scene(group,570,540);

        scene.getStylesheets().addAll("https://fonts.googleapis.com/css2?family=Shadows+Into+Light&display=swap",
                "https://fonts.googleapis.com/css2?family=Fuzzy+Bubbles&display=swap",
                "https://fonts.googleapis.com/css2?family=Kaushan+Script&display=swap",
                Objects.requireNonNull(DungeonKtisi.class.getResource("/mainmenu.css")).toExternalForm());

        scene.setFill(new ImagePattern(new Image("assets/main.jpg")));
        return scene;
    }

    private static Scene getHTP(Stage stage, DungeonKtisi dungeonktisi){

        Group group = new Group();

        Label title = new Label("How to play");
        setTitleStyle(title);

        Label info = new Label("←");
        setStyle(info);
        info.setOnMouseClicked((mouseEvent) -> {
            stage.setScene(getInfo(stage, dungeonktisi));
        });
        info.setId("mainButton");
        info.styleProperty().bind(Bindings.when(info.hoverProperty())
                .then("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 50;")
                .otherwise("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 40;"));
        //============================
        info.setMinHeight(90);
        info.setAlignment(Pos.TOP_CENTER);
        info.setMinWidth(700);
        info.setTranslateY(30);
        //============================

        HBox hbox = new HBox();
        hbox.setBackground(Background.EMPTY);
        hbox.setMinWidth(570);
        hbox.setAlignment(Pos.TOP_RIGHT);
        hbox.getChildren().addAll(title);
        hbox.setPadding(new Insets(10));



        Label moveK = new Label("Phím S W A D");
        setContentStyle(moveK);
        Label numK = new Label("Phím NUMPAD");
        setContentStyle(numK);
        Label idenK = new Label("Phím I");
        setContentStyle(idenK);
        Label dropK = new Label("Phím Q");
        setContentStyle(dropK);
        Label useK = new Label("Phím O");
        setContentStyle(useK);
        Label invenK = new Label("Phím E");
        setContentStyle(invenK);
        Label attK = new Label("Phím J");
        setContentStyle(attK);
        Label lightK = new Label("Phím U");
        setContentStyle(lightK);
        VBox keyCode = new VBox();
        keyCode.setBackground(Background.EMPTY);
        //keyCode.setMinWidth(570);
        keyCode.setAlignment(Pos.TOP_LEFT);
        keyCode.setSpacing(-20);
        keyCode.getChildren().addAll(moveK, numK, idenK, dropK, useK, invenK, attK, lightK);


        Label moveU = new Label(": để di chuyển");
        setContentStyle(moveU);
        Label numU = new Label(": để thay đổi các ô túi đồ");
        setContentStyle(numU);
        Label idenU = new Label(": để khai quan vật phẩm");
        setContentStyle(idenU);
        Label dropU = new Label(": để thả vật phẩm");
        setContentStyle(dropU);
        Label useU = new Label(": để sử dụng vật phẩm");
        setContentStyle(useU);
        Label invenU = new Label(": để mở túi đồ");
        setContentStyle(invenU);
        Label atkU = new Label(": để đánh");
        setContentStyle(atkU);
        Label lightU = new Label(": để tiếp đuốc");
        setContentStyle(lightU);
        VBox usage = new VBox();
        usage.setBackground(Background.EMPTY);
        //usage.setMinWidth(570);
        usage.setAlignment(Pos.TOP_LEFT);
        usage.setSpacing(-20);
        usage.getChildren().addAll(moveU, numU, idenU, dropU, useU, invenU, atkU, lightU);


        HBox contentKey = new HBox();
        contentKey.setBackground(Background.EMPTY);
        contentKey.setMinWidth(570);
        contentKey.setAlignment(Pos.TOP_CENTER);
        contentKey.setSpacing(-10);
        contentKey.getChildren().addAll(keyCode, usage);


        Label contents = new Label(
                    /*"Use S W A D\t: to move\n" +
                    "Use I\t: to identify ring items\n" +
                    "Use X\t: to drop items\n" +
                    "Use Z\t: to use items\n" +*/
                    "Giết quái để nhận Kinh nghiệm, khám phá tất cả các phòng và chiến thắng trò chơi!\n"

        );
        contents.setWrapText(true);
        contents.setTextAlignment(TextAlignment.JUSTIFY);
        setContentStyle(contents);


        Label back = new Label("Back");

        back.setOnMouseClicked((mouseEvent) -> {
            stage.setScene(getMainScene(stage, dungeonktisi));
        });
        setStyle(back);
        back.setId("mainButton");
        back.styleProperty().bind(Bindings.when(back.hoverProperty())
                .then("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 50;")
                .otherwise("-fx-text-fill: #C0C0C0FF; -fx-font-family: 'Shadows Into Light', cursive; -fx-font-size: 40;"));
        //============================
        back.setMinHeight(90);
        back.setAlignment(Pos.CENTER);
        back.setMinWidth(570);
        back.setTranslateY(430);
        //============================


        VBox vbox = new VBox();
        vbox.setBackground(Background.EMPTY);
        vbox.setAlignment(Pos.CENTER);
        vbox.setMaxWidth(570);
        vbox.setTranslateY(20);
        vbox.setSpacing(-10);
        //vbox.setSpacing(15);
        vbox.getChildren().addAll(hbox, contentKey, contents);

        vbox.setPadding(new Insets(10));

        group.getChildren().addAll(vbox, info, back);

        Scene scene = new Scene(group,570,540);

        scene.getStylesheets().addAll("https://fonts.googleapis.com/css2?family=Shadows+Into+Light&display=swap",
                "https://fonts.googleapis.com/css2?family=Fuzzy+Bubbles&display=swap",
                "https://fonts.googleapis.com/css2?family=Kaushan+Script&display=swap",
                Objects.requireNonNull(DungeonKtisi.class.getResource("/mainmenu.css")).toExternalForm());

        scene.setFill(new ImagePattern(new Image("assets/main.jpg")));
        return scene;
    }
}
