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
import platformer.Entities.Firespinner;
import platformer.Entities.PatrollingEnemy;
import platformer.Entities.Spike;

/**
 * Handles spritesheets and TILED maps (.tmx files)
 * @author Joonas
 */
public class SpriteHandler {

    private SpriteSheet sprites;
    private ArrayList<TiledMap> maps;
    private ArrayList<ArrayList<Entity>> maptiles;

    /**
     * read all maps and spritesheets
     * @throws SlickException 
     */
    public SpriteHandler() throws SlickException {
        Image img = new Image("src/resources/tileset.png");
        maps = new ArrayList<>();
        maps.add(new TiledMap("src/resources/test1.tmx"));
        sprites = new SpriteSheet(img, 32, 32);
        maptiles = new ArrayList<>();
        
        readMaps();  
    }
    /**
     * read all maps and save the position and spriteid of all entities
     */
    public void readMaps() {
        Entity entity;
        for (int i = 0; i < maps.size(); i++) {
            maptiles.add(new ArrayList<Entity>());
            
            for (int layer = 0; layer < maps.get(i).getLayerCount(); layer++) {
                for (int y = 0; y < maps.get(i).getHeight(); y++) {

                    for (int x = 0; x < maps.get(i).getWidth(); x++) {

                        if (maps.get(i).getTileId(x, y, layer) != 0) {
                            
                            entity = new Entity(x * maps.get(i).getTileWidth(), y * maps.get(i).getTileWidth(), maps.get(i).getTileId(x, y, layer)-1, 32, 32);
                            maptiles.get(i).add(entity);
                        }

                    }
                }
            }
        }
    }
    /**
     * convert .tmx tileid to spritesheet coordinates
     * @param id
     * @return 
     */
    public Image getSprite(int id) throws SlickException {
        if (id == 9) {
            return new Image("src/resources/boss.png"); 
        }
        id = id;
        int x = id % 10;
        int y = (id - (id % 10)) / 10;
        return sprites.getSprite(x, y);
    }

    
    
    
    /**
     * get all firespinners in map
     * @param mapid level id (0 is first)
     * @return 
     */
    public ArrayList<Firespinner> getFirespinners(int mapid) {
        ArrayList<Firespinner> result = new ArrayList<>();
        Firespinner fp;
        for (Entity e : maptiles.get(mapid)) {
            if (e.getSpriteSheetId() == 7) {
                
                fp = new Firespinner((int)e.getX(), (int)e.getY(), 25);
                result.add(fp);
            }
        }
        return result;
    }
    
    /**
     * get all plaforms (entities that dont deal damage)
     * @param mapid
     * @return 
     */
    public ArrayList<Collidable> getMapPlatforms(int mapid) {
        ArrayList<Collidable> result = new ArrayList<>();
        for (Entity e : maptiles.get(mapid)) {
            if (e.getSpriteSheetId() == 0 || e.getSpriteSheetId() == 1) {
                result.add(e);
            }
        }
        return result;
    }
    /**
     * get all spikes
     * @param mapid
     * @return 
     */
    public ArrayList<Collidable> getSpikes(int mapid) {
        ArrayList<Collidable> result = new ArrayList<>();
        
        for (Entity e : maptiles.get(mapid)) {
            // if spike
            if (e.getSpriteSheetId() > 1 && e.getSpriteSheetId() < 6) {
                
                result.add(new Spike(25, e));
                
            }
        }
        return result;
    }
    /**
     * create enemies from a pairs of patrollingEnemy sprites
     * @param mapid
     * @return arraylist containing all patrollingEnemies in the map
     */
    public ArrayList<PatrollingEnemy> getPatrollingEnemies(int mapid) {
        int radiusMinX = -1;
        int radiusMaxX = -1;
        ArrayList<PatrollingEnemy> result = new ArrayList<>();
        Entity e;
        //patrolling enemytiles come in pairs. The first one is the minimum x coordinate of patrol radius and the second one is the maximum x coordinate
        for (int i = 0; i < maptiles.get(mapid).size();i++) {
            e = maptiles.get(mapid).get(i);
            if (e.getSpriteSheetId() == 6) {
                if (radiusMinX == -1) {
                    radiusMinX = (int)e.getX();
                } else {
                    radiusMaxX = (int)e.getMaxX();
                    result.add(new PatrollingEnemy(25, radiusMinX, radiusMaxX, maptiles.get(mapid).get(i-1)));
                    radiusMinX = -1;
                    radiusMaxX = -1;
                }
            }
        }
        return result;
    }

    public SpriteSheet getSprites() {
        return sprites;
    }
    
    public ArrayList<TiledMap> getMaps() {
        return maps;
    }
}
