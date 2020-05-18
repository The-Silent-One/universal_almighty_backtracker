/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author safou
 */
public class Core extends StateBasedGame {

    public Core(String gameName) {
        super(gameName);
        this.addState(new Menu(Vars.menuId));
        this.addState(new Settings(Vars.settingsId));
        this.addState(new Simulate(Vars.simulateId));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(Vars.menuId).init(gc, this);
        this.getState(Vars.settingsId).init(gc, this);
        this.getState(Vars.simulateId).init(gc, this);
        this.enterState(Vars.menuId);
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new Core(Vars.gameName));
            appgc.setDisplayMode(Vars.width, Vars.height, false);
            appgc.start();
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }
}
