import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener; //for mouse inputs



public class UserInterface extends JPanel implements MouseInputListener
{
    static int x=0;//x cord
    static int y=0;//y cord 
    static int tileSize=64;
    public void paintComponent(Graphics g)
    {   
        super.paintComponent(g); //need this to set the background
        this.setBackground(Color.gray);
        this.addMouseListener(this); //need this to track mouse inputs
        this.addMouseMotionListener(this); //this for mouse movement
        for (int i=0; i<64; i+=2) //create the board
        {
            g.setColor(new Color(235,243,243));
            g.fillRect((i%8+(i/8)%2) *tileSize, (i/8)*tileSize, tileSize,tileSize);
            g.setColor(new Color(0,121,191));
            g.fillRect(((i+1)%8-((i+1)/8)%2) *tileSize, (i/8)*tileSize, tileSize,tileSize);

        }




            //import all the images of the pieces
     Image king= new ImageIcon("king.png").getImage();
      Image bking= new ImageIcon("bking.png").getImage();
      Image queen= new ImageIcon("queen.png").getImage();
      Image bqueen= new ImageIcon("bqueen.png").getImage();
      Image bishop= new ImageIcon("bishop.png").getImage();
      Image bbishop= new ImageIcon("bbishop.png").getImage();
      Image knight= new ImageIcon("knight.png").getImage();
      Image bknight= new ImageIcon("bknight.png").getImage();
      Image pawn= new ImageIcon("pawn.png").getImage();
      Image bpawn= new ImageIcon("bpawn.png").getImage();
      Image rook= new ImageIcon("rook.png").getImage();
      Image brook= new ImageIcon("brook.png").getImage();
        for (int i=0; i<64; i++) //place the pieces on the boarc
        {
            switch(App.board[i/8][i%8])
            {
                case 'K': 
                g.drawImage(king, (i%8)*tileSize, (i/8)*tileSize,(i%8+1)*tileSize ,(i/8+1)*tileSize,x,y,y,y, this); //FIX THE WAY IT LOOKS
                break;
            /*case 'k':
                g.drawImage(bking, x, y, this);
                break;
            case 'N':
                g.drawImage(knight, x, y, this);
                break;
            case 'n':
                g.drawImage(bknight, x, y, this);
                break;
            case 'p':
                g.drawImage(bpawn, x, y, this);
                break;
            case 'P':
                g.drawImage(pawn, x, y, this);
                break;
            case 'Q':
                g.drawImage(queen, x, y, this);
                break;
            case 'q':
                g.drawImage(bqueen, x, y, this);
                break;
            case 'r':
                g.drawImage(brook, x, y, this);
                break;
            case 'R':
                g.drawImage(rook, x, y, this);
                break;
            case 'B':
                g.drawImage(bishop, x, y, this);
                break;
            case 'b':
                g.drawImage(bbishop, x, y, this);
                break;   */
            }
        }
    }
    
   

    public void mouseClicked(java.awt.event.MouseEvent e) {
          
    }
   
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        x=e.getX();
        y=e.getY();
        repaint();
    }
  
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        x=e.getX();
        y=e.getY();
        repaint();
    }
    
    public void mouseEntered(java.awt.event.MouseEvent e) {
     
    }
    
    public void mouseExited(java.awt.event.MouseEvent e) {

    }
   
    public void mouseDragged(java.awt.event.MouseEvent e) {
    
    }
   
    public void mouseMoved(java.awt.event.MouseEvent e) {
    
    }


}