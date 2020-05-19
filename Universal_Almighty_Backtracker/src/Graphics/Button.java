/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 *
 * @author safou
 */
public class Button {

    float x;
    float y;
    float width;
    float height;
    String name;
    int cornerRadius = 10;
    Color c;

    public Button(String name, float x, float y, float width, float height, Color c) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.c = c;

    }

    public void draw(Graphics g) {

        g.setColor(this.c);
        g.drawRoundRect(this.x - this.width / 2, this.y - this.height / 2, this.width, this.height, this.cornerRadius);
        g.fillRoundRect(this.x - this.width / 2, this.y - this.height / 2, this.width, this.height, this.cornerRadius);

        g.setColor(Color.white);
        float h = MainWindow.font.getHeight(this.name);
        float w = MainWindow.font.getWidth(this.name);

        MainWindow.font.drawString(this.x - w / 2, this.y - h / 2, this.name);

    }

    public boolean isOnButton(int xpos, int ypos) {
        ypos = MainWindow.height - ypos;
        return xpos > this.x - this.width / 2 && xpos < this.x + this.width / 2 && ypos > this.y - this.height / 2&& ypos < this.y + this.height / 2;
    }

}
