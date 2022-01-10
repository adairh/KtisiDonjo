package me.adairh.ktisi.dungeonktisi.Utilities;

import java.util.Random;

public class StructureGenerator {
    boolean[][] structure;

    public void generate(int max) {

        Random generator = new Random();
        structure = new boolean[max][max];

        /* MAKING LINE GRAPH */
        for (int x = 0; x < max; x++) {
            for (int y = 0; y < max; y++) {
                structure[y][x] = false;
                if (x > 0 && y > 0) {
                    structure[x][x - 1] = true;
                    structure[y - 1][y] = true;
                }
            }
        }
        System.out.println("======================[Line graph]======================");
        terminalShowing(max);

        /* RANDOM CYCLES */
        for (int i = 0; i < max; i++) {
            if (i == 0 || i == (max - 1)) {
                for (int index = 0; index < 2; index++) {
                    if (generator.nextInt(2) == 0) { //50%
                        int max_got = generator.nextInt(max);
                        structure[i][max_got] = true;
                        structure[max_got][i] = true;
                    }
                }
            } else {
                if (generator.nextInt(2) == 0) {
                    int max_got = generator.nextInt(max);
                    structure[i][max_got] = true;
                    structure[max_got][i] = true;
                }
            }
        }
        System.out.println("======================[RANDOM CYCLES]======================");
        terminalShowing(max);
    }

    private void terminalShowing(int max) {
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                if (structure[i][j])
                    System.out.print("1 ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public boolean getValue(int x, int y){
        return structure[x][y];
    }
}
