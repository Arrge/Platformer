/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Sprites;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;
import platformer.Entities.Collidable;
import platformer.Entities.Entity;
import platformer.Entities.Spike;

/**
 *
 * @author Joonas
 */
public class SpriteHandler {

    private SpriteSheet sprites;
    private ArrayList<TiledMap> maps;
    private ArrayList<ArrayList<Entity>> maptiles;

    public SpriteHandler() throws SlickException {
        Image img = new Image("src/resources/tileset.png");
        maps = new ArrayList<>();
        maps.add(new TiledMap("src/resources/test.tmx"));
        sprites = new SpriteSheet(img, 32, 32);
        maptiles = new ArrayList<>();

        Entity entity;

        // save all tiles to maptiles
        for (int i = 0; i < maps.size(); i++) {
            maptiles.add(new ArrayList<Entity>());

            for (int x = 0; x < maps.get(i).getWidth(); x++) {

                for (int y = 0; y < maps.get(i).getHeight(); y++) {

                    if (maps.get(i).getTileId(x, y, 0) != 0) {
                        entity = new Entity(x * maps.get(i).getTileWidth(), y * maps.get(i).getTileWidth(), maps.get(i).getTileId(x, y, 0), 32, 32);
                        maptiles.get(i).add(entity);
                    }

                }
            }
        }
    }

    public Image getSprite(int id) {
        //convert .tmx tileid to spritesheet coordinates
        id = id + 1;
        int x = id % 10;
        int y = (id - (id % 10)) / 10;

        return sprites.getSprite(x, y);
    }

    public ArrayList<TiledMap> getMaps() {
        return maps;
    }

    public ArrayList<Collidable> getMapPlatforms(int i) {
        ArrayList<Collidable> result = new ArrayList<>();
        Entity entity;
        
        for (Entity e : maptiles.get(i)) {
            if (e.getSpriteSheetId() == 0 || e.getSpriteSheetId() == 1) {
                result.add(e);
            }
        }
        return result;
    }

    public ArrayList<Collidable> getSpikes(int i) {
        ArrayList<Collidable> result = new ArrayList<>();
        Entity entity;
        
        for (Entity e : maptiles.get(i)) {
            // if spike
            if (e.getSpriteSheetId() > 2 && e.getSpriteSheetId() < 7) {
                
                result.add(new Spike(25, e));
                
            }
        }
        return result;
    }

    public SpriteSheet getSprites() {
        return sprites;
    }
    

}
