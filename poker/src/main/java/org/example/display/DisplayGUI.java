package org.example.display;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayGUI {

    public JFrame frame;
    public JButton welcomeButton;
    public JButton nextButton;
    public JTextArea textArea;
    public JPanel panel;
    private int width;
    private int height;

    public DisplayGUI(int width, int height){
        this.width = width;
        this.height = height;
        frame = new JFrame();
        welcomeButton = new JButton("Play!");
        nextButton = new JButton("Next");
        textArea = new JTextArea(30,30);
        panel = new JPanel();
    }

    public void setUpSwing(){
        panel.add(textArea);
        panel.add(welcomeButton);
        frame.setSize(width, height);
        frame.setTitle("WELCOME TO TEXAS HOLD'EM ROUND");
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUpButtonListeners();
        frame.setVisible(true);
    }

    public void setUpButtonListeners(){
        ActionListener welcomeButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.remove(welcomeButton);
                panel.repaint();

                panel.add(nextButton);

                textArea.append("This text is appended.");
                textArea.append("\n");
            }
        };

        ActionListener nextButtonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("next button text");
                textArea.append("\n");
            }
        };
        welcomeButton.addActionListener(welcomeButtonListener);
        nextButton.addActionListener(nextButtonListener);
    }

    

}
