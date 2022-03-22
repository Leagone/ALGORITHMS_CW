package com.company;

/*
 W1780616
 Andrzej Baum
 5SE01
*/

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class mazeReader {

    String[][] maze;

    MyQueue queue = new MyQueue(20);

    ArrayList<Node> allNodes = new ArrayList<>();


    int rowCount;
    int columnCount;


    int[] start, finish;

    // O(n)
    public mazeReader(String filePath) {

        String strDimension = filePath.substring(13, 15);

        int dimension = Integer.parseInt(strDimension);
        String[][] newMaze = new String[dimension][dimension];


        try {

            File file = new File(filePath);
            Scanner sc = new Scanner(file).useDelimiter("");

            int i = 0;
            int j = 0;

            while (sc.hasNext()) {

                String currentChar = sc.next();

                if (currentChar.equals("S")) {

                    start = new int[]{i, j};

                }

                if (currentChar.equals("F")) {

                    finish = new int[]{i, j};

                }


                if (j < dimension) {
                    newMaze[i][j] = currentChar;
                    j++;
                } else {
                    i++;
                    j = 0;
                    newMaze[i][j] = currentChar;
                }


            }


        } catch (Exception ioex) {
            ioex.printStackTrace();
        }


        maze = newMaze;


        columnCount = maze[0].length;
        rowCount = maze.length;
    }


    // O(n)
    public boolean ifExists(Node toFind) {
        for (Node allNode : allNodes) {
            if (Arrays.equals(toFind.coordinates, allNode.coordinates)) {
                return true;
            }
        }
        return false;
    }

    public void addIfNew(Node node, int[] coordinates, String gotFromDirection) {
        Node temp = new Node(coordinates, gotFromDirection, node);

        if (!ifExists(temp)) {
            node.addAdjacency(temp);
            allNodes.add(temp);
        }

    }


    // O(n)
    public void getAdjacency(Node currentNode) {

        int y = currentNode.coordinates[0];
        int x = currentNode.coordinates[1];

        int movingLeft = x; // goes -- till edge
        int movingRight = x; // goes ++ till edge

        int movingUp = y; // goes -- till edge
        int movingDown = y; // goes ++ till edge

        while (movingLeft > -1) {

            if (maze[y][movingLeft].equals("F")) {
                int[] coordinates = {y, movingLeft};
                addIfNew(currentNode, coordinates, "left");

            }


            if (maze[y][movingLeft].equals("0")) {
                int[] coordinates = {y, movingLeft + 1};
                if (movingLeft + 1 != x) {
                    addIfNew(currentNode, coordinates, "left");


                }
                break;


            } else if (movingLeft == 0) {
                int[] coordinates = {y, movingLeft};
                if (movingLeft != x) {
                    addIfNew(currentNode, coordinates, "left");
                }
                break;

            }


            movingLeft--;

        }


        while (movingUp > -1) {

            if (maze[movingUp][x].equals("F")) {
                int[] coordinates = {movingUp, x};
                addIfNew(currentNode, coordinates, "up");

            }


            if (maze[movingUp][x].equals("0")) {
                int[] coordinates = {movingUp + 1, x};
                if (movingUp + 1 != y) {
                    addIfNew(currentNode, coordinates, "up");

                }
                break;


            } else if (movingUp == 0) {
                int[] coordinates = {movingUp, x};
                if (movingUp != y) {
                    addIfNew(currentNode, coordinates, "up");
                }
                break;
            }


            movingUp--;

        }

        while (movingRight < columnCount) {

            if (maze[y][movingRight].equals("F")) {
                int[] coordinates = {y, movingRight};
                addIfNew(currentNode, coordinates, "right");

            }


            if (maze[y][movingRight].equals("0")) {
                int[] coordinates = {y, movingRight - 1};
                if (movingRight - 1 != x) {
                    addIfNew(currentNode, coordinates, "right");
                }
                break;


            } else if (movingRight == columnCount - 1) {
                int[] coordinates = {y, movingRight};
                if (movingRight != x) {
                    addIfNew(currentNode, coordinates, "right");
                }
                break;
            }


            movingRight++;

        }

        while (movingDown < rowCount) {

            if (maze[movingDown][x].equals("F")) {
                int[] coordinates = {movingDown, x};
                addIfNew(currentNode, coordinates, "down");

            }


            if (maze[movingDown][x].equals("0")) {
                int[] coordinates = {movingDown - 1, x};
                if (movingDown - 1 != y) {
                    addIfNew(currentNode, coordinates, "down");
                }
                break;


            } else if (movingDown == rowCount - 1) {
                int[] coordinates = {movingDown, x};
                if (movingDown != y) {
                    addIfNew(currentNode, coordinates, "down");
                }
                break;
            }


            movingDown++;

        }


    }


    // O(n^2)
    public void bfs() {

        Node Start = new Node(start);

        queue.enqueue(Start);
        allNodes.add(Start);

        while (!queue.isEmpty()) {

            Node currentNode = queue.dequeue();

            if (Arrays.equals(currentNode.coordinates, finish)) {
                getPath(currentNode);
                System.out.println();
                System.out.println("Done!");
                System.out.println("===========================================================");
                break;
            } else {
                getAdjacency(currentNode);
                queue.enqueueAll(currentNode);


            }


        }


    }

    // O (N)
    public void getPath(Node node) {
        if (node.gotFrom == null) {
            System.out.println(Arrays.toString(node.coordinates));
        } else {
            getPath(node.gotFrom);
            System.out.println("Moving " + node.gotFromDirection + " to " + Arrays.toString(node.coordinates));
        }
    }


}
