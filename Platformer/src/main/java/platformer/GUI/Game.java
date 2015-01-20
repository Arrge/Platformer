package platformer.GUI;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

/**
 * A game using Slick2d
 */
public class Game extends BasicGame {

    /** Screen width */
    private static final int WIDTH = 800;
    /** Screen height */
    private static final int HEIGHT = 600;
    
    /** A counter... */
    private double counter;

    public Game() {
        super("Platformer");
    }

    public void render(GameContainer container, Graphics g) throws SlickException {
        double x = Math.sin(counter)*50+400;
        double y = Math.cos(counter)*80+300;
        g.drawString("xd",(int)x,(int)y);
        
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        counter = 0;
    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        counter+= 0.1;
    }
    
    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(WIDTH, HEIGHT, false);
        app.setForceExit(false);
        app.start();
    }

}
