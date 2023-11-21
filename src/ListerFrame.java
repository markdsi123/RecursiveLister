import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
public class ListerFrame extends JFrame
{
    JPanel mainPanel,displayPanel,buttonPanel;
    JScrollPane scrollPane;
    JTextArea textArea;
    JLabel label;
    JButton startButton,quitButton;
    JFileChooser myChoice;

    public ListerFrame()
    {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        label = new JLabel("RECURSIVE LISTER");
        label.setFont(new Font("Times New Roman", Font.BOLD, 40));
        label.setHorizontalAlignment(JLabel.CENTER);

        createDisplayPnl();
        createBtnPnl();

        mainPanel.add(displayPanel, BorderLayout.CENTER);
        mainPanel.add(label, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();

        int screenH = screenSize.height;
        int screenW = screenSize.width;

        setLocation(screenW / 3, screenH / 3);
        setSize(screenW / 2, screenH / 2);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createDisplayPnl()
    {
        displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout(1,1));
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        displayPanel.add(scrollPane);
    }
    public void createBtnPnl()
    {
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,2));
        startButton = new JButton("START");
        quitButton = new JButton("QUIT");
        startButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        quitButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
        startButton.addActionListener((ActionEvent ae) ->{display();});
        quitButton.addActionListener((ActionEvent ae) -> {System.exit(0);});
        buttonPanel.add(startButton);
        buttonPanel.add(quitButton);
    }
    private void display()
    {
        textArea.setText("");
        myChoice = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        File f = null;
        String[] paths;
        myChoice.setCurrentDirectory(workingDirectory);
        myChoice.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        myChoice.setAcceptAllFileFilterUsed(false);


        if
        (myChoice.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            f = myChoice.getCurrentDirectory();
            paths = f.list();
            textArea.append("Your Current Directory where the files is stored: " + f + "\n" + "Your Sub Directories where the files are stored: \n");
            for(String i:paths){
                textArea.append(i + "\n");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(mainPanel, "Please select a directory", "Cannot FIND ANYTHING", JOptionPane.ERROR_MESSAGE);
        }

    }
}