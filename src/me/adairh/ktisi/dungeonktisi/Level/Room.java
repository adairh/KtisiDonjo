package me.adairh.ktisi.dungeonktisi.Level;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child.Ghost;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child.Golem;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child.Skeleton;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Child.Zombie;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Stuff.Block.Door.Door;
import me.adairh.ktisi.dungeonktisi.Stuff.Block.Torch.Torch;
import me.adairh.ktisi.dungeonktisi.Stuff.Block.Treasure.Chests;
import me.adairh.ktisi.dungeonktisi.Stuff.Drop;
import me.adairh.ktisi.dungeonktisi.Utilities.IMGLoader.Block;
import me.adairh.ktisi.dungeonktisi.Utilities.StructureGenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class Room {

    //private boolean isEnemy, isZombiable, isSkeletonable, isGolemable, isGhostable = false;
    private Random generator = new Random();
    private int[] north, south, east, west;
    boolean visited = false;
    int height, width = 0;
    int index = 0;
    int[][] sizes;

    HashMap<Location<Integer, Integer>, Block> locations = new HashMap<Location<Integer, Integer>, Block>();

    ArrayList<Enemies> enemies_list;
    ArrayList<Chests> chests_list;
    ArrayList<Drop> drop_list;
    ArrayList<Door> doors;
    ArrayList<Torch> torches;

    private GameHandle handle;
    private int maxRoom;
    private int level;

    private RoomType roomType;

    Room(int level, int maxRoom, int index, GameHandle handle) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        System.out.println("Creating room no."+ index);
        this.level      = level;
        this.maxRoom    = maxRoom;
        this.handle     = handle;
        this.index      = index;
        doors           = new ArrayList<>();
        torches         = new ArrayList<>();
        chests_list     = new ArrayList<>();
        drop_list       = new ArrayList<>();
        enemies_list    = new ArrayList<>();
        if (index != maxRoom - 1 && index != 0) {
            height = generator.nextInt(10) + 11;
            width = generator.nextInt(10) + 11;
        } else {
            height = 11;
            width = 11;
        }
        sizes = new int[width][height];
        north = new int[width];
        south = new int[width];
        east  = new int[height];
        west  = new int[height];
        if (index != maxRoom - 1 && index != 0) {
            addWalls();
            roomType();
            addEnemies();
        } else {
            addWalls();
            roomTypeStairs();
            this.roomType = RoomType.QUARTZ;
        }
        System.out.println("List of enemies: " + enemies_list);

    }

    public void addKeyChest(){
        addChests(true);
    }

    private void roomType() {
        this.roomType = RoomType.getRandomType();
        while (level < roomType.getLevelAppear()){
            this.roomType = RoomType.getRandomType();
        }
        System.out.println(roomType.getName());
        if (height % 2 == 0 || width % 2 == 0) {
            while (Arrays.asList(/*RoomType.LIBRARY, */RoomType.HALL_QUARTZ, RoomType.ROUNDED_QUARTZ, RoomType.VERTICAL_QUARTZ).contains(roomType)) {
                this.roomType = RoomType.getRandomType();
            }
        }


        Random generator = new Random();

       for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                sizes[x][y] = roomType.getRandomBlock().getId();
            }
        }

        if (this.roomType == RoomType.VERTICAL_QUARTZ) {
            for (int y = 2; y < height - 2; y += 2) {
                sizes[2][y] = roomType.getRandomDecoration().getId();
                sizes[width - 3][y] = roomType.getRandomDecoration().getId();
            }
        }

        else if (this.roomType == RoomType.ROUNDED_QUARTZ) {
            for (int y = 2; y < height - 2; y += 2) {
                sizes[2][y] = roomType.getRandomDecoration().getId();
                sizes[width - 3][y] = roomType.getRandomDecoration().getId();
            }
            for (int x = 2; x < width - 2; x += 2) {
                sizes[x][2] = roomType.getRandomDecoration().getId();
                sizes[x][height - 3] = roomType.getRandomDecoration().getId();
            }
        }

        else if (this.roomType == RoomType.HALL_QUARTZ) {
            for (int x = 2; x < width - 2; x += 2) {
                for (int y = 2; y < height - 2; y += 2) {
                    sizes[x][y] = roomType.getRandomDecoration().getId();
                }
            }
        }

        addChests(false);

        /*else if (this.roomType == RoomType.LIBRARY) {
            for (int x = 2; x < width - 2; x++) {
                for (int y = 2; y < height - 2; y += 2) {
                    sizes[x][y] = roomType.getRandomDecoration().getId();
                    if (generator.nextInt(30) == 0) {
                        sizes[x][y] = 25;
                        Chests chest = new Chests(x, y, false, handle);
                        chests_list.add(chest);
                    }
                }
            }
        }*/
        /*else if (this.roomType == RoomType.PURPLE_HALL) {
            Random random = new Random();


            int centerX = (int)height/2;
            int centerY = (int)width/2;

            int range = Math.min(centerX, centerY) / 2;



            int k = 0;
            *//*while (k < Math.PI * 2){
                double i = Math.sin(k) * range;
                double j = Math.cos(k) * range;
                if ((int)i+centerX >= 1 && (int)i+centerX < height-1 && (int)j+centerY >= 1 && (int)j+centerY < width-1) {
                    sizes[(int)i+centerX][(int)j+centerY] = Block.PURPLE_TERRACOTTA.getId();
                    k += Math.PI / 16;
                }
            }*//*

            sizes[centerX][centerY] = Block.PURPLE_STAINED_GLASS.getId();

        }*/


    }

    private void roomTypeStairs() {

        /* TILES AROUND STAIRS */
        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                sizes[x][y] = Block.FLOOR.getId();
                if (generator.nextInt(4) == 0)
                    sizes[x][y] = Block.FLOOR_DIVIDED.getId();
                else if (generator.nextInt(4) == 0)
                    sizes[x][y] = Block.FLOOR_DECORATED.getId();
                else if (generator.nextInt(4) == 0)
                    sizes[x][y] = Block.FLOOR_TILE.getId();
            }
        }
        /* STAIRS UP */
        if (level > 0) {
            if (index == 0) {
                sizes[width / 2][height / 2] = Block.STAIR_UP.getId();
                visited = true;
            }
        }
        /* STAIRS DOWN */
        if (index == maxRoom - 1) {
            sizes[width / 2][height / 2] = Block.LOCKED_STAIR.getId(); //<== 26 = locked stair; normal stair = 29
        }
    }

    private void addWalls() {
        Arrays.fill(north, Block.WALL.getId());
        Arrays.fill(south, Block.WALL.getId());
        Arrays.fill(east, Block.WALL.getId());
        Arrays.fill(west, Block.WALL.getId());

        for (int i = 2; i < north.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                north[i] = Block.WALL_PLANT_UP.getId();
            else if (generator.nextInt(5) == 0)
                north[i] = Block.WALL_MOSS_UP.getId();
            else if (generator.nextInt(50) == 0)
                north[i] = Block.WALL_CRACKED.getId();
        }
        for (int i = 2; i < south.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                south[i] = Block.WALL_PLANT_DOWN.getId();
            else if (generator.nextInt(5) == 0)
                south[i] = Block.WALL_MOSS_DOWN.getId();
            else if (generator.nextInt(50) == 0)
                south[i] = Block.WALL_CRACKED.getId();
        }
        for (int i = 2; i < west.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                west[i] = Block.WALL_PLANT_LEFT.getId();
            else if (generator.nextInt(5) == 0)
                west[i] = Block.WALL_MOSS_LEFT.getId();
            else if (generator.nextInt(50) == 0)
                west[i] = Block.WALL_CRACKED.getId();
        }
        for (int i = 2; i < east.length - 2; i++) {
            if (generator.nextInt(100) == 0)
                east[i] = Block.WALL_PLANT_RIGHT.getId();
            else if (generator.nextInt(5) == 0)
                east[i] = Block.WALL_MOSS_RIGHT.getId();
            else if (generator.nextInt(50) == 0)
                east[i] = Block.WALL_CRACKED.getId();
        }
        addDoors();
        addTorches();

        for (int x = 0; x < width; x++) {
            sizes[x][0] = north[x];
            sizes[x][height - 1] = south[x];
        }
        for (int y = 0; y < height; y++) {
            sizes[width - 1][y] = east[y];
            sizes[0][y] = west[y];
        }
    }

    private void addDoors() {
        Random generator = new Random();
        StructureGenerator structure = handle.getStructure();
        for (int doorID = 0; doorID < maxRoom; doorID++) {
            //System.out.println("Access: "+ index + "-" + doorID + " meanwhile current: " + maxRoom);
            if (structure.getValue(index,doorID)) {
                int wall = generator.nextInt(4);
                int place;
                while (true) {
                    if (wall == 0) {
                        place = generator.nextInt(north.length - 4) + 2;
                        if (north[place] != Block.DOOR.getId()/*3*/ && north[place - 1] != Block.DOOR.getId() && north[place + 1] != Block.DOOR.getId()) {
                            north[place] = Block.DOOR.getId();
                            break;
                        } else
                            wall += 1;
                    }
                    if (wall == 1) {
                        place = generator.nextInt(south.length - 4) + 2;
                        if (south[place] != Block.DOOR.getId() && south[place - 1] != Block.DOOR.getId() && south[place + 1] != Block.DOOR.getId()) {
                            south[place] = Block.DOOR.getId();
                            break;
                        } else
                            wall += 1;
                    }
                    if (wall == 2) {
                        place = generator.nextInt(east.length - 4) + 2;
                        if (east[place] != Block.DOOR.getId() && east[place - 1] != Block.DOOR.getId() && east[place + 1] != Block.DOOR.getId()) {
                            east[place] = Block.DOOR.getId();
                            break;
                        } else
                            wall += 1;
                    }
                    if (wall == 3) {
                        place = generator.nextInt(west.length - 4) + 2;
                        if (west[place] != Block.DOOR.getId() && west[place - 1] != Block.DOOR.getId() && west[place + 1] != Block.DOOR.getId()) {
                            west[place] = Block.DOOR.getId();
                            break;
                        } else
                            wall = 0;
                    }
                }
                Door door = new Door(index, doorID, wall, place, height, width);
                //System.out.println("new door at "+ door.getPosx() + ":" + door.getPosy());
                doors.add(door);
            }
        }
    }

    private boolean notOverride(int... i){
        for (int ii : i){
            if (ii >= Block.DOOR.getId() && ii <= Block.TORCH_D.getId()){
                return false;
            }
        }
        return true;
    }

    private void addTorches() {
        Random generator = new Random();
        StructureGenerator structure = handle.getStructure();
        int touchPerRoom = 2;
        if (index != 0 && index != maxRoom-1) {
            int minTorch = 0;
            int maxTorch = 2;

            touchPerRoom = generator.nextInt(maxTorch - minTorch) + minTorch;
        }

        for (int torchID = 0; torchID < touchPerRoom; torchID++) {
            //System.out.println("Access: "+ index + "-" + torchID + " meanwhile current: " + maxRoom);
            //if (structure.getValue(index,torchID)) {
                int wall = generator.nextInt(4);
                int place;
                while (true) {
                    if (wall == 0) {
                        place = generator.nextInt(north.length - 4) + 2;
                        if (notOverride(north[place], north[place - 1], north[place + 1])) {
                            north[place] = Block.TORCH_A.getId();
                            break;
                        } else
                            wall += 1;
                    }
                    if (wall == 1) {
                        place = generator.nextInt(south.length - 4) + 2;
                        if (notOverride(south[place], south[place - 1], south[place + 1])) {
                            south[place] = Block.TORCH_B.getId();
                            break;
                        } else
                            wall += 1;
                    }
                    if (wall == 2) {
                        place = generator.nextInt(east.length - 4) + 2;
                        if (notOverride(east[place], east[place - 1], east[place + 1])) {
                            east[place] = Block.TORCH_C.getId();
                            break;
                        } else
                            wall += 1;
                    }
                    if (wall == 3) {
                        place = generator.nextInt(west.length - 4) + 2;
                        if (notOverride(west[place], west[place - 1], west[place + 1])) {
                            west[place] = Block.TORCH_D.getId();
                            break;
                        } else
                            wall = 0;
                    }
                }
                Torch torch = new Torch(wall, place, height, width);
                //System.out.println("new torch at "+ torch.getX() + ":" + torch.getY());
                torches.add(torch);
            //}
        }
    }

    private void addEnemies() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        if (roomType.getEntities().length > 0) {
            Class<Enemies> clazz = roomType.getEnemies();
            for (int numberOf = 0; numberOf < generator.nextInt(5-2) + 2; numberOf++) {
                int x_position = generator.nextInt(width - 4) + 2;
                int y_position = generator.nextInt(height - 4) + 2;
                if (Arrays.asList(roomType.getFloors()).contains(Block.getBlock(sizes[x_position][y_position]))) {
                    /*if (clazz.equals(Zombie.class)) {
                        Zombie zombie = new Zombie(handle,this, x_position, y_position);
                        enemies_list.add(zombie);
                    }
                    if (clazz.equals(Skeleton.class)) {
                        Skeleton skeleton = new Skeleton(handle,this, x_position, y_position);
                        enemies_list.add(skeleton);
                    }
                    if (clazz.equals(Golem.class)) {
                        Golem golem = new Golem(handle,this, x_position, y_position);
                        enemies_list.add(golem);
                    }
                    if (clazz.equals(Ghost.class)) {
                        Ghost ghost = new Ghost(handle,this, x_position, y_position);
                        enemies_list.add(ghost);
                    }*/
                    Enemies enemies = clazz.getConstructor(GameHandle.class, Room.class, int.class, int.class).newInstance(handle,this, x_position, y_position);
                    enemies_list.add(enemies);
                } else
                    numberOf--;
            }
        }

    }

    private void addChests(boolean keyChest) {
        int wall = generator.nextInt(4);
        if (!keyChest) {
            if (generator.nextInt(2) == 0) {
                if (wall == 0) {
                    int place = generator.nextInt(width - 2) + 1;
                    if (north[place] != Block.DOOR.getId()) {
                        sizes[place][1] = Block.CHEST.getId();
                        Chests chest = new Chests(place, 1, false, handle);
                        chests_list.add(chest);
                    }
                }
                if (wall == 1) {
                    int place = generator.nextInt(width - 2) + 1;
                    if (south[place] != Block.DOOR.getId()) {
                        sizes[place][height - 2] = Block.CHEST.getId();
                        Chests chest = new Chests(place, height - 2, false, handle);
                        chests_list.add(chest);
                    }
                }
                if (wall == 2) {
                    int place = generator.nextInt(height - 2) + 1;
                    if (east[place] != Block.DOOR.getId()) {
                        sizes[width - 2][place] = Block.CHEST.getId();
                        Chests chest = new Chests(width - 2, place, false, handle);
                        chests_list.add(chest);
                    }
                }
                if (wall == 3) {
                    int place = generator.nextInt(height - 2) + 1;
                    if (west[place] != Block.DOOR.getId()) {
                        sizes[1][place] = Block.CHEST.getId();
                        locations.put(new Location<>(1,place), Block.CHEST);
                        Chests chest = new Chests(1, place, false, handle);
                        chests_list.add(chest);
                    }
                }
            }
        } else {
            if (wall == 0) {
                int place;
                do {
                    place = generator.nextInt(width - 2) + 1;
                } while  (north[place] == Block.DOOR.getId());
                sizes[place][1] = Block.KEY_CHEST.getId();
                locations.put(new Location<>(place,1), Block.KEY_CHEST);
                Chests chest = new Chests(place, 1, true, handle);
                chests_list.add(chest);
            }
            if (wall == 1) {
                int place;
                do {
                    place = generator.nextInt(width - 2) + 1;
                } while  (south[place] == Block.DOOR.getId());
                sizes[place][height - 2] = Block.KEY_CHEST.getId();
                locations.put(new Location<>(place,height-2), Block.KEY_CHEST);
                Chests chest = new Chests(place, height - 2, true, handle);
                chests_list.add(chest);
            }
            if (wall == 2) {
                int place;
                do {
                    place = generator.nextInt(height - 2) + 1;
                } while (east[place] == Block.DOOR.getId());
                sizes[width - 2][place] = Block.KEY_CHEST.getId();
                locations.put(new Location<>(width - 2,place), Block.KEY_CHEST);
                Chests chest = new Chests(width - 2, place, true, handle);
                chests_list.add(chest);
            }
            if (wall == 3) {
                int place;
                do {
                    place=generator.nextInt(height - 2) + 1;
                } while (west[place] == Block.DOOR.getId());
                sizes[1][place] = Block.KEY_CHEST.getId();
                locations.put(new Location<>(1,place), Block.KEY_CHEST);
                Chests chest = new Chests(1, place, true, handle);
                chests_list.add(chest);
            }
        }
    }

    public ArrayList<Chests> getChests_list() {
        return chests_list;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }

    public ArrayList<Torch> getTorches() {
        return torches;
    }

    public ArrayList<Drop> getDrop_list() {
        return drop_list;
    }

    public ArrayList<Enemies> getEnemies_list() {
        return enemies_list;
    }


    public boolean isVisited() {
        return visited;
    }

    public int getHeight() {
        return height;
    }

    public int getIndex() {
        return index;
    }

    public int getWidth() {
        return width;
    }

    public int[] getEast() {
        return east;
    }

    public int[] getNorth() {
        return north;
    }

    public int[] getSouth() {
        return south;
    }

    public int[] getWest() {
        return west;
    }

    public int[][] getSizes() {
        return sizes;
    }

    public void setChests_list(ArrayList<Chests> chests_list) {
        this.chests_list = chests_list;
    }

    public void setDoors(ArrayList<Door> doors) {
        this.doors = doors;
    }

    public void setDrop_list(ArrayList<Drop> drop_list) {
        this.drop_list = drop_list;
    }

    public void updateEast(int index, int value) {
        this.east[index] = value;
    }

    public void setEnemies_list(ArrayList<Enemies> enemies_list) {
        this.enemies_list = enemies_list;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void updateNorth(int index, int value) {
        this.north[index] = value;
    }

    public void updateSizes(int x, int y, int value) {
        this.sizes[x][y] = value;
    }

    public void updateSouth(int index, int value) {
        this.south[index] = value;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public void updateWest(int index, int value) {
        this.west[index] = value;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void addDrops(Drop drop) {
        this.drop_list.add(drop);
    }

    public RoomType getRoomType() {
        return roomType;
    }
}
