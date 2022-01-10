package me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Sword;

import me.adairh.ktisi.dungeonktisi.Battle.AttackStyle;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Stats.StatsType;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Weapon;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentObject;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentType;

import java.util.HashMap;

public class LongSword extends Weapon {

    private boolean identified;

    public LongSword(GameHandle handle) {
        super(handle);
        identified = true;
    }

    @Override
    public AttackStyle getAttackStyle() {
        return AttackStyle.CLOSE;
    }

    @Override
    public double radius() {
        return 2.5;
    }
    @Override
    public int getId() {
        return 23;
    }

    @Override
    public EquipmentType getType() {
        return EquipmentType.SWORD;
    }

    @Override
    public EquipmentObject getEquipment() {
        return this;
    }

    @Override
    public String getText() {
        return "Trường kiếm";
    }

    @Override
    public HashMap<StatsType, Integer> getStatsField() {
        HashMap<StatsType, Integer> statField = new HashMap<StatsType, Integer>();
        statField.put(StatsType.STRENGTH, 7);
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
