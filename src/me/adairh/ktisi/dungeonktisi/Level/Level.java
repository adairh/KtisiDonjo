package me.adairh.ktisi.dungeonktisi.Level;

import me.adairh.ktisi.dungeonktisi.DungeonKtisi;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Utilities.Interface;
import me.adairh.ktisi.dungeonktisi.Utilities.StructureGenerator;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class Level {

    private int room_number;
    private ArrayList<Room> rooms_list;
    private GameHandle handle;
    private int id;

    public Level(int id, int room_number, GameHandle handle){
        this.id = id;
        this.room_number = room_number;
        this.handle = handle;
    }




    public void addRooms() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        StructureGenerator structure = handle.getStructure();
        structure.generate(room_number);
        rooms_list = new ArrayList<Room>();
        System.out.println("Sec gen: "+ room_number);
        for (int index = 0; index < room_number; index++) {
            System.out.println("Let's room: "+ index + "/" + room_number);
            Room room = new Room(id, room_number, index, handle);
            rooms_list.add(room);
        }
        Random rand = new Random();
        boolean again = true;
        int roomHasKey;
        do {
            roomHasKey = rand.nextInt(rooms_list.size());
            if (roomHasKey != 0 && roomHasKey != room_number-1){
                again = false;
            }
        } while (again);

        rooms_list.get(roomHasKey).addKeyChest();
    }

    public ArrayList<Room> getRooms_list() {
        return rooms_list;
    }
    public int getRoom_number() {
        return room_number;
    }
}