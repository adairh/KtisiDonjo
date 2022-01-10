package me.adairh.ktisi.dungeonktisi.Utilities;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Stats.StatsType;

public class Interface {

    private String[] message = new String[5];
    private boolean inventory_shown = true;
    private int[] inventory = new int[10];
    private int[] equipment = new int[5];

    private final Image ring_dex = new Image("assets/armory/ring_dex.png");
    private final Image ring_str = new Image("assets/armory/ring_str.png");
    private final Image ring_def = new Image("assets/armory/ring_def.png");
    private final Image sword_1 = new Image("assets/armory/sword_1.png");
    private final Image sword_2 = new Image("assets/armory/sword_2.png");
    private final Image sword_3 = new Image("assets/armory/sword_3.png");
    private final Image armor_1 = new Image("assets/armory/armor_1.png");
    private final Image armor_2 = new Image("assets/armory/armor_2.png");
    private final Image armor_3 = new Image("assets/armory/armor_3.png");
    private final Image shield_1 = new Image("assets/armory/shield_1.png");
    private final Image shield_2 = new Image("assets/armory/shield_2.png");
    private final Image shield_3 = new Image("assets/armory/shield_3.png");

    private final int size;

    private GameHandle handle;
    private Character character;

    public Interface(GameHandle handle, Character character) {
        this.handle = handle;
        this.character = character;
        this.size = handle.size;
    }

    public void newEvent(String message) {
        this.message[3] = this.message[2];
        this.message[2] = this.message[1];
        this.message[1] = this.message[0];
        this.message[0] = message;
    }

    public void refresh(Pane root){
        statusBar(root);
        statusArea(root);
        buffs(root);
        inventory(root);
        equipment(root);
        IdScrolls(root);
        keySlot(root);
    }

    public void newItem(int item) {
        if (inventory[9] == 0) {
            inventory[9] = inventory[8];
            inventory[8] = inventory[7];
            inventory[7] = inventory[6];
            inventory[6] = inventory[5];
            inventory[5] = inventory[4];
            inventory[4] = inventory[3];
            inventory[3] = inventory[2];
            inventory[2] = inventory[1];
            inventory[1] = inventory[0];
            inventory[0] = item;
        } else
            newEvent("Inventory full!");
    }

    public boolean addStuff(int item, GameHandle handle) {
        if (item < 20) { // ring
            if (equipment[0] == 0) {
                equipment[0] = item;
                return true;
            }
            else if (equipment[1] == 0) {
                equipment[1] = item;
                return true;
            }
            else {
                newEvent("Không thể đeo thêm nhẫn!");
                character.getInventory().setEq_full(true);
                return false;
            }
        } else if (item < 30) { // sword
            if (equipment[3] == 0) {
                equipment[3] = item;
                return true;
            }
            else {
                newEvent("Đang cầm một vũ khí gòii!");
                character.getInventory().setEq_full(true);
                return false;
            }
        } else if (item < 40) {
            if (equipment[2] == 0) {
                equipment[2] = item;
                return true;
            }
            else {
                newEvent("Đã mặc giáp gòi, mặc nữa nóng!");
                character.getInventory().setEq_full(true);
                return false;
            }
        } else if (item < 50) {
            if (equipment[4] == 0) {
                equipment[4] = item;
                return true;
            }
            else {
                newEvent("Đang cầm khiên gòi đây nè!");
                character.getInventory().setEq_full(true);
                return false;
            }
        }
        return false;
    }

