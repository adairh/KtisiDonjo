package me.adairh.ktisi.dungeonktisi.Stuff;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentType;

import java.util.Random;

public class Drop {

    int x_position;
    int y_position;
    private int treasure;
    private GameHandle handle;

    public Drop(int x_position, int y_position, GameHandle handle) {
        this.handle = handle;
        this.x_position = x_position;
        this.y_position = y_position;

        Random generator = new Random();
        int random = generator.nextInt(140);

        if (random < 70)
            treasure = handle.getPotion().dropMixture();
        else if (random < 90)
            treasure = 8;
        else if (random < 110)
            treasure = 9;
        else if (random < 115)
            treasure = 10;
        else if (random < 125)
            treasure = EquipmentType.RING.dropItems();
        else if (random < 130)
            treasure = EquipmentType.SWORD.dropItems();
        else if (random < 135)
            treasure = EquipmentType.ARMOR.dropItems();
        else if (random < 140)
            treasure = EquipmentType.SHIELD.dropItems();
    }

    Drop(int x_position, int y_position, int treasure, GameHandle handle) {
        this.handle = handle;
        this.x_position = x_position;
        this.y_position = y_position;
        if (treasure > 0 && treasure < 8)
            this.treasure = handle.getPotion().nextPotion(treasure - 1) + 1;
        else if (treasure == 8 || treasure == 9 || treasure == 10)
            this.treasure = treasure;
        else if (treasure >= 11 && treasure <= 16)
            this.treasure = treasure;
        else if (treasure == 21 || treasure == 22 || treasure == 23)
            this.treasure = treasure;
        else if (treasure == 31 || treasure == 32 || treasure == 33)
            this.treasure = treasure;
        else if (treasure == 41 || treasure == 42 || treasure == 43)
            this.treasure = treasure;
    }

    public void checkTreasure() {
        Character character = handle.getMainCharacter();
        if (treasure > 0 && treasure < 8)
            character.getInterface().newItem(handle.getPotion().prevPotion(treasure - 1) + 1);
        else if (treasure == 8 || treasure == 9 || treasure == 10)
            character.getInterface().newItem(treasure);
        else if (treasure >= 11 && treasure <= 16)
            character.getInterface().newItem(treasure);
        else if (treasure == 21 || treasure == 22 || treasure == 23)
            character.getInterface().newItem(treasure);
        else if (treasure == 31 || treasure == 32 || treasure == 33)
            character.getInterface().newItem(treasure);
        else if (treasure == 41 || treasure == 42 || treasure == 43)
            character.getInterface().newItem(treasure);
    }

    public int getY_position() {
        return y_position;
    }

    public int getX_position() {
        return x_position;
    }

    public int getTreasure() {
        return treasure;
    }

    public void setTreasure(int treasure) {
        this.treasure = treasure;
    }

    public void setY_position(int y_position) {
        this.y_position = y_position;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }
}