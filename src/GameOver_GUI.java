import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GameOver_GUI implements ActionListener
{
    private JFrame parentFrame;
    private JFrame gameOverFrame;
    private JLabel answerIs;
    private JLabel secretWord;
    private JLabel gameResult;
    private JButton returnButton;
    private ImageIcon background;
    private JPanel imagePanel;
    
    
    public GameOver_GUI(String letters, boolean userHasWon, JFrame parent)
    {
        parentFrame = parent;
        returnButton = new JButton("Return to the main menu");
        returnButton.addActionListener(this);

        if (userHasWon)
        	setUpWinScreen(letters);
        else
        	setUpLoseScreen(letters);
        
        gameOverFrame.setVisible(true);

    }

    private void setUpWinScreen(String letters)
    {
    	gameOverFrame = new JFrame("You are the winner!!!");
        bg(gameOverFrame);
        answerIs = new JLabel("The answer is ");
        
        secretWord = new JLabel(letters);
        secretWord.setFont(new Font("Default",Font.PLAIN,23));
        secretWord.setForeground(Color.red);
        gameResult = new JLabel("You are winner!"); 
        
        gameOverFrame.add(answerIs);
        gameOverFrame.add(secretWord);
        gameOverFrame.add(gameResult);
        gameOverFrame.add(returnButton);
    }
    
    private void setUpLoseScreen(String letters)
    {
        gameOverFrame = new JFrame("You are the loser!");
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameOverFrame.setSize(new Dimension(300,470));
        gameOverFrame.setLayout(new FlowLayout());
        
        secretWord = new JLabel("The answer is "+ letters +".");
        gameResult = new JLabel("You are the Loser!"); 
        
        ImageIcon icon = new ImageIcon("loser.gif"); 
        JLabel loserPic = new JLabel(icon);
        
        gameOverFrame.add(secretWord);
        gameOverFrame.add(gameResult);
        gameOverFrame.add(returnButton);
        gameOverFrame.add(loserPic);
    }
    
    public void bg(JFrame frame)
    {
        background = new ImageIcon("Congrats.gif");
        JLabel label = new JLabel(background);
     
        label.setBounds(0, 0, background.getIconWidth(),
            background.getIconHeight());
   
        imagePanel = (JPanel) frame.getContentPane();
        imagePanel.setOpaque(false);

        imagePanel.setLayout(new FlowLayout());

        frame.getLayeredPane().setLayout(null);

        frame.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(background.getIconWidth(), background.getIconHeight());
        frame.setResizable(false);

    }

    @Override
	public void actionPerformed(ActionEvent e)
    {
    	gameOverFrame.dispose(); //close the window
        parentFrame.dispose(); // and the parent
    	new Start();
		Start.createAndShowGUI(); // start over
    }
}