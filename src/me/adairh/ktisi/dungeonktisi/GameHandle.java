package me.adairh.ktisi.dungeonktisi;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;
import me.adairh.ktisi.dungeonktisi.Battle.Battle;
import me.adairh.ktisi.dungeonktisi.Entity.Actions;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Facing;
import me.adairh.ktisi.dungeonktisi.Entity.Motion.Motion;
import me.adairh.ktisi.dungeonktisi.Level.Level;
import me.adairh.ktisi.dungeonktisi.Level.Room;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Armor.ChainArmor;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Armor.LeatherArmor;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Armor.SilverArmor;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Ring.*;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Shield.SteelShield;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Shield.WoodenShield;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Sword.Dagger;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Sword.LongSword;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Sword.ShortSword;
import me.adairh.ktisi.dungeonktisi.Stuff.Potion.Potion;
import me.adairh.ktisi.dungeonktisi.Task.CharacterTask;
import me.adairh.ktisi.dungeonktisi.Task.EntityTask;
import me.adairh.ktisi.dungeonktisi.Utilities.AssetsLoader;
import me.adairh.ktisi.dungeonktisi.Utilities.StructureGenerator;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class GameHandle {

    public final int size    = 80;
    public final int spacing = (int) (1920/2 - (size*11)/2);

    private Character mainCharacter;
    private StructureGenerator structure;
    private ArrayList<ArrayList<Room>> levels_list = new ArrayList<>();
    private final int level_number = 10;

    private int minRoomNumber = 5;
    private int maxRoomNumber = 8;

    private int minRoomGap = 2;
    private int maxRoomGap = 4;

    private EntityTask entityTask;
    private CharacterTask characterTask;
    private AssetsLoader assets;

    private Potion potion;
    //private EquipmentObject equipmentObject;
    private Motion motion;
    private Actions actions;
    private Battle battle;


/*
    private Armors armor;
    private Rings ring;
    private Shields shield;
    private Swords sword;*/

    private int firstRoomNumber;

    private long count;

    private Timeline timeline;

    public Scene gameStart(Stage primaryStage) throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        count = 0;

        structure = new StructureGenerator();

        potion              = new Potion(this);
        //equipmentObject     = new EquipmentObject(this);

        /*armor = new Armors(this);
        ring = new Rings(this);
        shield = new Shields(this);
        sword = new Swords(this);*/

        //Armor
        new LeatherArmor(this);
        new ChainArmor(this);
        new SilverArmor(this);
        //Sword
        new Dagger(this);
        new ShortSword(this);
        new LongSword(this);
        //Shield
        new WoodenShield(this);
        new SteelShield(this);
        new SilverArmor(this);
        //Ring
        new RingOfDefender(this);
        new RingOfFragility(this);
        new RingOfPower(this);
        new RingOfTheWeak(this);
        new RingOfToughness(this);
        new RingOfViscous(this);

        Random rand = new Random();
        for (int index = 0; index < level_number; index++) {
            int roomNumber = rand.nextInt(maxRoomNumber - minRoomNumber) + minRoomNumber;

            int roomGap = rand.nextInt(maxRoomGap - minRoomGap) + minRoomGap;

            if (index == 0) {
                firstRoomNumber = roomNumber;
            }

            System.out.println("===================[LEVEL  "+ index + "]===================");
            System.out.println("First gen: "+ roomNumber);
            Level level = new Level(index, roomNumber + roomGap*index, this);
            level.addRooms();
            levels_list.add(level.getRooms_list());
        }


        mainCharacter = new Character(this,
                Facing.SOUTH,
                0, 0, 0,
                200,200, 1,
                0,100,1,0, 0.1,
                 10, 15, 5);
        assets = new AssetsLoader(this);
        assets.load();

        Pane pane = assets.draw();

        Scene scene = new Scene(pane, 1920, 1080);

        scene.getStylesheets().addAll(Objects.requireNonNull(DungeonKtisi.class.getResource("/stylesheet.css")).toExternalForm());
        motion = new Motion(this, scene, assets, primaryStage);

        mainCharacter.getInterface().refresh(pane);

        mainCharacter.getInterface().newEvent(" ");
        mainCharacter.getInterface().newEvent("Bạn đang ở hầm ngục Ktisi.");
        mainCharacter.getInterface().newEvent("Chúc may mắn!");
        mainCharacter.getInterface().newEvent(" ");

        actions = new Actions(pane, this);

        battle = new Battle(this);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(1), ev -> {
            count++;
            if (count % 150 == 0){
                scene.setRoot(assets.draw());
                getActions().refresh(pane);
                primaryStage.setScene(scene);
            }
            if (count % 250 == 0){
                EntityTask.enemyMove(this);
            }
            if (count % 1000 == 0){
                CharacterTask.action(this);
            }
            if (count % 5000 == 0){
                if (mainCharacter.getTorchLevel() > 0) mainCharacter.setTorchLevel(mainCharacter.getTorchLevel()-1);
            }
        }));
        this.timeline = timeline;
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        return scene;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public StructureGenerator getStructure() {
        return structure;
    }

    public Character getMainCharacter() {
        return mainCharacter;
    }

    public ArrayList<ArrayList<Room>> getLevels_list() {
        return levels_list;
    }

    public int getRoomNumber(int i){
        return levels_list.get(i).size();
    }

    public int getLevel_number() {
        return level_number;
    }

    public EntityTask getEntityTask() {
        return entityTask;
    }

    public CharacterTask getCharacterTask() {
        return characterTask;
    }

    public AssetsLoader getAssets() {
        return assets;
    }

    public Potion getPotion(){
        return potion;
    }
/*

    public EquipmentObject getEquipmentObject() {
        return equipmentObject;
    }
*/

    public Actions getActions() {
        return actions;
    }


    public Battle getBattle() {
        return battle;
    }
}
