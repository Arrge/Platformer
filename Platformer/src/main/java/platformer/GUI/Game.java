package platformer.GUI;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import platformer.Entities.Entity;
import platformer.Logic.Logic;

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

    /**
     * A counter...
     */
    public Game() {
        super("Platformer");
    }

    public void render(GameContainer container, Graphics g) throws SlickException {

        g.setColor(Color.white);
        //draw platforms
        for (Entity p : w.getPlatforms()) {
            g.setColor(p.getC());
            g.fill(p.getHitbox());
            g.setColor(Color.red);
            //platform coords for debug
            g.drawString(" " + p.getX() + ", " + p.getY(), p.getX(), p.getY());
        }
        //draw player
        g.setColor(Color.red);
        g.fill(w.getPlayer().getHitbox());
        //player coords for debug
        g.drawString(" " + w.getPlayer().getX() + ", " + w.getPlayer().getY(), w.getPlayer().getX(), w.getPlayer().getY());

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        w = new Logic(container.getInput());
        //debug platforms
        w.addPlatform(new Entity(0, 400, 20, 350));
        w.addPlatform(new Entity(550, 400, 20, 60));
        w.addPlatform(new Entity(350, 300, 200, 200));
        w.addPlatform(new Entity(400, 200, 20, 200));
        //block off the sides
        w.addPlatform(new Entity(0, 0, 20, 800));
        w.addPlatform(new Entity(0, 0, 800, 20));
        w.addPlatform(new Entity(780, 0, 800, 20));
        w.addPlatform(new Entity(0, 580, 20, 800));
        ////////////////////////////////////////////
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
