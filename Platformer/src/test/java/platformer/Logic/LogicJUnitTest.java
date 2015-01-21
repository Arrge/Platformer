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
import platformer.Logic.World;

/**
 *
 * @author Joonas
 */
public class LogicJUnitTest {

    World w;
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
        w = new World(null);
        w.getPlayer().setLocation(400, 250);
        w.addPlatform(new Entity(200, 300, 20, 400));
        platform = w.getPlatforms().get(0);
        p = w.getPlayer();
    }

    @After
    public void tearDown() {
    }

    //collision tests
    @Test
    public void FallsDownToPlatformAndStaysBetweenPlatformsWidth() {
        //run for 200 frames
        for (int i = 0; i < 200; i++) {
            w.applyGravity();
            w.checkForCollisions();
        }
        assertTrue(p.isOnPlatform() && p.getX() >= platform.getX() && p.getX() <= platform.getX() + platform.getWidth());
    }
    //check for Yaxis
}
