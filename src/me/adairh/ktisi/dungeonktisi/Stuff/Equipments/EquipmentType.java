package me.adairh.ktisi.dungeonktisi.Stuff.Equipments;

import me.adairh.ktisi.dungeonktisi.Stuff.Potion.PotionEffect;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

public enum EquipmentType {
    NULL(-1,"null"),
    RING(1,"ring"),
    SWORD(2,"sword"),
    ARMOR(3,"armor"),
    SHIELD(4,"shield");

    private int id;
    private String name;

    EquipmentType(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int dropItems(){
        HashMap<Integer, EquipmentObject> data =  EquipmentObject.getData().get(this.getID());
        int size = data.keySet().size();
        Random random = new Random();
        int gotIt = random.nextInt(size);
        int key = (int) data.keySet().toArray()[gotIt];
        return data.get(key).getId();
    }


    public static EquipmentType getById(int id){
        if (id > -1) {
            for (EquipmentType type : EquipmentType.values()) {
                if (type.getID() == id){
                    return type;
                }
            }
        }
        return EquipmentType.NULL;
    }

    public static EquipmentType getByName(String name){
        if (!name.equalsIgnoreCase("null")) {
            for (EquipmentType type : EquipmentType.values()) {
                if (type.getName().equalsIgnoreCase(name)) {
                    return type;
                }
            }
        }
        return EquipmentType.NULL;
    }
}
