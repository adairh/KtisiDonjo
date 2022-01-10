package me.adairh.ktisi.dungeonktisi.Stats;

public enum StatsType {
    NULL(-1, "null", -1),
    DEXTERITY(0, "dexterity", 10),
    STRENGTH(1,"strength", 15),
    DEFENSE(2, "defense", 5);

    int defaultValue;
    int id;
    String name;

    StatsType(int id, String name, int defaultValue) {
        this.id = id;
        this.name = name;
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public int getId(){
        return this.id;
    }

    public static StatsType getStatsById(int id){
        if (id > -1) {
            for (StatsType type : StatsType.values()) {
                if (type.getId() == id){
                    return type;
                }
            }
        }
        return StatsType.NULL;
    }

    public static StatsType getStatsByName(String name){
        if (!name.equalsIgnoreCase("null")) {
            for (StatsType type : StatsType.values()) {
                if (type.getName().equalsIgnoreCase(name)) {
                    return type;
                }
            }
        }
        return StatsType.NULL;
    }

    public static int availableStatTypes(){
        return StatsType.values().length-1;
    }
}
