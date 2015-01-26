package platformer.GUI;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import platformer.Entities.Entity;
import platformer.Logic.Logic;
import platformer.Sprites.SpriteHandler;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {

    /**
     * Screen width
     */
    private static final int WIDTH = 800;
    /**
     * Screen height
     */
    private static final int HEIGHT = 600;
    private Logic w;
    private SpriteHandler sh;

    /**
     * A counter...
     */
    public Game() {
        super("Platformer");
    }

    public void render(GameContainer container, Graphics g) throws SlickException {

        g.setColor(Color.white);
        //draw platforms
        Image sprite;
        for (Entity p : w.getPlatforms()) {
            g.setColor(p.getC());
            sprite = sh.getSprite(p.getSpriteX(), p.getSpriteY());
            g.drawImage(sprite, p.getX() - w.getPlayer().getX_offset(), p.getY() - w.getPlayer().getY_offset());
            //g.fillRect(p.getX() - w.getPlayer().getX_offset(), p.getY() - w.getPlayer().getY_offset(), p.getWidth(), p.getHeight());
            g.setColor(Color.red);
        }
        //draw player
        g.setColor(Color.red);
        sprite = sh.getSprite(w.getPlayer().getSpriteX(), w.getPlayer().getSpriteY());
        g.drawImage(sprite, w.getPlayer().getX() - w.getPlayer().getX_offset(), w.getPlayer().getY() - w.getPlayer().getY_offset());
        //g.fillRect(w.getPlayer().getX() - w.getPlayer().getX_offset(), w.getPlayer().getY() - w.getPlayer().getY_offset(), w.getPlayer().getWidth(), w.getPlayer().getHeight());
        //player coords for debug
        g.drawString(" " + w.getPlayer().getX() + ", " + w.getPlayer().getY(), w.getPlayer().getX() - w.getPlayer().getX_offset(), w.getPlayer().getY() - w.getPlayer().getY_offset());

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        w = new Logic(container.getInput());
        sh = new SpriteHandler();

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        w.update(delta);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.setTargetFrameRate(60);
        app.start();
    }

}
