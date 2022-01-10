package me.adairh.ktisi.dungeonktisi.Utilities;

import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Facing;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Level.Room;
import me.adairh.ktisi.dungeonktisi.LightLevel.LightLevel;
import me.adairh.ktisi.dungeonktisi.LightLevel.LightSpot;
import me.adairh.ktisi.dungeonktisi.Stuff.Block.Torch.Torch;
import me.adairh.ktisi.dungeonktisi.Stuff.Drop;
import me.adairh.ktisi.dungeonktisi.Utilities.IMGLoader.Block;

import java.io.IOException;
import java.util.*;

public class AssetsLoader {


    private Image shadow, drop_bag, hit_0, hit_1, hit_2, punch1, punch2, punch3, sword1, sword2, sword3;
    private Image wooden_doors, wooden_chest, key_chest, stairs_down, stairs_up, locked_stair, torches;
    private Image wall_block, wall_broken_block, column_block, bookshelf;
    private Image wall_plant_up, wall_plant_down, wall_plant_left, wall_plant_right,
            wall_moss_up, wall_moss_down, wall_moss_left, wall_moss_right;
    private Image floor_block, floor_divided, floor_decorated, floor_tile,
            grass_less, grass_up, grass_down, wooden_floor;
    private Image character_left, character_right, character_up, character_down;
    private Image enemy_zombie, enemy_skeleton, enemy_golem, enemy_ghost;
    final int tile_size;

    static final int spotLight = 16;

    static final int min_torch_size = 9;
    static final int max_torch_size = 11;

    static final int basicLight = 14;

    //private int enemy_x_index;
    //private int enemy_y_index;

    private GameHandle handle;

    public AssetsLoader(GameHandle handle) {
        System.out.println("Loading texture...");
        this.handle = handle;
        tile_size   = handle.size;
        //terminalShowing();
    }

    public void load() throws IOException {

        shadow = new Image("assets/gui/shadow.png");
        drop_bag = new Image("assets/inventory/bag.png");

        punch1 = new Image("assets/battle/punch1.png");
        punch2 = new Image("assets/battle/punch2.png");
        punch3 = new Image("assets/battle/punch3.png");

        sword1 = new Image("assets/battle/sword1.png");
        sword2 = new Image("assets/battle/sword2.png");
        sword3 = new Image("assets/battle/sword3.png");

        torches = new Image("assets/action/torch2.png");
    }



