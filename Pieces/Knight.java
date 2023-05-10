package Chess.Pieces;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;



public class Knight extends Piece {
    public Knight(boolean White, int xPos)
    {
        squaresDefending = new ArrayList<Point>();
        if(White)
        {
            this.xPos = xPos;
            yPos = 7;
            squaresDefending.add(new Point(this.xPos-2, this.yPos+1));
            squaresDefending.add(new Point(this.xPos-2, this.yPos-1));
            squaresDefending.add(new Point(this.xPos+2, this.yPos+1));
            squaresDefending.add(new Point(this.xPos+2, this.yPos-1));
            squaresDefending.add(new Point(this.xPos-1, this.yPos+2));
            squaresDefending.add(new Point(this.xPos-1, this.yPos-2));
            squaresDefending.add(new Point(this.xPos+1, this.yPos+2));
            squaresDefending.add(new Point(this.xPos+1, this.yPos-2));
            icon = new ImageIcon("Sprites/knightW.png");
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
            squaresDefending.add(new Point(this.xPos-2, this.yPos+1));
            squaresDefending.add(new Point(this.xPos-2, this.yPos-1));
            squaresDefending.add(new Point(this.xPos+2, this.yPos+1));
            squaresDefending.add(new Point(this.xPos+2, this.yPos-1));
            squaresDefending.add(new Point(this.xPos-1, this.yPos+2));
            squaresDefending.add(new Point(this.xPos-1, this.yPos-2));
            squaresDefending.add(new Point(this.xPos+1, this.yPos+2));
            squaresDefending.add(new Point(this.xPos+1, this.yPos-2));
            icon = new ImageIcon("Sprites/knightB.png");
            label = new JLabel();
            label.setOpaque(true);
            label.setIcon(icon);
            label.setBounds(xPos*64,yPos*64,icon.getIconWidth(),icon.getIconHeight());
            label.setBackground(new Color(0,0,0,0));
        }
        this.White = White;

        Type = Piece.KNIGHT;
    }
}
