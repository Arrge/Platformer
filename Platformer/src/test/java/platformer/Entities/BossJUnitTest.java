/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.entities;

import platformer.Entities.Player;
import platformer.Entities.Boss;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import platformer.Logic.Logic;

/**
 *
 * @author Joonas
 */
public class BossJUnitTest {

    Boss boss;
    Logic w;
    Player p;

    public BossJUnitTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        w = new Logic(null);
        boss = w.getBoss();
        p = w.getPlayer();
    }

    @After
    public void tearDown() {
    }

    @Test
    public void bossDoesntMoveBeforeEventStarts() {
        boss.update(100);
        assertTrue(!boss.isStarted() && boss.getX() == boss.getStartposX() && boss.getY() == boss.getStartposY());
    }

    @Test
    public void bossMovesAfterEventStarts() {
        boss.start();
        boss.update(100);
        assertTrue(boss.isStarted() && boss.getX() != boss.getStartposX() && boss.getY() == boss.getStartposY() && boss.getX_vel() > 100);
    }

    @Test
    public void bossStartsDeathAnimationWhenItReachesEndPos() {
        boss.start();
        boss.setLocation(boss.getEndposX(), boss.getStartposY());
        boss.update(100);
        assertTrue(boss.getTime() > 0 && boss.getScale() < 1 && boss.getScale() > 0 && boss.getX_vel() > 100);
    }

    @Test
    public void bossResetsWhenReset() {
        boss.start();
        boss.setLocation(boss.getEndposX(), boss.getStartposY());
        boss.update(100);
        boss.reset();
        assertTrue(!boss.isStarted() && boss.getX() == boss.getStartposX() && boss.getY() == boss.getStartposY() && boss.getTime() == 0 && !boss.isStarted() && boss.getX_vel() == 0 && boss.getScale() == 1);
    }

    @Test
    public void bossScaleDoesntGoNegative() {
        boss.start();
        boss.setLocation(boss.getEndposX(), boss.getStartposY());
        boss.update(100);
        boss.update(100000);
        assertTrue(boss.getScale() == 0);
    }
}
