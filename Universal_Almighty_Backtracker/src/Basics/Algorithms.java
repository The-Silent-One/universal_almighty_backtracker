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
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

class Algorithms {

    static HashMap[] dijkstra(Sheep sheep, Element goal, ArrayList<ArrayList<Element>> map) {
        ArrayList<Element> queue = new ArrayList<>();
        HashMap<Element, Integer> dist = new HashMap<>();
        HashMap<Element, Element> previous = new HashMap<>();

        for (ArrayList<Element> line : map) {
            for (Element element : line) {
                dist.put(element, Integer.MAX_VALUE);
                previous.put(element, null);
                queue.add(element);
            }
        }
        dist.put(sheep, 0);

        while (!queue.isEmpty()) {
            Element u = GameMap.minDistance(queue, dist);
            queue.remove(u);
            try {
                if (u.isOnElement(goal)) {
                    break;
                }
            } catch (NullPointerException ex) {
//                System.out.println(ex);
            }
            sheep.move(u, map);
            GameMap.printMap(map);

            ArrayList<Element> neighbors = sheep.neighbors(map);

            for (Element neighbor : neighbors) {
                int alt = dist.get(u) + 1;
                if (alt < dist.get(neighbor)) {
                    dist.put(neighbor, alt);
                    previous.put(neighbor, u);
                }
            }
            sheep.move(u, map);
        }
        HashMap[] path = new HashMap[2];
        path[0] = dist;
        path[1] = previous;
        return path;
    }

