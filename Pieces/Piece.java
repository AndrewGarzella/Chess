package Chess.Pieces;

import javax.swing.JLabel;
import Chess.Square;
import java.util.ArrayList;
import java.awt.Point;
import javax.swing.ImageIcon;



public class Piece  {
    final public static int PAWN = 0;
    final public static int KNIGHT = 1;
    final public static int BISHOP = 2;
    final public static int ROOK = 3;
    final public static int QUEEN = 4;
    final public static int KING = 5;
    final public static int INDICATOR = 6;


    public boolean Selected = false;
    public boolean Alive = true;
    public int xPos, yPos;
    public Boolean White;
    public ImageIcon icon;
    public JLabel label; 
    public int Type;
    

    // public Type getType()
    // {
    //     return type;
    // }

    // public void setType(Type type)
    // {
    //     this.type = type;
    // }
//     public ArrayList<Point> FindMove(Square[][] Board)
//     {
        
//         return LegalMoves;
//     }
}
