package me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Armor;

import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Stats.StatsType;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentObject;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentType;

import java.util.HashMap;

public class ChainArmor extends EquipmentObject {

    private boolean identified;

    public ChainArmor(GameHandle handle) {
        super(handle);
        identified = true;
    }

    @Override
    public int getId() {
        return 32;
    }

    @Override
    public EquipmentType getType() {
        return EquipmentType.ARMOR;
    }

    @Override
    public EquipmentObject getEquipment() {
        return this;
    }

    @Override
    public String getText() {
        return "Áo giáp xích";
    }

    @Override
    public HashMap<StatsType, Integer> getStatsField() {
        HashMap<StatsType, Integer> statField = new HashMap<StatsType, Integer>();
        statField.put(StatsType.DEFENSE, 5);
        return statField;
    }

    @Override
    public boolean identified() {
        return identified;
    }

    @Override
    public void setIdentify(boolean identified) {
        this.identified = identified;
    }


}
