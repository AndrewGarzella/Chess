package Chess;
/*
 * Rooks and queens indicators dont pop up on black pieces for straight line checks
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

import java.awt.*;
import java.awt.Point;

import Chess.Pieces.*;

public class Game implements MouseListener{
    private JFrame frame;
    private JPanel panel;
    private JLayeredPane layeredPane;
    private int Turn = 0;
    private Square[][] Board = new Square[8][8];
    Indicator[][] Indicators = new Indicator[8][8];
    
    //define pieces pawns are initalized int construtor with for loop
    Pawn[] WhitePawns = new Pawn[8];
    Pawn[] BlackPawns = new Pawn[8];

    // init kings
    King WhiteKing = new King(true, 4);
    King BlackKing = new King(false, 4);

    // init queen
    Queen WhiteQueen = new Queen(true, 3);
    Queen BlackQueen = new Queen(false, 3);

    // init Bishops
    Bishop[] WhiteBishop = {new Bishop(true, 2),new Bishop(true, 5)};
    Bishop[] BlackBishop = {new Bishop(false, 2),new Bishop(false, 5)};
    
    //inti Knight
    Knight[] WhiteKnight = {new Knight(true, 1),new Knight(true, 6)};
    Knight[] BlackKnight = {new Knight(false, 1),new Knight(false,6)};

    //init Rooks
    Rook[] WhiteRook = {new Rook(true, 0),new Rook(true, 7)};
    Rook[] BlackRook = {new Rook(false, 0),new Rook(false,7)};

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
    // King WhiteKing = new King(true, 3);
    // King BlackKing = new King(false, 3);
    Board[WhiteKing.xPos][WhiteKing.yPos] = new Square(true, WhiteKing);
    Board[BlackKing.xPos][BlackKing.yPos] = new Square(true, BlackKing);    
    layeredPane.add(WhiteKing.label);
    layeredPane.add(BlackKing.label);
    
    //adds queens
    // Queen WhiteQueen = new Queen(true, 4);
    // Queen BlackQueen = new Queen(false, 4);
    Board[WhiteQueen.xPos][WhiteQueen.yPos] = new Square(true, WhiteQueen);
    Board[BlackQueen.xPos][BlackQueen.yPos] = new Square(true, BlackQueen);
    layeredPane.add(WhiteQueen.label);
    layeredPane.add(BlackQueen.label);

    //adds bishops
    // Bishop[] WhiteBishop = {new Bishop(true, 2),new Bishop(true, 5)};
    // Bishop[] BlackBishop = {new Bishop(false, 2),new Bishop(false, 5)};
    Board[WhiteBishop[0].xPos][WhiteBishop[0].yPos] = new Square(true, WhiteBishop[0]);
    Board[WhiteBishop[1].xPos][WhiteBishop[1].yPos] = new Square(true, WhiteBishop[1]);
    Board[BlackBishop[0].xPos][BlackBishop[0].yPos] = new Square(true, BlackBishop[0]);
    Board[BlackBishop[1].xPos][BlackBishop[1].yPos] = new Square(true, BlackBishop[1]);
    layeredPane.add(WhiteBishop[0].label);
    layeredPane.add(WhiteBishop[1].label);
    layeredPane.add(BlackBishop[0].label);
    layeredPane.add(BlackBishop[1].label);
    

    //adds knights
    // Knight[] WhiteKnight = {new Knight(true, 1),new Knight(true, 6)};
    // Knight[] BlackKnight = {new Knight(false, 1),new Knight(false,6)};
    Board[WhiteKnight[0].xPos][WhiteKnight[0].yPos] = new Square(true, WhiteKnight[0]);
    Board[WhiteKnight[1].xPos][WhiteKnight[1].yPos] = new Square(true, WhiteKnight[1]);
    Board[BlackKnight[0].xPos][BlackKnight[0].yPos] = new Square(true, BlackKnight[0]);
    Board[BlackKnight[1].xPos][BlackKnight[1].yPos] = new Square(true, BlackKnight[1]);
    layeredPane.add(WhiteKnight[0].label);
    layeredPane.add(WhiteKnight[1].label);
    layeredPane.add(BlackKnight[0].label);
    layeredPane.add(BlackKnight[1].label);

    //adds Rooks
    // Rook[] WhiteRook = {new Rook(true, 0),new Rook(true, 7)};
    // Rook[] BlackRook = {new Rook(false, 0),new Rook(false,7)};
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
    

    // Point SelectedPiece = new Point();

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
                if(((x > (i*64)+8 && x <= ((i+1)*64)+8) && (y > (j*64)+30 && y <= ((j+1)*64)+30)) && Indicators[i][j].label.isVisible())  
                {
                    isInVisibleIndicator = true;
                    xSquare = i;
                    ySquare = j;
                }
                if(isInVisibleIndicator)
                    break;
                if(((x > (i*64)+8 && x <= ((i+1)*64)+8) && (y > (j*64)+30 && y <= ((j+1)*64)+30)) && Board[i][j].isOcupied)  
                {
                    
                    xSquare = i;
                    ySquare = j;
                    if((Turn % 2 == 0) && Board[i][j].piece.White)
                    {
                        Board[i][j].piece.isSelected = true;
                        isInPiece = true;
                    }
                    
                    if((Turn % 2 == 1) && !Board[i][j].piece.White)
                    {
                        Board[i][j].piece.isSelected = true;
                        isInPiece = true;
                    }
                    HideIndicators();

                }
                
                
                
            }
            if(isInVisibleIndicator)
                break;
                        
        }
        
        if(isInPiece)
        {
            // make sure only selected peice is one you are trying to move
            //deselects other pieces        
            for(int i = 0; i < 8; i++)
            {
                for(int j = 0; j < 8; j++)
                {
                    try {
                        if(Board[i][j].piece.isSelected && (Board[i][j].piece.xPos != Board[xSquare][ySquare].piece.xPos || Board[i][j].piece.yPos != Board[xSquare][ySquare].piece.yPos))
                            Board[i][j].piece.isSelected = false; 
                    } catch (Exception n) {

                    }
                       
                }
                            
            }

            // find what piece is in that square
            // piece is in squar Board[xSquare][ySquare]
            // TODO then find all legal moves
            // IMPORTANT points are not ints they must be casted (For futer andrew)
            ArrayList<Point> LegalMoves = new ArrayList<Point>();
                        
            // switch checks what kind of piece is in the square then each case is what moves are legal
            // legal moves get added to LegalMoves ArrayList in the form of points
            switch(Board[xSquare][ySquare].piece.Type){
                case Piece.PAWN:
                    
                    if(Board[xSquare][ySquare].piece.White)
                    {
                        if(Board[xSquare][ySquare].piece.isFirstMove)
                        {
                            if(!Board[xSquare][ySquare-2].isOcupied)
                                LegalMoves.add(new Point(xSquare, ySquare-2));
                        }
                        // checks if square ahead of pawn has a piece in it as white
                        if(!Board[xSquare][ySquare-1].isOcupied)
                            LegalMoves.add(new Point(xSquare,ySquare-1));

                        // checks if diagonals are ocupied by a black piece
                        try{
                            if(Board[xSquare-1][ySquare-1].isOcupied && !Board[xSquare-1][ySquare-1].piece.White)
                                LegalMoves.add(new Point(xSquare-1,ySquare-1));
                            if(Board[xSquare+1][ySquare-1].isOcupied && !Board[xSquare+1][ySquare-1].piece.White)
                                LegalMoves.add(new Point(xSquare+1,ySquare-1));
                        } catch(Exception n){ // cant use e because mouseclicked passes e in 
                            // System.out.println(n);
                        }
                    }
                    // ONLY white pieces for now
                    if(!Board[xSquare][ySquare].piece.White)
                    {
                        if(Board[xSquare][ySquare].piece.isFirstMove)
                        {
                            if(!Board[xSquare][ySquare+2].isOcupied)
                                LegalMoves.add(new Point(xSquare, ySquare+2));
                        }
                        // checks if square ahead of pawn has a piece in it as balck
                        if(!Board[xSquare][ySquare+1].isOcupied)
                            LegalMoves.add(new Point(xSquare,ySquare+1));

                        // checks if diagonals are ocupied by a black piece
                        try{
                            if(Board[xSquare-1][ySquare+1].isOcupied && Board[xSquare-1][ySquare+1].piece.White)
                                LegalMoves.add(new Point(xSquare-1,ySquare+1));
                            if(Board[xSquare+1][ySquare+1].isOcupied && Board[xSquare+1][ySquare+1].piece.White)
                                LegalMoves.add(new Point(xSquare+1,ySquare+1));
                        } catch(Exception n){ // cant use e because mouseclicked passes e in 
                            // System.out.println(n);
                        }

                    }
                    
                    // TODO add en passant 

                    break;

                case Piece.KNIGHT:
                    // need to check square (Starting form most left)
                    
                    // points (-2 +1), (-2 -1)
                    try{
                        if(!Board[xSquare-2][ySquare+1].isOcupied || !(Board[xSquare-2][ySquare+1].piece.White == Board[xSquare][ySquare].piece.White))
                            LegalMoves.add(new Point(xSquare-2,ySquare+1));
                        
                    } catch (Exception n){}
                    try{
                        if(!Board[xSquare-2][ySquare-1].isOcupied || !(Board[xSquare-2][ySquare-1].piece.White == Board[xSquare][ySquare].piece.White))
                            LegalMoves.add(new Point(xSquare-2,ySquare-1));
                        
                    } catch (Exception n){}
                    // -1 +2. -1 -2
                    try{
                        if(!Board[xSquare-1][ySquare+2].isOcupied || !(Board[xSquare-1][ySquare+2].piece.White == Board[xSquare][ySquare].piece.White))
                            LegalMoves.add(new Point(xSquare-1,ySquare+2));
                        
                    } catch (Exception n){}
                    try{
                        if(!Board[xSquare-1][ySquare-2].isOcupied || !(Board[xSquare-1][ySquare-2].piece.White == Board[xSquare][ySquare].piece.White))
                            LegalMoves.add(new Point(xSquare-1,ySquare-2));
                        
                    } catch (Exception n){}
                    // +1 +2, +1 -2, 
                    try{
                        if(!Board[xSquare+1][ySquare+2].isOcupied || !(Board[xSquare+1][ySquare+2].piece.White == Board[xSquare][ySquare].piece.White))
                            LegalMoves.add(new Point(xSquare+1,ySquare+2));
                        
                    } catch (Exception n){}
                    try{
                        if(!Board[xSquare+1][ySquare-2].isOcupied || !(Board[xSquare+1][ySquare-2].piece.White == Board[xSquare][ySquare].piece.White))
                            LegalMoves.add(new Point(xSquare+1,ySquare-2));
                        
                    } catch (Exception n){}
                    // +2 +1, +2 -1, 
                    try{
                        if(!Board[xSquare+2][ySquare+1].isOcupied || !(Board[xSquare+2][ySquare+1].piece.White == Board[xSquare][ySquare].piece.White))
                            LegalMoves.add(new Point(xSquare+2,ySquare+1));
                        
                    } catch (Exception n){}
                    try{
                        if(!Board[xSquare+2][ySquare-1].isOcupied || !(Board[xSquare+2][ySquare-1].piece.White == Board[xSquare][ySquare].piece.White))
                            LegalMoves.add(new Point(xSquare+2,ySquare-1));
                        
                    } catch (Exception n){}
                    break;

                case Piece.BISHOP:
                    //up left diagonal check
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare-i][ySquare-i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare-i));
                            }
                            else if(!(Board[xSquare-i][ySquare-i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare-i));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // checks up right digonal
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare+i][ySquare-i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare-i));
                            }
                            else if(!(Board[xSquare+i][ySquare-i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare-i));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // checks down left diagonal
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare-i][ySquare+i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare+i));
                            }
                            else if(!(Board[xSquare-i][ySquare+i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare+i));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // down rigth diagonal check
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare+i][ySquare+i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare+i));
                            }
                            else if(!(Board[xSquare+i][ySquare+i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare+i));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    break;

                case Piece.ROOK:
                    // checks staright Up
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare][ySquare-i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare,ySquare-i));
                            }
                            else if(!(Board[xSquare][ySquare-i].piece.White  == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare,ySquare-i));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // checks staright Down
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare][ySquare+i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare,ySquare+i));
                            }
                            else if(!(Board[xSquare][ySquare+i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare,ySquare+i));
                                break;
                            }
                            else 
                            {
                                break;
                            } 
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // checks staright Left
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare-i][ySquare].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare));
                            }
                            else if(!(Board[xSquare-i][ySquare].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }                   
                    // checks staright Right
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare+i][ySquare].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare));
                            }
                            else if(!(Board[xSquare+i][ySquare].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    
                    break;

                case Piece.QUEEN:
                    //up left diagonal check
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare-i][ySquare-i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare-i));
                            }
                            else if(!(Board[xSquare-i][ySquare-i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare-i));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // checks up right digonal
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare+i][ySquare-i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare-i));
                            }
                            else if(!(Board[xSquare+i][ySquare-i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare-i));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // checks down left diagonal
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare-i][ySquare+i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare+i));
                            }
                            else if(!(Board[xSquare-i][ySquare+i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare+i));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // down rigth diagonal check
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare+i][ySquare+i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare+i));
                            }
                            else if(!(Board[xSquare+i][ySquare+i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare+i));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // checks staright Up
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare][ySquare-i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare,ySquare-i));
                            }
                            else if(!(Board[xSquare][ySquare-i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare,ySquare-i));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // checks staright Down
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare][ySquare+i].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare,ySquare+i));
                            }
                            else if(!(Board[xSquare][ySquare+i].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare,ySquare+i));
                                break;
                            }
                            else 
                            {
                                break;
                            } 
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    // checks staright Left
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare-i][ySquare].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare));
                            }
                            else if(!(Board[xSquare-i][ySquare].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare-i,ySquare));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }                   
                    // checks staright Right
                    for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
                    {
                        try {
                            if(!Board[xSquare+i][ySquare].isOcupied)
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare));
                            }
                            else if(!(Board[xSquare+i][ySquare].piece.White == Board[xSquare][ySquare].piece.White))
                            {
                                LegalMoves.add(new Point(xSquare+i,ySquare));
                                break;
                            }
                            else 
                            {
                                break;
                            }
                            
                        } catch (Exception n) {
                            break;
                        }                        
                    }
                    break; 

                case Piece.KING:                  
                    // TODO check if square king is trying to move to is defended
                    for (int i = -1; i < 2; i++)
                    {
                        // check squares 1 row above king
                        try{
                            if(!Board[xSquare+i][ySquare+1].isOcupied)
                                LegalMoves.add(new Point(xSquare+i,ySquare+1));
                            else if(!Board[xSquare+i][ySquare+1].isDefended && !(Board[xSquare][ySquare].piece.White == Board[xSquare+i][ySquare+1].piece.White))
                                LegalMoves.add(new Point(xSquare+i,ySquare+1));
                            
                            
                        } catch (Exception n){

                        }

                        try {
                            if(!Board[xSquare+i][ySquare].isOcupied)
                                LegalMoves.add(new Point(xSquare+i,ySquare));
                            else if(!Board[xSquare+i][ySquare].isDefended && !(Board[xSquare][ySquare].piece.White == Board[xSquare+i][ySquare].piece.White))
                                LegalMoves.add(new Point(xSquare+i,ySquare));
                        } catch (Exception n) {
                            
                        }
                        
                        try {
                            if(!Board[xSquare+i][ySquare-1].isOcupied)
                                LegalMoves.add(new Point(xSquare+i,ySquare-1));
                            else if(!Board[xSquare+i][ySquare-1].isDefended && !(Board[xSquare][ySquare].piece.White == Board[xSquare+i][ySquare-1].piece.White))
                                LegalMoves.add(new Point(xSquare+i,ySquare-1));
                        } catch (Exception n) {

                        }   
                    }
                        
                    
                    break;

                 
                            
            }

            

            //show all legal moves 
            for(Point i : LegalMoves)
            {
                Indicators[(int) i.getX()][(int) i.getY()].label.setVisible(true);
            }
            
    }

        // show moves
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
            // deselect the all pieces
            
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
        Board[oldXPos][oldYPos].piece.isFirstMove = false;
        try {
            Board[newXPos][newYPos].piece.label.setVisible(false);

        } catch (Exception e) {}
        switch(Board[oldXPos][oldYPos].piece.Type){
            case Piece.PAWN:
                if(Board[oldXPos][oldYPos].piece.White)
                {  
                    for(Pawn i : WhitePawns)
                    {
                        if(i.xPos == newXPos && i.yPos == newYPos)
                        {
                            Board[newXPos][newYPos].piece = i;
                            Board[newXPos][newYPos].piece.Type = Piece.PAWN;
                        }
                    }
                }
                else
                {
                    for(Pawn i : BlackPawns)
                    {
                        if(i.xPos == newXPos && i.yPos == newYPos)
                        {
                            Board[newXPos][newYPos].piece = i;
                            Board[newXPos][newYPos].piece.Type = Piece.PAWN;
                        }
                    }
                }
                break;

            case Piece.KNIGHT:
                if(Board[oldXPos][oldYPos].piece.White)
                {
                    for(Knight i : WhiteKnight)
                    {
                        if(i.xPos == newXPos && i.yPos == newYPos)
                        {
                            Board[newXPos][newYPos].piece = i;
                            Board[newXPos][newYPos].piece.Type = Piece.KNIGHT;
                        }                  
                    }
                }
                else
                {   
                    for(Knight i : BlackKnight)
                    {
                        if(i.xPos == newXPos && i.yPos == newYPos)
                        {
                            Board[newXPos][newYPos].piece = i;
                            Board[newXPos][newYPos].piece.Type = Piece.KNIGHT;
                        }                  
                    }
                }
                break;

            case Piece.BISHOP:
                if(Board[oldXPos][oldYPos].piece.White)
                {
                    for(Bishop i : WhiteBishop)
                    {
                        if(i.xPos == newXPos && i.yPos == newYPos)
                        {
                            Board[newXPos][newYPos].piece = i;
                            Board[newXPos][newYPos].piece.Type = Piece.BISHOP;
                        } 
                            
                    }
                }
                else
                {
                    for(Bishop i : BlackBishop)
                    {
                        if(i.xPos == newXPos && i.yPos == newYPos)
                        {
                            Board[newXPos][newYPos].piece = i;
                            Board[newXPos][newYPos].piece.Type = Piece.BISHOP;
                        } 
                            
                    }
                }
                break;

            case Piece.ROOK:
                if(Board[oldXPos][oldYPos].piece.White)
                {
                    for(Rook i : WhiteRook)
                    {
                        if(i.xPos == newXPos && i.yPos == newYPos)
                        {
                            Board[newXPos][newYPos].piece = i;
                            Board[newXPos][newYPos].piece.Type = Piece.ROOK;
                        }                    
                    }
                }
                else
                {
                    for(Rook i : BlackRook)
                    {
                        if(i.xPos == newXPos && i.yPos == newYPos)
                        {
                            Board[newXPos][newYPos].piece = i;
                            Board[newXPos][newYPos].piece.Type = Piece.ROOK;
                        }                    
                    }
                }
                break;
            
            case Piece.QUEEN:
                if(Board[oldXPos][oldYPos].piece.White)
                {
                    Board[newXPos][newYPos].piece = WhiteQueen;    
                    Board[newXPos][newYPos].piece.Type = Piece.QUEEN;
                }
                else
                {
                    Board[newXPos][newYPos].piece = BlackQueen;    
                    Board[newXPos][newYPos].piece.Type = Piece.QUEEN;
                }
                break; 

            case Piece.KING:
            if(Board[oldXPos][oldYPos].piece.White)
            {
                Board[newXPos][newYPos].piece = WhiteKing;
                Board[newXPos][newYPos].piece.Type = Piece.KING;
            }
            else
            {
                Board[newXPos][newYPos].piece = BlackKing;
                Board[newXPos][newYPos].piece.Type = Piece.KING;
            }
                
            break;

             
                        
        }
        

        Board[newXPos][newYPos].Type = Piece.PAWN;
        // Board[newXPos][newXPos].piece = new Piece();
        Board[oldXPos][oldYPos].piece = null;
        Board[oldXPos][oldYPos].isOcupied = false;
        Board[newXPos][newYPos].isOcupied = true;
        // Board[oldXPos][oldYPos]
        // need switch 
        
        Turn++;
        
        
    }

    // private boolean isSquareDefended(int xSquare, int ySquare, )
    // {
    //     switch(Board[xSquare][ySquare].piece.Type){
    //         case Piece.PAWN:
    //             // TODO pawn moves twice on turn 1
    //             if(Board[xSquare][ySquare].piece.White && Board[xSquare][ySquare].piece.isFirstMove)
    //             {
    //                 if(!Board[xSquare][ySquare-2].isOcupied)
    //                     LegalMoves.add(new Point(xSquare, ySquare-2));
    //             }
    //             if(Board[xSquare][ySquare].piece.White)
    //             {
    //                 // checks if square ahead of pawn has a piece in it as white
    //                 if(!Board[xSquare][ySquare-1].isOcupied)
    //                     LegalMoves.add(new Point(xSquare,ySquare-1));

    //                 // checks if diagonals are ocupied by a black piece
    //                 try{
    //                     if(Board[xSquare-1][ySquare-1].isOcupied && !Board[xSquare-1][ySquare-1].piece.White)
    //                         LegalMoves.add(new Point(xSquare-1,ySquare-1));
    //                     if(Board[xSquare+1][ySquare-1].isOcupied && !Board[xSquare+1][ySquare-1].piece.White)
    //                         LegalMoves.add(new Point(xSquare+1,ySquare-1));
    //                 } catch(Exception n){ // cant use e because mouseclicked passes e in 
    //                     // System.out.println(n);
    //                 }
    //             }
    //             //ONLY white pieces for now
    //             // if(!Board[xSquare][ySquare].piece.White)
    //             // {
    //             //     // checks if square ahead of pawn has a piece in it as balck
    //             //     if(!Board[xSquare][ySquare+1].isOcupied)
    //             //         LegalMoves.add(new Point(xSquare,ySquare+1));

    //             //     // checks if diagonals are ocupied by a black piece
    //             //     try{
    //             //         if(Board[xSquare-1][ySquare+1].isOcupied && !Board[xSquare-1][ySquare+1].piece.White)
    //             //             LegalMoves.add(new Point(xSquare-1,ySquare-1));
    //             //         if(Board[xSquare+1][ySquare+1].isOcupied && !Board[xSquare+1][ySquare+1].piece.White)
    //             //             LegalMoves.add(new Point(xSquare+1,ySquare-1));
    //             //     } catch(ArrayIndexOutOfBoundsException i){ // cant use e because mouseclicked passes e in 
    //             //         System.out.println(i);
    //             //     }

    //             // }
                
    //             // TODO add en passant 

    //             break;

    //         case Piece.KNIGHT:
    //             // need to check square (Starting form most left)
    //             // points (-2 +1), (-2 -1)
    //             try{
    //                 if(!Board[xSquare-2][ySquare+1].isOcupied || !Board[xSquare-2][ySquare+1].piece.White)
    //                     LegalMoves.add(new Point(xSquare-2,ySquare+1));
                    
    //             } catch (Exception n){

    //             }
    //             try{
    //                 if(!Board[xSquare-2][ySquare-1].isOcupied || !Board[xSquare-2][ySquare-1].piece.White)
    //                     LegalMoves.add(new Point(xSquare-2,ySquare-1));
                    
    //             } catch (Exception n){

    //             }
    //             // -1 +2. -1 -2
    //             try{
    //                 if(!Board[xSquare-1][ySquare+2].isOcupied || !Board[xSquare-1][ySquare+2].piece.White)
    //                     LegalMoves.add(new Point(xSquare-1,ySquare+2));
                    
    //             } catch (Exception n){}
    //             try{
    //                 if(!Board[xSquare-1][ySquare-2].isOcupied || !Board[xSquare-1][ySquare-2].piece.White)
    //                     LegalMoves.add(new Point(xSquare-1,ySquare-2));
                    
    //             } catch (Exception n){

    //             }
    //             // +1 +2, +1 -2, 
    //             try{
    //                 if(!Board[xSquare+1][ySquare+2].isOcupied || !Board[xSquare+1][ySquare+2].piece.White)
    //                     LegalMoves.add(new Point(xSquare+1,ySquare+2));
                    
    //             } catch (Exception n){

    //             }
    //             try{
    //                 if(!Board[xSquare+1][ySquare-2].isOcupied || !Board[xSquare+1][ySquare-2].piece.White)
    //                     LegalMoves.add(new Point(xSquare+1,ySquare-2));
                    
    //             } catch (Exception n){

    //             }
    //             // +2 +1, +2 -1, 
    //             try{
    //                 if(!Board[xSquare+2][ySquare+1].isOcupied || !Board[xSquare+2][ySquare+1].piece.White)
    //                     LegalMoves.add(new Point(xSquare+2,ySquare+1));
                    
    //             } catch (Exception n){

    //             }
    //             try{
    //                 if(!Board[xSquare+2][ySquare-1].isOcupied || !Board[xSquare+2][ySquare-1].piece.White)
    //                     LegalMoves.add(new Point(xSquare+2,ySquare-1));
                    
    //             } catch (Exception n){

    //             }
    //             break;

    //         case Piece.BISHOP:
    //             //up left diagonal check
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare-i][ySquare-i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare-i));
    //                     }
    //                     else if(!Board[xSquare-i][ySquare-i].piece.White)
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare-i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // checks up right digonal
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare+i][ySquare-i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare-i));
    //                     }
    //                     else if(!Board[xSquare+i][ySquare-i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare-i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // checks down left diagonal
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare-i][ySquare+i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare+i));
    //                     }
    //                     else if(!Board[xSquare-i][ySquare+i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare+i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // down rigth diagonal check
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare+i][ySquare+i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare+i));
    //                     }
    //                     else if(!Board[xSquare+i][ySquare+i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare+i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             break;

    //         case Piece.ROOK:
    //             // checks staright left
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare][ySquare-i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare,ySquare-i));
    //                     }
    //                     else if(!Board[xSquare][ySquare+i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare,ySquare-i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // checks staright Right
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare][ySquare+i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare,ySquare+i));
    //                     }
    //                     else if(!Board[xSquare][ySquare+i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare,ySquare+i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     } 
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // checks staright Up
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare-i][ySquare].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare));
    //                     }
    //                     else if(!Board[xSquare-i][ySquare].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }                   
    //             // checks staright Down
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare+i][ySquare].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare));
    //                     }
    //                     else if(!Board[xSquare+i][ySquare].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
                
    //             break;

    //         case Piece.QUEEN:
    //             //up left diagonal check
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare-i][ySquare-i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare-i));
    //                     }
    //                     else if(!Board[xSquare-i][ySquare-i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare-i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // checks up right digonal
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare+i][ySquare-i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare-i));
    //                     }
    //                     else if(!Board[xSquare+i][ySquare-i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare-i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // checks down left diagonal
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare-i][ySquare+i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare+i));
    //                     }
    //                     else if(!Board[xSquare-i][ySquare+i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare+i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // down rigth diagonal check
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare+i][ySquare+i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare+i));
    //                     }
    //                     else if(!Board[xSquare+i][ySquare+i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare+i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // checks staright left
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare][ySquare-i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare,ySquare-i));
    //                     }
    //                     else if(!Board[xSquare][ySquare+i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare,ySquare-i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // checks staright Right
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare][ySquare+i].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare,ySquare+i));
    //                     }
    //                     else if(!Board[xSquare][ySquare+i].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare,ySquare+i));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     } 
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             // checks staright Up
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare-i][ySquare].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare));
    //                     }
    //                     else if(!Board[xSquare-i][ySquare].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare-i,ySquare));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }                   
    //             // checks staright Down
    //             for(int i = 1; i < 8; i++) // starts at 1 because you cant move to the square you are already in
    //             {
    //                 try {
    //                     if(!Board[xSquare+i][ySquare].isOcupied)
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare));
    //                     }
    //                     else if(!Board[xSquare+i][ySquare].piece.White )
    //                     {
    //                         LegalMoves.add(new Point(xSquare+i,ySquare));
    //                         break;
    //                     }
    //                     else 
    //                     {
    //                         break;
    //                     }
                        
    //                 } catch (Exception n) {
    //                     break;
    //                 }                        
    //             }
    //             break; 

    //         case Piece.KING:                  
    //             // TODO check if square king is trying to move to is defended
    //             for (int i = -1; i < 2; i++)
    //             {
    //                 // check squares 1 row above king
    //                 try{
    //                     if(!Board[xSquare+i][ySquare+1].isOcupied)
    //                         LegalMoves.add(new Point(xSquare+i,ySquare+1));
    //                     else if(!Board[xSquare+i][ySquare+1].isDefended && !Board[xSquare+i][ySquare+1].piece.White)
    //                         LegalMoves.add(new Point(xSquare+i,ySquare+1));
                        
                        
    //                 } catch (Exception n){

    //                 }

    //                 try {
    //                     if(!Board[xSquare+i][ySquare].isOcupied)
    //                         LegalMoves.add(new Point(xSquare+i,ySquare));
    //                     else if(!Board[xSquare+i][ySquare].isDefended && !Board[xSquare+i][ySquare].piece.White)
    //                         LegalMoves.add(new Point(xSquare+i,ySquare));
    //                 } catch (Exception n) {
                        
    //                 }
                    
    //                 try {
    //                     if(!Board[xSquare+i][ySquare-1].isOcupied)
    //                         LegalMoves.add(new Point(xSquare+i,ySquare-1));
    //                     else if(!Board[xSquare+i][ySquare-1].isDefended && !Board[xSquare+i][ySquare-1].piece.White)
    //                         LegalMoves.add(new Point(xSquare+i,ySquare-1));
    //                 } catch (Exception n) {

    //                 }   
    //             }
                    
                
    //             break;

             
                        
    //     }

    //     return false;
    // }


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
            this.Type = piece.Type;

            if((this.piece.xPos+this.piece.yPos)%2 == 0)
                isWhite = true;
            else 
                isWhite = false;

        }
        
        public boolean isDefended;
        public boolean isWhite;
        public boolean isOcupied;
        public Piece piece;
        public int Type; // type of piece or indicator in square

        public boolean isDefended()
        {
            
            return false;
        }
    }

    private class Indicator{
        Indicator(int xPos, int yPos, boolean isWhite)
        {
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
