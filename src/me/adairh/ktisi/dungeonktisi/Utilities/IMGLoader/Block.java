package me.adairh.ktisi.dungeonktisi.Utilities.IMGLoader;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import me.adairh.ktisi.dungeonktisi.Utilities.ObjectImage;

import java.util.Random;

public enum Block {
    WALL(88, new Image("assets/wall/brick.png"), "wall"),
    WALL_CRACKED(89, new Image("assets/wall/brick_broken.png"), "cracked_wall"),
    
    WALL_PLANT_UP(90, new Image("assets/wall/brick_plant_u.png"), "wall_plant_up"),
    WALL_PLANT_DOWN(91, new Image("assets/wall/brick_plant_d.png"), "wall_plant_down"),
    WALL_PLANT_LEFT(92, new Image("assets/wall/brick_plant_l.png"), "wall_plant_left"),
    WALL_PLANT_RIGHT(93, new Image("assets/wall/brick_plant_r.png"), "wall_plant_right"),
    
    WALL_MOSS_UP(94, new Image("assets/wall/brick_moss_u.png"), "wall_moss_up"),
    WALL_MOSS_DOWN(95, new Image("assets/wall/brick_moss_d.png"), "wall_moss_down"),
    WALL_MOSS_LEFT(96, new Image("assets/wall/brick_moss_l.png"), "wall_moss_left"),
    WALL_MOSS_RIGHT(97, new Image("assets/wall/brick_moss_r.png"), "wall_moss_right"),
    
    COLUMN(87, new Image("assets/wall/column.png"), "column"),
    BOOKSHELF(81,new Image("assets/wall/bookshelf.png"), "bookshelf"),
    
    FLOOR(10, new Image("assets/floor/floor/floor_block.png"),"floor_block"),
    FLOOR_DIVIDED(11, new Image("assets/floor/floor/floor_divided.png"),"floor_divided"),
    FLOOR_DECORATED(12, new Image("assets/floor/floor/floor_decorated.png"), "floor_decorated"),
    FLOOR_TILE(13, new Image("assets/floor/floor/floor_tile.png"), "floor_tile"),

    WOOD(14, new Image("assets/floor/wood/wooden_floor.png"), "wood"),
    WOOD2(18, new Image("assets/floor/wood/wood2.png"), "wood2"),
    WOOD3(19, new Image("assets/floor/wood/wood3.png"), "wood3"),

    GRASS(15, new Image("assets/floor/grass/grass_less.png"), "grass"),
    GRASS_UP(16, new Image("assets/floor/grass/grass_up.png"), "grass_up"),
    GRASS_DOWN(17, new Image("assets/floor/grass/grass_down.png"), "grass_down"),
    
    DOOR(20, new Image("assets/action/doors.png"), "door"),
    CHEST(25, new Image("assets/action/chest.png"), "chest"),
    KEY_CHEST(27, new Image("assets/action/KeyChest.png"), "key_chest"),
    
    STAIR_DOWN(29, new Image("assets/action/stairs_down.png"), "stair_down"),
    STAIR_UP(30, new Image("assets/action/stairs_up.png"), "stair_up"),
    LOCKED_STAIR(26, new Image("assets/action/locked_stair.png"), "locked_stair"),

    CHARACTER_LEFT(44, new Image("assets/character/newCharacter.png"), "character_left", 4, 4),
    CHARACTER_RIGHT(45, new Image("assets/character/newCharacter.png"), "character_right", 4, 4),
    CHARACTER_UP(46, new Image("assets/character/newCharacter.png"), "character_up", 4, 4),
    CHARACTER_DOWN(47, new Image("assets/character/newCharacter.png"), "character_down", 4, 4),

    ZOMBIE(70, new Image("assets/enemies/zombie.png"), "zombie"),
    SKELETON(71, new Image( "assets/enemies/skeleton.png"), "skeleton"),
    GOLEM(72, new Image( "assets/enemies/golem.png"), "golem"),
    GHOST(73, new Image("assets/enemies/ghost.png"), "ghost"),

