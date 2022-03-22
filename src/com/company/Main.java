package com.company;

/*
 W1780616
 Andrzej Baum
 5SE01
*/

public class Main {


    public static void main(String[] args) {



        mazeReader maze = new mazeReader("examples/maze10_1.txt");
        System.out.println("===========================================================");
        System.out.println("Starting at [" + maze.start[0] + "," + maze.start[1] + "]");
        System.out.println();
        System.out.println("Solving the maze.....");
        System.out.println("===========================================================");
        maze.bfs();



    }
}

