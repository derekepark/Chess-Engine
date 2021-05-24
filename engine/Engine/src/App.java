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
    public static String generateLegalRook(int i)
    {
        String legalMove="";
        return legalMove;
    }
    public static String generateLegalKnight (int i)
    {
        String legalMove="";
        return legalMove;
    }
    public static String generateLegalKing (int i) //add castling later
    {
        String legalMove="";
        char oldPiece=' ';
        int rank= (i/8), file= (i%8);
        for (int j=0;j<9; j++)
        {   
            if (j!=4) //position of initial king /* 
            /*
            012
            3x5
            678    
            */
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
        return legalMove;
    }
    public static String generateLegalPawn (int i)
    {
        String legalMove="";
        return legalMove;
    }
    public static String generateLegalBishop (int i)
    {
        String legalMove="";
        return legalMove;
    }
    public static boolean kingSafe()
    {
        return true;
    }
 
}