    COBBLESTONE(101, new Image("assets/floor/cobble/cobble.png"), "cobblestone"),
    COBBLESTONE_DEEP(102, new Image("assets/floor/cobble/cobbled_deep.png"), "cobblestone_deep"),

    PURPLE_STAINED_GLASS( 105, new Image("assets/floor/purple/purple_stained_glass.png"), "purple_stained_glass"),
    PURPLE_TERRACOTTA( 106, new Image("assets/floor/purple/purple_terracotta.png"), "purple_terracotta"),
    PURPLE_WOOL( 107, new Image("assets/floor/purple/purple_wool.png"), "purple_wool"),
    PURPLE_CONCRETE_POWER( 108, new Image("assets/floor/purple/purple_concrete_powder.png"), "purple_concrete_powder"),
    PURPLE_CONCRETE( 109, new Image("assets/floor/purple/purple_concrete.png"), "purple_concrete"),

    CYAN_STAINED_GLASS( 115, new Image("assets/floor/cyan/cyan_stained_glass.png"), "cyan_stained_glass"),
    CYAN_TERRACOTTA( 116, new Image("assets/floor/cyan/cyan_terracotta.png"), "cyan_terracotta"),
    CYAN_WOOL( 117, new Image("assets/floor/cyan/cyan_wool.png"), "cyan_wool"),
    CYAN_CONCRETE_POWER( 118, new Image("assets/floor/cyan/cyan_concrete_powder.png"), "cyan_concrete_powder"),
    CYAN_CONCRETE( 119, new Image("assets/floor/cyan/cyan_concrete.png"), "cyan_concrete"),
    
    GREEN_STAINED_GLASS( 120, new Image("assets/floor/green/green_stained_glass.png"), "green_stained_glass"),
    GREEN_TERRACOTTA( 121, new Image("assets/floor/green/green_terracotta.png"), "green_terracotta"),
    GREEN_WOOL( 122, new Image("assets/floor/green/green_wool.png"), "green_wool"),
    GREEN_CONCRETE_POWER( 123, new Image("assets/floor/green/green_concrete_powder.png"), "green_concrete_powder"),
    GREEN_CONCRETE( 124, new Image("assets/floor/green/green_concrete.png"), "green_concrete"),

    QUARTZ_1(110, new Image("assets/floor/quartz/quartz1.png"), "quartz_1"),
    QUARTZ_2(111, new Image("assets/floor/quartz/quartz2.png"), "quartz_2"),
    QUARTZ_3(112, new Image("assets/floor/quartz/quartz3.png"), "quartz_3"),

    TORCH_A(21, new Image("assets/action/torch2.png"), "torch_a", 15, 4),
    TORCH_B(22, new Image("assets/action/torch2.png"), "torch_b", 15, 4),
    TORCH_C(23, new Image("assets/action/torch2.png"), "torch_c", 15, 4),
    TORCH_D(24, new Image("assets/action/torch2.png"), "torch_d", 15, 4)

    ;


    private int id;
    private Image image;
    private String name;
    private int column;
    private int row;
    
    Block(int id, Image image, String name){
        this.id = id;
        this.image = image;
        this.name = name;
        this.column = 1;
        this.row = 1;
    }
    
    Block(int id, Image image, String name, int column, int row){
        this.id = id;
        this.image = image;
        this.name = name;
        this.column = column;
        this.row = row;
    }

    private String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    private Image getImageSource() {
        return image;
    }
    
    private ObjectImage getSprite() {
        return new ObjectImage(getImageSource(), column, row);
    }
    
    public ImageView getImageView(int from, int to) {
        Random ran = new Random();
        ObjectImage iv = getSprite();
        iv.setViewport(iv.getClips()[ran.nextInt(to-from)+from]);
        return iv;
    }

    public ImageView getImageView() {
        ImageView iv = new ImageView();
        iv.setImage(getImageSource());
        return iv;
    }

    public static Block getBlock(int id){
        for (Block block : values()){
            if (id == block.getId()){
                return block;
            }
        }
        return null;
    }

}
