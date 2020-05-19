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

    public Core(String gameName) throws SlickException {
        super(gameName);
        this.addState(new Menu(MainWindow.menuId));
        this.addState(new Settings(MainWindow.settingsId));
        this.addState(new Simulate(MainWindow.simulateId));
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(MainWindow.menuId).init(gc, this);
        this.getState(MainWindow.settingsId).init(gc, this);
        this.getState(MainWindow.simulateId).init(gc, this);
        this.enterState(MainWindow.menuId);
        gc.setAlwaysRender(true);
        gc.setTargetFrameRate(60);
        gc.setVSync(true);
        
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new Core(MainWindow.gameName));
            appgc.setDisplayMode(MainWindow.width, MainWindow.height, false);
            appgc.start();
        } catch (SlickException ex) {
            ex.printStackTrace();
        }
    }
}