    private void statusBar(Pane root) {
        Character character = handle.getMainCharacter();
        Image icons = new Image("assets/gui/status_bar.png");
        int str = character.getBonusStatsPoints(StatsType.STRENGTH);
        int dex = character.getBonusStatsPoints(StatsType.DEXTERITY);
        int def = character.getBonusStatsPoints(StatsType.DEFENSE);
        Label status_bar = new Label(
                "HP: " + character.getHealth_points() + " / " + character.getMax_health()
                        + "\nSTR: " + character.getStatsPoints(StatsType.STRENGTH) + " (" + (str < 0 ? "" : "+") + str  +")"
                        + "\nDEX: " + character.getStatsPoints(StatsType.DEXTERITY) + " (" + (dex < 0 ? "" : "+") + dex  +")"
                        + "\nDEF: " + character.getStatsPoints(StatsType.DEFENSE) + " (" + (def < 0 ? "" : "+") + def +")"
                        + "\nEXP: " + character.getExperience() + " / " + character.getNext_level()
                        + "\nLVL: " + character.getLevel()
                        //+ "\nHunger: " + Character.getHunger()

        );
        status_bar.setStyle("-fx-font-size: 24px;");
        ImageView iv = new ImageView(icons);
        iv.setFitWidth(icons.getWidth()*2);
        iv.setFitHeight(icons.getHeight()*2);
        status_bar.setMinWidth(size * 3 + (int)(size/4 * 2));
        status_bar.setMaxWidth(size * 3 + (int)(size/4 * 2));
        status_bar.setGraphic(iv);
        status_bar.setAlignment(Pos.CENTER);
        status_bar.getStyleClass().add("status_bar");
        status_bar.setPadding(new Insets(20, 10, 20, 10));
        status_bar.setLayoutY(50 + 4 * size + (int)(size/4 * 3) + 50);
        status_bar.setLayoutX(size * 11 + handle.spacing + 50 /*- (int)((size/4)/2)*/);
        //status_bar.layoutXProperty().bind(root.widthProperty()
        //        .subtract(status_bar.widthProperty()).subtract(20));
        root.getChildren().add(status_bar);
    }

    private void buffs(Pane root) {


        Character character = handle.getMainCharacter();
        Image rooted = new Image("assets/gui/buff_root.png");
        Image hunger = new Image("assets/gui/buff_hunger.png");
        Image confuse = new Image("assets/gui/buff_confuse.png");
        Image harm = new Image("assets/gui/buff_harm.png");

        if (character.getHunger() > 400*(1/character.getStepLength())) {
            ImageView buff = new ImageView();
            buff.setImage(hunger);
            buff.setX(350);
            buff.setY(50);
            root.getChildren().add(buff);
        }
        if (handle.getMainCharacter().getCharacter_paralyze() > 0) {
            ImageView buff = new ImageView();
            buff.setImage(rooted);
            buff.setX(315);
            buff.setY(50);
            root.getChildren().add(buff);
        }
        if (handle.getMainCharacter().getCharacter_confused() > 0) {
            ImageView buff = new ImageView();
            buff.setImage(confuse);
            buff.setX(280);
            buff.setY(50);
            root.getChildren().add(buff);
        }
        if (handle.getMainCharacter().getCharacter_harm() > 0) {
            ImageView buff = new ImageView();
            buff.setImage(harm);
            buff.setX(245);
            buff.setY(50);
            root.getChildren().add(buff);
        }
    }

