/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.awt.Font;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author safou
 */
public class Menu extends BasicGameState {
    private static final int id = 0;
    private Button sim = new Button("Simulate",200, 100, 200, 100,new Color(0.709f, 0.870f, 0.415f, 1));
    private Button exit = new Button("Exit",200, 200, 200, 100,new Color(0.839f, 0.298f, 0.227f, 1));
    public Menu(int state) {
    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Vars.font = new TrueTypeFont(new Font(Font.SANS_SERIF, Font.BOLD, 40), true);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        sim.draw(g);
        exit.draw(g);
        g.drawString(Vars.mouse, 200, 300);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        Vars.mouse = "{x : "+xpos+", y : "+ypos+"}";
        
    }

    @Override
    public int getID() {
        return this.id;
    }
    
    
}
