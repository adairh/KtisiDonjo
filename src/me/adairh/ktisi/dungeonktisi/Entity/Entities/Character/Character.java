package me.adairh.ktisi.dungeonktisi.Entity.Entities.Character;

import javafx.scene.image.ImageView;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Level.Room;
import me.adairh.ktisi.dungeonktisi.Level.RoomType;
import me.adairh.ktisi.dungeonktisi.Sound.SoundEffect;
import me.adairh.ktisi.dungeonktisi.Stats.StatsType;
import me.adairh.ktisi.dungeonktisi.Stuff.Block.Door.Door;
import me.adairh.ktisi.dungeonktisi.Stuff.Block.Treasure.Chests;
import me.adairh.ktisi.dungeonktisi.Stuff.Drop;
import me.adairh.ktisi.dungeonktisi.Stuff.Inventory;
import me.adairh.ktisi.dungeonktisi.Task.CharacterTask;
import me.adairh.ktisi.dungeonktisi.Utilities.IMGLoader.Block;
import me.adairh.ktisi.dungeonktisi.Utilities.Interface;

import java.util.ListIterator;
import java.util.Random;

public class Character {

    private static Random random = new Random();

    private  double x_value, y_value;
    private  int present_room;
    private  int present_level;
    private  int next_level;
    private  int level;
    private  int hunger;

    private  boolean is_dead = false;
    private  boolean is_attacking = false;
    private  boolean action_made = false;

    private  boolean isMoving = false;

    //private int movingDelay;

    private  int max_health;
    private  int health_points;
    private  int experience;

    private int[][] statField;
    private int[][] bonusStatField;

    private int character_paralyze = 0;
    private int character_confused = 0;
    private int character_harm = 0;
    private int character_full = 0;

    private Inventory inventory;
    private Interface interf;


    private boolean hasKey = false;

    private GameHandle handle;

    private int torchLevel = 20;

    private  double stepLength;

    private double defaultAttackRange;

    private ImageView characterView;

    private Facing facing;

    private boolean win = false;

    
    public Character(GameHandle handle,
                     Facing facing,
                     int last_tile, int present_level, int present_room,
                     int max_health, int health_points, double defaultAttackRange,
                     int experience, int next_level, int level, int hunger, double stepLength,//int movingDelay,
                     int... statValue

    ) {
        this.stepLength     = stepLength;

        this.facing         = facing;

        this.inventory      = new Inventory(handle, this);
        this.interf         = new Interface(handle, this);

        this.statField = new int[StatsType.availableStatTypes()][1];
        this.bonusStatField = new int[StatsType.availableStatTypes()][1];

        for (int i = 0; i < statValue.length; i++){
            statField[i][0] = statValue[i];
        }
        if (statValue.length < StatsType.availableStatTypes()){
            for (int i = statValue.length; i < StatsType.availableStatTypes(); i++){
                statField[i][0] = StatsType.getStatsById(i).getDefaultValue();
            }
        }
        this.handle             = handle;
        this.present_level      = present_level;
        this.present_room       = present_room;

        this.max_health         = max_health;
        this.health_points      = health_points;

        this.experience         = experience;
        this.next_level         = next_level;
        this.level              = level;
        this.hunger             = hunger;
        //this.movingDelay      = movingDelay;
        this.defaultAttackRange = defaultAttackRange;
        Room room               = handle.getLevels_list().get(0).get(0);
        this.x_value            = Math.round((double)room.getWidth() / 2) - 1;
        this.y_value            = Math.round((double)room.getHeight() / 2) - 1;
        //room.updateSizes(x_value,y_value,47);
        room.setVisited(true);
        getInterface().updateInventory(0 , 9);

    }


