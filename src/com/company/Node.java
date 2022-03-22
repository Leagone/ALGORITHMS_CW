package com.company;

/*
 W1780616
 Andrzej Baum
 5SE01
*/

import java.util.ArrayList;

public class Node {

    int[] coordinates;
    ArrayList<Node> adjacency = new ArrayList<>();
    Node gotFrom = null;
    String gotFromDirection;


    Node(int[] coordinates) {
        this.coordinates = coordinates;
    }


    Node(int[] coordinates, String gotFromDirection, Node gotFrom) {
        this.coordinates = coordinates;
        this.gotFromDirection = gotFromDirection;
        this.gotFrom = gotFrom;


    }

    public void addAdjacency(Node adjacentNode) {
        adjacency.add(adjacentNode);
    }


}





