package Chess.Pieces;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(boolean White, int xPos)
    {
        squaresDefending = new ArrayList<Point>();
        if(White){
            this.xPos = xPos;
            yPos = 6;
            squaresDefending.add(new Point(xPos+1, yPos-1));
            squaresDefending.add(new Point(xPos-1, yPos-1));
            icon = new ImageIcon("Sprites/pawnW.png");
            label = new JLabel();
            label.setOpaque(true);
            label.setIcon(icon);
            label.setBounds(xPos*64,yPos*64,icon.getIconWidth(),icon.getIconHeight());
            label.setBackground(new Color(0,0,0,0));
        }
        else{
            this.xPos = xPos;
            yPos = 1;
            squaresDefending.add(new Point(xPos+1, yPos+1));
            squaresDefending.add(new Point(xPos-1, yPos+1));
            icon = new ImageIcon("Sprites/pawnB.png");
            label = new JLabel();
            label.setOpaque(true);
            label.setIcon(icon);
            label.setBounds(xPos*64,yPos*64,icon.getIconWidth(),icon.getIconHeight());
            label.setBackground(new Color(0,0,0,0));
        }

        this.White = White;

        // Type = Piece.PAWN;
    }

    public Pawn(boolean White, int xPos, int yPos)
    {
        if(White){
            this.xPos = xPos;
            // OriginalyPos = this.xPos;
            this.yPos = yPos;
            icon = new ImageIcon("Sprites/pawnW.png");
            label = new JLabel();
            label.setOpaque(true);
            label.setIcon(icon);
            label.setBounds(xPos*64,yPos*64,icon.getIconWidth(),icon.getIconHeight());
            label.setBackground(new Color(0,0,0,0));
        }
        else{
            this.xPos = xPos;
            this.yPos = yPos;
            icon = new ImageIcon("Sprites/pawnB.png");
            label = new JLabel();
            label.setOpaque(true);
            label.setIcon(icon);
            label.setBounds(xPos*64,yPos*64,icon.getIconWidth(),icon.getIconHeight());
            label.setBackground(new Color(0,0,0,0));
        }

        this.White = White;

        Type = Piece.PAWN;
    }
}