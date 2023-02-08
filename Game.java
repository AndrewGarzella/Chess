package Chess;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;

import Chess.Pieces.*;

public class Game implements MouseListener{
    public JFrame frame;
    public JPanel panel;
    public JLayeredPane layeredPane;
    // public boolean[][] Board = new boolean[8][8];
    private Square[][] Board = new Square[8][8];
    Indicator[][] Indicators = new Indicator[8][8];
    
    //define pieces
    Pawn[] WhitePawns = new Pawn[8];
    Pawn[] BlackPawns = new Pawn[8];
    

    public Game()
    {
        frame = new JFrame();
        frame.setBounds(0,0,526,545);
        
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0,0,526,545);

        panel = new JPanel(){
        @Override
        public void paint(Graphics g) 
        {
            boolean white = true;
            super.paint(g);
            for(int x = 0; x < 8; x++)
            {                    
                for(int y = 0; y < 8; y++)
                {
                    if(white)
                    {
                        g.setColor(Color.WHITE);
                    }                     
                    else 
                    {        
                        g.setColor(Color.DARK_GRAY);
                    }
                    //is painting them 70 pixels big 
                    g.fillRect(x*64, y*64, 64, 64);
                    white = !white;
                }

                white = !white;

            }
        }
    };

    panel.setBounds(0,0,560,560);


    
    Boolean White = true;

    // sets board and adds indicators
    for(int i = 0; i < 8; i++)
    {
        for(int j = 0; j < 8; j++)
        {
            
            // white not white
            if((i+j)%2 == 0)
                White = true;
            else 
                White = false;

            Indicators[i][j] = new Indicator(i, j, White);

            layeredPane.add(Indicators[i][j].label);

            if(!(j == 0 || j == 1 || j == 6 || j == 7))
            {
                Board[i][j] = new Square(false,White);
            }              
        }
    } 
    

    //adds pawns
    // Pawn[] WhitePawns = new Pawn[8];
    // Pawn[] BlackPawns = new Pawn[8];

    for(int i = 0; i < 8;i++)
    {
        WhitePawns[i] = new Pawn(true, i);
        layeredPane.add(WhitePawns[i].label);
        Board[WhitePawns[i].xPos][WhitePawns[i].yPos] = new Square(true, WhitePawns[i]);
    }
    for(int i = 0; i < 8;i++)
    {
        BlackPawns[i] = new Pawn(false, i);
        layeredPane.add(BlackPawns[i].label);
        Board[BlackPawns[i].xPos][BlackPawns[i].yPos] = new Square(true, BlackPawns[i]);
        
    }

    //adds Kings 
    King WhiteKing = new King(true, 3);
    King BlackKing = new King(false, 3);
    Board[WhiteKing.xPos][WhiteKing.yPos] = new Square(true, WhiteKing);
    Board[BlackKing.xPos][BlackKing.yPos] = new Square(true, BlackKing);    
    layeredPane.add(WhiteKing.label);
    layeredPane.add(BlackKing.label);
    
    //adds queens
    Queen WhiteQueen = new Queen(true, 4);
    Queen BlackQueen = new Queen(false, 4);
    Board[WhiteQueen.xPos][WhiteQueen.yPos] = new Square(true, WhiteQueen);
    Board[BlackQueen.xPos][BlackQueen.yPos] = new Square(true, BlackQueen);
    layeredPane.add(WhiteQueen.label);
    layeredPane.add(BlackQueen.label);

