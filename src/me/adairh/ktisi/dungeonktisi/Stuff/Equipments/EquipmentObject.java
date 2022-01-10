package me.adairh.ktisi.dungeonktisi.Stuff.Equipments;

import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Stats.StatsType;
import me.adairh.ktisi.dungeonktisi.Utilities.Interface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class EquipmentObject {

    /**
     *
     * 1x = Ring
     * 2x = Sword
     * 3x = Armor
     * 4x = Shield
     *
     */

    private static HashMap<Integer,HashMap<Integer, EquipmentObject>> data = new HashMap<Integer,HashMap<Integer, EquipmentObject>>();
    private GameHandle handle;
    private String unidentifiedMessage = "Hmmm, cái này có công dụng gì đây ta";


    public EquipmentObject(GameHandle handle){
        System.out.println(getText() + " with id is " + getId() + " and type is " + getType().getName() + " with type ID is " + getType().getID());
        this.handle = handle;
        int id = getType().getID();
        HashMap<Integer, EquipmentObject> oldList = new HashMap<Integer, EquipmentObject>();
        if(data.containsKey(id))
            if (data.get(id).keySet().size() > 0)
                for (int i : data.get(id).keySet())
                    oldList.put(i,data.get(id).get(i));
        oldList.put(getId(),this);
        data.put(id,oldList);
    }

    public static HashMap<Integer,HashMap<Integer, EquipmentObject>> getData() {
        return data;
    }

    public void info(){
        if (identified()) {
            handle.getMainCharacter().getInterface().newEvent(this.getText());
        } else {
            handle.getMainCharacter().getInterface().newEvent(unidentifiedMessage);
        }
    }

    public void wear() {
        for (StatsType st : getStatsField().keySet()) {
            if (handle.getMainCharacter().getInterface().addStuff(getId(), handle)){
                handle.getMainCharacter().addBonusStatsPoints(st,getStatsField().get(st));
            }
        }
    }

    public void takeOff(){
        for (StatsType st : getStatsField().keySet()) {
            handle.getMainCharacter().addBonusStatsPoints(st,-getStatsField().get(st));
        }
    }

    public String getUnidentifiedMessage() {
        return unidentifiedMessage;
    }

    public abstract int getId();

    public abstract String getText();

    public abstract HashMap<StatsType, Integer> getStatsField();

    public abstract boolean identified();

    public abstract void setIdentify(boolean identified);

    public abstract EquipmentType getType();

    public abstract EquipmentObject getEquipment();

    /*
    public void updateStats() {
        int DEX = 0, STR = 0, DEF = 0, ATT = 0;

        for (int place = 0; place < 2; place++) {
            if (handle.getInterface().getEquipment()[place] == 11)
                DEX += 2;
            if (handle.getInterface().getEquipment()[place] == 12)
                DEX -= 5;
            if (handle.getInterface().getEquipment()[place] == 13)
                STR += 2;
            if (handle.getInterface().getEquipment()[place] == 14)
                STR -= 5;
            if (handle.getInterface().getEquipment()[place] == 15)
                DEF += 2;
            if (handle.getInterface().getEquipment()[place] == 16)
                DEF -= 5;
        }
        if (handle.getInterface().getEquipment()[3] == 21)
            ATT += 1;
        if (handle.getInterface().getEquipment()[3] == 22)
            ATT += 3;
        if (handle.getInterface().getEquipment()[3] == 23)
            ATT += 5;
        if (handle.getInterface().getEquipment()[2] == 31)
            DEF += 1;
        if (handle.getInterface().getEquipment()[2] == 32)
            DEF += 3;
        if (handle.getInterface().getEquipment()[2] == 33)
            DEF += 5;
        if (handle.getInterface().getEquipment()[4] == 41)
            DEF += 1;
        if (handle.getInterface().getEquipment()[4] == 42)
            DEF += 3;
        if (handle.getInterface().getEquipment()[4] == 43)
            DEF += 5;

        handle.getMainCharacter().setATT(ATT);
        handle.getMainCharacter().setDEX(DEX);
        handle.getMainCharacter().setDEF(DEF);
        handle.getMainCharacter().setSTR(STR);
    }*/

    public static boolean isEquipment(int id){
        if (!data.isEmpty())
            for (int mainID : data.keySet())
                if (!data.get(mainID).isEmpty())
                    if (data.get(mainID).containsKey(id))
                        return true;
        return false;
    }

    public static EquipmentObject getEquipment(int id){
        if (!data.isEmpty())
            for (int mainID : data.keySet())
                if (!data.get(mainID).isEmpty())
                    if (data.get(mainID).containsKey(id))
                        return data.get(mainID).get(id);
        return null;
    }

}

