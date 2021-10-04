package com.lab1.game;

import java.util.Arrays;
import java.util.Optional;

public class Utils {

    public static int[][] copy(int[][] arr){
        var copy = new int[arr.length][];
        for(int i = 0; i < arr.length; i++){
            copy[i] = Arrays.copyOf(arr[i], arr[i].length);
        }

        return copy;
    }

    public static Optional<int[][]> swapSafeImmutable(int[][] target, int[] a, int[] b){
        if(a.length != 2){
            throw new IllegalArgumentException("Tuple length must be 2");
        }

        if(b.length != 2){
            throw new IllegalArgumentException("Tuple length must be 2");
        }

        int i1 = a[0], j1 = a[1];
        int i2 = b[0], j2 = b[1];
        if(!isIndexInArray(target, i1, j1) || !isIndexInArray(target, i2, j2)){
            return Optional.empty();
        }

        var newArr = copy(target);
        newArr[i1][j1] = target[i2][j2];
        newArr[i2][j2] = target[i1][j1];

        return Optional.of(newArr);
    }

    private static boolean isIndexInArray(int[][] arr, int i, int j){
        return !(i < 0 || j < 0 || i >= arr.length || j >= arr.length);
    }
}
