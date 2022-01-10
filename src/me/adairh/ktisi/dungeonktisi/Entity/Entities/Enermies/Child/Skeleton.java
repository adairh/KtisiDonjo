package me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Level.Room;
import me.adairh.ktisi.dungeonktisi.Utilities.IMGLoader.Block;

public class Skeleton extends Enemies {

    public Skeleton(GameHandle handle, Room room, int positionX, int positionY) {
        super(handle,"Skeleton", room, Block.SKELETON,
                positionX, positionY,35,
                71,15,10,5,25, 0.35, 1.1);
    }
}
