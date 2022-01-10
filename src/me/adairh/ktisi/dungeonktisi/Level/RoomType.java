package me.adairh.ktisi.dungeonktisi.Level;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child.Golem;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child.Skeleton;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child.Zombie;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.Sound.SoundEffect;
import me.adairh.ktisi.dungeonktisi.Utilities.IMGLoader.Block;

import java.util.Random;

public enum RoomType {

    WOODEN("wooden",
            new Class[]{Zombie.class},
            new Block[]{Block.WOOD, Block.WOOD2},
            new Block[]{},
            new SoundEffect[]{SoundEffect.WOOD_STEP},
            0),
    /*LIBRARY("library",
            new Class[]{},
            new Block[]{Block.WOOD3, Block.WOOD},
            new Block[]{Block.BOOKSHELF},
            new SoundEffect[]{SoundEffect.WOOD_STEP},
            0),*/
    GRASS("grass",
            new Class[]{Golem.class},
            new Block[]{Block.GRASS, Block.GRASS_UP, Block.GRASS_DOWN},
            new Block[]{},
            new SoundEffect[]{SoundEffect.GRASS_STEP, SoundEffect.GRASS_STEP_2},
            0),
    QUARTZ("normal",
            new Class[]{},
            new Block[]{Block.FLOOR, Block.FLOOR_DECORATED, Block.FLOOR_DIVIDED, Block.FLOOR_TILE},
            new Block[]{},
            new SoundEffect[]{SoundEffect.FLOOR_STEP},
            0),
    VERTICAL_QUARTZ("vertical",
            new Class[]{Skeleton.class},
            new Block[]{Block.FLOOR, Block.FLOOR_DECORATED, Block.FLOOR_DIVIDED, Block.FLOOR_TILE},
            new Block[]{Block.QUARTZ_1, Block.QUARTZ_2, Block.QUARTZ_3},
            new SoundEffect[]{SoundEffect.FLOOR_STEP},
            0),
    ROUNDED_QUARTZ("rounded",
            new Class[]{Skeleton.class},
            new Block[]{Block.FLOOR, Block.FLOOR_DECORATED, Block.FLOOR_DIVIDED, Block.FLOOR_TILE},
            new Block[]{Block.QUARTZ_1, Block.QUARTZ_2, Block.QUARTZ_3},
            new SoundEffect[]{SoundEffect.FLOOR_STEP},
            0),
    HALL_QUARTZ("hall",
            new Class[]{Skeleton.class},
            new Block[]{Block.FLOOR, Block.FLOOR_DECORATED, Block.FLOOR_DIVIDED, Block.FLOOR_TILE},
            new Block[]{Block.QUARTZ_1, Block.QUARTZ_2, Block.QUARTZ_3},
            new SoundEffect[]{SoundEffect.FLOOR_STEP},
            0),
    COBBLESTONE_ROOM("cobblestone_room",
            new Class[]{},
            new Block[]{Block.COBBLESTONE, Block.COBBLESTONE_DEEP},
            new Block[]{},
            new SoundEffect[]{SoundEffect.FLOOR_STEP},
            0),
    PURPLE_HALL("purple_hall",
            new Class[]{},
            new Block[]{Block.PURPLE_WOOL},
            new Block[]{Block.PURPLE_STAINED_GLASS, Block.PURPLE_TERRACOTTA},
            new SoundEffect[]{SoundEffect.FLOOR_STEP},
            0)
    ;

    private String name;
    private Class<Enemies>[] entities;
    private Block[] floors;
    private Block[] decoration;
    private SoundEffect[] soundEffect;
    private int levelAppear;
    private static Random ran = new Random();

    RoomType(String name, Class<Enemies>[] entities, Block[] floors, Block[] decoration, SoundEffect[] stepSound, int levelAppear){
        this.name               = name;
        this.entities           = entities;
        this.floors             = floors;
        this.decoration         = decoration;
        this.soundEffect        = stepSound;
        this.levelAppear        = levelAppear;
    }

    public String getName() {
        return name;
    }

    public Block[] getFloors() {
        return floors;
    }

    public Block[] getDecoration() {
        return decoration;
    }

    public SoundEffect[] getSoundEffect() {
        return soundEffect;
    }

    public Class<Enemies>[] getEntities() {
        return entities;
    }

    public int getLevelAppear() {
        return levelAppear;
    }

    public SoundEffect getRandomSound(){
        return getSoundEffect()[ran.nextInt(getSoundEffect().length)];
    }

    public Block getRandomBlock(){
        return getFloors()[ran.nextInt(getFloors().length)];
    }

    public Block getRandomDecoration(){
        return getDecoration()[ran.nextInt(getDecoration().length)];
    }

    public Class<Enemies> getEnemies() {
        return getEntities()[ran.nextInt(getEntities().length)];
    }

    public static RoomType getRandomType(){
        return values()[ran.nextInt(values().length)];
    }


}