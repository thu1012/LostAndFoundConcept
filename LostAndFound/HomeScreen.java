import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HomeScreen extends JPanel {
    private JLabel title;
    private JLabel firstNameLabel, lastNameLabel, emailLabel;
    private JTextField firstNameText, lastNameText, emailText;
    private JButton logIn;
    public HomeScreen(JFrame parentFrame){
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        title = new JLabel("   Lost and Found Log In");
        title.setOpaque(true);
        title.setBackground(new Color(3, 100, 252));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("TimesRoman", Font.BOLD, 30));
        title.setSize(new Dimension(1200, 45));
        title.setLocation(0, 0);
        this.add(title);
        firstNameLabel = new JLabel("    First Name:");
        firstNameLabel.setLocation(0, 62);
        firstNameLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        firstNameLabel.setSize(130, 23);
        this.add(firstNameLabel);
        lastNameLabel = new JLabel("    Last Name:");
        lastNameLabel.setLocation(0, 112);
        lastNameLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        lastNameLabel.setSize(130, 23);
        this.add(lastNameLabel);
        emailLabel = new JLabel("    School Email Address:");
        emailLabel.setLocation(0, 162);
        emailLabel.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        emailLabel.setSize(250, 23);
        this.add(emailLabel);
        firstNameText = new JTextField();
        firstNameText.setSize(610, 40);
        firstNameText.setLocation(140, 55);
        firstNameText.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        this.add(firstNameText);
        lastNameText = new JTextField();
        lastNameText.setSize(610, 40);
        lastNameText.setLocation(140, 105);
        lastNameText.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        this.add(lastNameText);
        emailText = new JTextField();
        emailText.setSize(510, 40);
        emailText.setLocation(240, 155);
        emailText.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        this.add(emailText);
        logIn = new JButton("Log In");
        logIn.setSize(200, 40);
        logIn.setLocation(275, 207);
        logIn.setFont(new Font("TimesRoman", Font.BOLD, 30));
        logIn.setBackground(new Color(3, 100, 252));
        logIn.setForeground(Color.WHITE);
        this.add(logIn);
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isValidAccount()!=0) {
                    parentFrame.dispose();
                    JFrame frame = new JFrame("Lost and Found");
                    frame.setSize(600, 300);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setLocation((int) ((screenSize.getWidth() - frame.getWidth()) / 2), (int) ((screenSize.getHeight() - frame.getHeight()) / 2));
                    if(isValidAccount()==1){
                        frame.getContentPane().add(new LostItemsPage(frame, 1));
                    }
                    if(isValidAccount()==2){
                        frame.getContentPane().add(new TeacherRemoveAddPage(frame));
                    }
                    frame.setResizable(false);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null, "Please check for first name, last name, and/or school email address\nIf they are correct, please contact a staff member");
                }
            }
        });
    }
    private int isValidAccount(){
        if(firstNameText.getText().equals("")||lastNameText.getText().equals("")||emailText.getText().equals("")){
            return 0;
        }
        try {
            String name = firstNameText.getText() + " " + lastNameText.getText();
            String email = emailText.getText();
            File studentInfo = new File("accountInfo.txt");
            Scanner scan = new Scanner(studentInfo);
            while(scan.hasNextLine()){
                String line = scan.nextLine();
                if(line.contains(name)){
                    boolean isStudent = line.contains("Student");
                    if(scan.nextLine().equals(email)) {
                        return (isStudent?1:2);
                    }
                }
            }
            scan.close();
        } catch(FileNotFoundException e){
            System.out.println("Error" + e.getMessage());
        }
        return 0;
    }
}
