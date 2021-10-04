package com.lab1;

import com.lab1.game.GameState;
import com.lab1.routing.Astar;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
	    var start = GameState.of(new int[][]{
	            new int[]{2,8,3},
                new int[]{1,6,4},
                new int[]{7, GameState.EMPTY_CELL, 5},
        });
	    var target = GameState.of(new int[][]{
				new int[]{1,2,3},
				new int[]{8,GameState.EMPTY_CELL,4},
				new int[]{7, 6, 5},
		});

	    var path = Astar.findPath(start, target, new GameAstarImpl()).orElseGet(ArrayList::new);

	    System.out.println("Found path with length " + (path.size() - 1));
	    for(var state : path){
			System.out.println(GameState.prettyPrint(state));
			System.out.println("-".repeat(20));
		}
    }
}
