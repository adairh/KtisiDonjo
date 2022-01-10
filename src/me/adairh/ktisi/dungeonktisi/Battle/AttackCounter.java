package me.adairh.ktisi.dungeonktisi.Battle;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.GameHandle;

import java.util.ArrayList;
import java.util.List;

public class AttackCounter {

    private int counter = 0;
    private Enemies enemies;
    private Timeline timeline;
    private static List<AttackCounter> list = new ArrayList<AttackCounter>();
    private GameHandle handle;

    public AttackCounter(Enemies enemies, GameHandle handle){
        this.handle = handle;
        this.enemies = enemies;
        enemies.setUnderAttack(true);
        this.counter = 2;
        this.timeline = new Timeline(new KeyFrame(Duration.millis(100), ev -> {
            if (enemies.isAlive()) {
                if (counter > 0) {
                    counter--;
                } else {
                    stop();
                }
            } else {
                counter = 0;
                stop();
            }
        }));
        start();
    }

    private void start(){
        handle.getMainCharacter().setIs_attacking(true);
        System.out.println("Counter start " + handle.getMainCharacter().isAttacking());
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        list.add(this);
        enemies.setUnderAttack(true);
        //handle.getAssets().battle(enemies.getPositionX(), enemies.getPositionY());
        handle.getMainCharacter().setAction_made(true);
    }

    private void stop(){
        handle.getMainCharacter().setIs_attacking(false);
        System.out.println("Counter stop");
        list.remove(this);
        timeline.stop();
        enemies.setUnderAttack(false);
        handle.getMainCharacter().setAction_made(false);
        handle.getAssets().draw();
        handle.getBattle().removeTarget(enemies);
    }

    public void reset(){
        System.out.println("Counter reset");
        this.counter = 2;
    }

    public Enemies getEnemies() {
        return enemies;
    }

    public AttackCounter getAttackCounter(){
        return this;
    }

    public static List<AttackCounter> getList() {
        return list;
    }

    public static boolean isCountingEntity(Enemies enemies){
        for (AttackCounter counter : list){
            if (counter.getEnemies().equals(enemies)){
                return true;
            }
        }
        return false;
    }

    public static AttackCounter getAttackCounter(Enemies enemies){
        for (AttackCounter counter : list){
            if (counter.getEnemies().equals(enemies)){
                return counter;
            }
        }
        return null;
    }
}
