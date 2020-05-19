/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.awt.Font;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author safou
 */
public class Menu extends BasicGameState {

    Animation sheepAnim;
    
    private Button sim = new Button("Play",
            MainWindow.width / 2, 7.5f * MainWindow.height / 11,
            MainWindow.width / 2, MainWindow.height / 6,
            new Color(0.709f, 0.870f, 0.415f, 1));
    
    private Button exit = new Button("Exit",
            MainWindow.width / 2, 9.5f * MainWindow.height / 11, 
            MainWindow.width / 2, MainWindow.height / 6, 
            new Color(0.839f, 0.298f, 0.227f, 1));

    public Menu(int state) throws SlickException {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Image[] images = new Image[]{new Image("res/sheep.png",false),
            new Image("res/sheep1.png",false),
            new Image("res/sheep.png",false),
            new Image("res/sheep2.png",false)};
        this.sheepAnim = new Animation(images, 500);
        
        MainWindow.font = new TrueTypeFont(new Font(Font.SANS_SERIF, Font.BOLD, 40), true);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(new Color(0.231f, 0.921f, 0.909f, 1));
        
        this.sheepAnim.draw(MainWindow.width / 2 - sheepAnim.getWidth() / 2, MainWindow.height / 3 - sheepAnim.getHeight() / 2);
        
        sim.draw(g);
        exit.draw(g);
        
        g.drawString(MainWindow.mouse, 3*MainWindow.width/5, 29 * MainWindow.height / 30); //debug, to remove later
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        Input input = gc.getInput();
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        
        if (sim.isOnButton(xpos, ypos)) {
            if (input.isMousePressed(0)) {
                sbg.enterState(MainWindow.settingsId);
            }
        }
        
        if (exit.isOnButton(xpos, ypos)) {
            if (input.isMousePressed(0)) {
                System.exit(0);
            }
        }
        MainWindow.mouse = "{x : " + xpos + ", y : " + ypos + "}";

    }

    @Override
    public int getID() {
        return MainWindow.menuId;
    }

}
