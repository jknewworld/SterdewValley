package com.P.model.Maps;
import com.P.model.Maps.Tile;
import com.P.model.Maps.Position;
import com.P.model.Maps.Tile;

import java.util.ArrayList;

import dev.morphia.annotations.Embedded;

@Embedded
public class Building {
    private  ArrayList<Tile> tiles;

    public Building() {
    }

    public Building(ArrayList<Tile> tiles) {
        this.tiles = tiles;
        for(Tile tile : tiles)
            tile.setInsideBuilding(true);
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public Tile getTileByCoordinate(Position position) {
        for(Tile tile : this.tiles)
            if(tile.getCoordinate().equals(position))
                return tile;
        return null;
    }
}
