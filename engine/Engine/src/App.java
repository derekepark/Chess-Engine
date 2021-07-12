import javax.swing.*; //new commands and added functionality


public class App {

    //represent the board as a 2D array
    static char board[][]=
    {
        {'r','n','b','q','k','b','n','r'},
        {'p','p','p','p','p','p','p','p'},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {' ',' ',' ',' ',' ',' ',' ',' '},
        {'P','P','P','P','P','P','P','P'},
        {'R','N','B','Q','K','B','N','R'}};

    static int king, bking; //position of the kings on the board so when generating moves cannot put king in check
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        JFrame UI=new JFrame("Derek Park's Chess Engine");
        UI.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE)); //closes the UI when you close the window
        UserInterface ui=new UserInterface(); 
        UI.add(ui);
        UI.setSize(528, 550); 
        UI.setVisible(true);
        System.out.println(generateAllLegal());
        while ('K'!=board[king/8][king%8])
        {
            king++;
        }
        while ('k'!=board[bking/8][bking%8])
        {
            bking++;
        }

    }

    public static void makeMove(String move) //format for move x1,y1,x2,y2,captured piece //basically a swap 
    {
        if (move.charAt(4)!='P') //check if it is a pawn promotion move
        {
            board[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))];
            board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=' ';

        }
        else //if it is a pawn promotion
        {
            board[1][Character.getNumericValue(move.charAt(0))] =' ';
            board[0][Character.getNumericValue(move.charAt(1))]=String.valueOf(move.charAt(3));
        }
    }
    public static void undoMove (String move)
    {
        if (move.charAt(4)!='P') //check if it is a pawn promotion move
        {
            board[Character.getNumericValue(move.charAt(0))][Character.getNumericValue(move.charAt(1))]=board[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))];
            board[Character.getNumericValue(move.charAt(2))][Character.getNumericValue(move.charAt(3))]=' ';

        }
        else //if it is a pawn promotion
        {
            board[1][Character.getNumericValue(move.charAt(0))] ='P';
            board[0][Character.getNumericValue(move.charAt(1))]=String.valueOf(move.charAt(2));
        }
    }
    //get location of array by [i/8][i%8]
    public static String generateAllLegal()
    {
        String legalMove= ""; //list of all legal moves
        for (int i=0; i<64; i++)
        {
            switch(board[i/8][i%8])
            {
            case 'K': 
            legalMove+= generateLegalKing(i);
                break;
                
            case 'N':
            legalMove+= generateLegalKnight(i);   
                break;

            case 'P':
            legalMove+= generateLegalPawn(i);
                break;
            case 'Q':
            legalMove+= generateLegalQueen(i);
                break;
            case 'R':
            legalMove+= generateLegalRook(i);
                break;
            case 'B':
            legalMove+= generateLegalBishop(i);
                break;   
            }
    
        }
        return legalMove;
    }
    public static String generateLegalBishop(int i)
    {
        String legalMove="";
        char oldPiece=' ';
        int rank= (i/8), file= (i%8);
        int temp=1;
        for(int x=-1; x<=1; x+=2) //fan out in either positive or negative direction for x (files) 
        {
            for(int y=-1; y<=1; y+=2) //fan out in either positive or negative direction for y (ranks)
            {
                try {
                    while(' '==board[rank+temp*y][file+temp*x] ) //go out in all directions and check if it is an empty square
                    {
                        oldPiece=board[rank+temp*y][file+temp*x]; //
                        board[rank][file]= ' ';
                        board[rank+temp*y][file+temp*x]='B'; //place bishop at the OldPiece location
                        if (kingSafe())
                        {
                            legalMove=legalMove+rank+file+(rank+temp*y)+(file+temp*x)+oldPiece;
                        }
                        board[rank][file]='B';
                        board[rank+temp*y][file+temp*x]=oldPiece;
                        temp++;// increment temp so it can go through each possible square
                    }
                    if (Character.isLowerCase(board[rank+temp*y][file+temp*x])) //check the square if there are any captures
                    {
                        oldPiece=board[rank+temp*y][file+temp*x]; //
                        board[rank][file]= ' ';
                        board[rank+temp*y][file+temp*x]='B'; //place bishop at the OldPiece location
                        if (kingSafe())
                        {
                            legalMove=legalMove+rank+file+(rank+temp*y)+(file+temp*x)+oldPiece;
                        }
                        board[rank][file]='B';
                        board[rank+temp*y][file+temp*x]=oldPiece;
                    }
                } catch (Exception e) { }
                temp=1; //need to reset temp so bishop goes back to original square
        }
    }
    return legalMove;
}

    public static String generateLegalKnight (int i)
    {
        String legalMove="";
        char oldPiece=' ';
        int rank= (i/8), file= (i%8);

        for (int x=-1; x<=1; x+=2)
        {
            for (int y=-1; y<=1; y+=1)
            {
                try {
                    if (Character.isLowerCase(board[rank+y][file+x*2]) || ' '==board[rank+y][file+x*2]) //check the square is empty of capturable
                    {
                        oldPiece=board[rank+y][file+x*2]; //
                        board[rank][file]= ' ';
                        if (kingSafe())
                        {
                            legalMove=legalMove+rank+file+(rank+y)+(file+x*2)+oldPiece;
                        }
                        board[rank][file]='N';
                        board[rank+y][file+x*2]=oldPiece;
                    }
                } catch (Exception e) { }
               
                try {   
                    if (Character.isLowerCase(board[rank+y*2][file+x]) || ' '==board[rank+y*2][file+x])
                    {
                        oldPiece=board[rank+y*2][file+x]; //
                        board[rank][file]= ' ';
                        if (kingSafe())
                        {
                            legalMove=legalMove+rank+file+(rank+y*2)+(file+x)+oldPiece;
                        }
                        board[rank][file]='N';
                        board[rank+y*2][file+x]=oldPiece;
                    }
                } catch (Exception e) { }
            }
        }
    
         
        return legalMove;
    }
    public static String generateLegalKing (int i) //add castling later
    {
        String legalMove="";
        char oldPiece=' ';
        int rank= (i/8), file= (i%8);
        for (int j=0;j<9; j++) 
        {   
            if (j!=4) //position of initial king cannot move onto itself/* 
            /*
            012
            3x5
            678    
            */
            //rank-1 file-1 upper leftmost king can move
            //j/3 j%3 location in array based off j like i/8 and i%8
            //combine to get [rank-1+j/3][file-1+j%3] goes through each sqaure king can possibly move to
            {
                try{
                    if (Character.isLowerCase(board[rank-1+j/3][file-1+j%3]) || ' '==board[rank-1+j/3][file-1+j%3])  //checks if king can move to the square
                    {
                        oldPiece=board[rank-1+j/3][file-1+j%3];
                        board[rank][file]=' ';
                        board [rank-1+j/3][file-1+j%3]= 'K';
                        int kingTemp=king; 
                        king=i+(j/3)*8+j%3-9; //get the position of the updated white king
    
                        board[rank][file]='K';
                        board[rank-1+j/3][file-1+j%3]= oldPiece;
                        if (kingSafe())
                        {
                            legalMove=legalMove+rank+file+(rank-1+j/3)+(file-1+j%3)+oldPiece;
                        }
                        king= kingTemp;
    
    
                    }     
                } catch(Exception e)
                {

                }
                
            }
        }

        return legalMove;
    }
    public static String generateLegalQueen (int i) 
    {
        String legalMove="";
        char oldPiece=' ';
        int rank= (i/8), file= (i%8);
        int temp=1; //variable used to go check each square in while loop

        for(int x=-1; x<=1; x++) //go positive or negative or nuetral in the x direction(files) //a file b file
        {
            for (int y=-1; y<=1;y++) //go positive or negative or neutral in the y directoin(ranks)1st rank 2nd rank
            {
            try {
                while(' '==board[rank+temp*y][file+temp*x] ) //go out in all directions and check if it is an empty square
                {
                    oldPiece=board[rank+temp*y][file+temp*x]; //
                    board[rank][file]= ' ';
                    board[rank+temp*y][file+temp*x]='Q'; //place queen at the OldPiece location
                    if (kingSafe())
                    {
                        legalMove=legalMove+rank+file+(rank+temp*y)+(file+temp*x)+oldPiece;
                    }
                    board[rank][file]='Q';
                    board[rank+temp*y][file+temp*x]=oldPiece;
                    temp++;// increment temp so it can go through each possible square
                }
                if (Character.isLowerCase(board[rank+temp*y][file+temp*x])) //check the square if there are any captures
                {
                    oldPiece=board[rank+temp*y][file+temp*x]; //
                    board[rank][file]= ' ';
                    board[rank+temp*y][file+temp*x]='Q'; //place queen at the OldPiece location
                    if (kingSafe())
                    {
                        legalMove=legalMove+rank+file+(rank+temp*y)+(file+temp*x)+oldPiece;
                    }
                    board[rank][file]='Q';
                    board[rank+temp*y][file+temp*x]=oldPiece;
                }
            } catch (Exception e) { }
            temp=1; //need to reset temp so queen goes back to original square
            } 
        }
        return legalMove;
    }
    public static String generateLegalPawn (int i)
    {
        String legalMove="";
        char oldPiece=' ';
        int rank= (i/8), file= (i%8);
        for (int x=-1; x<=1; x+=2)
        {
            try{ 
                if (Character.isLowerCase(board[rank-1][file+x]) && (i>=16) ) //capture
                {
                    oldPiece=board[rank-1][file+x]; //
                    board[rank][file]= ' ';
                    board[rank-1][file+x]='P'; //place queen at the OldPiece location
                    if (kingSafe())
                    {
                        legalMove=legalMove+rank+file+(rank-1)+(file+x)+oldPiece;
                    }
                    board[rank][file]='P';
                    board[rank-1][file+x]=oldPiece;
                }


            }
            catch (Exception e)
            {}
            try{ 
                if (Character.isLowerCase(board[rank-1][file+x]) && (i<=16) ) //capture and promotion
                {
                    char[] temp={'Q', 'R', 'B', 'N',}; //possible promotion pieces
                    for (int j=0; j<4; j++) //iterate through all possible promotion pieces and list the move for each piece
                    {
                        oldPiece=board[rank-1][file+x]; //
                    board[rank][file]= ' ';
                    board[rank-1][file+x]= temp[j]; //puts the promotion piece at the new location
                    if (kingSafe())
                    {

                        legalMove=legalMove+file+(file+x)+oldPiece+temp[j]+'P'; //added temp[i] to know what it promoted too
                    }
                    board[rank][file]='P';
                    board[rank-1][file+x]=oldPiece;
                    }
                }


            }
            catch (Exception e)
            {}
        }
            try{ //promotion no capture
                if (' '== board[rank-1][file] && i<16) //capture and promotion
                {

                    char[] temp={'Q', 'R', 'B', 'N'};//possible promotion pieces
                    for (int j=0; j<4; j++) //iterate through all possible promotion pieces and list the move for each piece
                    {
                        oldPiece=board[rank-1][file]; //
                    board[rank][file]= ' ';
                    board[rank-1][file]= temp[j]; //puts the promotion piece at the new location
                    if (kingSafe())
                    {

                        legalMove=legalMove+file+(file)+oldPiece+temp[j]+'P'; //added temp[i] to know what it promoted too
                    }
                    board[rank][file]='P';
                    board[rank-1][file]=oldPiece;
                    }
                }
            }
            catch (Exception e)
            {}
            try{ //moving up once
                if (' '== board[rank-1][file] && i>=16)
                {

                    oldPiece=board[rank-1][file]; //
                    board[rank][file]= ' ';
                    board[rank-1][file]= 'P'; 
                    if (kingSafe())
                    {

                        legalMove=legalMove+rank+file+(rank-1)+(file)+oldPiece;
                 
                    }
                    board[rank][file]='P';
                    board[rank-1][file]=oldPiece;
                }


            }
            catch (Exception e)
            {}
            try{ //move up 2
                if (' '== board[rank-1][file] && ' '==board[rank-2][file] && i>=48) //if pawn is on first rank and can move up 2 places
                {

        
                     oldPiece=board[rank-2][file]; 
                    board[rank][file]= ' ';
                    board[rank-2][file]= 'P'; 
                    if (kingSafe())
                    {

                        legalMove=legalMove+rank+file+(rank-2)+(file)+oldPiece; 
                    }
                    board[rank][file]='P';
                    board[rank-2][file]=oldPiece;
                    }
                }
            catch (Exception e)
            {}
        return legalMove;
    }
    public static String generateLegalRook (int i)
    {
        String legalMove="";
        char oldPiece=' ';
        int rank= (i/8), file= (i%8);
        int temp=1; //variable used to go check each square in while loop

        for(int x=-1; x<=1; x+=2) //go either in positive or negative direction 
        {
            try {
                while(' '==board[rank+temp*x][file] ) //go out in all directions and check if it is an empty square //scannign through rows
                {
                    oldPiece=board[rank+temp*x][file]; //
                    board[rank][file]= ' ';
                    board[rank+temp*x][file]='R'; //place rook at the OldPiece location
                    if (kingSafe())
                    {
                        legalMove=legalMove+rank+file+(rank+temp*x)+file+oldPiece;
                    }
                    board[rank][file]='R';
                    board[rank+temp*x][file]=oldPiece;
                    temp++;// increment temp so it can go through each possible square
                }
                if (Character.isLowerCase(board[rank+temp*x][file])) //check the square if there are any captures
                {
                    oldPiece=board[rank+temp*x][file]; //
                    board[rank][file]= ' ';
                    board[rank+temp*x][file]='R'; //place rook at the OldPiece location
                    if (kingSafe())
                    {
                        legalMove=legalMove+rank+file+(rank+temp*x)+file+oldPiece;
                    }
                    board[rank][file]='R';
                    board[rank+temp*x][file]=oldPiece;
                }
            } catch (Exception e) { }
            temp=1; //need to reset temp so queen goes back to original square
            try {
                while(' '==board[rank][file+temp*x] ) //go out in all directions and check if it is an empty square
                {
                    oldPiece=board[rank][file+temp*x]; //
                    board[rank][file]= ' ';
                    board[rank][file+temp*x]='R'; //place rook at the OldPiece location
                    if (kingSafe())
                    {
                        legalMove=legalMove+rank+file+(rank)+(file+temp*x)+oldPiece;
                    }
                    board[rank][file]='R';
                    board[rank][file+temp*x]=oldPiece;
                    temp++;// increment temp so it can go through each possible square
                }
                if (Character.isLowerCase(board[rank][file+temp*x])) //check the square if there are any captures
                {
                    oldPiece=board[rank][file+temp*x]; //
                    board[rank][file]= ' ';
                    board[rank][file+temp*x]='R'; //place rook at the OldPiece location
                    if (kingSafe())
                    {
                        legalMove=legalMove+rank+file+(rank)+(file+temp*x)+oldPiece;
                    }
                    board[rank][file]='R';
                    board[rank][file+temp*x]=oldPiece;
                }
            } catch (Exception e) { }
            temp=1; //need to reset temp so queen goes back to original square 
        }

        return legalMove;
       
    }
    public static boolean kingSafe()
    {
        int temp=1;
        for (int x=-1; x<=1; x+=2)
        {
            for (int y=-1;y<=1; y+=2)
            {
                try {
                     while (' '==board[king/8+temp*x][king%8+temp*y])
                     {
                         temp++;
                     }
                     if ('b'==board[king/8+temp*x][king%8+temp*y] || 'q'==board[king/8+temp*x][king%8+temp*y]) //check for bishop and queen pin
                     {
                         return false;
                     }
                } catch (Exception e) { }
            }
        } 

        for (int i=-1; i<=1; i+=2)
        {
            try {
                while (' '==board[king/8][king%8+temp*i])
                {
                    temp++;
                }
                if ('r'==board[king/8][king%8+temp*i] || 'q'== board[king/8][king%8+temp*i]) //queen and rook pin
                {
                    return false;
                }

            } catch (Exception e) {}
            temp=1;
    
        try {
            while (' '== board [king/8+temp*1][king%8])
            {
                temp++;
            }
            if ('r'==board[king/8+temp*1][king%8] || 'q'== board[king/8+temp*1][king%8])  
            {
                return false;
            }
        } catch (Exception e) {}
        temp=1;
    }

        for (int i=-1; i<=1; i+=2) //knight
        {
            for (int j=-1; j<=1; j+=2)
            {
                try {
                    if('k'==board[king/8+i][king%8+j*2])
                    {
                        return false;
                    }
                } catch (Exception e)
                {}
                try {
                    if('k'==board[king/8+i*2][king%8+j])
                    {
                        return false;
                    }
                } catch (Exception e)
                {}
                
            }
        }

        if (king>=16)
        {
          try {
              if('p'==board[king/8-1][king%8-1])
              {
                  return false;
              }
          } catch (Exception e) {
          }      
          try {
              if('p'== board[king/8-1][king%8+1])
              {
                  return false;
              }
          } catch (Exception e) {
          }
        }
        
        for (int i=-1; i<=1; i++)
        {
            for (int j=-1; j<=1; j++)
            {
                if (i!=0 ||j!=0 )
                {
                    try {
                        if ('a'==board[king/8+i][king%8+j])
                        {
                            return false;
                        }
                    } catch (Exception e) {
                    
                    }
                }
            }
        }
        return true;
    }
 
}