    //adds bishops
    Bishop[] WhiteBishop = {new Bishop(true, 2),new Bishop(true, 5)};
    Bishop[] BlackBishop = {new Bishop(false, 2),new Bishop(false, 5)};
    Board[WhiteBishop[0].xPos][WhiteBishop[0].yPos] = new Square(true, WhiteBishop[0]);
    Board[WhiteBishop[1].xPos][WhiteBishop[1].yPos] = new Square(true, WhiteBishop[1]);
    Board[BlackBishop[0].xPos][BlackBishop[0].yPos] = new Square(true, BlackBishop[0]);
    Board[BlackBishop[1].xPos][BlackBishop[1].yPos] = new Square(true, BlackBishop[1]);
    layeredPane.add(WhiteBishop[0].label);
    layeredPane.add(WhiteBishop[1].label);
    layeredPane.add(BlackBishop[0].label);
    layeredPane.add(BlackBishop[1].label);
    

    //adds knights
    Knight[] WhiteKnight = {new Knight(true, 1),new Knight(true, 6)};
    Knight[] BlackKnight = {new Knight(false, 1),new Knight(false,6)};
    Board[WhiteKnight[0].xPos][WhiteKnight[0].yPos] = new Square(true, WhiteKnight[0]);
    Board[WhiteKnight[1].xPos][WhiteKnight[1].yPos] = new Square(true, WhiteKnight[1]);
    Board[BlackKnight[0].xPos][BlackKnight[0].yPos] = new Square(true, BlackKnight[0]);
    Board[BlackKnight[1].xPos][BlackKnight[1].yPos] = new Square(true, BlackKnight[1]);
    layeredPane.add(WhiteKnight[0].label);
    layeredPane.add(WhiteKnight[1].label);
    layeredPane.add(BlackKnight[0].label);
    layeredPane.add(BlackKnight[1].label);

    //adds Rooks
    Rook[] WhiteRook = {new Rook(true, 0),new Rook(true, 7)};
    Rook[] BlackRook = {new Rook(false, 0),new Rook(false,7)};
    Board[WhiteRook[0].xPos][WhiteRook[0].yPos] = new Square(true, WhiteRook[0]);
    Board[WhiteRook[1].xPos][WhiteRook[1].yPos] = new Square(true, WhiteRook[1]);
    Board[BlackRook[0].xPos][BlackRook[0].yPos] = new Square(true, BlackRook[0]);
    Board[BlackRook[1].xPos][BlackRook[1].yPos] = new Square(true, BlackRook[1]);
    layeredPane.add(WhiteRook[0].label);
    layeredPane.add(WhiteRook[1].label);
    layeredPane.add(BlackRook[0].label);
    layeredPane.add(BlackRook[1].label);

    



    // adds board
    layeredPane.add(panel);

