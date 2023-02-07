package Chess.Pieces;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import java.awt.Color;

public class Pawn extends Piece {
    public Pawn(boolean White, int xPos)
    {
        if(White){
            this.xPos = xPos;
            // OriginalyPos = this.xPos;
            yPos = 6;
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

    // @Override
    // public ArrayList<Point> FindMove(Square[][] Board)
    // {
    //     ArrayList<Point> LegalMoves = new ArrayList<Point>();
    //     if(!(Board[xPos][yPos+1].isOcupied))
    //     {
    //         LegalMoves.add(new Point(xPos,yPos));
    //     }

    //     return LegalMoves;
    // }

    // private int OriginalyPosp;

    // boolean CanOPS()
    // {
    //     if(yPos == OriginalyPos+2)
    //     {

    //     }

    // }

}