    public void modify(double x, double y) {

        double additionX = 0;
        double additionY = 0;

        Room room = handle.getLevels_list().get(present_level).get(present_room);
        getInventory().setWas_clicked(false);

        int step_x = (int)( ((x_value + x) - (int)(x_value + x) > 0.5 ? (int)(x_value + x) + 1 : (int)(x_value + x)) + additionX );
        int step_y = (int)( ((y_value + y) - (int)(y_value + y) > 0.5 ? (int)(y_value + y) + 1 : (int)(y_value + y)) + additionY );

        //int step_x = x <= 0 ? (int) (x_value + x) : (int) (x_value + x) + 1;
        //int step_y = y <= 0 ? (int) (y_value + y) : (int) (y_value + y) + 1;

        //double step_x = (x_value + x);
        //double step_y = (y_value + y);

        System.out.println("Facing: " + facing + " relate: " + step_x + "-" + step_y + " is " + room.getSizes()[step_x][step_y]);

        double addedX = (x_value + x >= handle.getLevels_list().get(getPresent_level()).get(getPresent_room()).getWidth() - 2) ? 0 :
                (x_value + x <= 1) ? 0 : x;
        double addedY = (y_value + y >= handle.getLevels_list().get(getPresent_level()).get(getPresent_room()).getHeight() - 2) ? 0 :
                (y_value + y <= 1) ? 0 : y;


        if (room.getSizes()[step_x][step_y] >= 21 && room.getSizes()[step_x][step_y] <= 24) {// 21-24 = torch

        }
        else if (room.getSizes()[step_x][step_y] == 20) {// 20 = door
            nextRoom(step_x, step_y);
            SoundEffect.DOOR_OPEN.play();
        }
        else if (room.getSizes()[step_x][step_y] == 26) {// 26 = locked down stair
            if (isHasKey()) {
                hasKey = false;
                room.updateSizes(step_x, step_y, 29);
                SoundEffect.GATE_OPEN.play();
                getInterface().newEvent("Mở khóa thành công, hãy bước vào để đến tầng tiếp theo");
            } else {
                getInterface().newEvent("Cửa đã bị khóa, hãy đi tìm chìa khóa, nó ở đâu đó trong tầng!");
                SoundEffect.GATE_LOCKED.play();
            }
        }
        else if (room.getSizes()[step_x][step_y] == 29)// 29 = down stair
            nextLevel();
        else if (room.getSizes()[step_x][step_y] == 30)// 30 = up stair
            prevLevel();
        else if (room.getSizes()[step_x][step_y] == 25 || room.getSizes()[step_x][step_y] == 27) { // 25 = chest 27 = key chest
            for (Chests chest : room.getChests_list()) {
                if (chest.getX_position() == step_x && chest.getY_position() == step_y) {
                    chest.checkTreasure();
                }
            }
        }
        else if (room.getSizes()[step_x][step_y] < 70 || room.getSizes()[step_x][step_y] > 99) {
            if (getCharacter_paralyze() == 0) {
                RoomType rt = room.getRoomType();
                    rt.getRandomSound().play();


                ListIterator<Drop> iterator = room.getDrop_list().listIterator();
                while (iterator.hasNext()) {
                    Drop drop = iterator.next();
                    if (drop.getX_position() == step_x && drop.getY_position() == step_y) {
                        if (getInterface().getInventory()[9] == 0) {
                            drop.checkTreasure();
                            iterator.remove();
                        } else
                            getInterface().newEvent("Túi đồ đã đầy!");
                    }
                }
            } else {}

            x_value += x;
            y_value += y;
            action_made = true;
        }


        System.out.println("Current Location: " + x_value + ":" + y_value);

    }


