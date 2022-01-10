package me.adairh.ktisi.dungeonktisi.Battle;

import me.adairh.ktisi.dungeonktisi.Entity.Entities.Character.Character;
import me.adairh.ktisi.dungeonktisi.Entity.Entities.Enermies.Enemies;
import me.adairh.ktisi.dungeonktisi.GameHandle;
import me.adairh.ktisi.dungeonktisi.Stats.StatsType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Battle {

    private Random generator;
    private GameHandle handle;
    private List<Enemies> targets;

    public Battle(GameHandle handle){
        generator = new Random();
        this.handle = handle;
        this.targets = new ArrayList<Enemies>();
    }

    public void enemyAttack(Enemies enemy) {
        Character character = handle.getMainCharacter();
        //handle.getEquipmentObject().updateStats();
        int strength = generator.nextInt(enemy.getStrength_points() + 5 * character.getPresent_level());
        int defence = generator.nextInt(character.getStatsPoints(StatsType.DEFENSE) + character.getBonusStatsPoints(StatsType.DEFENSE)) / 2;
        int true_damage = strength - defence;
        if (generator.nextInt(100) <= (character.getStatsPoints(StatsType.DEXTERITY) + character.getBonusStatsPoints(StatsType.DEXTERITY)))
            character.getInterface().newEvent("Bạn vừa né đòn.");
        else if (true_damage <= 0)
            character.getInterface().newEvent("Bạn vừa chặn đòn.");
        else {
            character.getInterface().newEvent(enemy.getType() + " đã gây ra : " + true_damage + " sát thương");
            character.modifyHealth(-true_damage);
        }
    }

    public void characterAttack(Enemies enemy) {
        if (enemy.isAlive()) {
            if (!targets.contains(enemy))
                targets.add(enemy);
            Character character = handle.getMainCharacter();

            if (AttackCounter.isCountingEntity(enemy)) {
                AttackCounter counter = AttackCounter.getAttackCounter(enemy);
                assert counter != null;
                counter.reset();
            } else {
                new AttackCounter(enemy, handle);
            }
            int strength = generator.nextInt(character.getStatsPoints(StatsType.STRENGTH) + character.getBonusStatsPoints(StatsType.STRENGTH));
            int defence = generator.nextInt(enemy.getDefence_points()) / 2;
            int true_damage = strength - defence;// + handle.getMainCharacter().getATT();
            if (handle.getMainCharacter().getCharacter_confused() > 0 || handle.getMainCharacter().getCharacter_paralyze() > 0) {
                character.getInterface().newEvent("Bạn vừa đánh trượt.");
            } else if (generator.nextInt(100) <= enemy.getDexterity_points()) {
                character.getInterface().newEvent(enemy.getType() + " vừa né đòn.");
            } else if (true_damage <= 0)
                character.getInterface().newEvent(enemy.getType() + " vừa chặn đòn đánh.");
            else {
                character.getInterface().newEvent("Bạn vừa gây ra : " + true_damage + " sát thương ");
                enemy.setHealth(enemy.getHealth_points() - true_damage);
            }
            if (enemy.getHealth_points() < 0)
                enemy.setHealth(0);

            System.out.println("Đấm " + enemy.getType() + " tại " + enemy.getPositionX() + ":" + enemy.getPositionY());
        }
    }

    public boolean isAttacking(Enemies enemies){
        return targets.contains(enemies);
    }

    public void removeTarget(Enemies enemy) {
        if (isAttacking(enemy))
        {
            targets.remove(enemy);
        }
    }

    public List<Enemies> getTargets() {
        return targets;
    }
}