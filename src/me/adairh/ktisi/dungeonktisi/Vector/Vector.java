package me.adairh.ktisi.dungeonktisi.Vector;

public class Vector {
    double xFrom;
    double xTo;

    double yFrom;
    double yTo;

    public Vector(double xFrom, double yFrom, double xTo, double yTo) {
        this.xFrom = xFrom;
        this.xTo = xTo;

        this.yFrom = yFrom;
        this.yTo = yTo;
    }

    public double vectorLength(){
        //A(x1,y1)
        //B(x2,y2)
        return Math.sqrt( Math.pow(xTo-xFrom,2) + Math.pow(yTo-yFrom,2) );
    }

    public void add(Vector v) {
        add(v.getxTo()-v.getxFrom(), v.getyTo()-v.getyFrom());
    }

    public void add(double x, double y) {
        setxTo(getxTo() + x);
        setyTo(getyTo() + y);
    }

    public double getxFrom() {
        return xFrom;
    }

    public double getxTo() {
        return xTo;
    }

    public double getyFrom() {
        return yFrom;
    }

    public double getyTo() {
        return yTo;
    }

    private void setxTo(double xTo) {
        this.xTo = xTo;
    }

    private void setyTo(double yTo) {
        this.yTo = yTo;
    }
}
