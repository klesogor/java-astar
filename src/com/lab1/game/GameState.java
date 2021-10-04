package com.lab1.game;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GameState {
    //use -1 to represent empty cell that is swappable
    public static final int EMPTY_CELL = -1;
    private int[][] gameState;
    private int size;

    private GameState(int[][] field){
        size = field.length;
        gameState = field;
    }

    public static GameState of(int[][] field){
        if(field.length == 0){
            throw new IllegalArgumentException("Field cannot be empty");
        }
        var size = field.length;
        var isUniformField = Arrays.stream(field).allMatch(row -> row.length == size);
        if(!isUniformField){
            throw new IllegalArgumentException("Field should be square");
        }

        var seenValues = Arrays.stream(field)
                .flatMap(row -> Arrays.stream(row).boxed())
                .collect(Collectors.toSet());

        if(!seenValues.contains(EMPTY_CELL)){
            throw new IllegalArgumentException("Field MUST have an empty cell");
        }
        if(seenValues.size() != size * size){
            throw new IllegalArgumentException("Field has non-unique elements");
        }

        return new GameState(field);
    }

    public Stream<GameState> getPossibleTurns(){
        int[] emptyIdx = new int[]{-1, -1};
        // It is assumed that empty field is always present, since public-facing constructor function is validating this
        // invariant
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(gameState[i][j] == EMPTY_CELL){
                    emptyIdx[0] = i;
                    emptyIdx[1] = j;
                    break;
                }
            }
        }

        return Stream.of(
                Utils.swapSafeImmutable(gameState, emptyIdx, new int[]{emptyIdx[0] + 1, emptyIdx[1]}),
                Utils.swapSafeImmutable(gameState, emptyIdx, new int[]{emptyIdx[0] - 1, emptyIdx[1]}),
                Utils.swapSafeImmutable(gameState, emptyIdx, new int[]{emptyIdx[0], emptyIdx[1] + 1}),
                Utils.swapSafeImmutable(gameState, emptyIdx, new int[]{emptyIdx[0], emptyIdx[1] - 1})
        )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(GameState::new);
    }

    // Calculate diff between two game states
    public static int diff(GameState a, GameState b){
        int diff = 0;
        if(a.size != b.size){
            throw new IllegalArgumentException("Game states have different field sizes!");
        }
        for(int i = 0; i < a.size; i++){
            for(int j = 0; j < a.size; j++){
                if(a.gameState[i][j] != b.gameState[i][j]){
                    diff++;
                }
            }
        }

        return diff;
    }

    //pretty prints game field. Can be used as a hash, since pretty-printed game field is unique
    public static String prettyPrint(GameState game){
        return Arrays
                .stream(game.gameState)
                .map(
                        row -> Arrays
                                .stream(row)
                                .mapToObj(x -> x == EMPTY_CELL ? " " : Integer.toString(x))
                                .collect(Collectors.joining("|"))
                )
                .collect(
                        Collectors.joining("\n" + "-".repeat(game.size * 2 - 1) + "\n")
                );
    }
}
