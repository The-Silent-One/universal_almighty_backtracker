package Basics;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author safou
 */
import java.util.ArrayList;
import java.util.Comparator;

class eltComparator implements Comparator<Element> {

    @Override
    public int compare(Element e1, Element e2) {
        return e1.f - e2.f;
    }

}

class Element implements Cloneable {

    int x;
    int y;
    final char symbol;
    boolean istransparent;
    int f;

    public Element(int x, int y, char symbol, boolean istransparent) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.istransparent = istransparent;
    }

    boolean isOnElement(Element e) {
        return (this.x == e.x) && (this.y == e.y);
    }

    int distance(Element e) { //calculate euclidean distance
        return (int) Math.round(Math.pow(e.x - this.x, 2) + Math.pow(e.y - this.y, 2));
    }

    void heuristic(Element start, Element goal) { //used in A* ,f[n]=h[n]+g[n]
        this.f = this.distance(start) + this.distance(goal);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Sheep extends Element {

    boolean diagMvt = false;

    public Sheep(int x, int y) {
        super(x, y, 'S', false);
    }

    public Sheep(int x, int y, boolean diagMvt) {
        super(x, y, 'S', false);
        this.diagMvt = diagMvt;
    }

    boolean unlockDoor(Key k, Door d, ArrayList<ArrayList<Element>> map) {
        try {
            k.useKey(map);
            d.openDoor(map);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

    boolean move(Element e, ArrayList<ArrayList<Element>> map) {
        try {
            int auxx = this.x;
            int auxy = this.y;

            ArrayList<Element> sheepLine = map.get(this.x);
            ArrayList<Element> elementLine = map.get(e.x);

            this.x = e.x;
            this.y = e.y;
            elementLine.remove(this.y);
            elementLine.add(this.y, this);
            map.remove(this.x);
            map.add(this.x, elementLine);

            e.x = auxx;
            e.y = auxy;
            sheepLine.remove(e.y);
            sheepLine.add(e.y, e);
            map.remove(e.x);
            map.add(e.x, sheepLine);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    ArrayList<Element> neighbors(ArrayList<ArrayList<Element>> map) {
        ArrayList<Element> n = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                if (this.diagMvt) {
                    try {
                        if (map.get(i + this.x).get(j + this.y).istransparent) {
                            n.add(map.get(i + this.x).get(j + this.y));
                        }
                    } catch (Exception e) {
                        continue;
                    }
                } else {
                    if (i == 0 || j == 0) {
                        try {
                            if (map.get(i + this.x).get(j + this.y).istransparent) {
                                n.add(map.get(i + this.x).get(j + this.y));
                            }
                        } catch (Exception e) {
                            continue;
                        }
                    }
                }
            }
        }
        return n;
    }

}

class Door extends Element {

    public Door(int x, int y) {
        super(x, y, 'D', false);
    }

    void openDoor(ArrayList<ArrayList<Element>> map) {
        Field f = new Field(this.x, this.y);
        ArrayList<Element> line = map.get(this.x);
        line.remove(this.y);
        line.add(this.y, f);
        map.remove(this.x);
        map.add(this.x, line);
    }
}

class Key extends Element {

    public Key(int x, int y) {
        super(x, y, 'K', true);
    }

    void useKey(ArrayList<ArrayList<Element>> map) {
        Field f = new Field(this.x, this.y);
        ArrayList<Element> line = map.get(this.x);
        line.remove(this.y);
        line.add(this.y, f);
        map.remove(this.x);
        map.add(this.x, line);
    }

}

class Grass extends Element {

    public Grass(int x, int y) {
        super(x, y, 'G', true);
    }

}

class Wolf extends Element {

    public Wolf(int x, int y) {
        super(x, y, 'W', false);
    }

    ArrayList<ArrayList<Element>> markTerritory(ArrayList<ArrayList<Element>> map) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (map.get(i + this.x).get(j + this.y) instanceof Field) {
                    try {
                        ((Field) map.get(i + this.x).get(j + this.y)).setDangerous();
                    } catch (Exception e) {
                        continue;
                    }
                }
            }

        }
        return map;
    }
}

class Fence extends Element {

    public Fence(int x, int y) {
        super(x, y, 'F', false);
    }

}

class Field extends Element {

    public Field(int x, int y) {
        super(x, y, '.', true);
    }

    void setDangerous() {
        this.istransparent = false;
    }
}