    frame.add(layeredPane);
    frame.addMouseListener(this);
    frame.setLayout(null);

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setVisible(true);
    }
    
    // public static void SetBoard(boolean[][] Board)
    // {
    //     for(int i = 0; i < 8; i++)
    //     {
    //         for(int j = 0; j < 8; j++)
    //         {
    //             if(!(j == 0 || j == 1 || j == 6 || j == 7))
    //                 Board[i][j] = new Square(false);
    //         }
    //     }
    // }


    @Override
    public void mouseClicked(MouseEvent e) {
        // Get mouse position
        int x = e.getX();
        int y = e.getY();
        
        // System.out.println(x+","+y);
        // if mouse position is inside a piece label
        boolean isInPiece = false; 
        boolean isInVisibleIndicator = false;
        int xSquare = -1;
        int ySquare = -1;
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                // +8 for the boarder of the screen, +30 for the top of the screan
                if(((x > (i*64)+8 && x <= ((i+1)*64)+8) && (y > (j*64)+30 && y <= ((j+1)*64)+30)) && Board[i][j].isOcupied)  
                {
                    isInPiece = true;
                    xSquare = i;
                    ySquare = j;
                    Board[i][j].piece.isSelected = true;
                }
                if(((x > (i*64)+8 && x <= ((i+1)*64)+8) && (y > (j*64)+30 && y <= ((j+1)*64)+30)) && Indicators[i][j].label.isVisible())  
                {
                    isInVisibleIndicator = true;
                    xSquare = i;
                    ySquare = j;
                }
                if(isInPiece || isInVisibleIndicator)
                    break;
                
            }
            if(isInPiece || isInVisibleIndicator)
                break;
                        
        }
        
        
        // show a move
        if(isInPiece)
        {
            // find what piece is in that square
            // piece is in squar Board[xSquare][ySquare]
            // TODO then find all legal moves
            // IMPORTANT points are not ints they must be casted (For futer andrew)
            ArrayList<Point> LegalMoves = new ArrayList<Point>();
            
            // switch checks what kind of piece is in the square then each case is what moves are legal
            // legal moves get added to LegalMoves ArrayList in the form of points
            

            switch(Board[xSquare][ySquare].piece.Type){
                case Piece.PAWN:
                    // TODO pawn moves twice on turn 1
                    if(Board[xSquare][ySquare].piece.White)
                    {
                        // checks if square ahead of pawn has a piece in it as white
                        if(!Board[xSquare][ySquare-1].isOcupied)
                            LegalMoves.add(new Point(xSquare,ySquare-1));

                        // checks if diagonals are ocupied by a black piece
                        try{
                            if(Board[xSquare-1][ySquare-1].isOcupied && !Board[xSquare-1][ySquare-1].piece.White)
                                LegalMoves.add(new Point(xSquare-1,ySquare-1));
                            if(Board[xSquare+1][ySquare-1].isOcupied && !Board[xSquare+1][ySquare-1].piece.White)
                                LegalMoves.add(new Point(xSquare+1,ySquare-1));
                        } catch(ArrayIndexOutOfBoundsException i){ // cant use e because mouseclicked passes e in 
                            System.out.println(i);
                        }
                    }
                    if(!Board[xSquare][ySquare].piece.White)
                    {
                        // checks if square ahead of pawn has a piece in it as balck
                        if(!Board[xSquare][ySquare+1].isOcupied)
                            LegalMoves.add(new Point(xSquare,ySquare+1));

                        // checks if diagonals are ocupied by a black piece
                        try{
                            if(Board[xSquare-1][ySquare+1].isOcupied && !Board[xSquare-1][ySquare+1].piece.White)
                                LegalMoves.add(new Point(xSquare-1,ySquare-1));
                            if(Board[xSquare+1][ySquare+1].isOcupied && !Board[xSquare+1][ySquare+1].piece.White)
                                LegalMoves.add(new Point(xSquare+1,ySquare-1));
                        } catch(ArrayIndexOutOfBoundsException i){ // cant use e because mouseclicked passes e in 
                            System.out.println(i);
                        }

                    }
                    
                    // TODO add en passant 

                    break;

                case Piece.KNIGHT:
                    // TODO need to check int L shape
                    
                    break;

                case Piece.BISHOP:
                    // TODO check diagonals
                    break;

                case Piece.ROOK:
                    // TODO check straight lines
                    break;

                case Piece.KING:
                    // TODO all squares around king by one
                    break;

                case Piece.QUEEN:
                    // TODO diagonals and straight aways
                    break;  
                            
            }

            

            //show all legal moves 
            for(Point i : LegalMoves)
            {
                Indicators[(int) i.getX()][(int) i.getY()].label.setVisible(true);
            }
            

        }

        // TODO if clicked on indicator move piece to square 
        else if(isInVisibleIndicator)
        {
            // finds the piece player is trying to move
            int pieceXPos = -1;
            int pieceYPos = -1;
            for(int i = 0; i < 8; i ++)
            {
                for(int j = 0; j < 8; j++)
                {
                    try {
                        if(Board[i][j].piece.isSelected)
                        {
                            pieceXPos = i;
                            pieceYPos = j;
                        }
                    } catch (Exception n) {
                        // TODO: handle exception
                        // System.out.println(n);
                    }
                    
                }
            }

            // calls move funtion for piece
            Board[pieceXPos][pieceYPos].piece.Move(xSquare, ySquare);
            // deselects the piece
            Board[pieceXPos][pieceYPos].piece.isSelected = false;
            // hides the inicators 
            HideIndicators();
            // updates board
            
            UpdateBoard(pieceXPos,pieceYPos,xSquare,ySquare);
            // for(int i = 0; i < 8; i++)
            // {
            //     if(WhitePawns[i].xPos == xSquare && WhitePawns[i].yPos == ySquare)
            //         Board[xSquare][ySquare].piece = WhitePawns[i];

                
            // }
            // Board[xSquare][ySquare].Type = Piece.PAWN;
            // // Board[newXPos][newXPos].piece = new Piece();

            // Board[pieceXPos][pieceYPos].piece = null;
            // Board[pieceXPos][pieceYPos].isOcupied = false;
            // Board[xSquare][ySquare].isOcupied = true;

        }

        // if isInPiece is still false need to remove old indicators if they are on screan
        else // isInPeice = false && isInIndicator == false
        {  

            // if mouse position is inside of a indicated square hide the indicators
             
            HideIndicators();
        }

        
        
        
    }

    private void HideIndicators() {
        for(int i = 0; i < 8; i ++)
        {
            for(int j = 0; j < 8; j++)
            {
                if(Indicators[i][j].label.isVisible())
                {
                    Indicators[i][j].label.setVisible(false);
                }
            }
        }
    }

    private void UpdateBoard(int oldXPos,int oldYPos, int newXPos, int newYPos)
    {
        // switch(Board[oldXPos][oldYPos].piece.Type){
        //     case Piece.PAWN:
        //         Board[]
        //         break;

        //     case Piece.KNIGHT:
                
        //         break;

        //     case Piece.BISHOP:
        //         break;

        //     case Piece.ROOK:
        //         break;

        //     case Piece.KING:
        //         break;

        //     case Piece.QUEEN:
        //         break;  
                        
        // }
        for(int i = 0; i < 8; i++)
        {
            if(WhitePawns[i].xPos == newXPos && WhitePawns[i].yPos == newYPos)
                Board[newXPos][newYPos].piece = WhitePawns[i];

            
        }
        Board[newXPos][newYPos].Type = Piece.PAWN;
        // Board[newXPos][newXPos].piece = new Piece();

        Board[oldXPos][oldYPos].piece = null;
        Board[oldXPos][oldYPos].isOcupied = false;
        Board[newXPos][newYPos].isOcupied = true;
        // Board[oldXPos][oldYPos]
        // need switch 
        
        
        
    }



    public class Square{
        Square(boolean isOcupied, boolean isWhite)
        {
            this.isOcupied = isOcupied;
            this.isWhite = isWhite;
            piece = new Piece();
            Type = Piece.OPEN;
        }

        Square(boolean isOcupied, Piece piece)
        {
            this.isOcupied = isOcupied;
            this.piece = piece;

            if((this.piece.xPos+this.piece.yPos)%2 == 0)
                isWhite = true;
            else 
                isWhite = false;

        }
        
    

        public boolean isWhite;
        public boolean isOcupied;
        public Piece piece;
        public int Type; // type of piece or indicator in square
    }

    private class Indicator{
        Indicator(int xPos, int yPos, boolean isWhite)
        {
            // this.Position = new Point(xPos, yPos); 

            label = new JLabel(){
                @Override
                public void paint(Graphics g) 
                {
                    super.paint(g);
                    if(isWhite)
                        label.setBackground(Color.WHITE);
                    else
                        label.setBackground(Color.DARK_GRAY);
                        
                    g.setColor(new Color(0,0,0,50));
                    g.fillOval((int) (label.getWidth()*.25), (int) (label.getHeight()*.25), 32,32);

                        
                }
            };
            label.setVisible(false);
            label.setBounds(xPos*64, yPos*64, 64, 64);
            
            

        }

        public JLabel label;
        // public Point Position;
    }










    @Override
    public void mousePressed(MouseEvent e) {
        
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

}
