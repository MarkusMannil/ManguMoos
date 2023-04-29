package com.mygdx.moos;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.tiles.AnimatedTiledMapTile;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.mygdx.moos.noise.OpenSimplex2S;


public class MegaTile {


    final int MEGATILE_SIZE = 30;
    final long SEED = 0;
    final double FREQUENCY = 1.0 / (24.0 * 1);
    int mega_x;
    int mega_y;

    int TILE_SIZE = 64;
    private final TextureRegion[][] textureRegions = TextureRegion.split(new Texture("tiles/sea/sprite.png"), TILE_SIZE, TILE_SIZE);

    public MegaTile(int mega_x, int mega_y) {
        this.mega_x = mega_x;
        this.mega_y = mega_y;
    }

    public int[][] generateMap() {
        int[][] map = new int[MEGATILE_SIZE][MEGATILE_SIZE];
        for (int i = 0; i < MEGATILE_SIZE; i++) {
            for (int j = 0; j < MEGATILE_SIZE; j++) {
                double noise1 = OpenSimplex2S.noise2(SEED, (MEGATILE_SIZE * mega_x + i) * FREQUENCY, (MEGATILE_SIZE * mega_y + j) * FREQUENCY);
                double noise2 = OpenSimplex2S.noise2(SEED, (MEGATILE_SIZE * mega_x + i) * (FREQUENCY / 24), (MEGATILE_SIZE * mega_y + j) * (FREQUENCY / 24));
                double noise3 = OpenSimplex2S.noise2(SEED, (MEGATILE_SIZE * mega_x + i) * (FREQUENCY / 48), (MEGATILE_SIZE * mega_y + j) * (FREQUENCY / 48));
                double noise = (noise1 + noise2) / 2;

                map[i][j] = noiseToTile(noise);
            }
        }
        return map;
    }

    private int noiseToTile(double noise) {
        int val;

        if (noise > 0.5) {
            val = 1;
        } else if (noise < 0.5 && noise > 0) {
            val = 2;
        } else if (noise < 0 && noise > -0.5) {
            val = 3;
        } else val = 4;

        return val;
    }

    public TiledMapTileLayer generateMapLayer() {
        int[][] map = generateMap();
        // new layer
        TiledMapTileLayer layer = new TiledMapTileLayer(MEGATILE_SIZE * mega_x + MEGATILE_SIZE, MEGATILE_SIZE * mega_y + MEGATILE_SIZE, TILE_SIZE, TILE_SIZE);
        // tile textures
        // for coordinate in map create cell and add it to a layer
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                TiledMapTileLayer.Cell cell = new TiledMapTileLayer.Cell();
                int tile_id = map[i][j];

                Array<StaticTiledMapTile> tiles = new Array<StaticTiledMapTile>();
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 7; l++) {
                        tiles.add(new StaticTiledMapTile(textureRegions[k + (tile_id-1) * 2][l]));
                    }
                }


                //TiledMapTile tile = new StaticTiledMapTile(textureRegions[0][tile_id]);
                AnimatedTiledMapTile animaTile = new AnimatedTiledMapTile(0.1f, tiles);
                cell.setTile(animaTile);


                layer.setCell(MEGATILE_SIZE * mega_x + i, MEGATILE_SIZE * mega_y + j, cell);
            }
        }

        return layer;
    }

}