    private void inventory(Pane root) {

        Image empty = new Image("assets/inventory/empty.png");
        /* POTIONS */
        Image green_potion = new Image("assets/inventory/green_potion.png");
        Image aqua_potion = new Image("assets/inventory/aqua_potion.png");
        Image orange_potion = new Image("assets/inventory/orange_potion.png");
        Image yellow_potion = new Image("assets/inventory/yellow_potion.png");
        Image blue_potion = new Image("assets/inventory/blue_potion.png");
        Image red_potion = new Image("assets/inventory/red_potion.png");
        Image purple_potion = new Image("assets/inventory/purple_potion.png");
        /* FOOD */
        Image food_apple = new Image("assets/inventory/food_eggplant.png");
        Image food_steak = new Image("assets/inventory/food_steak.png");
        Image food_mushroom = new Image("assets/inventory/food_mushroom.png");

        if (inventory_shown) {
            for (int row = 0, index = 0; row < 4; row++) {
                for (int column = 0; column < 3; column++) {
                    ImageView eq_item = new ImageView();
                    if (inventory[index] == 0)
                        eq_item.setImage(empty);
                /* POTIONS */
                    if (inventory[index] == 1)
                        eq_item.setImage(yellow_potion);
                    if (inventory[index] == 2)
                        eq_item.setImage(blue_potion);
                    if (inventory[index] == 3)
                        eq_item.setImage(red_potion);
                    if (inventory[index] == 4)
                        eq_item.setImage(purple_potion);
                    if (inventory[index] == 5)
                        eq_item.setImage(green_potion);
                    if (inventory[index] == 6)
                        eq_item.setImage(aqua_potion);
                    if (inventory[index] == 7)
                        eq_item.setImage(orange_potion);
                /* FOOD */
                    if (inventory[index] == 8)
                        eq_item.setImage(food_apple);
                    if (inventory[index] == 9)
                        eq_item.setImage(food_steak);
                    if (inventory[index] == 10)
                        eq_item.setImage(food_mushroom);
                /* RINGS */
                    if (inventory[index] == 11 || inventory[index] == 12)
                        eq_item.setImage(ring_dex);
                    if (inventory[index] == 13 || inventory[index] == 14)
                        eq_item.setImage(ring_str);
                    if (inventory[index] == 15 || inventory[index] == 16)
                        eq_item.setImage(ring_def);
                /* SWORDS */
                    if (inventory[index] == 21)
                        eq_item.setImage(sword_1);
                    if (inventory[index] == 22)
                        eq_item.setImage(sword_2);
                    if (inventory[index] == 23)
                        eq_item.setImage(sword_3);
                /* ARMORS */
                    if (inventory[index] == 31)
                        eq_item.setImage(armor_1);
                    if (inventory[index] == 32)
                        eq_item.setImage(armor_2);
                    if (inventory[index] == 33)
                        eq_item.setImage(armor_3);
                /* SHIELDS */
                    if (inventory[index] == 41)
                        eq_item.setImage(shield_1);
                    if (inventory[index] == 42)
                        eq_item.setImage(shield_2);
                    if (inventory[index] == 43)
                        eq_item.setImage(shield_3);
                    eq_item.setFitHeight(size);
                    eq_item.setFitWidth(size);
                    //eq_item.setY(235 + row * 40);
                    //eq_item.setX(430 + column * 40);
                    //eq_item.setX(root.getWidth() - (eq_item.getFitWidth()) - (int)(size/2) + (column*40));
                    //eq_item.setY(root.getHeight() - (eq_item.getFitHeight()) - (int)(size/2) + (row*40));

                    eq_item.setY(50 + row * (size+(int)(size/4)));
                    eq_item.setX(size * 11 + handle.spacing + 50 + column * (size+(int)(size/4)));

                    if (character.getInventory().isWas_clicked() && character.getInventory().getLast_position() == index) {
                        eq_item.setFitHeight(size+(int)(size/4));
                        eq_item.setFitWidth(size+(int)(size/4));
                        eq_item.setY(50 + row * (size+(int)(size/4)) - (int)((size/4)/2));
                        eq_item.setX(size * 11 + handle.spacing + 50 + column * (size+(int)(size/4)) - (int)((size/4)/2));
                    }

                    root.getChildren().add(eq_item);
                    if (row == 3)
                        break;
                    index += 1;
                }
            }

        }
    }

