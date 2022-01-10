package me.adairh.ktisi.dungeonktisi.Stuff.Block.Treasure;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Sound.SoundEffect;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentType;

import java.util.Random;

public class Chests {

    int x_position;
    int y_position;
    private boolean was_taken = false;
    private int treasure = 0;
    private boolean isKey;
    private GameHandle handle;

    public Chests(int x_position, int y_position, boolean isKey, GameHandle handle) {
        this.handle = handle;
        this.x_position = x_position;
        this.y_position = y_position;
        this.isKey = isKey;
        if (!isKey) {
            Random generator = new Random();
            int random = generator.nextInt(165);

            if (random < 10)
                treasure = 0;
            else if (random < 20)
                treasure = 1;
            else if (random < 80)
                treasure = handle.getPotion().dropMixture();
            else if (random < 100)
                treasure = 8;
            else if (random < 120)
                treasure = 9;
            else if (random < 125)
                treasure = 10;
            else if (random < 140)
                treasure = 50;
            else if (random < 150)
                treasure = EquipmentType.RING.dropItems();
            else if (random < 155)
                treasure = EquipmentType.SWORD.dropItems();
            else if (random < 160)
                treasure = EquipmentType.ARMOR.dropItems();
            else if (random < 165)
                treasure = EquipmentType.SHIELD.dropItems();
        }
    }

    public void checkTreasure() {
        Character character = handle.getMainCharacter();
        if (!was_taken) {
            if (!isKey) {
                if (character.getInterface().getInventory()[9] == 0) {
                    if (treasure == 0)
                        character.getInterface().newEvent("Hỏng có gì hết chơn.");
                    else if (treasure > 0 && treasure < 8)
                        character.getInterface().newItem(handle.getPotion().prevPotion(treasure - 1) + 1);
                    else if (treasure == 8 || treasure == 9 || treasure == 10)
                        character.getInterface().newItem(treasure);
                    else if (treasure == 50) {
                        character.getInventory().setScrolls(character.getInventory().getScrolls() + 1);
                        character.getInterface().newEvent("Ủa cuộn giấy phép!");
                    } else if (treasure >= 11 && treasure <= 16) {
                        character.getInterface().newItem(treasure);
                    } else if (treasure == 21 || treasure == 22 || treasure == 23) {
                        character.getInterface().newItem(treasure);
                    } else if (treasure == 31 || treasure == 32 || treasure == 33) {
                        character.getInterface().newItem(treasure);
                    } else if (treasure == 41 || treasure == 42 || treasure == 43) {
                        character.getInterface().newItem(treasure);
                    }
                    SoundEffect.CHEST_OPEN.play();
                    was_taken = true;
                } else
                    character.getInterface().newEvent("Túi đồ đầy gòi, chọn item và nhấn X để ném bớt!");
            } else {
                if (!handle.getMainCharacter().isHasKey()) {
                    character.getInterface().newEvent("Bạn tìm thấy chìa khóa! Hãy dùng nó để lên tầng tiếp theo");
                    SoundEffect.GOLDEN_CHEST_OPEN.play();
                }
                handle.getMainCharacter().setHasKey(true);
                was_taken = true;
            }
        } else
            character.getInterface().newEvent("Rương này lấy hết gòii.");
    }

    public boolean isWas_taken() {
        return was_taken;
    }

    public int getTreasure() {
        return treasure;
    }

    public int getX_position() {
        return x_position;
    }

    public int getY_position() {
        return y_position;
    }

    public void setTreasure(int treasure) {
        this.treasure = treasure;
    }

    public void setWas_taken(boolean was_taken) {
        this.was_taken = was_taken;
    }

    public void setX_position(int x_position) {
        this.x_position = x_position;
    }

    public void setY_position(int y_position) {
        this.y_position = y_position;
    }

    public boolean isKey() {
        return isKey;
    }
}