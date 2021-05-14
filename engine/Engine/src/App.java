import javax.swing.*; //new commands and added functionality


public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        JFrame UI=new JFrame("Derek Park's Chess Engine");
        UI.setDefaultCloseOperation((JFrame.EXIT_ON_CLOSE)); //closes the UI when you close the window
        UserInterface ui=new UserInterface(); 
        UI.add(ui);
        UI.setSize(600, 600); 
        UI.setVisible(true);

    }
}
