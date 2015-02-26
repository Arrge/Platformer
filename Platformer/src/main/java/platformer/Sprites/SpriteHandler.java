/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.sprites;

import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TiledMap;
import platformer.entities.Collidable;
import platformer.entities.Entity;
import platformer.entities.Firespinner;
import platformer.entities.PatrollingEnemy;
import platformer.entities.Spike;

/**
 * Handles spritesheets and TILED maps (.tmx files)
 *
 * @author Joonas
 */
public class SpriteHandler {

    private SpriteSheet sprites;
    private TiledMap map;
    private ArrayList<Entity> maptiles;

    /**
     * create a new spritehandler and read all maps and spritesheets
     *
     * @throws SlickException
     */
    public SpriteHandler() throws SlickException {
        ClassLoader classLoader = getClass().getClassLoader();
        Image img = new Image("src/resources/tileset.png");
        map = new TiledMap(("src/resources/test1.tmx"));
        sprites = new SpriteSheet(img, 32, 32);
        maptiles = new ArrayList<>();

        readMaps();
    }

    /**
     * read all maps and save the position and spriteid of all entities
     */
    public void readMaps() {
        Entity entity;

        for (int layer = 0; layer < map.getLayerCount(); layer++) {
            for (int y = 0; y < map.getHeight(); y++) {

                for (int x = 0; x < map.getWidth(); x++) {

                    if (map.getTileId(x, y, layer) != 0) {

                        entity = new Entity(x * map.getTileWidth(), y * map.getTileWidth(), map.getTileId(x, y, layer) - 1, 32, 32);
                        maptiles.add(entity);
                    }

                }
            }
        }

    }

    /**
     * convert .tmx tileid to spritesheet coordinates
     *
     * @param id sprite id
     * @return return sprite Image that matches the sprite id
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
     *
     * @return Arraylist of firespinners
     */
    public ArrayList<Firespinner> getFirespinners() {
        ArrayList<Firespinner> result = new ArrayList<>();
        Firespinner fp;
        for (Entity e : maptiles) {
            if (e.getSpriteSheetId() == 7) {

                fp = new Firespinner((int) e.getX(), (int) e.getY(), 25);
                result.add(fp);
            }
        }
        return result;
    }

    /**
     * get all plaforms (entities that dont deal damage)
     *
     * @return arraylist of platforms
     */
    public ArrayList<Collidable> getMapPlatforms() {
        ArrayList<Collidable> result = new ArrayList<>();
        for (Entity e : maptiles) {
            if (e.getSpriteSheetId() == 0 || e.getSpriteSheetId() == 1) {
                result.add(e);
            }
        }
        return result;
    }

    /**
     * get all spikes
     *
     * @return arraylist of spikes
     */
    public ArrayList<Collidable> getSpikes() {
        ArrayList<Collidable> result = new ArrayList<>();

        for (Entity e : maptiles) {
            // if spike
            if (e.getSpriteSheetId() > 1 && e.getSpriteSheetId() < 6) {

                result.add(new Spike(25, e));

            }
        }
        return result;
    }

    /**
     * create enemies from a pairs of patrollingEnemy sprites
     *
     * @return arraylist containing all patrollingEnemies in the map
     */
    public ArrayList<PatrollingEnemy> getPatrollingEnemies() {
        int radiusMinX = -1;
        int radiusMaxX = -1;
        ArrayList<PatrollingEnemy> result = new ArrayList<>();
        Entity e;
        //patrolling enemytiles come in pairs. The first one is the minimum x coordinate of patrol radius and the second one is the maximum x coordinate
        for (int i = 0; i < maptiles.size(); i++) {
            e = maptiles.get(i);
            if (e.getSpriteSheetId() == 6) {
                if (radiusMinX == -1) {
                    radiusMinX = (int) e.getX();
                } else {
                    radiusMaxX = (int) e.getMaxX();
                    result.add(new PatrollingEnemy(25, radiusMinX, radiusMaxX, maptiles.get(i - 1)));
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

    public TiledMap getMap() {
        return map;
    }

}
