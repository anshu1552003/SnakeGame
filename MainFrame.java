package snake;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    JTextField nameField;

    public MainFrame(){
        setTitle("Snake Game");

        setExtendedState(JFrame.MAXIMIZED_BOTH); // fullscreen
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        ThemeManager.setDarkTheme();

        showMenu();

        setVisible(true);
    }

    void showMenu(){

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(ThemeManager.bg);

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setBackground(ThemeManager.bg);

        JLabel label = new JLabel("Enter Name");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setForeground(ThemeManager.head);

        nameField = new JTextField(15);
        nameField.setMaximumSize(new Dimension(200,30));

        JButton start = new JButton("Start Game");
        JButton darkTheme = new JButton("Dark Theme");
        JButton neonTheme = new JButton("Neon Theme");

        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        darkTheme.setAlignmentX(Component.CENTER_ALIGNMENT);
        neonTheme.setAlignmentX(Component.CENTER_ALIGNMENT);

        // 🎮 START GAME
        start.addActionListener(e -> {
            String name = nameField.getText().trim();
            if(name.isEmpty()) name = "Player";

            nameField.setText("");
            startGame(name);
        });

        // 🎨 THEME BUTTONS
        darkTheme.addActionListener(e -> {
            ThemeManager.setDarkTheme();
            showMenu();
        });

        neonTheme.addActionListener(e -> {
            ThemeManager.setNeonTheme();
            showMenu();
        });

        box.add(label);
        box.add(Box.createRigidArea(new Dimension(0,10)));
        box.add(nameField);
        box.add(Box.createRigidArea(new Dimension(0,10)));
        box.add(start);
        box.add(Box.createRigidArea(new Dimension(0,10)));
        box.add(darkTheme);
        box.add(Box.createRigidArea(new Dimension(0,5)));
        box.add(neonTheme);

        mainPanel.add(box);

        setContentPane(mainPanel);
        revalidate();
        repaint();
    }

    void startGame(String name){

        GamePanel panel = new GamePanel(this, name);

        setContentPane(panel);
        revalidate();
        repaint();

        panel.requestFocusInWindow();
    }

    public void backToMenu(){
        showMenu();
    }
}