package me.adairh.ktisi.dungeonktisi.Task;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.GameHandle;

import java.util.ListIterator;

public class EntityTask {

    public static void enemyMove(GameHandle handle) {
        Character character = handle.getMainCharacter();
        ListIterator<Enemies> iterator = handle.getLevels_list().get
                (character.getPresent_level()).get(character.getPresent_room()).getEnemies_list().listIterator();
        while (iterator.hasNext()) {
            Enemies enemies = iterator.next();
            if (enemies.getRoom().getIndex() == character.getPresent_room()) {
                enemies.moveAlgorithm();
                if (!enemies.isAlive()) {
                    iterator.remove();
                }
            }
        }
    }

}
