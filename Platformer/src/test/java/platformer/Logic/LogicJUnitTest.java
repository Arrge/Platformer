package platformer.Logic;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.newdawn.slick.Input;
import platformer.Entities.Entity;
import platformer.Entities.Player;
import platformer.Logic.Logic;

/**
 *
 * @author Joonas
 */
public class LogicJUnitTest {

    Logic w;
    Entity platform;
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
        w.getPlayer().setLocation(400, 250);
        w.addPlatform(new Entity(200, 300,1,1, 20, 400));
        platform = w.getPlatforms().get(0);
        p = w.getPlayer();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void PlatformAddedCorrectly() {
        w.addPlatform(new Entity(200,300,1,1, 20, 400));
        assertTrue(w.getPlatforms().size() == 2);
    }

    //collision tests
    @Test
    public void FallsDownToPlatformAndStaysBetweenPlatformsWidth() {
        //run for 200 frames
        for (int i = 0; i < 200; i++) {
            w.applyGravity(17);
            p.move(17);
            w.checkForCollisions();
        }
        assertTrue(p.isOnPlatform() && p.xIsInsidePlatform(platform));
    }

    @Test
    public void fallsDownToPlatformAndStaysOnTopOfIt() {
        //run for 200 frames
        for (int i = 0; i < 200; i++) {
            w.applyGravity(17);
            p.move(17);
            w.checkForCollisions();
        }
        assertTrue(p.isOnPlatform() && p.getY() == platform.getY() - p.getHeight());
    }

    @Test
    public void pushesAwayFromPlatformWhenIntersectingFromUnder() {
        p.setLocation(300, 330);
        p.setY_vel(-20 * 60);
        p.setX_vel(2 * 60);
        w.applyGravity(17);
        p.move(17);
        w.checkForCollisions();
        //not going upwards & under platform
        assertTrue(p.getY_vel() >= 0 && p.getY() >= platform.getY() + platform.getHeight());
    }

    @Test
    public void PushesAwayFromPlatformWhenIntersectingFromLeft() {
        p.setLocation(190, 305);
        p.setY_vel(-1);
        p.setX_vel(20);
        w.applyGravity(17);
        p.move(17);
        w.checkForCollisions();
        assertTrue(!p.xIsInsidePlatform(platform));
    }

    @Test
    public void PushesAwayFromPlatformWhenIntersectingFromRight() {
        p.setLocation(610, 305);
        p.setY_vel(-1);
        p.setX_vel(-20);
        w.applyGravity(17);
        p.move(17);
        w.checkForCollisions();
        assertTrue(!p.xIsInsidePlatform(platform));
    }
}
