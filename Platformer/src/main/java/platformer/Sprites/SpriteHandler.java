/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Sprites;

import java.net.URL;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.tiled.TileSet;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Joonas
 */
public class SpriteHandler {
    
    SpriteSheet sprites;
    ArrayList<TiledMap> maps;
    int size;
    
    public SpriteHandler() throws SlickException {
        Image img = new Image("src/resources/tileset.png");
        sprites = new SpriteSheet(img, 32, 32);
        
    }
    
    public Image getSprite(int x, int y) {
        
        return sprites.getSprite(x, y);
    }
    
}