    public Pane draw() {

        Character character = handle.getMainCharacter();
        Pane root = new Pane();
        Room room = handle.getLevels_list().get(character.getPresent_level()).get(character.getPresent_room());
        Random generator = new Random();
        int random = generator.nextInt(3);



        /* CAMERA */
        int x_begin, y_begin, x_end, y_end;
        x_begin = (int)character.getX_value() - 5;
        y_begin = (int)character.getY_value() - 5;
        x_end = (int)character.getX_value() + 6;
        y_end = (int)character.getY_value() + 6;
        if ((int)character.getX_value() < 5) {
            x_begin = 0;
            x_end += (5 - (int)character.getX_value());
        }
        if ((int)character.getY_value() < 5) {
            y_begin = 0;
            y_end += (5 - (int)character.getY_value());
        }
        if ((int)character.getX_value() > (room.getWidth() - 6)) {
            x_end = room.getWidth();
            x_begin += (room.getWidth() - (int)character.getX_value() - 6);
        }
        if ((int)character.getY_value() > (room.getHeight() - 6)) {
            y_end = room.getHeight();
            y_begin += (room.getHeight() - (int)character.getY_value() - 6);
        }

        List<Block> stepOn = new ArrayList<>(Arrays.asList(room.getRoomType().getFloors()));

       /* stepOn.add(Block.DOOR);
        stepOn.add(Block.LOCKED_STAIR);
        stepOn.add(Block.STAIR_UP);
        stepOn.add(Block.STAIR_DOWN);*/

        for (int y_tile = y_begin, y_index = 0; y_tile < y_end; y_tile++, y_index++) {
            for (int x_tile = x_begin, x_index = 0; x_tile < x_end; x_tile++, x_index++) {
                //ImageView iV = new ImageView();
                try {
                    int index = room.getSizes()[x_tile][y_tile];
                    Block block = Block.getBlock(index);
                    assert block != null;
                    ImageView iV;

                    /*if (index  >= 44 && index <= 47){
                        iV = Objects.requireNonNull(Block.getBlock(character.getLast_tile())).getImageView();
                        iV.setFitHeight(tile_size);
                        iV.setFitWidth(tile_size);
                        iV.setX(x_index * tile_size + handle.spacing);
                        iV.setY(y_index * tile_size + 50);
                        root.getChildren().add(iV);
                    }
                    if (index  >= 70 && index < 80){
                        iV = Objects.requireNonNull(Objects.requireNonNull(Block.getBlock(checkEnemyTile(x_tile, y_tile, room.getIndex())))).getImageView();
                        iV.setFitHeight(tile_size);
                        iV.setFitWidth(tile_size);
                        iV.setX(x_index * tile_size + handle.spacing);
                        iV.setY(y_index * tile_size + 50);
                        root.getChildren().add(iV);
                    }*/

                        if (x_tile == 0 || y_tile == 0 || y_tile == room.getHeight() - 1 || x_tile == room.getWidth() - 1
                        || Arrays.asList(room.getRoomType().getDecoration()).contains(Block.getBlock(index))) {
                            ImageView baseBlock = new ImageView();
                            baseBlock = (room.getRoomType().getFloors()[0].getImageView());
                            baseBlock.setFitHeight(tile_size);
                            baseBlock.setFitWidth(tile_size);
                            baseBlock.setX(x_index * tile_size + handle.spacing);
                            baseBlock.setY(y_index * tile_size + 50);

                            root.getChildren().add(baseBlock);
                        }

                        if (index < 21 || index > 24) {
                            iV = block.getImageView();
                            if (index == 25 || index == 27) {
                                ImageView baseBlock = new ImageView();
                                baseBlock = (room.getRoomType().getFloors()[0].getImageView());
                                baseBlock.setFitHeight(tile_size);
                                baseBlock.setFitWidth(tile_size);
                                baseBlock.setX(x_index * tile_size + handle.spacing);
                                baseBlock.setY(y_index * tile_size + 50);
                                root.getChildren().add(baseBlock);
                            }

                        }
                        else {
                            iV = Block.WALL.getImageView();
                            iV.setFitHeight(tile_size);
                            iV.setFitWidth(tile_size);
                            iV.setX(x_index * tile_size + handle.spacing);
                            iV.setY(y_index * tile_size + 50);
                            root.getChildren().add(iV);
                            switch (index) {
                                case 21:
                                    iV.setFitHeight(tile_size/2);
                                    break;
                                case 22:
                                    iV.setFitHeight(tile_size/2);
                                    iV.setY(y_index * tile_size + 50 + tile_size/2);
                                    break;
                                case 24:
                                    iV.setFitWidth(tile_size/2);
                                    break;
                                case 23:
                                    iV.setFitWidth(tile_size/2);
                                    iV.setX(x_index * tile_size + handle.spacing + tile_size/2);
                                    break;
                            }

                            iV = block.getImageView((index - 21) * 15, (index - 21 + 1) * 15);
                        }
                        iV.setFitHeight(tile_size);
                        iV.setFitWidth(tile_size);
                        iV.setX(x_index * tile_size + handle.spacing);
                        iV.setY(y_index * tile_size + 50);
                        if (index < 21 || index > 24) {
                            if (!stepOn.contains(block) && index != 25 && index != 27) {
                                if (y_tile == 0) {
                                    iV.setFitHeight(tile_size / 2);
                                } else if (y_tile == room.getHeight() - 1) {
                                    iV.setFitHeight(tile_size / 2);
                                    iV.setY(y_index * tile_size + 50 + tile_size / 2);
                                } else if (x_tile == 0) {
                                    iV.setFitWidth(tile_size / 2);
                                } else if (x_tile == room.getWidth() - 1) {
                                    iV.setFitWidth(tile_size / 2);
                                    iV.setX(x_index * tile_size + handle.spacing + tile_size / 2);
                                }
                            }

                            if (index == 25 || index == 27) {
                                iV.setFitHeight(tile_size);
                                iV.setFitWidth(tile_size);
                                if (y_tile == 1) {
                                    iV.setY(y_index * tile_size + 50);
                                    iV.setX(x_index * tile_size + handle.spacing);
                                } else if (y_tile == room.getHeight() - 2) {
                                    iV.setY(y_index * tile_size + 50);
                                    iV.setX(x_index * tile_size + handle.spacing);
                                } else if (x_tile == 1) {
                                    iV.setX(x_index * tile_size + handle.spacing);
                                    iV.setY(y_index * tile_size + 50);
                                } else if (x_tile == room.getWidth() - 2) {
                                    iV.setX(x_index * tile_size + handle.spacing);
                                    iV.setY(y_index * tile_size + 50);
                                }
                            }
                        }

                        root.getChildren().add(iV);


                        if ((x_tile == 0 && y_tile == 0) || (x_tile == 0 && y_tile == room.getHeight() - 1)){
                            iV = Block.WALL.getImageView();
                            iV.setFitHeight(tile_size);
                            iV.setFitWidth(tile_size/2);
                            iV.setX(x_index * tile_size + handle.spacing);
                            iV.setY(y_index * tile_size + 50);
                            root.getChildren().add(iV);
                        }
                        else if ((x_tile == (room.getWidth() - 1) && y_tile == 0) || (x_tile == (room.getWidth() - 1) && y_tile == room.getHeight() - 1)){
                            iV = Block.WALL.getImageView();
                            iV.setFitHeight(tile_size);
                            iV.setFitWidth(tile_size/2);
                            iV.setX(x_index * tile_size + handle.spacing + tile_size/2);
                            iV.setY(y_index * tile_size + 50);
                            root.getChildren().add(iV);
                        }

                        /* DROP */
                        for (Drop drop : room.getDrop_list()) {
                            if (x_tile == drop.getX_position() && y_tile == drop.getY_position()) {
                                iV = new ImageView();
                                iV.setFitHeight(tile_size);
                                iV.setFitWidth(tile_size);
                                iV.setImage(drop_bag);
                                iV.setX(x_index * tile_size + handle.spacing);
                                iV.setY(y_index * tile_size + 50);
                                root.getChildren().add(iV);
                            }
                        }


                } catch (Exception ignored) {}
            }
        }

        ImageView iv = new ImageView();




        if (!character.isMoving()) {
            if (character.getFacing() == Facing.WEST)
                iv = (Block.CHARACTER_LEFT.getImageView(4 * 3, 4 * 3 + 1  +1));
            if (character.getFacing() == Facing.EAST)
                iv = (Block.CHARACTER_RIGHT.getImageView(4 * 2, 4 * 2 + 1  +1));
            if (character.getFacing() == Facing.NORTH)
                iv = (Block.CHARACTER_UP.getImageView(4, 4 + 1  +1));
            if (character.getFacing() == Facing.SOUTH)
                iv = (Block.CHARACTER_DOWN.getImageView(0, 1  +1));
        } else {
            if (character.getFacing() == Facing.WEST)
                iv = (Block.CHARACTER_LEFT.getImageView(4 * 3 + 1, 4 * 3 + 3  +1));
            if (character.getFacing() == Facing.EAST)
                iv = (Block.CHARACTER_RIGHT.getImageView(4 * 2 + 1, 4 * 2 + 3  +1));
            if (character.getFacing() == Facing.NORTH)
                iv = (Block.CHARACTER_UP.getImageView(4 + 1, 4 + 3  +1));
            if (character.getFacing() == Facing.SOUTH)
                iv = (Block.CHARACTER_DOWN.getImageView(1, 3  +1));
        }
        iv.setFitHeight(tile_size);
        iv.setFitWidth(tile_size);
        iv.setX((character.getX_value() - x_begin) * tile_size + handle.spacing);
        iv.setY((character.getY_value() - y_begin) * tile_size + 50);
        if (character.getCharacterView() != null){
            //root.getChildren().remove(character.getCharacterView());
        }
        root.getChildren().add(iv);
        character.setCharacterView(iv);

        for (Enemies enemies : room.getEnemies_list()) {
            if (enemies.getPositionX() <= x_end-1 && enemies.getPositionY() <= y_end-1 && enemies.getPositionX() >= x_begin+1 && enemies.getPositionY() >= y_begin+1) {
                iv = enemies.getModel().getImageView();
                iv.setFitHeight(tile_size);
                iv.setFitWidth(tile_size);
                iv.setX((enemies.getPositionX() - x_begin) * tile_size + handle.spacing);
                iv.setY((enemies.getPositionY() - y_begin) * tile_size + 50);
                root.getChildren().add(iv);
            }
        }

        HashMap<LightSpot<Integer,Integer>,Byte> liv = new HashMap<>();

        Random rand = new Random();
        for (Torch torch : room.getTorches()) {
            int size = (rand.nextInt(max_torch_size - min_torch_size) + min_torch_size);
            for (int x_light = (torch.getX()) * (tile_size / spotLight) - size, x_index = (torch.getX()-x_begin) * (tile_size / spotLight) - size; x_light < (torch.getX()) * (tile_size / spotLight) + size + (tile_size / spotLight); x_light++, x_index++) {
                for (int y_light = (torch.getY()) * (tile_size / spotLight) - size, y_index = (torch.getY()-y_begin) * (tile_size / spotLight) - size; y_light < (torch.getY()) * (tile_size / spotLight) + size + (tile_size / spotLight); y_light++, y_index++) {
                    double distance = distance(x_light, y_light, torch.getX() * ((int) (tile_size / spotLight)) + (int) (((int) (tile_size / 2) - (spotLight / 2)) / spotLight), torch.getY() * ((int) (tile_size / spotLight)) + (int) (((int) (tile_size / 2) - (spotLight / 2)) / spotLight));
                    if (distance < size) {
                        if (
                                x_light >= x_begin * (tile_size / spotLight)
                                        &&
                                        x_light < x_end * (tile_size / spotLight)
                                        &&
                                        y_light >= y_begin * (tile_size / spotLight)
                                        &&
                                        y_light < y_end * (tile_size / spotLight)
                        ) {

                            double rateOfChange = (double) size / 11;

                            byte value = (byte) Math.round(distance / rateOfChange);


                            if (liv.containsKey(new LightSpot<>(x_index, y_index))) {
                                if (liv.get(new LightSpot<>(x_index, y_index)) > value) {
                                    liv.put(new LightSpot<>(x_index, y_index), value);
                                }
                            } else {
                                liv.put(new LightSpot<>(x_index, y_index), value);
                            }

                        }
                    }
                }
            }
        }
        double maxDistance = basicLight + (double) character.getTorchLevel()/2;
        int extraSize = rand.nextInt(2);
        for (int x_light = x_begin*(tile_size/spotLight), x_index = 0; x_light < x_end*(tile_size/spotLight); x_light++, x_index++) {
            for (int y_light = y_begin*(tile_size/spotLight), y_index = 0; y_light < y_end*(tile_size/spotLight); y_light++, y_index++) {

                double distanceAVG = distance(x_light, y_light, character.getX_value() * ((int)(tile_size/spotLight)) + (int)(((int)(tile_size/2) - (spotLight/2))/spotLight), character.getY_value() * ((int)(tile_size/spotLight)) + (int)(((int)(tile_size/2) - (spotLight/2))/spotLight));
                double distance = extraSize + distanceAVG;
                double rateOfChange = maxDistance / 11;

                byte value = (byte) Math.round(distance / rateOfChange);

                if (liv.containsKey(new LightSpot<>(x_index,y_index))) {
                    if (liv.get(new LightSpot<>(x_index,y_index)) > value){
                        liv.put(new LightSpot<>(x_index,y_index), value);
                    }
                } else {
                    liv.put(new LightSpot<>(x_index,y_index), value);
                }
            }
        }


        for (LightSpot<Integer,Integer> i : liv.keySet()) {
            byte value = liv.get(i);
            int x_index = i.key1;
            int y_index = i.key2;
            ImageView V = new ImageView();
            Image blur = LightLevel.DARK.getImage();
            V.setImage(blur);
            V.setFitHeight((int)(spotLight));
            V.setFitWidth((int)(spotLight));
            V.setX(x_index * spotLight + handle.spacing);
            V.setY(y_index * spotLight + 50);
            byte blurLevel = (byte) (5 - (value - 5));
            V.setOpacity(value * .1);

            root.getChildren().add(V);
        }

        ImageView iV = new ImageView();
        iV.setFitHeight(tile_size * 11);
        iV.setFitWidth(tile_size * 11);
        iV.setImage(shadow);
        iV.setX(handle.spacing);
        iV.setY(50);
        root.getChildren().add(iV);

        int weapon = character.getInterface().getEquipment()[3];

        /* ATTACK MARK */
        if (character.isAttacking()) {
            //System.out.println("hitting");
            for (Enemies e : handle.getBattle().getTargets()) {
                iV = new ImageView();
                if (random == 0)
                    iV.setImage(weapon == 0 ? punch1 : sword1);
                if (random == 1)
                    iV.setImage(weapon == 0 ? punch2 : sword2);
                if (random == 2)
                    iV.setImage(weapon == 0 ? punch3 : sword3);
                iV.setFitHeight(tile_size);
                iV.setFitWidth(tile_size);
                iV.setX((e.getPositionX() - x_begin) * tile_size + handle.spacing);
                iV.setY((e.getPositionY() - y_begin) * tile_size + 50);
                root.getChildren().add(iV);
            }
        }

        try {
            character.getInterface().refresh(root);
            handle.getActions().refresh(root);
        } catch (Exception ignored) {}

        return root;
    }


