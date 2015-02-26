/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package platformer.entities;

import java.util.ArrayList;

/**
 * A "firewall" that spins in a circular motion
 *
 * @author Joonas
 */
public class Firespinner {

    ArrayList<Spike> fireballs;
    float centerTileX, centerTileY;
    double counter;

    /**
     * create a spinning firewall
     *
     * @param centerTileX x coordinate of the center fireball
     * @param centerTileY y coordinate of the center fireball
     * @param collisionDamage collision damage
     */
    public Firespinner(int centerTileX, int centerTileY, int collisionDamage) {
        this.fireballs = new ArrayList<>();
        counter = 0;

        for (int i = 0; i < 3; i++) {
            fireballs.add(new Spike(collisionDamage, centerTileX, centerTileY, 7, 32, 32));
        }

        this.centerTileX = centerTileX;
        this.centerTileY = centerTileY;
    }

    /**
     * update the location of the fireballs
     *
     * @param delta milliseconds since last tick
     */
    public void update(int delta) {
        counter += 3 * (delta / 1000f);

        for (int i = 1; i < 3; i++) {
            fireballs.get(i).setLocation((float) (centerTileX + (32 * i) * Math.sin(counter)), (float) (centerTileY + (32 * i) * Math.cos(counter)));
        }
    }

    public ArrayList<Spike> getFireballs() {
        return fireballs;
    }

    public double getCounter() {
        return counter;
    }
}
