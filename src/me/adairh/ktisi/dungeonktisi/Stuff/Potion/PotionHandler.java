package me.adairh.ktisi.dungeonktisi.Stuff.Potion;

import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentObject;

import java.util.HashMap;

public abstract class PotionHandler {


    private static HashMap<Integer,HashMap<Integer, PotionHandler>> data = new HashMap<Integer,HashMap<Integer, PotionHandler>>();
    private GameHandle handle;
    private String unidentifiedMessage = "Hmmm, thuốc này có công dụng gì đây ta";


    public PotionHandler(GameHandle handle){
        this.handle = handle;
        int id = getType().getID();
        HashMap<Integer, PotionHandler> oldList = new HashMap<Integer, PotionHandler>();
        if(data.containsKey(id))
            if (data.get(id).keySet().size() > 0)
                for (int i : data.get(id).keySet())
                    oldList.put(i,data.get(id).get(i));
        oldList.put(getId(),this);
        data.put(id,oldList);
    }

    public static HashMap<Integer,HashMap<Integer, PotionHandler>> getData() {
        return data;
    }


    public abstract void drink();

    public abstract int getId();

    public abstract String getText();

    public abstract PotionEffect getType();


}
