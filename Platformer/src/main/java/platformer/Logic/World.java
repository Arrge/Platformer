/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.Logic;

import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Input;
import platformer.Entities.*;

/**
 *
 * @author Joonas
 */
public class World {

    private ArrayList<Entity> platforms;
    private ArrayList<Enemy> enemies;
    private Input input;
    private Player player;

    //get input class from 
    public World(Input input) {
        this.input = input;
        this.player = new Player(200, 30, 20, 10);
        this.platforms = new ArrayList<Entity>();
        this.enemies = new ArrayList<Enemy>();
    }

    public void update(int delta) {
        checkInput();
        applyGravity();
        checkForCollisions();
    }

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getPlatforms() {
        return platforms;
    }

    public void addPlatform(Entity p) {
        platforms.add(p);
    }

    public void checkInput() {
        if (input.isKeyDown(Input.KEY_A)) {
            player.goLeft();
        }
        if (input.isKeyDown(Input.KEY_D)) {
            player.goRight();
        }
        if (input.isKeyDown(Input.KEY_SPACE)) {
            player.jump();
        }
        if (input.isKeyDown(Input.KEY_ESCAPE)) {
            player.setLocation(200, 200);
        }

    }

    public void applyGravity() {
        player.applyGravity();
    }

    public void checkForCollisions() {
        player.move();
        for (Entity p : platforms) {
            if (player.getHitbox().intersects(p.getHitbox())) {
                //if intersecting, set platform color to green
                p.setC(Color.green);
                //if intersecting from left and player Y coordinate is between a platform
                if (player.getX_old() < p.getX() && player.yIsInsidePlatform(p)) {
                    player.setLocation(p.getX() - player.getWidth() - 2, player.getY());
                    player.setX_vel(0);
                } //if intersecting from right and player Y coordinate is between a platform
                else if (player.getX_old() > p.getX() + p.getWidth() && player.yIsInsidePlatform(p)) {
                    player.setLocation(p.getX() + p.getWidth() + 2, player.getY());
                    player.setX_vel(0);
                } //if falling from above && player x coordinate is inside platform
                else if (player.getY_old() < p.getY() && player.xIsInsidePlatform(p)) {
                    player.setLocation(player.getX(), p.getY() - player.getHeight());
                    player.setOnPlatform(true);
                    player.setY_vel(0);
                } //if coming towards platform from under && player x coordinate is inside platform
                else if (player.getY_old() > p.getY() + p.getHeight() && player.xIsInsidePlatform(p)) {
                    player.setLocation(player.getX(), p.getY() + p.getHeight());
                    player.setY_vel(0);
                }
            } else {
                //if not intersecting set platform color to white
                p.setC(Color.white);
            }
        }
    }
}
