package platformer.GUI;

import org.lwjgl.opencl.APPLESetMemObjectDestructor;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import platformer.Entities.Entity;
import platformer.Entities.Firespinner;
import platformer.Entities.PatrollingEnemy;
import platformer.Entities.Spike;
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
        sh.getMaps().get(0).render(0 - (int) w.getPlayer().getX_offset(), 0 - (int) w.getPlayer().getY_offset(),0);

        for (PatrollingEnemy p : w.getPatrollingEnemies()) {
            g.drawImage(sh.getSprite(p.getSpriteSheetId()), p.getX() - w.getPlayer().getX_offset(), p.getY() - w.getPlayer().getY_offset());
            
        }
        
        for (Firespinner fs : w.getFirespinners()) {
            for (Spike s : fs.getFireballs()) {
                g.drawImage(sh.getSprite(s.getSpriteSheetId()), s.getX() - w.getPlayer().getX_offset(), s.getY() - w.getPlayer().getY_offset());
            }
        }
        
        g.setColor(new Color(w.getPlayer().getOpacity(), 128, 128, 255));
        //g.drawImage(sprite, w.getPlayer().getX() - w.getPlayer().getX_offset(), w.getPlayer().getY() - w.getPlayer().getY_offset());
        g.fillRect(w.getPlayer().getX() - w.getPlayer().getX_offset(), w.getPlayer().getY() - w.getPlayer().getY_offset(), w.getPlayer().getWidth(), w.getPlayer().getHeight());
        //player coords for debug
        g.setColor(Color.red);
        g.drawString("health: "+ w.getPlayer().getHealth(), 40, 40);

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        w = new Logic(container.getInput());
        sh = new SpriteHandler();
        w.addPlatformArrayList(sh.getMapPlatforms(0));
        w.addDamagingCollidablesArrayList(sh.getSpikes(0));
        w.addPatrollingEnemiesArrayList(sh.getPatrollingEnemies(0));
        w.addFirespinnerArrayList(sh.getFirespinners(0));

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
