import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGame_GUI implements ActionListener
{
    private JFrame frame;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private HangmanGame game;
    private char InputLetter;
    private JLabel result;


    public MainGame_GUI(int letters, int guesses)
    {
    	game = new EvilHangMan(letters, guesses);
    	
    }
    
    /*
     * This method creates all the UI components and then
     * opens a new window.
     */
    public void show() {

    	frame = new JFrame("Evil Hangman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(360,370));
        frame.setLayout(new FlowLayout());
        frame.setResizable(false);
        
        label1 = new JLabel("Let's play Evil Hangman!");
        
        label2 = new JLabel("Secret Word: "+game.displayGameState());
        label2.setFont(new Font("Default",Font.PLAIN,23));
       
        label3 = new JLabel(String.valueOf("Guesses Remaining: "+ game.numGuessesRemaining() +"\n"));
        result = new JLabel("");
        result.setForeground(Color.red);
        
        
        //this generates an image
        ImageIcon icon = new ImageIcon("blank.gif"); 
        JLabel hangmanPic = new JLabel(icon);

        frame.add(label1);
        frame.add(label2);
        frame.add(label3);


        frame.add(result);
        
        frame.add(hangmanPic);
        
        //add user choice
        for(int i = 65; i<91;i++)
        {
            char x = (char)i;
            JButton tempBtn = new JButton(String.valueOf(x));
            tempBtn.addActionListener(this);
            frame.add(tempBtn);
            
        }
        
        frame.setResizable(false);
        frame.setVisible(true);
    }
    
    /*
     * This is called when the user clicks any of the buttons in the UI.
     */
    @Override
	public void actionPerformed(ActionEvent e)
    {
        //to figure out which button the user pressed
        JButton temp = (JButton)e.getSource();
        temp.setEnabled(false);
        InputLetter = temp.getText().charAt(0);
        check(InputLetter); // make sure it's a valid choice
        String[] newLabels = game.controller(InputLetter, frame);
        label1.setText(newLabels[0]);
        label2.setText(newLabels[1]);
        label3.setText(newLabels[2]);
        result.setText(newLabels[3]);
    }

    public boolean check(char input)
    {
        //do the input check. Player can just input the English letters.
        if((input >= 'a' && input <= 'z') || (input >= 'A' && input <= 'Z'))
            return true;
        else
            return false;
    }
    
}