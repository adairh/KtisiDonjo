package me.adairh.ktisi.dungeonktisi.Entity.Motion;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import me.adairh.ktisi.dungeonktisi.Battle.AttackStyle;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Facing;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Level.Room;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.Child.Weapon;
import me.adairh.ktisi.dungeonktisi.Stuff.Equipments.EquipmentObject;
import me.adairh.ktisi.dungeonktisi.Stuff.Inventory;
import me.adairh.ktisi.dungeonktisi.Utilities.AssetsLoader;

import java.util.Objects;

public class Motion {

    private Scene scene;
    private AssetsLoader assets;
    private Stage stage;
    private GameHandle handle;

    public Motion(GameHandle handle, Scene scene, AssetsLoader assets, Stage stage) {
        this.handle = handle;
        this.scene = scene;
        this.assets = assets;
        this.stage = stage;
        keyHandler();
    }

    private double distance(Enemies e, Character character) {
        return Math.sqrt((Math.pow(e.getPositionX() - character.getX_value(), 2) + Math.pow(e.getPositionY() - character.getY_value(), 2)));
    }

    final boolean[] called = {false};

    private void keyHandler() {

        Character character = handle.getMainCharacter();
        Inventory inventory = character.getInventory();


        scene.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.A || event.getCode() == KeyCode.W || event.getCode() == KeyCode.S || event.getCode() == KeyCode.D) {
                character.setIsMoving(false);
            }
        });



        scene.setOnKeyPressed(event -> {
           double x = character.getX_value();
           double y = character.getY_value();

           int xFacing = (int) x;
           int yFacing = (int) y;

           Room room = handle.getLevels_list().get(character.getPresent_level()).get(character.getPresent_room());


            switch (character.getFacing()) {
                case WEST: xFacing -= ((x > 0) ? 1 : 0); break;
                case EAST: xFacing += ((x < room.getWidth()-1) ? 1 : 0); break;
                case NORTH: yFacing -= ((y > 0) ? 1 : 0); break;
                case SOUTH: yFacing += ((y < room.getHeight()-1) ? 1 : 0); break;
            }

            if (xFacing < 0) {
                xFacing = 0;
            } else if (xFacing >= room.getWidth()) {
                xFacing = room.getWidth() - 1;
            }
            if (yFacing < 0) {
                yFacing = 0;
            } else if (yFacing >= room.getHeight()) {
                yFacing = room.getHeight() - 1;
            }
            /*switch (pose) {
                case 44: xFacing -= 1; break;
                case 45: xFacing += 1; break;
                case 46: yFacing -= 1; break;
                case 47: yFacing += 1; break;
            }*/

            if (character.isDead() || character.isWin()) {
                return;
            } else {
                if (event.getCode() == KeyCode.J) {
                    int weapon = character.getInterface().getEquipment()[3];
                    if (
                            !EquipmentObject.isEquipment(weapon) || (
                            EquipmentObject.isEquipment(weapon)
                            && (EquipmentObject.getEquipment(weapon) instanceof Weapon)
                            && ((Weapon) Objects.requireNonNull(EquipmentObject.getEquipment(weapon))).getAttackStyle() == AttackStyle.CLOSE
                            )
                    ){
                        double range = character.getDefaultAttackRange();
                        if (weapon > 0) {
                            Weapon eo = (Weapon) Weapon.getEquipment(weapon);
                            assert eo != null;
                            range     = eo.radius();
                        }
                        enemy: for (Enemies en : room.getEnemies_list()) {
                            if (distance(en, character) <= range) {
                                double distanceX    = Math.abs(x - en.getPositionX());
                                double gapY         = Math.tan(Math.toRadians(30)) * distanceX;
                                switch (character.getFacing()) {
                                    case WEST:
                                        if (en.getPositionX() <= x) {
                                            if (en.getPositionY() < y + gapY || en.getPositionY() > y - gapY) {
                                                handle.getBattle().characterAttack(en);
                                                character.setAction_made(true);
                                                break enemy;
                                            }
                                        }
                                        break;
                                    case EAST:
                                        if (en.getPositionX() >= x) {
                                            if (en.getPositionY() < y + gapY || en.getPositionY() > y - gapY) {
                                                handle.getBattle().characterAttack(en);
                                                character.setAction_made(true);
                                                break enemy;
                                            }
                                        }
                                        break;
                                }

                                double distanceY    = Math.abs(y - en.getPositionY());
                                double gapX         = Math.tan(Math.toRadians(30)) * distanceY;
                                switch (character.getFacing()) {
                                    case NORTH:
                                        if (en.getPositionY() <= y) {
                                            if (en.getPositionX() < x + gapX || en.getPositionX() > x - gapX) {
                                                handle.getBattle().characterAttack(en);
                                                character.setAction_made(true);
                                                break enemy;
                                            }
                                        }
                                        break;
                                    case SOUTH:
                                        if (en.getPositionY() >= y) {
                                            if (en.getPositionX() < x + gapX || en.getPositionX() > x - gapX) {
                                                handle.getBattle().characterAttack(en);
                                                character.setAction_made(true);
                                                break enemy;
                                            }
                                        }
                                        break;
                                }
                            }
                        }

                        /*if (room.getSizes()[xFacing][yFacing] >= 70 || room.getSizes()[xFacing][yFacing] <= 80) {
                            for (Enemies enemy : room.getEnemies_list()) {
                                if (enemy.getRoom().getIndex() == character.getPresent_room()) {
                                    if ((xFacing) == enemy.getPositionX() && yFacing == enemy.getPositionY()) {
                                        handle.getBattle().characterAttack(enemy);
                                        character.setAction_made(true);
                                    }
                                }
                            }
                        }*/
                    }
                    else if ((EquipmentObject.isEquipment(weapon)
                            && (EquipmentObject.getEquipment(weapon) instanceof Weapon) &&
                            ((Weapon) Objects.requireNonNull(EquipmentObject.getEquipment(weapon))).getAttackStyle() == AttackStyle.LINE)){
                        //System.out.println("KK");
                        Weapon we = ((Weapon) Objects.requireNonNull(EquipmentObject.getEquipment(weapon)));
                        //List<Enemies> attackedEnemies = new ArrayList<Enemies>();
                        switch (character.getFacing()) {
                            case WEST:
                                //System.out.println("xFacing" + xFacing);
                                for (int i = xFacing; i > (Math.max(xFacing - we.radius(), 0)); i--){
                                    //System.out.println("A1");
                                    if (room.getSizes()[i][yFacing] >= 70 || room.getSizes()[i][yFacing] <= 80) {
                                        //System.out.println("B1");
                                        for (Enemies enemy : room.getEnemies_list()) {
                                            if (enemy.getRoom().getIndex() == character.getPresent_room()) {
                                                //System.out.println("C1");
                                                if ((i) == enemy.getPositionX() && yFacing == enemy.getPositionY()) {
                                                    //System.out.println("D1");
                                                    handle.getBattle().characterAttack(enemy);
                                                }
                                            }
                                        }
                                    }
                                }
                                character.setAction_made(true);
                                break;
                            case EAST:
                                //System.out.println("xFacing" + xFacing);
                                character.setAction_made(true);
                                for (int i = xFacing; i < (Math.min(xFacing + we.radius(), room.getWidth()-1)); i++){
                                    //System.out.println("A2");
                                    if (room.getSizes()[i][yFacing] >= 70 || room.getSizes()[i][yFacing] <= 80) {
                                        //System.out.println("B2");
                                        for (Enemies enemy : room.getEnemies_list()) {
                                            if (enemy.getRoom().getIndex() == character.getPresent_room()) {
                                                //System.out.println("C2");
                                                if ((i) == enemy.getPositionX() && yFacing == enemy.getPositionY()) {
                                                    //System.out.println("D2");
                                                    handle.getBattle().characterAttack(enemy);
                                                }
                                            }
                                        }
                                    }
                                }
                                break;
                            case NORTH:
                                //System.out.println("yFacing" + yFacing);
                                for (int j = yFacing; j > (Math.max(yFacing - we.radius(), 0)); j--){
                                    //System.out.println("A3");
                                    if (room.getSizes()[xFacing][j] >= 70 || room.getSizes()[xFacing][j] <= 80) {
                                        //System.out.println("B3");
                                        for (Enemies enemy : room.getEnemies_list()) {
                                            if (enemy.getRoom().getIndex() == character.getPresent_room()) {
                                                //System.out.println("C3");
                                                if ((xFacing) == enemy.getPositionX() && j == enemy.getPositionY()) {
                                                    //System.out.println("D3");
                                                    handle.getBattle().characterAttack(enemy);
                                                }
                                            }
                                        }
                                    }
                                }
                                character.setAction_made(true);
                                break;
                            case SOUTH:
                                //System.out.println("yFacing" + yFacing);
                                for (int j = yFacing; j < (Math.min(yFacing + we.radius(), room.getHeight()-1)); j++){
                                    //System.out.println("A4");
                                    if (room.getSizes()[xFacing][j] >= 70 || room.getSizes()[xFacing][j] <= 80) {
                                        //System.out.println("B4");
                                        for (Enemies enemy : room.getEnemies_list()) {
                                            if (enemy.getRoom().getIndex() == character.getPresent_room()) {
                                                //System.out.println("C4");
                                                if ((xFacing) == enemy.getPositionX() && j == enemy.getPositionY()) {
                                                    //System.out.println("D4");
                                                    handle.getBattle().characterAttack(enemy);
                                                }
                                            }
                                        }
                                    }
                                }
                                character.setAction_made(true);
                                break;
                        }
                    } else {
                        //System.out.println("EE");
                    }
                }
                else if (event.getCode() == KeyCode.A){
                    character.setIsMoving(true);
                    if (character.getCharacter_confused() <= 0) {
                        character.setFacing(Facing.WEST);
                        character.modify(-character.getStepLength(), 0);
                    } else {
                        character.setFacing(Facing.NORTH);
                        character.modify(0, -character.getStepLength());
                    }
                }
                else if (event.getCode() == KeyCode.D){
                    character.setIsMoving(true);
                    if (character.getCharacter_confused() <= 0) {
                        character.setFacing(Facing.EAST);
                        character.modify(character.getStepLength(), 0);
                    } else {
                        character.setFacing(Facing.SOUTH);
                        character.modify(0, character.getStepLength());
                    }
                }
                else  if (event.getCode() == KeyCode.W){
                    character.setIsMoving(true);
                    if (character.getCharacter_confused() <= 0) {
                        character.setFacing(Facing.NORTH);
                        character.modify(0, -character.getStepLength());
                    } else {
                        character.setFacing(Facing.EAST);
                        character.modify(character.getStepLength(), 0);
                    }
                }
                else if (event.getCode() == KeyCode.S){
                    character.setIsMoving(true);
                    if (character.getCharacter_confused() <= 0) {
                        character.setFacing(Facing.SOUTH);
                        character.modify(0, character.getStepLength());
                    } else {
                        character.setFacing(Facing.WEST);
                        character.modify(-character.getStepLength(), 0);
                    }
                } else if (event.getCode() == KeyCode.DIGIT1) {
                    inventory.checkItem(0, false);
                    inventory.setLast_position(0);
                } else if (event.getCode() == KeyCode.DIGIT2) {
                    inventory.checkItem(1, false);
                    inventory.setLast_position(1);
                } else if (event.getCode() == KeyCode.DIGIT3) {
                    inventory.checkItem(2, false);
                    inventory.setLast_position(2);
                } else if (event.getCode() == KeyCode.DIGIT4) {
                    inventory.checkItem(3, false);
                    inventory.setLast_position(3);
                } else if (event.getCode() == KeyCode.DIGIT5) {
                    inventory.checkItem(4, false);
                    inventory.setLast_position(4);
                } else if (event.getCode() == KeyCode.DIGIT6) {
                    inventory.checkItem(5, false);
                    inventory.setLast_position(5);
                } else if (event.getCode() == KeyCode.DIGIT7) {
                    inventory.checkItem(6, false);
                    inventory.setLast_position(6);
                } else if (event.getCode() == KeyCode.DIGIT8) {
                    inventory.checkItem(7, false);
                    inventory.setLast_position(7);
                } else if (event.getCode() == KeyCode.DIGIT9) {
                    inventory.checkItem(8, false);
                    inventory.setLast_position(8);
                } else if (event.getCode() == KeyCode.DIGIT0) {
                    inventory.checkItem(9, false);
                    inventory.setLast_position(9);
                } else if (event.getCode() == KeyCode.O) {
                    if (inventory.isWas_clicked()) {
                        inventory.checkItem(inventory.getLast_position(), true);
                    }
                } else if (event.getCode() == KeyCode.Q) {
                    if (inventory.isWas_clicked()) {
                        inventory.dropItem(inventory.getLast_position());
                    }
                } else if (event.getCode() == KeyCode.I) {
                    if (inventory.isWas_clicked()) {
                        inventory.identify(inventory.getLast_position());
                    }
                } else if (event.getCode() == KeyCode.U) {
                    if (room.getSizes()[xFacing][yFacing] >= 21 && room.getSizes()[xFacing][yFacing] <= 24){
                        character.setTorchLevel(20);
                    }
                }
                else if (event.getCode() == KeyCode.E) {
                    character.getInterface().setInventory_shown(!character.getInterface().isInventory_shown());
                    inventory.setWas_clicked(false);
                }
                if (character.isAction_made()) {
                    //enemyMove();
                    character.hunger();
                    inventory.setWas_clicked(false);
                    character.setAction_made(false);
                    //character.setIs_attacking(false);
                }
            }
        });
    }
/*
    private void enemyMove() {
        ListIterator<Enemies> iterator = Level.getLevels_list().get
                (Character.getPresent_level()).get(Character.getPresent_room()).getEnemies_list().listIterator();
        while (iterator.hasNext()) {
            Enemies enemies = iterator.next();
            if (enemies.getRoom().getIndex() == Character.getPresent_room()) {
                enemies.moveAlgorithm();
                if (!enemies.isAlive()) {
                    iterator.remove();
                    scene.setRoot(assets.draw());
                    stage.setScene(scene);
                }
                scene.setRoot(assets.draw());
                stage.setScene(scene);
            }
        }
    }
*/
}