    private void equipment(Pane root) {

        Image sword_empty = new Image("assets/armory/sword_empty.png");
        Image ring_empty = new Image("assets/armory/ring_empty.png");
        Image armor_empty = new Image("assets/armory/armor_empty.png");
        Image shield_empty = new Image("assets/armory/shield_empty.png");
        Image background = new Image("assets/armory/background.png");

        if (!inventory_shown) {

            ImageView background_image = new ImageView();
            background_image.setImage(background);
            background_image.setFitWidth(background.getWidth() * ( (int)(size/32)) * 1.25 );
            background_image.setFitHeight(background.getHeight() * ( (int)(size/32)) * 1.25 );

            background_image.setY(50);
            background_image.setX(size * 11 + handle.spacing + 50);
            root.getChildren().add(background_image);

            ImageView left_ring = new ImageView();
            if (equipment[0] == 0)
                left_ring.setImage(ring_empty);
            if (equipment[0] == 11 || equipment[0] == 12)
                left_ring.setImage(ring_dex);
            if (equipment[0] == 13 || equipment[0] == 14)
                left_ring.setImage(ring_str);
            if (equipment[0] == 15 || equipment[0] == 16)
                left_ring.setImage(ring_def);
            left_ring.setFitHeight(size);
            left_ring.setFitWidth(size);
            //left_ring.setY(235);
            //left_ring.setX(430);

            left_ring.setY(50 + background_image.getFitHeight()/6);
            left_ring.setX(size * 11 + handle.spacing + 50 + background_image.getFitWidth()/2 - size*1.5);

            if (character.getInventory().isWas_clicked() && character.getInventory().getLast_position() == 0) {
                left_ring.setFitHeight(size+(int)(size/4));
                left_ring.setFitWidth(size+(int)(size/4));
                left_ring.setY(50 + background_image.getFitHeight()/6 - (int)((size/4)/2));
                left_ring.setX(size * 11 + handle.spacing + 50 + background_image.getFitWidth()/2 - size*1.5 - (int)((size/4)/2));
            }
            root.getChildren().add(left_ring);

            ImageView right_ring = new ImageView();
            if (equipment[1] == 0)
                right_ring.setImage(ring_empty);
            if (equipment[1] == 11 || equipment[1] == 12)
                right_ring.setImage(ring_dex);
            if (equipment[1] == 13 || equipment[1] == 14)
                right_ring.setImage(ring_str);
            if (equipment[1] == 15 || equipment[1] == 16)
                right_ring.setImage(ring_def);
            right_ring.setFitHeight(size);
            right_ring.setFitWidth(size);
            right_ring.setY(50 + background_image.getFitHeight()/6);
            right_ring.setX(size * 11 + handle.spacing + 50 + background_image.getFitWidth()/2 + size*0.5);
            if (character.getInventory().isWas_clicked() && character.getInventory().getLast_position() == 1) {
                right_ring.setFitHeight(size+(int)(size/4));
                right_ring.setFitWidth(size+(int)(size/4));
                right_ring.setY(50 + background_image.getFitHeight()/6 - (int)((size/4)/2));
                right_ring.setX(size * 11 + handle.spacing + 50 + background_image.getFitWidth()/2 + size*0.5 - (int)((size/4)/2));
            }
            root.getChildren().add(right_ring);

            ImageView armor = new ImageView();
            if (equipment[2] == 0)
                armor.setImage(armor_empty);
            if (equipment[2] == 31)
                armor.setImage(armor_1);
            if (equipment[2] == 32)
                armor.setImage(armor_2);
            if (equipment[2] == 33)
                armor.setImage(armor_3);
            armor.setFitHeight(size);
            armor.setFitWidth(size);
            armor.setY(50 + background_image.getFitHeight()/6 * 2);
            armor.setX(size * 11 + handle.spacing + 50 + background_image.getFitWidth()/2 - size*0.5);
            if (character.getInventory().isWas_clicked() && character.getInventory().getLast_position() == 2) {
                armor.setFitHeight(size+(int)(size/4));
                armor.setFitWidth(size+(int)(size/4));
                armor.setY(50 + background_image.getFitHeight()/6 * 2 - (int)((size/4)/2));
                armor.setX(size * 11 + handle.spacing + 50 + background_image.getFitWidth()/2 - size*0.5 - (int)((size/4)/2));
            }
            root.getChildren().add(armor);

            ImageView sword = new ImageView();
            if (equipment[3] == 0)
                sword.setImage(sword_empty);
            if (equipment[3] == 21)
                sword.setImage(sword_1);
            if (equipment[3] == 22)
                sword.setImage(sword_2);
            if (equipment[3] == 23)
                sword.setImage(sword_3);
            sword.setFitHeight(size);
            sword.setFitWidth(size);
            sword.setY(50 + background_image.getFitHeight()/6 * 3);
            sword.setX(size * 11 + handle.spacing + 50 + background_image.getFitWidth()/2 - size*1.5);
            if (character.getInventory().isWas_clicked() && character.getInventory().getLast_position() == 3) {
                sword.setFitHeight(size+(int)(size/4));
                sword.setFitWidth(size+(int)(size/4));
                sword.setY(50 + background_image.getFitHeight()/6 * 3 - (int)((size/4)/2));
                sword.setX(size * 11 + handle.spacing + 50 + background_image.getFitWidth()/2 - size*1.5 - (int)((size/4)/2));
            }
            root.getChildren().add(sword);

            ImageView shield = new ImageView();
            if (equipment[4] == 0)
                shield.setImage(shield_empty);
            if (equipment[4] == 41)
                shield.setImage(shield_1);
            if (equipment[4] == 42)
                shield.setImage(shield_2);
            if (equipment[4] == 43)
                shield.setImage(shield_3);
            shield.setFitHeight(size);
            shield.setFitWidth(size);
            shield.setY(50 + background_image.getFitHeight()/6 * 3);
            shield.setX(size * 11 + handle.spacing + 50 + background_image.getFitWidth()/2 + size*0.5);
            if (character.getInventory().isWas_clicked() && character.getInventory().getLast_position() == 4) {
                shield.setFitHeight(size+(int)(size/4));
                shield.setFitWidth(size+(int)(size/4));
                shield.setY(50 + background_image.getFitHeight()/6 * 3 - (int)((size/4)/2));
                shield.setX(size * 11 + handle.spacing + 50 + background_image.getFitWidth()/2 + size*0.5 - (int)((size/4)/2));
            }
            root.getChildren().add(shield);
        }
    }

