package com.mygdx.moos.Enums;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.HashMap;

public enum TileType {
    OCEAN1(1,TextureRegion.split(new Texture("tiles/sea/sprite.png"),64,64),0,7,true,true),
    OCEAN2(2,TextureRegion.split(new Texture("tiles/sea/sprite.png"),64,64),2,7,true,true),
    OCEAN3(3,TextureRegion.split(new Texture("tiles/sea/sprite.png"),64,64),4,7,true,true),
    OCEAN4(4,TextureRegion.split(new Texture("tiles/sea/sprite.png"),64,64),6,7,true,true),
    BOAT1(5, TextureRegion.split(new Texture("tiles/boatTex/boatTiles.png"),64,64),0,0,false,true),
    BOAT2(6, TextureRegion.split(new Texture("tiles/boatTex/boatTiles.png"),64,64),0,1,false,true),
    BOAT3(7, TextureRegion.split(new Texture("tiles/boatTex/boatTiles.png"),64,64),0,2,false,false),
    BOAT4(8, TextureRegion.split(new Texture("tiles/boatTex/boatTiles.png"),64,64),0,3,false,false),
    BEACHSIDE1(9, TextureRegion.split(new Texture("sprites/beach.png"),64,64),0,7,true,false),
    BEACHSIDE2(10, TextureRegion.split(new Texture("sprites/beach.png"),64,64),2,7,true,false),
    BEACHSIDE3(11, TextureRegion.split(new Texture("sprites/beach.png"),64,64),4,7,true,false),
    BEACHSIDE4(12, TextureRegion.split(new Texture("sprites/beach.png"),64,64),6,7,true,false),
    BEACHSIDE5(13, TextureRegion.split(new Texture("sprites/beach2.png"),64,64),0,7,true,false),
    BEACHSIDE6(14, TextureRegion.split(new Texture("sprites/beach2.png"),64,64),2,7,true,false),
    BEACHSIDE7(15, TextureRegion.split(new Texture("sprites/beach2.png"),64,64),4,7,true,false),
    BEACHSIDE8(16, TextureRegion.split(new Texture("sprites/beach2.png"),64,64),6,7,true,false),
    SAND(17, TextureRegion.split(new Texture("sprites/beach3.png"),64,64),0,0,false,false);

    ;

    private final int id;
    private final TextureRegion[][] textureRegions;
    private final int pos;

    private final int len;

    private final boolean animated;

    private static final HashMap<Integer, TileType> tileTypeMap;
    private final boolean movable;

    TileType(int id, TextureRegion[][] textureRegions, int pos, int len, boolean animated, boolean movable) {
        this.id = id;
        this.textureRegions = textureRegions;
        this.pos = pos;
        this.len = len;
        this.animated = animated;
        this.movable = movable;
    }

    //
    static {
        tileTypeMap = new HashMap<>();
        for (TileType tileType : TileType.values()) {
            tileTypeMap.put(tileType.getId(), tileType);
        }
    }

    public static TileType getTileTypeById (int id) {
        return tileTypeMap.get(id);
    }

    public int getId() {
        return id;
    }

    public TextureRegion[][] getTextureRegions() {
        return textureRegions;
    }

    public int getPos() {
        return pos;
    }

    public int getLen() {
        return len;
    }

    public boolean isAnimated() {
        return animated;
    }

    public static HashMap<Integer, TileType> getTileTypeMap() {
        return tileTypeMap;
    }
}
