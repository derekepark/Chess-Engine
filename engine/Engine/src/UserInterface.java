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






    /*  Image king= new ImageIcon("king.jpg").getImage();
      Image bking= new ImageIcon("bking.jpg").getImage();
      Image queen= new ImageIcon("queen.jpg").getImage();
      Image bqueen= new ImageIcon("bqueen.jpg").getImage();
      Image bishop= new ImageIcon("bishop.jpg").getImage();
      Image bbishop= new ImageIcon("bbishop.jpg").getImage();
      Image knight= new ImageIcon("knight.jpg").getImage();
      Image bknight= new ImageIcon("bknight.jpg").getImage();
      Image pawn= new ImageIcon("pawn.jpg").getImage();
      Image bpawn= new ImageIcon("bpawn.jpg").getImage();
      Image rook= new ImageIcon("rook.jpg").getImage();
      Image brook= new ImageIcon("brook.jpg").getImage();
        */
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