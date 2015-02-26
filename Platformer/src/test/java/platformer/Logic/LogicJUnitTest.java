package platformer.logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import platformer.entities.Collidable;
import platformer.entities.Entity;
import platformer.entities.Firespinner;
import platformer.entities.Player;
import platformer.entities.Spike;

/**
 *
 * @author Joonas
 */
public class LogicJUnitTest {

    Logic w;
    Collidable platform;
    Player p;

    public LogicJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        //no need to listen to input on automated tests so set Input to null
        w = new Logic(null);
        w.getPlayer().setLocation(40, 0);
        w.addPlatform(new Entity(32, 100, 1, 32, 32));
        w.addPlatform(new Spike(25, 300, 100, 4, 32, 32));
        w.addPlatform(new Spike(25, new Entity(320, 100, 1, 32, 32)));
        platform = w.getPlatforms().get(0);
        p = w.getPlayer();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void takesDamageFromSpike() {
        int health = p.getHealth();
        p.setLocation(300, 99);
        p.setY_vel(4 * 60);
        w.applyGravity(17);
        p.move(17);
        w.checkForCollisions(w.getPlatforms());
        assertTrue(p.getHealth() < health);
    }

    @Test
    public void PlatformAddedCorrectly() {
        int size = w.getPlatforms().size();
        w.addPlatform(new Entity(32, 100, 1, 32, 32));
        assertTrue(w.getPlatforms().size() == size + 1);
    }

    //collision tests
    @Test
    public void FallsDownToPlatformAndStaysBetweenPlatformsWidth() {
        //run for 200 frames
        for (int i = 0; i < 200; i++) {
            w.applyGravity(17);
            p.move(17);
            w.checkForCollisions(w.getPlatforms());
        }
        assertTrue(p.getX() >= platform.getX() && p.getMaxX() <= platform.getMaxX());
    }

    @Test
    public void fallsDownToPlatformAndStaysOnTopOfIt() {
        //run for 200 frames
        for (int i = 0; i < 200; i++) {
            w.applyGravity(17);
            p.move(17);
            w.checkForCollisions(w.getPlatforms());
        }

        assertTrue(p.getMaxY() < platform.getY() && p.getX_vel() == 0 && p.getY_vel() < 5 && p.isOnPlatform());
    }

    @Test
    public void pushesAwayFromPlatformWhenIntersectingFromUnder() {
        p.setLocation(p.getX(), 102 + 32);
        p.setY_vel(-20 * 60);

        w.applyGravity(17);
        p.move(17);
        w.checkForCollisions(w.getPlatforms());

        //not going upwards & under platform
        assertTrue(p.getY_vel() >= 0 && p.getY() >= platform.getMaxY());
    }

    @Test
    public void PushesAwayFromPlatformWhenIntersectingFromLeft() {
        p.setLocation(32 - p.getWidth() - 2, 100);
        p.setX_vel(5 * 60);
        w.applyGravity(17);
        p.move(17);
        w.checkForCollisions(w.getPlatforms());
        assertTrue(p.getMaxX() < platform.getX() && p.getX_vel() == 0);
    }

    @Test
    public void PushesAwayFromPlatformWhenIntersectingFromRight() {
        p.setLocation(64 + 2, 100);
        p.setX_vel(-5 * 60);
        w.applyGravity(17);
        p.move(17);
        w.checkForCollisions(w.getPlatforms());
        assertTrue(p.getX() > platform.getMaxX() && p.getX_vel() == 0);
    }

    @Test
    public void playerLosesHealthButDoesntMoveWhenCheckingForOnlyCollisionDamage() {
        int health = p.getHealth();
        p.setLocation(0, 0);
        w.addDamagingCollidable(new Spike(25, 0, 0, 4, 32, 32));
        w.checkOnlyCollisionDamage(w.getDamagingCollidables());
        assertTrue(health != p.getHealth() && p.getX() == 0);
    }

    @Test
    public void BossStartsWhenPlayerIsAtBossFightHeight() {
        p.setLocation(0, w.getBoss().getStartposY() + 10);
        w.checkForBossFight();
        assertTrue(w.getBoss().isStarted());
    }

    @Test
    public void FirespinnersDealDamage() {
        int health = p.getHealth();
        ArrayList<Firespinner> list = new ArrayList<>();
        list.add(new Firespinner(0, 32, 1));
        w.addFirespinnerArrayList(list);
        p.setLocation(0, 0);
        for (int i = 0; i < 101; i++) {
            w.applyGravity(17);
            p.move(17);
            w.checkFirespinners();
            if (p.getHealth() < health) {
                break;
            }
        }
        assertTrue(p.getHealth() < health);
    }

    @Test
    public void checkCollisionDetectionTresholdValues() {
        p.setLocation(0, 0);
        p.move(0);
        p.getHitbox().setWidth(32);
        p.getHitbox().setHeight(32);
        platform = new Entity(0, 0, 1, 32, 32);
        w.addPlatform(platform);
        System.out.println(p.getX() + " *** " + p.getY());
        w.checkForCollisions(w.getPlatforms());
        System.out.println(p.getX() + " *** " + p.getY());
        assertTrue(p.getX() == 0 && p.getY() == 0);
    }
}
