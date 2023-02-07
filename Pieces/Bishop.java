package Chess.Pieces;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;

public class Bishop extends Piece {
    public Bishop(boolean White, int xPos)
    {
        if(White)
        {
            this.xPos = xPos;
            yPos = 7;
            icon = new ImageIcon("Sprites/bishopW.png");
            label = new JLabel();
            label.setOpaque(true);
            label.setIcon(icon);
            label.setBounds(xPos*64,yPos*64,icon.getIconWidth(),icon.getIconHeight());
            label.setBackground(new Color(0,0,0,0));
        }
        else
        {
            this.xPos = xPos;
            yPos = 0;
            icon = new ImageIcon("Sprites/bishopB.png");
            label = new JLabel();
            label.setOpaque(true);
            label.setIcon(icon);
            label.setBounds(xPos*64,yPos*64,icon.getIconWidth(),icon.getIconHeight());
            label.setBackground(new Color(0,0,0,0));
        }
        this.White = White;

        Type = Piece.BISHOP;
    }
}
