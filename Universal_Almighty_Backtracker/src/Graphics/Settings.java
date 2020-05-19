/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author safou
 */
public class Settings extends BasicGameState {

    private static final int id = 1;

    public Settings(int state) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }

    @Override
    public int getID() {
        return this.id;
    }

}
