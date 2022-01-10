package me.adairh.ktisi.dungeonktisi.Stuff;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Level.Room;
import me.adairh.ktisi.dungeonktisi.Sound.SoundEffect;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentObject;

import java.util.Objects;

public class Inventory {

    private boolean was_clicked = false;
    private boolean eq_full = false;
    private int last_position;
    private int scrolls = 1;

    private GameHandle handle;
    private Character character;
    public Inventory(GameHandle handle, Character character) {
        this.character = character;
        this.handle = handle;
    }

    public void checkItem(int position, boolean action) {

        Character character = handle.getMainCharacter();
        was_clicked = true;

        if (character.getInterface().isInventory_shown()) {
        /* EMPTY */
            if (character.getInterface().getInventory()[position] == 0) {
                if (!action)
                    character.getInterface().newEvent("Ô này không có gì cả.");
                else
                    character.getInterface().newEvent("Bạn chưa cầm gì lên tay hết.");
            }
        /* POTIONS */
            if (character.getInterface().getInventory()[position] >= 1 && character.getInterface().getInventory()[position] <= 7) {
                if (!action)
                    handle.getPotion().mixtureType(character.getInterface().getInventory()[position] - 1);
                else
                    handle.getPotion().drinkPotion(character.getInterface().getInventory()[position] - 1);
            }
        /* FOOD */
            if (character.getInterface().getInventory()[position] == 8) {
                if (!action)
                    character.getInterface().newEvent("Cà tím tươi, tươi róiiii");
                else {
                    character.setHunger(0);
                    character.modifyHealth(25);
                    character.setCharacter_full(handle.getMainCharacter().getCharacter_full() + 15);
                    character.getInterface().newEvent("Cà tím thật toẹt zời.");
                    SoundEffect.EAT.play();
                }
            }
            if (character.getInterface().getInventory()[position] == 9) {
                if (!action)
                    character.getInterface().newEvent("Thịt khô, ai đó giấu nó lâu lắm rồi nè.");
                else {
                    character.setHunger(0);
                    character.modifyHealth(35);
                    character.setCharacter_full(handle.getMainCharacter().getCharacter_full() + 25);
                    character.getInterface().newEvent("Khô và cứng nhưng mà còn đủ ngon.");
                    SoundEffect.EAT.play();
                }
            }
            if (character.getInterface().getInventory()[position] == 10) {
                if (!action)
                    character.getInterface().newEvent("Một cây nấm, có nên ăn khum nhỉ.");
                else {
                    character.setCharacter_confused(15);
                    character.setCharacter_harm(handle.getMainCharacter().getCharacter_harm() + 10);
                    character.setMax_health(character.getMax_health() + 15);
                    character.getInterface().newEvent("U là trời, lạ lạ ta.");
                    SoundEffect.EAT.play();
                }
            }
        /* ARMORY */
            if (character.getInterface().getInventory()[position] >= 11 && character.getInterface().getInventory()[position] <= 16) {
                if (!action)
                    EquipmentObject.getData().get(1).get(character.getInterface().getInventory()[position]).info();
                else
                    EquipmentObject.getData().get(1).get(character.getInterface().getInventory()[position]).wear();
            }
            if (character.getInterface().getInventory()[position] >= 21 && character.getInterface().getInventory()[position] <= 23) {
                if (!action)
                    EquipmentObject.getData().get(2).get(character.getInterface().getInventory()[position]).info();
                else
                    EquipmentObject.getData().get(2).get(character.getInterface().getInventory()[position]).wear();
            }
            if (character.getInterface().getInventory()[position] >= 31 && character.getInterface().getInventory()[position] <= 33) {
                if (!action)
                    EquipmentObject.getData().get(3).get(character.getInterface().getInventory()[position]).info();
                else
                    EquipmentObject.getData().get(3).get(character.getInterface().getInventory()[position]).wear();
            }
            if (character.getInterface().getInventory()[position] >= 41 && character.getInterface().getInventory()[position] <= 43) {
                if (!action)
                    EquipmentObject.getData().get(4).get(character.getInterface().getInventory()[position]).info();
                else
                    EquipmentObject.getData().get(4).get(character.getInterface().getInventory()[position]).wear();
            }
            if (action && !eq_full)
                removeItem(position);
        }

        /* EQUIPMENT */
        else if (position < 5) {
            if (character.getInterface().getEquipment()[position] >= 11 && character.getInterface().getEquipment()[position] <= 16) {
                if (!action)
                    EquipmentObject.getData().get(1).get(character.getInterface().getEquipment()[position]).info();
            }
            if (character.getInterface().getEquipment()[position] >= 21 && character.getInterface().getEquipment()[position] <= 23) {
                if (!action)
                    EquipmentObject.getData().get(2).get(character.getInterface().getEquipment()[position]).info();
            }
            if (character.getInterface().getEquipment()[position] >= 31 && character.getInterface().getEquipment()[position] <= 33) {
                if (!action)
                    EquipmentObject.getData().get(3).get(character.getInterface().getEquipment()[position]).info();
            }
            if (character.getInterface().getEquipment()[position] >= 41 && character.getInterface().getEquipment()[position] <= 43) {
                if (!action)
                    EquipmentObject.getData().get(4).get(character.getInterface().getEquipment()[position]).info();
            }
            if (action)
                dropEquipment(position);
        }
        eq_full = false;
    }

