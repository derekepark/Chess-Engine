import javax.swing.*; //new commands and added functionality


public class App {


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
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        JFrame UI=new JFrame("Derek Park's Chess Engine");
        UI.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE)); //closes the UI when you close the window
        UserInterface ui=new UserInterface(); 
        UI.add(ui);
        UI.setSize(528, 550); 
        UI.setVisible(true);

    }
}
