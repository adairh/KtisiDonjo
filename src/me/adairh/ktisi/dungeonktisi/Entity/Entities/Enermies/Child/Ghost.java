package me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Level.Room;
import me.adairh.ktisi.dungeonktisi.Utilities.IMGLoader.Block;

public class Ghost extends Enemies {

    public Ghost(GameHandle handle, Room room, int positionX, int positionY) {
        super(handle, "Ghost", room, Block.GHOST,
                positionX, positionY,10,
                73,90,5,1,5, 0.45, 1.5);

    }
}
