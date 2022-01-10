package me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Level.Room;
import me.adairh.ktisi.dungeonktisi.Utilities.IMGLoader.Block;

public class Zombie extends Enemies {

    public Zombie(GameHandle handle, Room room, int positionX, int positionY) {
        super(handle,"Zombie", room, Block.ZOMBIE,
                positionX, positionY,25,
                70,5,10,5,25, .3, 1.1);
    }
}
