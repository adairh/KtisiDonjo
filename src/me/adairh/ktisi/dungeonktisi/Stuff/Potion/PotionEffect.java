package me.adairh.ktisi.dungeonktisi.Stuff.Potion;

import me.adairh.ktisi.dungeonktisi.Stats.StatsType;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentObject;

import java.util.HashMap;
import java.util.Random;

public enum PotionEffect {
    NULL(-1,"null"),
    FINESSE(0,"finesse"),
    HEALING(1,"healing"),
    STRENGTH(2,"strength"),
    PROTECTION(3,"protection"),
    EXPERIENCE(4,"experience"),
    HARM(5,"harm"),
    CONFUSED(6,"confused"),
    PARALYZE(7,"paralyze");


    int id;
    String name;

    PotionEffect(int id, String name){
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
        HashMap<Integer, PotionHandler> data =  PotionHandler.getData().get(this.getID());
        int size = data.keySet().size();
        Random random = new Random();
        int gotIt = random.nextInt(size);
        int key = (int) data.keySet().toArray()[gotIt];
        return data.get(key).getId();
    }

    public static PotionEffect getById(int id){
        if (id > -1) {
            for (PotionEffect type : PotionEffect.values()) {
                if (type.getID() == id){
                    return type;
                }
            }
        }
        return PotionEffect.NULL;
    }

    public static PotionEffect getByName(String name){
        if (!name.equalsIgnoreCase("null")) {
            for (PotionEffect type : PotionEffect.values()) {
                if (type.getName().equalsIgnoreCase(name)) {
                    return type;
                }
            }
        }
        return PotionEffect.NULL;
    }
}
