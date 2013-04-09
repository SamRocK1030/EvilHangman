 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
 

public class Start extends JPanel
                           implements ActionListener {
    static JFrame frame;
    int numLetters, numGuesses;
    private JComboBox<Integer> numLettersList, numGuessesList;
 
    public Start() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
   
        Integer[] numLettersOptions = {
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                };
       numLetters = numLettersOptions[0];
       
        Integer[] numGuessesOptions = {
                4,
                5,
                6,
                7,
                8,
                9,
                10,
                11,
                12,
                13,
                14,
                15,
                16,
                };
        numGuesses = numGuessesOptions[0];

        JLabel patternLabel1 = new JLabel("Select the number of letters");
        JLabel patternLabel2 = new JLabel("that will be in the word:");
 
        numLettersList = new JComboBox<Integer>(numLettersOptions);
        numLettersList.setEditable(true);
 
        JLabel patternLabel3 = new JLabel("Select the number of incorrect");
        JLabel patternLabel4 = new JLabel("guesses that are allowed:");
 
        numGuessesList = new JComboBox<Integer>(numGuessesOptions);
        numGuessesList.setEditable(true);
        
        JPanel patternPanel = new JPanel();
        patternPanel.setLayout(new BoxLayout(patternPanel,
                               BoxLayout.PAGE_AXIS));
        patternPanel.add(patternLabel1);
        patternPanel.add(patternLabel2);
        numLettersList.setAlignmentX(Component.LEFT_ALIGNMENT);
        patternPanel.add(numLettersList);
 
        
        JPanel guessPanel = new JPanel();
        guessPanel.setLayout(new BoxLayout(guessPanel,
                               BoxLayout.PAGE_AXIS));
        guessPanel.add(patternLabel3);
        guessPanel.add(patternLabel4);
        numGuessesList.setAlignmentX(Component.LEFT_ALIGNMENT);
        guessPanel.add(numGuessesList);
        
 
        patternPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
 
        add(patternPanel);
        add(guessPanel);
        add(Box.createRigidArea(new Dimension(0, 10)));
        
        JButton button = new JButton("Play!");
        button.addActionListener(this);
        add(button);
 
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

    } 
 
    @Override
	public void actionPerformed(ActionEvent e) {
    	//numLetters = (String)(patternList.getSelectedItem());
    	numLetters = (int) (numLettersList.getSelectedItem());
    	numGuesses = (int) (numGuessesList.getSelectedItem());
    	frame.dispose();
    	new MainGame_GUI(numLetters, numGuesses).show();

    }

 
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    public static void createAndShowGUI() {
        //Create and set up the window.
        frame = new JFrame("Evil Hangman");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        //Create and set up the content pane.
        JComponent newContentPane = new Start();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
    	createAndShowGUI();

    }
}
