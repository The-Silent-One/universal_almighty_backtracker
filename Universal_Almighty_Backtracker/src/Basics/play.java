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
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class play {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] sl = in.readLine().split(" ");
        int n = Integer.parseInt(sl[0]); //number of lines
        int m = Integer.parseInt(sl[1]); // number of columns

        ArrayList<ArrayList<Element>> map = new ArrayList<>();
        ArrayList<Wolf> wolfPack = new ArrayList<>();
        ArrayList<Key> keys = new ArrayList<>();
        ArrayList<Door> doors = new ArrayList<>();

        Sheep s = null;
        Grass g = null;

        for (int i = 0; i < n; i++) {
            String l = in.readLine();
            ArrayList<Element> line = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                switch (l.charAt(j)) {
                    case 'S': {
                        s = new Sheep(i, j, false);
                        line.add(s);
                        break;
                    }
                    case 'D': {
                        Door d = new Door(i, j);
                        line.add(d);
                        doors.add(d);
                        break;
                    }
                    case 'K': {
                        Key k = new Key(i, j);
                        line.add(k);
                        keys.add(k);
                        break;
                    }
                    case 'W': {
                        Wolf w = new Wolf(i, j);
                        line.add(w);
                        wolfPack.add(w);
                        break;
                    }
                    case 'G': {
                        g = new Grass(i, j);
                        line.add(g);
                        break;
                    }
                    case 'F': {
                        line.add(new Fence(i, j));
                        break;
                    }
                    case '.': {
                        line.add(new Field(i, j));
                    }
                    default:
                }
            }
            map.add(line);
        }
        solve(4, s, g, map, wolfPack, keys, doors);
    }

    static void solve(int method, Sheep sheep, Grass goal, ArrayList<ArrayList<Element>> map, ArrayList<Wolf> wolfPack, ArrayList<Key> keys, ArrayList<Door> doors) {
        for (Wolf wolf : wolfPack) {
            wolf.markTerritory(map);
        }
        for (int i = 0; i < Math.min(keys.size(), doors.size()); i++) {
            switch (method) {
                case 0: {
                    Stack s = Algorithms.aStar(sheep, keys.get(i), map);
                    GameMap.printMap(map);
                    GameMap.printPath(s);
                    sheep.unlockDoor(keys.get(i), doors.get(i), map);
                    break;

                }
                case 1: {
                    HashMap[] res = Algorithms.dijkstra(sheep, keys.get(i), map);
                    Stack s = GameMap.returnPath(res[1], keys.get(i));
                    GameMap.printMap(map);
                    GameMap.printPath(s);
                    sheep.unlockDoor(keys.get(i), doors.get(i), map);
                    break;
                }
                case 2: {
                    HashMap<Element, Element> res = Algorithms.BFS(sheep, keys.get(i), map);
                    Stack s = GameMap.returnPath(res, keys.get(i));
                    GameMap.printMap(map);
                    GameMap.printPath(s);
                    sheep.unlockDoor(keys.get(i), doors.get(i), map);
                    break;
                }
                case 3: {
                    HashMap<Element, Element> res = Algorithms.DFS(sheep, keys.get(i), map);
                    Stack s = GameMap.returnPath(res, keys.get(i));
                    GameMap.printMap(map);
                    GameMap.printPath(s);
                    sheep.unlockDoor(keys.get(i), doors.get(i), map);
                    break;
                }
                case 4: {
                    HashMap<Element, Element> res = Algorithms.GBVF(sheep, keys.get(i), map);
                    Stack s = GameMap.returnPath(res, keys.get(i));
                    GameMap.printMap(map);
                    GameMap.printPath(s);
                    sheep.unlockDoor(keys.get(i), doors.get(i), map);
                    break;
                }
            }

        }
        switch (method) {
            case 0: {
                Stack s = Algorithms.aStar(sheep, goal, map);
                GameMap.printMap(map);
                GameMap.printPath(s);
                break;
            }
            case 1: {
                HashMap[] res = Algorithms.dijkstra(sheep, goal, map);
                Stack s = GameMap.returnPath(res[1], goal);
                GameMap.printMap(map);
                GameMap.printPath(s);
                break;
            }
            case 2: {
                HashMap<Element, Element> res = Algorithms.BFS(sheep, goal, map);
                Stack s = GameMap.returnPath(res, goal);
                GameMap.printMap(map);
                GameMap.printPath(s);
                break;
            }
            case 3: {
                HashMap<Element, Element> res = Algorithms.DFS(sheep, goal, map);
                Stack s = GameMap.returnPath(res, goal);
                GameMap.printMap(map);
                GameMap.printPath(s);
                break;
            }
            case 4: {
                HashMap<Element, Element> res = Algorithms.GBVF(sheep, goal, map);
                Stack s = GameMap.returnPath(res, goal);
                GameMap.printMap(map);
                GameMap.printPath(s);
                break;
            }
        }

    }

}
