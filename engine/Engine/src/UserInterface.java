import java.awt.*;
import javax.swing.*;
import javax.swing.event.MouseInputListener; //for mouse inputs



public class UserInterface extends JPanel implements MouseInputListener
{
    static int x=0;//x cord
    static int y=0;//y cord 
    public void paintComponent(Graphics g)
    {   
        super.paintComponent(g); //need this to set the background
        this.setBackground(Color.gray);
        this.addMouseListener(this); //need this to track mouse inputs
        this.addMouseMotionListener(this); //this for mouse movement
        g.setColor(Color.black); //order of the colors matter!
        g.fillRect(10,20,20,20);
        g.setColor(Color.pink);
        g.fillRect(20,40,20,20); //pink will always be on top of black since it order of colors
    }
    
    
    public void mouseClicked(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
          
    }
   
    public void mousePressed(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
  
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
    
    public void mouseExited(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
   
    public void mouseDragged(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }
   
    public void mouseMoved(java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub
        
    }


}