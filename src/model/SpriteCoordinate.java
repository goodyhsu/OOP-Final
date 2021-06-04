package model;

public class SpriteCoordinate {
    int x;
    int y;

    public SpriteCoordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    void setX(int x){ this.x = x; }
    void setY(int y){ this.y = y; }
    int getX(){ return this.x; }
    int getY(){ return this.y; }
}
