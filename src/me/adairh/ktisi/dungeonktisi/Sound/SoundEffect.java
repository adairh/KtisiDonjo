package me.adairh.ktisi.dungeonktisi.Sound;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.media.AudioClip;
import me.adairh.ktisi.dungeonktisi.DungeonKtisi;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;


public enum SoundEffect {
        FLOOR_STEP(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/floor_step.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/stone1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/stone2.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/stone3.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/stone4.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/stone5.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/stone6.mp3")).toExternalForm())
        ),
        GRASS_STEP(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/grass_step.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/grass1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/grass2.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/grass3.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/grass4.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/grass5.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/grass6.mp3")).toExternalForm())
        ),
        GRASS_STEP_2(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/gravel1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/gravel2.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/gravel3.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/gravel4.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/gravel5.mp3")).toExternalForm())

        ),
        WOOD_STEP(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/wood_step.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/wood_step_2.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/wood1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/wood2.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/wood3.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/wood4.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/wood5.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/step/wood6.mp3")).toExternalForm())
        ),
        IDENTIFY(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/action/identify.mp3")).toExternalForm())),

        EAT(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/action/eat.mp3")).toExternalForm()),
            new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/action/eat_2.mp3")).toExternalForm())
        ),
        DRINK(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/action/drink.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/character/action/drink_2.mp3")).toExternalForm())
        ),

        CHEST_OPEN(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/block/chest_open.mp3")).toExternalForm())),
        DOOR_OPEN(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/block/door_open.mp3")).toExternalForm())),
        GOLDEN_CHEST_OPEN(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/block/golden_chest_open.mp3")).toExternalForm())),
        GATE_OPEN(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/block/gate_open.mp3")).toExternalForm())),
        GATE_LOCKED(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/block/gate_locked.mp3")).toExternalForm())),

        GOLEM_DEATH(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/golem/death.mp3")).toExternalForm())),
        GOLEM_HURT(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/golem/hurt1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/golem/hurt2.mp3")).toExternalForm())
        ),
        GOLEM_HIT(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/golem/hit1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/golem/hit2.mp3")).toExternalForm())
        ),
        GOLEM_WALK(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/golem/step1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/golem/step2.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/golem/step3.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/golem/step4.mp3")).toExternalForm())
        ),
        ZOMBIE_DEATH(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/zombie/death.mp3")).toExternalForm())),
        ZOMBIE_HURT(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/zombie/hurt1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/zombie/hurt2.mp3")).toExternalForm())
        ),
        ZOMBIE_WALK(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/zombie/step1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/zombie/step2.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/zombie/step3.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/zombie/step4.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/zombie/step5.mp3")).toExternalForm())
        ),
        SKELETON_DEATH(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/skeleton/death.mp3")).toExternalForm())),
        SKELETON_HURT(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/skeleton/hurt1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/skeleton/hurt2.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/skeleton/hurt3.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/skeleton/hurt4.mp3")).toExternalForm())
        ),
        SKELETON_WALK(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/skeleton/step1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/skeleton/step2.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/skeleton/step3.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/skeleton/step4.mp3")).toExternalForm())
        ),
        GHOST_DEATH(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/ghost/death.mp3")).toExternalForm())),
        GHOST_HURT(new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/ghost/hurt1.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/ghost/hurt2.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/ghost/hurt3.mp3")).toExternalForm()),
                new AudioClip(Objects.requireNonNull(DungeonKtisi.class.getResource("/audio/entity/ghost/hurt4.mp3")).toExternalForm())
        ),
    ;


    private static HashMap<SoundEffect, Boolean> hsb = new HashMap<SoundEffect, Boolean>();


    private Task playAudio(SoundEffect se){
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                if (hsb.containsKey(se) && hsb.get(se)) {
                    hsb.put(se, false);
                }
            }
        });
        return sleeper;
    }

    private AudioClip[] audio;
    SoundEffect(AudioClip... audio){
        this.audio = audio;
    }

    public void play(){
        if (!hsb.containsKey(this) || !hsb.get(this)) {
            Random rand = new Random();
            int i = rand.nextInt(audio.length);
            AudioClip au = audio[i];
            au.setVolume(0.3);
            au.play();
            hsb.put(this, true);
            new Thread(playAudio(this)).start();
        }
    }
}
