package me.adairh.ktisi.dungeonktisi;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class DungeonKtisi extends Application{

    private static Stage stage;

    private static AudioClip ing;
    private static AudioClip menu;

    private static GameHandle handle;

    private static DungeonKtisi dungeonktisi;

    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        dungeonktisi = this;
        //gameStart(primaryStage);
        //primaryStage.setResizable(false);
        primaryStage.setTitle("Ktisi Donjo");
        primaryStage.getIcons().add(new Image("assets/ktisi_2.png"));
        primaryStage.setResizable(false);

        Timer timer = new Timer();

        ImageView iv = new ImageView();
        Image i = new Image("assets/ktisi_2.png");
        iv.setImage(i);
        iv.setFitWidth(570/3);
        iv.setFitHeight(540/3);
        iv.setX(570/2 - iv.getFitWidth()/2);
        iv.setY(540/2 - iv.getFitHeight()/2);
        iv.setOpacity(0.0);
        StackPane root = new StackPane();
        Rectangle rect = new Rectangle(570,540);
        rect.setFill(Color.BLACK);
        root.getChildren().addAll(rect, iv);
        Scene scene = new Scene(root,570, 540);
        primaryStage.setScene(scene);
        primaryStage.show();
        AudioClip audio = new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/intro.mp3")).toExternalForm());
        audio.setVolume(0.6);
        audio.play();
        TimerTask welcomeTask = new TimerTask() {
        boolean up = true;
            @Override
            public void run() {
                double opacity = iv.getOpacity();
                if (up) {
                    opacity += 0.02;
                } else {
                    opacity -= 0.02;
                }
                if (opacity > 1) {
                    up = false;
                }
                iv.setOpacity(opacity);
                if (opacity < 0) {
                    this.cancel();
                }
            }

        };

        Task<Void> hj = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(5800);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };

        hj.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                ImageView im = new ImageView();
                Image i = new Image("assets/headphone.png");
                im.setImage(i);
                im.setFitWidth(570/2);
                im.setFitHeight(540/2);
                im.setX(570/2 - im.getFitWidth()/2);
                im.setY(540/2 - im.getFitHeight()/2);
                im.setOpacity(0.0);
                StackPane root = new StackPane();
                Rectangle rect = new Rectangle(600,600);
                rect.setX(0);
                rect.setY(0);
                rect.setFill(Color.BLACK);
                root.getChildren().addAll(rect, im);
                Scene scene = new Scene(root,570, 540);
                primaryStage.setScene(scene);
                primaryStage.show();
                AudioClip audio = new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/intro.mp3")).toExternalForm());
                audio.setVolume(0.6);
                audio.play();
                TimerTask task = new TimerTask() {
                    boolean up = true;
                    @Override
                    public void run() {
                        double opacity = im.getOpacity();
                        if (up) {
                            opacity += 0.02;
                        } else {
                            opacity -= 0.02;
                        }
                        if (opacity > 1) {
                            up = false;
                        }
                        im.setOpacity(opacity);
                        if (opacity < 0) {
                            this.cancel();
                        }
                    }
                };


                timer.schedule(task, 50,50);
            }
        });


        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(11600);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                timer.cancel();
                primaryStage.setScene(MainMenu.getMainScene(primaryStage, dungeonktisi));
                primaryStage.show();
                stage = primaryStage;
                musicMenu();
            }
        });
        new Thread(sleeper).start();
        new Thread(hj).start();
        timer.schedule(welcomeTask, 50,50);
        //primaryStage.show();
    }

    public void musicGame() {
        AudioClip audio = new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/mainBG.mp3")).toExternalForm());
        audio.setCycleCount(AudioClip.INDEFINITE);
        audio.setVolume(1);
        audio.play();
        ing = audio;
    }

    public void musicMenu() {
        AudioClip audio = new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/openingBG.mp3")).toExternalForm());
        audio.setCycleCount(AudioClip.INDEFINITE);
        audio.setVolume(0.6);
        audio.play();
        menu = audio;
    }

    public static void main(String[] args) {
        launch(args);
    }

    Scene gameStart(Stage primaryStage) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        menu.stop();
        musicGame();
        handle = new GameHandle();
        return handle.gameStart(primaryStage);
    }


    public void backToMain(){
        try {
            ing.stop();
            musicMenu();
            stage.setScene(MainMenu.getMainScene(stage, this));
        } catch (Exception ignored){}
    }


    public Stage getStage() {
        return stage;
    }

    public GameHandle getHandle() {
        return handle;
    }
}
