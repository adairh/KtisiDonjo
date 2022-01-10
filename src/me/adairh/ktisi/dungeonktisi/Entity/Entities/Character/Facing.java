package me.adairh.ktisi.dungeonktisi.Entity.Entities.Character;

public enum Facing {
        NORTH("north"),
        SOUTH("south"),
        EAST("east"),
        WEST("west");

        private String name;

        Facing(String str){
            name = str;
        }

        public String getName() {
            return name;
        }
    }