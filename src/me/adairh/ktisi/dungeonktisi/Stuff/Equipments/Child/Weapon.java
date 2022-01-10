package me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child;

import me.adairh.ktisi.dungeonktisi.Battle.AttackStyle;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentObject;

public abstract class Weapon extends EquipmentObject{
    public Weapon(GameHandle handle) {
        super(handle);
    }

    public abstract AttackStyle getAttackStyle();

    public abstract double radius();
}
