package me.adairh.ktisi.dungeonktisi.Stuff.Potion;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Sound.SoundEffect;
import me.adairh.ktisi.dungeonktisi.Stats.StatsType;

import java.util.Arrays;
import java.util.Random;

public class Potion {

    boolean[] mixtures_known = new boolean[7];


    private int randomize;
    private Random generator = new Random();
    private GameHandle handle;

    public Potion(GameHandle handle) {
        this.handle = handle;
        Arrays.fill(mixtures_known, false);
        randomize = generator.nextInt(7);
    }

    public void mixtureType(int index) {
        Character character = handle.getMainCharacter();

        int type = Math.floorMod((randomize + index), 7);
        String[] mixtures = new String[7];
        mixtures[0] = "gây choáng";
        mixtures[1] = "tê";
        mixtures[2] = "sức mạnh";
        mixtures[3] = "khéo léo";
        mixtures[4] = "hồi phục";
        mixtures[5] = "kinh nghiệm";
        mixtures[6] = "gây hại";
        String[] colors = new String[7];
        colors[0] = "vàng";
        colors[1] = "xanh dương";
        colors[2] = "đỏ";
        colors[3] = "tím";
        colors[4] = "xanh lục";
        colors[5] = "xanh ngọc";
        colors[6] = "cam";

        if (!mixtures_known[index])
            character.getInterface().newEvent("Bình thuốc màu " + colors[index] + ". Nó là gì dị ta??!?");
        else
            character.getInterface().newEvent("Thuốc " + mixtures[type] + ". Bạn mún ún nó hok?");
    }

    public void drinkPotion(int index) {

        Character character = handle.getMainCharacter();
        int type = Math.floorMod((randomize + index), 7);
        if (type == 0) {
            character.getInterface().newEvent("Á á gì dị chùi?");
            character.setCharacter_confused(character.getCharacter_confused() + 15);
        } else if (type == 1) {
            character.getInterface().newEvent("Dụ gì dị ta.");
            character.setCharacter_paralyze(character.getCharacter_paralyze() + 10);
        } else if (type == 2) {
            character.getInterface().newEvent("Ú khỏe dị, cái dì dị taaa.");
            character.setStatsPoints(StatsType.STRENGTH, character.getStatsPoints(StatsType.STRENGTH) + 2);
        } else if (type == 3) {
            character.getInterface().newEvent("Ú chà gân cốt đã ghê.");
            character.setStatsPoints(StatsType.DEXTERITY, character.getStatsPoints(StatsType.DEXTERITY) + 2);
        } else if (type == 4) {
            character.getInterface().newEvent("Ủa ngon dị!");
            character.setHealth_points(character.getMax_health());
            character.setCharacter_paralyze(0);
            character.setCharacter_confused(0);
            character.setCharacter_harm(0);
        } else if (type == 5) {
            character.getInterface().newEvent("Ông bà phù hộ!.");
            character.experience(character.getNext_level() - character.getExperience());
        } else if (type == 6) {
            character.getInterface().newEvent("Ớ ớ ớ đau quá hmu hmu.");
            character.setCharacter_harm(character.getCharacter_harm() + 25);
        }
        SoundEffect.DRINK.play();
        mixtures_known[index] = true;
    }

    public void identify(int index) {
        mixtures_known[index] = true;
        mixtureType(index);
    }

    public int dropMixture() {

        int random = generator.nextInt(60);

        if (random < 10)
            return 2;
        else if (random < 15)
            return 3;
        else if (random < 20)
            return 4;
        else if (random < 45)
            return 5;
        else if (random < 50)
            return 6;
        else
            return 7;
    }

    public int prevPotion(int numOf) {
        return Math.floorMod((numOf - randomize), 7);
    }

    public int nextPotion(int numOf) {
        return Math.floorMod((numOf + randomize), 7);
    }

    public boolean[] getMixtures_known() {
        return mixtures_known;
    }



}