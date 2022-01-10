package me.adairh.ktisi.dungeonktisi.Task;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.GameHandle;

public class CharacterTask {


    public static void action(GameHandle handle) {
        Character character = handle.getMainCharacter();
        character.buffs();
    }

}
