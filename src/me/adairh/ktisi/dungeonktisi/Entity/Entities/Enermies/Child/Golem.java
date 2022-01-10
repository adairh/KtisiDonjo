package me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Level.Room;
import me.adairh.ktisi.dungeonktisi.Utilities.IMGLoader.Block;

public class Golem extends Enemies {

    public Golem(GameHandle handle, Room room, int positionX, int positionY) {
        super(handle,"Golem", room, Block.GOLEM,
                positionX, positionY,50,
                72,1,20,10,50, 0.2, 0.75);
    }
}
