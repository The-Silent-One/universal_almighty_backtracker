

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author safou
 */
public class Element {

    final int x;
    final int y;
    final char symbol;
    boolean istransparent;
    public Element(int x, int y, char symbol,boolean istransparent) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.istransparent = istransparent;
    }
}

class Sheep extends Element{
    public Sheep(int x, int y) {
        super(x, y, 'S', false);
    }
}

class Door extends Element{
    public Door(int x, int y) {
        super(x, y, 'D', false);
    }
    
}

class Key extends Element{
    public Key(int x, int y) {
        super(x, y, 'K', true);
    }
    
}

class Grass extends Element{
    public Grass(int x, int y) {
        super(x, y, 'G', true);
    }
    
}

class Wolf extends Element{
    public Wolf(int x, int y) {
        super(x, y, 'W', false);
    }
    
}

class Field extends Element{
    public Field(int x, int y) {
        super(x, y, '.',true);
    }
    
}