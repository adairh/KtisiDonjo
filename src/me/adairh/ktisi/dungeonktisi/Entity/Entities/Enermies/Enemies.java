package me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Level.Room;
import me.adairh.ktisi.dungeonktisi.Stuff.Drop;
import me.adairh.ktisi.dungeonktisi.Utilities.IMGLoader.Block;
import me.adairh.ktisi.dungeonktisi.Vector.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Enemies {

    private Random generator = new Random();
    private boolean is_under_attack = false;
    private String type = "";
    private Room room;
    private double positionX, positionY = 0;
    private int experience_points;
    private int enemy_type_tile;
    private int dexterity_points;
    private int strength_points;
    private int defence_points;
    private int health_points;
    private double movingStep;
    private double attackRange;

    private Block model;

    private GameHandle handle;

    private static List<Enemies> living = new ArrayList<>();

    public Enemies(GameHandle handle, String type, Room room, Block model, int positionX, int positionY, int experience_points, int enemy_type_tile, int dexterity_points, int strength_points, int defence_points, int health_points, double step, double attackRange) {
        this.handle             = handle;
        this.type               = type;
        this.room               = room;
        this.positionX          = positionX;
        this.positionY          = positionY;

        this.experience_points  = experience_points;
        this.enemy_type_tile    = enemy_type_tile;
        this.dexterity_points   = dexterity_points;
        this.strength_points    = strength_points;
        this.defence_points     = defence_points;
        this.health_points      = health_points;

        this.movingStep         = step;
        this.attackRange        = attackRange;

        this.model              = model;

        living.add(this);
    }

    public boolean isAlive() {
        if (!living.contains(this)){
            return false;
        } else {
            if (this.health_points == 0) {
                Character character = handle.getMainCharacter();
                character.getInterface().newEvent(type + " đã bay màu");
                character.experience(this.experience_points);
                handle.getBattle().removeTarget(this);
                if (generator.nextInt(3) == 0) {
                    Drop drop = new Drop((int)positionX, (int)positionY, handle);
                    room.addDrops(drop);
                }
                living.remove(this);
                return false;
            }
        }
        /*if (is_under_attack)
            handle.getAssets().battle(this.positionX, this.positionY);
        is_under_attack = false;*/
        return true;
    }

    Vector v;

    public void moveAlgorithm() {

        Character character = handle.getMainCharacter();
        double xChar = character.getX_value();
        double yChar = character.getY_value();
        if (Math.sqrt((positionX - character.getX_value()) * (positionX - character.getX_value()) +
                (positionY - character.getX_value()) * (positionY - character.getY_value())) < 8) {

            v = new Vector(positionX, positionY, xChar, yChar);

            double stepX = (v.getxTo()-v.getxFrom()) * movingStep/(v.vectorLength());
            double stepY = (v.getyTo()-v.getyFrom()) * movingStep/(v.vectorLength());

            positionX += stepX;
            positionY += stepY;

            if (Math.sqrt((positionX - character.getX_value()) * (positionX - character.getX_value())
                + (positionY - character.getY_value()) * (positionY - character.getY_value()) ) <= attackRange){
                handle.getBattle().enemyAttack(this);
            }
        }


        /*if (Math.sqrt((positionX - character.getX_value()) * (positionX - character.getX_value()) +
                (positionY - character.getX_value()) * (positionY - character.getY_value())) < 8) {
            if (positionX > character.getX_value()) {
                if (positionY > character.getY_value()) {
                    positionX--;
                    positionY--;
                } else if (positionY < character.getY_value()) {
                    positionX--;
                    positionY++;
                } else
                    positionX--;
            } else if (positionX < character.getX_value()) {
                if (positionY > character.getY_value()) {
                    positionX++;
                    positionY--;
                } else if (positionY < character.getY_value()) {
                    positionX++;
                    positionY++;
                } else
                    positionX++;
            } else {
                if (positionY > character.getY_value()) {
                    positionY--;
                } else if (positionY < character.getY_value()) {
                    positionY++;
                }
            }
        }*/


       /* if (room.getSizes()[positionX][positionY] >= 10 && room.getSizes()[positionX][positionY] < 20) {
            last_tile = room.getSizes()[positionX][positionY];
            room.updateSizes(positionX,positionY,enemy_type_tile);
        } else {
            if (room.getSizes()[positionX][positionY] >= 44 && room.getSizes()[positionX][positionY] <= 47) {
                handle.getBattle().enemyAttack(this);
            }
            positionX = prevX;
            positionY = prevY;
            room.updateSizes(positionX,positionY,enemy_type_tile);
        }*/
    }

    public int getStrength_points() {
        return strength_points;
    }

    public int getHealth_points() {
        return health_points;
    }

    public int getDexterity_points() {
        return dexterity_points;
    }

    public int getDefence_points() {
        return defence_points;
    }

    public int getEnemy_type_tile() {
        return enemy_type_tile;
    }

    public boolean isUnderAttack() {
        return is_under_attack;
    }

    public int getExperience_points() {
        return experience_points;
    }

    public double getPositionX() {
        return positionX;
    }

    public double getPositionY() {
        return positionY;
    }

    public Room getRoom() {
        return room;
    }

    public String getType() {
        return type;
    }

    public void setUnderAttack(boolean underAttack){
        this.is_under_attack = underAttack;
    }

    public void setHealth(int health_points){
        this.health_points = health_points;
    }

    public Block getModel() {
        return model;
    }
}