    private boolean isNearTorch(Room room, int x, int y){
        Random random = new Random();
        for (Torch torch : room.getTorches()){
            if (distance(x, y,
                    torch.getX()*((int)(tile_size/spotLight))+(int)(((int)(tile_size/2) - (spotLight/2))/spotLight),
                    torch.getY()*((int)(tile_size/spotLight))+(int)(((int)(tile_size/2) - (spotLight/2))/spotLight)) < 10/*(random.nextInt(max_torch_size-min_torch_size)+min_torch_size)*/){
                return true;
            }
        }
        return false;
    }




    private double distance(double x, double y, double x1, double y1) {
        double xGap = Math.abs((x1) - x);
        double yGap = Math.abs((y1) - y);

        return Math.sqrt(xGap * xGap + yGap * yGap);
    }

    private Image background(int last_tile) {
        Character character = handle.getMainCharacter();
        if (last_tile == 10)
            return floor_block;
        if (last_tile == 11)
            return floor_divided;
        if (last_tile == 12)
            return floor_decorated;
        if (last_tile == 13)
            return floor_tile;
        if (last_tile == 14)
            return wooden_floor;
        if (last_tile == 15)
            return grass_up;
        if (last_tile == 16)
            return grass_down;
        if (last_tile == 17)
            return grass_less;
        if (last_tile == 29)
            return stairs_down;
        if (last_tile == 30)
            return stairs_up;
        else
            return floor_block;
    }

    private void terminalShowing() {
        Character character = handle.getMainCharacter();
        for (int index = 0; index < handle.getRoomNumber(character.getPresent_level()); index++) {
            Room room = handle.getLevels_list().get(character.getPresent_level()).get(index);
            System.out.println("==================[Level "+character.getPresent_level()+" Room "+room.getIndex()+"]=================");
            for (int y = 0; y < room.getHeight(); y++) {
                for (int x = 0; x < room.getWidth(); x++) {
                    System.out.print(room.getSizes()[x][y]+ " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}