    static Stack aStar(Sheep sheep, Element goal, ArrayList<ArrayList<Element>> map) {

        Sheep start = null;
        try {
            start = (Sheep) sheep.clone();
        } catch (CloneNotSupportedException ex) {
//            System.out.println(ex);
        }

        PriorityQueue openSet = new PriorityQueue(new eltComparator());
        openSet.add(sheep);

        HashMap<Element, Element> cameFrom = new HashMap<>();

        HashMap<Element, Integer> gScore = new HashMap<>();
        gScore.put(sheep, 0);

        HashMap<Element, Integer> fScore = new HashMap<>();
        gScore.put(sheep, sheep.distance(goal));

        while (!openSet.isEmpty()) {
            Element current = (Element) openSet.poll();

            if (!sheep.isOnElement(current)) {
                if (current.isOnElement(goal)) {
                    return GameMap.returnPath(cameFrom, current); //return path
                }
                sheep.move(current, map);
//                GameMap.printMap(map);
            }

            int score = gScore.get(current) + 1;
            ArrayList<Element> neighbors = sheep.neighbors(map);

            for (Element neighbor : neighbors) {

                if (score < gScore.getOrDefault(neighbor, score + 1)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, score);
                    fScore.put(neighbor, score + neighbor.distance(goal));
                    neighbor.heuristic(start, goal);
                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
            sheep.move(current, map);
        }
        return null; //return failure
    }

    static HashMap<Element, Element> BFS(Sheep sheep, Element goal, ArrayList<ArrayList<Element>> map) {
        LinkedList queue = new LinkedList();
        ArrayList<Element> discovered = new ArrayList<>();
        HashMap<Element, Element> previous = new HashMap<>();

        previous.put(sheep, null);
        queue.push(sheep);
        discovered.add(sheep);

        while (!queue.isEmpty()) {
            Element u = (Element) queue.pop();
            if (u.isOnElement(goal)) {
                return previous;
            }

            sheep.move(u, map);
//            GameMap.printMap(map);
            ArrayList<Element> neighbors = sheep.neighbors(map);
            for (Element neighbor : neighbors) {
                if (!discovered.contains(neighbor)) {
                    discovered.add(neighbor);
                    previous.put(neighbor, u);
                    queue.push(neighbor);
                }
            }
            sheep.move(u, map);
        }
        return null;
    }

    static HashMap<Element, Element> DFS(Sheep sheep, Element goal, ArrayList<ArrayList<Element>> map) {
        LinkedList<Element> queue = new LinkedList<>();
        ArrayList<Element> discovered = new ArrayList<>();
        HashMap<Element, Element> previous = new HashMap<>();

        previous.put(sheep, null);
        queue.push(sheep);

        while (!queue.isEmpty()) {
            Element u = (Element) queue.pop();
            if (u.isOnElement(goal)) {
                return previous;
            }

            if (!discovered.contains(u)) {
                discovered.add(u);
                sheep.move(u, map);
//                GameMap.printMap(map);
                ArrayList<Element> neighbors = sheep.neighbors(map);
                for (Element neighbor : neighbors) {
                    if (!discovered.contains(neighbor)) {
                        previous.put(neighbor, u);
                        queue.push(neighbor);
                    }
                }
                sheep.move(u, map);
            }
        }
        return null;
    }

    static HashMap<Element, Integer> heatMapGenerator(Sheep sheep, Element goal, ArrayList<ArrayList<Element>> map) {
        sheep.move(goal, map);

        HashMap<Element, Integer> res = Algorithms.dijkstra(sheep, null, map)[0];

        int sheepHeat = res.get(sheep);
        int goalHeat = res.get(goal);

        res.put(goal, sheepHeat);
        res.put(sheep, goalHeat);

        sheep.move(goal, map);

        return res;
    }

    static HashMap<Element, Integer[]> vectorFieldGenerator(Sheep sheep, ArrayList<ArrayList<Element>> map, HashMap<Element, Integer> heatMap) {
        HashMap<Element, Integer[]> vectorField = new HashMap<>();
        for (Element tile : heatMap.keySet()) {
            Integer[] vector = new Integer[2];

            sheep.move(tile, map);

            int udist = heatMap.get(sheep);
            int ddist = heatMap.get(sheep);
            int ldist = heatMap.get(sheep);
            int rdist = heatMap.get(sheep);

            try {
                Element u = map.get(sheep.x - 1).get(sheep.y);
                udist = heatMap.get(u);
            } catch (IndexOutOfBoundsException ex) {
//                System.out.println(ex);
            }

            try {
                Element d = map.get(sheep.x + 1).get(sheep.y);
                ddist = heatMap.get(d);
            } catch (IndexOutOfBoundsException ex) {
//                System.out.println(ex);
            }

            try {
                Element l = map.get(sheep.x).get(sheep.y - 1);
                ldist = heatMap.get(l);
            } catch (IndexOutOfBoundsException ex) {
//                System.out.println(ex);
            }

            try {
                Element r = map.get(sheep.x).get(sheep.y + 1);
                rdist = heatMap.get(r);
            } catch (IndexOutOfBoundsException ex) {
//                System.out.println(ex);
            }

            vector[0] = (udist - ddist);
            try {
                vector[0] = (udist - ddist) / Math.abs(udist - ddist);
            } catch (ArithmeticException ex) {
//                System.out.println(ex);
            }

            vector[1] = (ldist - rdist);
            try {
                vector[1] = (ldist - rdist) / Math.abs(ldist - rdist);
            } catch (ArithmeticException ex) {
//                System.out.println(ex);
            }

            sheep.move(tile, map);
            vectorField.put(tile, vector);
        }

        return vectorField;

    }

    static HashMap<Element, Element> GBVF(Sheep sheep, Element goal, ArrayList<ArrayList<Element>> map) {
        //known flowfield pathfinding
        HashMap<Element, Integer> heatMap = Algorithms.heatMapGenerator(sheep, goal, map);
        HashMap<Element, Integer[]> vectorField = Algorithms.vectorFieldGenerator(sheep, map, heatMap);
        HashMap<Element, Element> previous = new HashMap<>();

        GameMap.printHeatMap(map, heatMap);
        GameMap.printVectorField(map, vectorField);

        Element oldTile = null;
        Element newTile = sheep;
        Element temp = sheep;
        Sheep start = null;
        try {
            start = (Sheep) sheep.clone();
        } catch (CloneNotSupportedException ex) {
//            System.out.println(ex);
        }

        while (true) {
            previous.put(temp, oldTile);
            Integer[] nextTile = vectorField.get(sheep);
            oldTile = temp;
            newTile = map.get(sheep.x + nextTile[0]).get(sheep.y + nextTile[1]);
            if (newTile.isOnElement(goal)) {
                break;
            }
            try {
                temp = (Element) newTile.clone();
            } catch (CloneNotSupportedException ex) {
                System.out.println(ex);
            }
            sheep.move(newTile, map);
//            GameMap.printMap(map);

        }
        previous.put(newTile, oldTile);
        sheep.move(map.get(start.x).get(start.y), map);
        return previous;
    }

}