    private void nextRoom(int x, int y) {

        Room former_room = handle.getLevels_list().get(present_level).get(present_room);
        for (Door door : former_room.getDoors()) {
            if (door.getX() == x && door.getY() == y) {
                //former_room.updateSizes(x_value,y_value,last_tile);
                int whereIWas = present_room;
                present_room = door.getWhere();
                Room new_room = handle.getLevels_list().get(present_level).get(present_room);
                if (whereIWas == present_room)
                    getInterface().newEvent("Gì dị trùi, cùng một phòng màa.");
                else if (new_room.isVisited())
                    getInterface().newEvent("Ủa chỗ này đi gòi...");
                else
                    getInterface().newEvent("UwU phòng mới kaka...");
                new_room.setVisited(true);

                System.out.println("Access room " + new_room.getRoomType().getName());


                //EntityTask.clearEnemies();
                /*for (Enemies enemies : new_room.getEnemies_list()) {
                    if (enemies.isAlive()) {
                        EntityTask.addEnemy(enemies);
                        System.out.println(enemies.getType());
                    }
                }*/



                for (Door newdoor : handle.getLevels_list().get(present_level).get(present_room).getDoors()) {
                    if (newdoor.getWhere() == whereIWas) {
                        Room newroom = handle.getLevels_list().get(present_level).get(present_room);
                        //last_tile = newroom.getSizes()[newdoor.getPosx()][newdoor.getPosy()];
                        if (newdoor.getWall() == 0) {
                            //newroom.updateSizes(newdoor.getPosx(),newdoor.getPosy(), 47);
                            facing = Facing.SOUTH;
                            x_value = newdoor.getPosx();
                            y_value = newdoor.getPosy() + stepLength;
                        }
                        if (newdoor.getWall() == 1) {
                            //newroom.updateSizes(newdoor.getPosx(),newdoor.getPosy(), 46);
                            facing = Facing.NORTH;
                            x_value = newdoor.getPosx();
                            y_value = newdoor.getPosy() - stepLength;
                        }
                        if (newdoor.getWall() == 2) {
                            //newroom.updateSizes(newdoor.getPosx(),newdoor.getPosy(), 44);
                            facing = Facing.WEST;
                            x_value = newdoor.getPosx() - stepLength;
                            y_value = newdoor.getPosy();
                        }
                        if (newdoor.getWall() == 3) {
                            //newroom.updateSizes(newdoor.getPosx(),newdoor.getPosy(), 45);
                            facing = Facing.EAST;
                            x_value = newdoor.getPosx() + stepLength;
                            y_value = newdoor.getPosy();
                        }
                    }
                }
            }
        }
    }

    private void nextLevel() {
        if (present_level < handle.getLevel_number() - 1) {
            //handle.getLevels_list().get(present_level).get(present_room).updateSizes(x_value, y_value, last_tile);
            present_level += 1;
            present_room = 0;
            //last_tile = 30;
            Room room = handle.getLevels_list().get(present_level).get(present_room);
            x_value = room.getWidth() / 2 - 1;
            y_value = room.getWidth() / 2;
            //room.updateSizes(x_value, y_value, 47);
            getInterface().newEvent("U chà tầng " + (present_level + 1));
        } else
            win = true;
            getInterface().newEvent("Bạn đã chiến thắng!.");//end
    }

    private void prevLevel() {
        if (present_level > 0) {
            //handle.getLevels_list().get(present_level).get(present_room).updateSizes(x_value,y_value,last_tile);
            present_level -= 1;
            present_room = handle.getRoomNumber(present_level) - 1;
            //last_tile = 29;
            Room room = handle.getLevels_list().get(present_level).get(present_room);
            x_value = room.getWidth()  / 2 - 1;
            y_value = room.getWidth()  / 2;
            //room.updateSizes(x_value,y_value,47);
            getInterface().newEvent("Tầng này đi gòi.");
        } else
            getInterface().newEvent("Hong còn đường dề đâuu.");
    }

    public  void hunger() {
        hunger += 1;
        if (hunger == 100 * (1/stepLength))
            getInterface().newEvent("Hmm, vẫn còn no.");
        if (hunger == 200 * (1/stepLength))
            getInterface().newEvent("Dạ dày zẫn tốt.");
        if (hunger == 300 * (1/stepLength))
            getInterface().newEvent("Hmm, kiếm gì ăn hong ta.");
        if (hunger == 400 * (1/stepLength))
            getInterface().newEvent("Đói quá gòiii!");
        if (hunger == 500 * (1/stepLength))
            getInterface().newEvent("Sắp chết đóiii!");
        if (hunger > 500 * (1/stepLength)) {
            modifyHealth(-1);
        }
    }

    public void modifyHealth(int points) {
        health_points += points;
        if (health_points > max_health)
            health_points = max_health;
        if (health_points <= 0) {
            health_points = 0;
            System.out.println("die");
            is_dead = true;
            CharacterTask.action(handle);
        }
    }

    public void buffs() {
        if (getCharacter_harm() > 0) {
            modifyHealth(-5);
            setCharacter_harm(getCharacter_harm() - 1);
        }
        if (getCharacter_full() > 0) {
            modifyHealth(1);
            setCharacter_full(getCharacter_full() - 1);
        }
        if (getCharacter_paralyze() > 0) {
            setCharacter_paralyze(getCharacter_paralyze() - 1);
        }
        if (getCharacter_confused() > 0) {
            setCharacter_confused(getCharacter_confused() - 1);
        }
    }

