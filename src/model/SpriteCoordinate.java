package model;

public class SpriteCoordinate {
    int x;
    int y;

    public SpriteCoordinate(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }
    public int getX(){ return this.x; }
    public int getY(){ return this.y; }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SpriteCoordinate) {
            return this.x == ((SpriteCoordinate) o).getX() && this.y == ((SpriteCoordinate) o).getY();
        }
        return false;
    }
 }
