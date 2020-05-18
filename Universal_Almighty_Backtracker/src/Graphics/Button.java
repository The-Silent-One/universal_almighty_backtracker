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
        float h = Vars.font.getHeight(this.name);
        float w = Vars.font.getWidth(this.name);

        Vars.font.drawString(this.x - w / 2, this.y - h / 2, this.name);

    }

}
