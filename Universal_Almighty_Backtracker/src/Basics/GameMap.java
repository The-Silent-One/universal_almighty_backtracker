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
import java.util.HashMap;
import java.util.Stack;

class GameMap {

    static void printMap(ArrayList<ArrayList<Element>> map) {
        System.out.println("\n***\n");
        for (ArrayList<Element> line : map) {
            for (Element element : line) {
                System.out.print(element.symbol);
            }
            System.out.println();
        }
        System.out.println("\n***\n");
    }

    static Element minDistance(ArrayList<Element> querry, HashMap<Element, Integer> dist) {
        Element minElement = null;
        int minDist = Integer.MAX_VALUE;
        for (Element element : querry) {
            if (dist.get(element) < minDist) {
                minElement = element;
                minDist = dist.get(element);
            }
        }
        return minElement;
    }

    static Stack returnPath(HashMap<Element, Element> cameFrom, Element current) {
        Element p = current;
        Stack path = new Stack();
        while (cameFrom.containsKey(p)) {
            path.push(p);
            p = cameFrom.get(p);
        }
        return path;
    }

    static void printPath(Stack s) {
        while (!s.isEmpty()) {
            Element p = (Element) s.pop();
            System.out.println(p.x);
            System.out.println(p.y);
            System.out.println("***");
        }
    }

    static void printHeatMap(ArrayList<ArrayList<Element>> map, HashMap<Element, Integer> heatMap) {
        for (ArrayList<Element> line : map) {
            for (Element tile : line) {
                System.out.print(heatMap.get(tile));
                System.out.print(" ");
            }
            System.out.println();
        }
        System.out.println("***");
    }

    static void printVectorField(ArrayList<ArrayList<Element>> map, HashMap<Element, Integer[]> vectorField) {
        for (ArrayList<Element> line : map) {
            for (Element tile : line) {
                try {
                    System.out.print("(");
                    System.out.print(vectorField.get(tile)[0]);
                    System.out.print(",");
                    System.out.print(vectorField.get(tile)[1]);
                    System.out.print(") ");
                } catch (NullPointerException ex) {
                    System.out.print(" , ) ");
                }
            }
            System.out.println();
        }
        System.out.println("***");
    }
}