    public  void experience(int experience_points) {
        experience += experience_points;
        if (experience >= next_level) {
            level += 1;
            getInterface().newEvent("Bạn đã đạt cấp " + level);
            experience = experience - next_level;
            next_level += 50;
            statField[StatsType.STRENGTH.getId()][0]    += 1;
            statField[StatsType.DEXTERITY.getId()][0]   += 1;
            statField[StatsType.DEFENSE.getId()][0]     += 1;
            /*strength_points += 1;
            dexterity_points += 1;
            defence_points += 1;*/
            max_health += 10;
            modifyHealth(50 + 10 * level);
        }
    }

    public boolean isHasKey() {
        return hasKey;
    }

    public  boolean isAction_made() {
        return action_made;
    }

    public  boolean isAttacking() {
        return is_attacking;
    }

    public  boolean isDead() {
        return is_dead;
    }



    public  int getExperience() {
        return experience;
    }

    public  int getHealth_points() {
        return health_points;
    }

    public  int getHunger() {
        return hunger;
    }

    public  int getLevel() {
        return level;
    }

    public  int getMax_health() {
        return max_health;
    }

    public  int getNext_level() {
        return next_level;
    }

    public  int getPresent_level() {
        return present_level;
    }

    public  int getPresent_room() {
        return present_room;
    }

    public  int getStatsPoints(StatsType type) {
        return statField[type.getId()][0];
    }

    public  int getBonusStatsPoints(StatsType type) {
        return bonusStatField[type.getId()][0];
    }


    public  double getX_value() {
        return x_value;
    }

    public  double getY_value() {
        return y_value;
    }

    public  void setAction_made(boolean action_made) {
        this.action_made = action_made;
    }

    public  void setExperience(int experience) {
        this.experience = experience;
    }

    public  void setHealth_points(int health_points) {
        this.health_points = health_points;
    }

    public  void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public  void setIs_attacking(boolean is_attacking) {
        this.is_attacking = is_attacking;
    }

    public  void setIs_dead(boolean is_dead) {
        this.is_dead = is_dead;
    }

    public  void setLevel(int level) {
        this.level = level;
    }

    public  void setMax_health(int max_health) {
        this.max_health = max_health;
    }

    public  void setNext_level(int next_level) {
        this.next_level = next_level;
    }

    public  void setPresent_level(int present_level) {
        this.present_level = present_level;
    }

    public  void setPresent_room(int present_room) {
        this.present_room = present_room;
    }

    public  void setStatsPoints(StatsType type, int value) {
        this.statField[type.getId()][0] = value;
    }

    public  void setBonusStatsPoints(StatsType type, int value) {
        this.bonusStatField[type.getId()][0] = value;
    }
    public  void addBonusStatsPoints(StatsType type, int value) {
        this.bonusStatField[type.getId()][0] += value;
    }

    public  void setX_value(int x_value) {
        this.x_value = x_value;
    }

    public  void setY_value(int y_value) {
        this.y_value = y_value;
    }

    public  boolean isMoving() {
        return isMoving;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    public int getCharacter_paralyze() {
        return character_paralyze;
    }

    public int getCharacter_harm() {
        return character_harm;
    }

    public int getCharacter_full() {
        return character_full;
    }

    public int getCharacter_confused() {
        return character_confused;
    }

    public void setCharacter_paralyze(int character_paralyze) {
        this.character_paralyze = character_paralyze;
    }

    public void setCharacter_harm(int character_harm) {
        this.character_harm = character_harm;
    }

    public void setCharacter_full(int character_full) {
        this.character_full = character_full;
    }

    public void setCharacter_confused(int character_confused) {
        this.character_confused = character_confused;
    }

    public void setHasKey(boolean hasKey) {
        this.hasKey = hasKey;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public Interface getInterface() {
        return interf;
    }

    public int getTorchLevel() {
        return torchLevel;
    }

    public void setTorchLevel(int torchLevel) {
        this.torchLevel = torchLevel;
    }

    public Facing getFacing() {
        return facing;
    }

    public void setFacing(Facing facing) {
        this.facing = facing;
    }

    public ImageView getCharacterView() {
        return characterView;
    }

    public void setCharacterView(ImageView characterView) {
        this.characterView = characterView;
    }

    public double getStepLength() {
        return stepLength;
    }

    public double getDefaultAttackRange() {
        return defaultAttackRange;
    }

    public boolean isWin() {
        return win;
    }


}