    public void dropItem(int position) {
        Character character = handle.getMainCharacter();
        Room room = handle.getLevels_list().get(character.getPresent_level()).get(character.getPresent_room());
        if (character.getInterface().getInventory()[position] == 0) {
            character.getInterface().newEvent("Bạn chưa cầm gì lên tay hết.");
            return;
        }
        if (room.getSizes()[(int)character.getX_value()][(int)character.getY_value()] == 29 || room.getSizes()[(int)character.getX_value()][(int)character.getY_value()]  == 30){
            character.getInterface().newEvent("Này này, hong được ném đồ ở cầu thang!");
            return;
        }
        if (character.getInterface().isInventory_shown()) {
            room.addDrops(new Drop((int)character.getX_value(), (int)character.getY_value(), character.getInterface().getInventory()[position], handle));
            System.arraycopy(character.getInterface().getInventory(), position + 1,
                    character.getInterface().getInventory(), position, 9 - position);
            character.getInterface().updateInventory(9,0);
        } else {
            room.addDrops(new Drop((int)character.getX_value(), (int)character.getY_value(), character.getInterface().getEquipment()[position], handle));
            Objects.requireNonNull(EquipmentObject.getEquipment(character.getInterface().getEquipment()[position])).takeOff();
            character.getInterface().updateEquipment(position,0);
        }
        was_clicked = false;
    }

    private void removeItem(int position) {
        System.arraycopy(character.getInterface().getInventory(), position + 1,
                character.getInterface().getInventory(), position, 9 - position);
        character.getInterface().updateInventory(9,0);
        was_clicked = false;
    }

    private void dropEquipment(int position) {
        if (!character.getInterface().isInventory_shown() && character.getInterface().getEquipment()[position] != 0 &&
        EquipmentObject.isEquipment(character.getInterface().getEquipment()[position])) {
            if (character.getInterface().getInventory()[9] == 0) {
                character.getInterface().newItem(character.getInterface().getEquipment()[position]);
                Objects.requireNonNull(EquipmentObject.getEquipment(character.getInterface().getEquipment()[position])).takeOff();
                character.getInterface().updateEquipment(position,0);
            } else
                character.getInterface().newEvent("Túi đồ đã đầy!");
        }
        was_clicked = false;
    }

    public void identify(int position) {
        if (character.getInterface().getInventory()[position] == 0) {
            character.getInterface().newEvent("Bạn chưa cầm gì lên tay hết.");
            return;
        }
        if (scrolls > 0) {
            if (character.getInterface().isInventory_shown()) {
                if (character.getInterface().getInventory()[position] >= 1 && character.getInterface().getInventory()[position] <= 7
                        && !handle.getPotion().getMixtures_known()[character.getInterface().getInventory()[position] - 1]) {
                    handle.getPotion().identify(character.getInterface().getInventory()[position] - 1);
                    SoundEffect.IDENTIFY.play();
                    scrolls -= 1;
                } else if (character.getInterface().getInventory()[position] >= 11 && character.getInterface().getInventory()[position] <= 16
                        && !EquipmentObject.getData().get(1).get(character.getInterface().getInventory()[position]).identified()) {
                    EquipmentObject.getData().get(1).get(character.getInterface().getInventory()[position]).setIdentify(true);
                    scrolls -= 1;
                    SoundEffect.IDENTIFY.play();
                } else
                    character.getInterface().newEvent("Hong có cần khai quan vật phẩm này.");
            } else {
                if (character.getInterface().getEquipment()[position] >= 11 && character.getInterface().getEquipment()[position] <= 16
                        && !EquipmentObject.getData().get(1).get(character.getInterface().getInventory()[position]).identified()) {
                    EquipmentObject.getData().get(1).get(character.getInterface().getInventory()[position]).setIdentify(true);
                    scrolls -= 1;
                }
            }
        } else
            character.getInterface().newEvent("Hết cuộn giấy phép gòi.");
    }

    public boolean isEq_full() {
        return eq_full;
    }

    public int getLast_position() {
        return last_position;
    }

    public boolean isWas_clicked() {
        return was_clicked;
    }

    public int getScrolls() {
        return scrolls;
    }

    public void setEq_full(boolean eq_full) {
        this.eq_full = eq_full;
    }

    public void setLast_position(int last_position) {
        this.last_position = last_position;
    }

    public void setScrolls(int scrolls) {
        this.scrolls = scrolls;
    }

    public void setWas_clicked(boolean was_clicked) {
        this.was_clicked = was_clicked;
    }

}