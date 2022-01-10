package me.adairh.ktisi.dungeonktisi.Stuff.Block.Door;

public class Door {

    int x, y, posx, posy, where, wall;

    public Door(int from, int where, int wall, int place, int height, int width) {
        this.where = where;
        this.wall = wall;

        /* NORTH */
        if (wall == 0) {
            x = place;
            y = 0;
            posx = x;
            posy = y + 1;
        } /* SOUTH */
        if (wall == 1) {
            x = place;
            y = height - 1;
            posx = x;
            posy = y - 1;
        } /* EAST */
        if (wall == 2) {
            x = width - 1;
            y = place;
            posx = x - 1;
            posy = y;
        } /* WEST */
        if (wall == 3) {
            x = 0;
            y = place;
            posx = x + 1;
            posy = y;
        }
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getPosx() {
        return posx;
    }

    public int getPosy() {
        return posy;
    }

    public int getWall() {
        return wall;
    }

    public int getWhere() {
        return where;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public void setWall(int wall) {
        this.wall = wall;
    }

    public void setWhere(int where) {
        this.where = where;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}