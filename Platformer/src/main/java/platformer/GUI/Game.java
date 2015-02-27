package platformer.GUI;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import platformer.Entities.Firespinner;
import platformer.Entities.PatrollingEnemy;
import platformer.Entities.Spike;
import platformer.Logic.Logic;
import platformer.Sprites.SpriteHandler;

/**
 * A game made using Slick2d
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
    private Logic logic;
    private SpriteHandler sh;
    private Image image;
    private int frame;

    public Game() {
        super("Platformer");
    }

    /**
     * draw all non moving entities
     */
    private void drawStationaryMapEntities() {
        sh.getMap().render(0 - (int) logic.getPlayer().getX_offset(), 0 - (int) logic.getPlayer().getY_offset(), 0);
    }

    /**
     * draw patrolling enemies
     *
     * @param g graphics object
     * @throws SlickException
     */
    private void drawPatrollingEnemies(Graphics g) throws SlickException {
        for (PatrollingEnemy p : logic.getPatrollingEnemies()) {
            g.drawImage(sh.getSprite(p.getSpriteSheetId()), p.getX() - logic.getPlayer().getX_offset(), p.getY() - logic.getPlayer().getY_offset());

        }
    }

    /**
     * set rotation and draw all firespinners
     *
     * @param g graphics object
     * @throws SlickException
     */
    private void drawFirespinners(Graphics g) throws SlickException {
        for (Firespinner fs : logic.getFirespinners()) {
            for (Spike s : fs.getFireballs()) {
                image = sh.getSprite(s.getSpriteSheetId());
                image.setCenterOfRotation(image.getWidth() / 2, image.getHeight() / 2);
                image.setRotation(frame * 2);
                g.drawImage(image, s.getX() - logic.getPlayer().getX_offset(), s.getY() - logic.getPlayer().getY_offset());
            }
        }
    }

    /**
     * set rotation and draw boss
     *
     * @throws SlickException
     */
    private void drawBoss() throws SlickException {
        image = sh.getSprite(logic.getBoss().getSpriteSheetId());
        image.setCenterOfRotation(image.getWidth() * logic.getBoss().getScale() / 2, image.getHeight() * logic.getBoss().getScale() / 2);
        image.setRotation((float) frame * (float) logic.getBoss().getX_vel() / 20f);

        image.draw(logic.getBoss().getX() - logic.getPlayer().getX_offset(), logic.getBoss().getY() - logic.getPlayer().getY_offset(), logic.getBoss().getScale());
    }

    /**
     * set the opacity (to indicate if player is on immunity timer) and draw the
     * player and health
     *
     * @param g
     */
    private void drawPlayer(Graphics g) {
        g.setColor(new Color(logic.getPlayer().getOpacity(), 128, 128, 255));
        //g.drawImage(sprite, w.getPlayer().getX() - w.getPlayer().getX_offset(), w.getPlayer().getY() - w.getPlayer().getY_offset());
        g.fillRect(logic.getPlayer().getX() - logic.getPlayer().getX_offset(), logic.getPlayer().getY() - logic.getPlayer().getY_offset(), logic.getPlayer().getWidth(), logic.getPlayer().getHeight());
        //player coords for debug
        g.setColor(Color.red);
        g.drawString("health: " + logic.getPlayer().getHealth(), 40, 40);
    }

    /**
     * increase frame number and call all drawing methods
     *
     * @param container
     * @param g
     * @throws SlickException
     */
    public void render(GameContainer container, Graphics g) throws SlickException {
        frame++;
        //render stationary objects
        drawStationaryMapEntities();
        //draw boss
        drawBoss();
        //draw patrolling enemies
        drawPatrollingEnemies(g);
        //draw firespinners
        drawFirespinners(g);
        //draw player
        drawPlayer(g);

    }

    @Override
    public void init(GameContainer container) throws SlickException {
        logic = new Logic(container.getInput());
        sh = new SpriteHandler();
        logic.addPlatformArrayList(sh.getMapPlatforms());
        logic.addDamagingCollidablesArrayList(sh.getSpikes());
        logic.addPatrollingEnemiesArrayList(sh.getPatrollingEnemies());
        logic.addFirespinnerArrayList(sh.getFirespinners());
        frame = 0;

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        logic.update(delta);
    }

    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.setTargetFrameRate(60);
        app.start();
    }

}
