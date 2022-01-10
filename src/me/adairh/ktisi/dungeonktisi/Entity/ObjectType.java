package me.adairh.ktisi.dungeonktisi.Entity;

public enum ObjectType {
    ENEMY("enemy"),
    CHARACTER("character"),
    MISC("misc");

    private String name;
    ObjectType(String s){
        this.name = s;
    }

    public String getName() {
        return name;
    }
}
