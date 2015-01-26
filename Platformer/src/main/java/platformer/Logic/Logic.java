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
public class Logic {

    private ArrayList<Entity> platforms;
    private ArrayList<Enemy> enemies;
    private Input input;
    private Player player;

    //get input class from 
    public Logic(Input input) {

        this.player = new Player(80, 80, 1, 0, 32, 32);
        this.input = input;
        this.platforms = new ArrayList<Entity>();
        this.enemies = new ArrayList<Enemy>();
    }

    public void update(int delta) {
        checkInput();
        applyGravity(delta);

        player.move(delta);
        checkForCollisions();
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

    public Player getPlayer() {
        return player;
    }

    public ArrayList<Entity> getPlatforms() {
        return platforms;
    }

    public void addPlatform(Entity p) {
        platforms.add(p);
    }

    public void applyGravity(int delta) {
        player.applyGravityAndVelocity(delta);
    }

    public void checkForCollisions() {

        for (Entity platform : platforms) {
            if (player.getHitbox().intersects(platform.getHitbox())) {
                //if intersecting, set platform color to green
                platform.setC(Color.green);
                //if intersecting from left and player Y coordinate is between a platform
                if (player.getX_old() < platform.getX() && player.yIsInsidePlatform(platform)) {
                    player.setLocation(platform.getX() - player.getWidth() - 2.5f, player.getY());
                    player.setX_vel(0);
                } //if intersecting from right and player Y coordinate is between a platform
                else if (player.getX_old() > platform.getX() + platform.getWidth() && player.yIsInsidePlatform(platform)) {
                    player.setLocation(platform.getX() + platform.getWidth() + 2.5f, player.getY());
                    player.setX_vel(0);
                } //if falling from above && player x coordinate is inside platform
                else if (player.getY_old() < platform.getY() && player.xIsInsidePlatform(platform) && player.isFalling()) {
                    player.setLocation(player.getX(), platform.getY() - player.getHeight());
                    player.setOnPlatform(true);
                    player.setY_vel(0);
                } //if coming towards platform from under && player x coordinate is inside platform
                else if (player.getY_old() > platform.getY() + platform.getHeight() && player.xIsInsidePlatform(platform) && player.isFlying()) {
                    player.setLocation(player.getX(), platform.getY() + platform.getHeight());
                    player.setY_vel(0);
                }
            } else {
                //if not intersecting set platform color to white
                platform.setC(Color.white);
            }
        }
    }
}