    private void IdScrolls(Pane root) {
        if (inventory_shown) {
            Image scroll = new Image("assets/gui/scroll.png");
            Label scrollIMG = new Label();
            ImageView iv = new ImageView(scroll);

            iv.setFitHeight(size);
            iv.setFitWidth(size);

            scrollIMG.setGraphic(iv);
            scrollIMG.setAlignment(Pos.CENTER);
            scrollIMG.getStyleClass().add("scrolls");
            scrollIMG.setPadding(new Insets(5));

            scrollIMG.setMinWidth(size);
            scrollIMG.setMaxWidth(size);

            scrollIMG.setMinHeight(size);
            scrollIMG.setMaxHeight(size);

            scrollIMG.setLayoutY(50 + 3 * (size + (int)(size/4)));
            scrollIMG.setLayoutX(size * 11 + handle.spacing + 50 + 2 * (size + (int)(size/4)));

            Label scroll_counter = new Label(""+character.getInventory().getScrolls());
            scroll_counter.setAlignment(Pos.BOTTOM_RIGHT);
            scroll_counter.setStyle("-fx-border: none;-fx-border-radius: 0;-fx-border-width: 0;-fx-background-color: none; -fx-font-size: 16px;");

            scroll_counter.setPadding(new Insets(5));
            scroll_counter.setLayoutY(50 + 3 * (size + (int)(size/4)));
            scroll_counter.setLayoutX(size * 11 + handle.spacing + 50 + 2 * (size + (int)(size/4)));

            root.getChildren().addAll(scrollIMG, scroll_counter);
        }
    }

    private void keySlot(Pane root) {
        if (inventory_shown) {
            Image key;
            if (!handle.getMainCharacter().isHasKey()) {
                key = new Image("assets/gui/key_empty.png");
            } else {
                key = new Image("assets/gui/key_taken.png");
            }
            Label keyIMG = new Label();
            ImageView iv = new ImageView(key);

            keyIMG.setMinWidth(size);
            keyIMG.setMaxWidth(size);

            keyIMG.setMinHeight(size);
            keyIMG.setMaxHeight(size);

            iv.setFitHeight(size);
            iv.setFitWidth(size);

            /*keyIMG.setMinWidth(size);
            keyIMG.setMaxWidth(size);

            keyIMG.setMinHeight(size);
            keyIMG.setMaxHeight(size);*/

            keyIMG.setGraphic(iv);
            keyIMG.setAlignment(Pos.CENTER);
            keyIMG.getStyleClass().add("scrolls");
            keyIMG.setPadding(new Insets(5));
            keyIMG.setStyle("-fx-border-color: none; -fx-border-style: none;-fx-border-radius: 5;-fx-border-width: 0;-fx-background-color: none;");
            //keyIMG.setLayoutY(size*9);
            //keyIMG.layoutXProperty().bind(root.widthProperty()
            //        .subtract(keyIMG.widthProperty()).subtract(size + size));

            keyIMG.setLayoutY(50 + 3 * (size + (int)(size/4)));
            keyIMG.setLayoutX(size * 11 + handle.spacing + 50 + (size + (int)(size/4)));

            root.getChildren().addAll(keyIMG);
        }
    }

    private void statusArea(Pane root) {
        Label status_area = new Label(
                message[2] + "\n"
                        + message[1] + "\n"
                        + message[0]);
        status_area.setAlignment(Pos.CENTER);
        status_area.setPadding(new Insets(10, 50, 10, 50));
        status_area.getStyleClass().add("status_area");
        status_area.setMinWidth(size*11);
        status_area.setMaxWidth(size*11);
        status_area.setLayoutX(handle.spacing);
        status_area.setLayoutY(50+size*11);
        root.getChildren().add(status_area);
    }

    public boolean isInventory_shown() {
        return inventory_shown;
    }

    public int[] getEquipment() {
        return equipment;
    }

    public int[] getInventory() {
        return inventory;
    }

    public String[] getMessage() {
        return message;
    }

    public void updateMessage(int messageSlot, String mess) {
        message[messageSlot] = mess;
    }

    public void updateInventory(int inventorySlot, int value) {
        inventory[inventorySlot] = value;
    }

    public void updateEquipment(int equipmentSlot, int value) {
        equipment[equipmentSlot] = value;
    }

    public void setInventory_shown(boolean inventory_shown) {
        this.inventory_shown = inventory_shown;
    }

}