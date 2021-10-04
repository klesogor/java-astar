package com.lab1;

import com.lab1.game.GameState;
import com.lab1.routing.AstarParams;

import java.util.stream.Stream;

public class GameAstarImpl implements AstarParams<GameState> {
    @Override
    public Stream<GameState> getConnections(GameState state) {
        return state.getPossibleTurns();
    }

    @Override
    public double getNodesDistance(GameState a, GameState b) {
        return 1;
    }

    @Override
    public double getHeuiristicValue(GameState node, GameState target) {
        return GameState.diff(node, target) * 1000;
    }

    @Override
    public int getItemHash(GameState node) {
        return GameState.prettyPrint(node).hashCode();
    }

    @Override
    public boolean isSameNode(GameState a, GameState b) {
        return GameState.diff(a, b) == 0;
    }
}
