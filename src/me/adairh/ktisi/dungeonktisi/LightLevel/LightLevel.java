package me.adairh.ktisi.dungeonktisi.LightLevel;

import javafx.scene.image.Image;

public enum LightLevel {
    DARK(0, new Image("assets/light/pixelLight0.png")),
    LOW(1, new Image("assets/light/pixelLight1.png")),
    LOW2(2, new Image("assets/light/pixelLight2.png")),
    LOW3(3, new Image("assets/light/pixelLight3.png")),
    MEDIUM(4, new Image("assets/light/pixelLight4.png")),
    MEDIUM2(5, new Image("assets/light/pixelLight5.png")),
    MEDIUM3(6, new Image("assets/light/pixelLight6.png")),
    HIGH(7, new Image("assets/light/pixelLight7.png")),
    HIGH2(8, new Image("assets/light/pixelLight8.png")),
    HIGH3(9, new Image("assets/light/pixelLight9.png")),
    LIGHT(10, new Image("assets/light/pixelLight10.png"));



    private int id;
    private Image im;

    LightLevel(int id, Image im){
        this.id = id;
        this.im = im;
    }

    public int getId() {
        return id;
    }

    public Image getImage() {
        return im;
    }

    public static LightLevel getLightLevel(int id){
        for (LightLevel ll : values()){
            if (ll.getId() == id){
                return ll;
            }
        }
        return LightLevel.DARK;
    }

    public static LightLevel getDarkLevel(int id){
        int i = id;
        int light = 10;
        while (i >= 0) {
            i--;
            light--;
        }
        return getLightLevel(light);
    }
}
