package me.adairh.ktisi.dungeonktisi.Battle;

public enum AttackStyle {
    CLOSE(1,"close"),
    AREA(2,"area"),
    LINE(3,"line");

    private int id;
    private String name;

    AttackStyle(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
