import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class AddItemPage extends JPanel {
    private JButton back, completeAddingItem;
    private JLabel title;
    private JLabel imageurlLabel, imageDisplay;
    private JTextField imageurl;
    private FileWriter fw;
    private PrintWriter pw;
    public AddItemPage(JFrame parentFrame, int isStudentAccount, String category, String subcategory){
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        if(isStudentAccount==2) {
            title = new JLabel("   Add Lost Item Image (" + category + " : " + subcategory + ")");
        }
        else{
            title = new JLabel("   Remove Lost Item (" + category + " : " + subcategory + ")");
        }
        title.setOpaque(true);
        title.setBackground(new Color(3, 100, 252));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("TimesRoman", Font.BOLD, 30));
        title.setSize(new Dimension(1200, 45));
        title.setLocation(0, 0);
        this.add(title);
        back = new JButton("Back to Lost Item Info");
        back.setSize(170, 20);
        back.setLocation(425, 205);
        back.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        back.setBackground(new Color(3, 100, 252));
        back.setForeground(Color.WHITE);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parentFrame.dispose();
                JFrame frame = new JFrame("Lost and Found");
                frame.setSize(600, 300);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((int) ((screenSize.getWidth() - frame.getWidth()) / 2), (int) ((screenSize.getHeight() - frame.getHeight()) / 2));
                frame.getContentPane().add(new LostItemsPage(frame, isStudentAccount));
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        this.add(back);
        if(isStudentAccount==2) {
            imageurlLabel = new JLabel("Image URL: ");
        }
        else{
            imageurlLabel = new JLabel("Item number: ");
        }
        imageurlLabel.setLocation(60, 53);
        imageurlLabel.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        imageurlLabel.setSize(130, 23);
        this.add(imageurlLabel);
        imageurl = new JTextField();
        imageurl.setSize(610, 20);
        imageurl.setLocation(140, 55);
        imageurl.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        imageurl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isStudentAccount==2) {
                    try {
                        File file = new File(imageurl.getText());
                        if (file.exists()) {
                            imageDisplay.setText("");
                            imageDisplay.setIcon(resizeImage(Toolkit.getDefaultToolkit().getImage(imageurl.getText())));
                        } else {
                            imageDisplay.setIcon(null);
                            imageDisplay.setText("Image Not Found!");
                        }
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }
                else{
                    try {
                        File file = new File("lostItems.txt");
                        Scanner scan = new Scanner(file);
                        while(scan.hasNextLine()){
                            if(scan.nextLine().contains(category)){
                                if(!scan.hasNextLine()){
                                    break;
                                }
                                if(scan.nextLine().contains(subcategory)){
                                    if(!scan.hasNextLine()){
                                        break;
                                    }
                                    if(getNumber(scan.nextLine())==Integer.valueOf(imageurl.getText())) {
                                        imageDisplay.setText("");
                                        imageDisplay.setIcon(resizeImage(Toolkit.getDefaultToolkit().getImage(scan.nextLine())));
                                    }
                                }
                            }
                        }
                    }
                    catch(Exception e){
                        System.out.println("Error: " + e.getMessage());
                    }
                }
            }
        });
        this.add(imageurl);
        imageDisplay = new JLabel();
        imageDisplay.setSize(250, 250);
        imageDisplay.setLocation(350, 13);
        this.add(imageDisplay);
        if(isStudentAccount==2) {
            completeAddingItem = new JButton("Complete Adding Item");
        }
        else{
            completeAddingItem = new JButton("Complete Removing Item");
        }
        completeAddingItem.setSize(300, 30);
        completeAddingItem.setLocation(105, 200);
        completeAddingItem.setFont(new Font("TimesRoman", Font.BOLD, 20));
        completeAddingItem.setBackground(new Color(3, 100, 252));
        completeAddingItem.setForeground(Color.WHITE);
        completeAddingItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(isStudentAccount==2) {
                    File file = new File(imageurl.getText());
                    if (file.exists()) {
                        addItem(new LostItemInfo(category, subcategory, resizeImage(Toolkit.getDefaultToolkit().getImage(imageurl.getText())).getImage()));
                        parentFrame.dispose();
                        JFrame frame = new JFrame("Lost and Found");
                        frame.setSize(600, 300);
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        frame.setLocation((int) ((screenSize.getWidth() - frame.getWidth()) / 2), (int) ((screenSize.getHeight() - frame.getHeight()) / 2));
                        frame.getContentPane().add(new LostItemsPage(frame, isStudentAccount));
                        frame.setResizable(false);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                    }
                }
                else{
                    if(imageurl.getText().equals("")){
                        JOptionPane.showMessageDialog(null, "No number is entered.");
                    }
                    else{
                        removeItem(category, subcategory, imageurl.getText());
                        parentFrame.dispose();
                        JFrame frame = new JFrame("Lost and Found");
                        frame.setSize(600, 300);
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        frame.setLocation((int) ((screenSize.getWidth() - frame.getWidth()) / 2), (int) ((screenSize.getHeight() - frame.getHeight()) / 2));
                        frame.getContentPane().add(new LostItemsPage(frame, isStudentAccount));
                        frame.setResizable(false);
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.setVisible(true);
                    }
                }
            }
        });
        this.add(completeAddingItem);
    }
    private void removeItem(String category, String subcategory, String number){
        try {
            File file = new File("lostItems.txt");
            String result = "";
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String cat = scan.nextLine();
                if(!scan.hasNextLine()){
                    break;
                }
                String sub = scan.nextLine();
                if(!scan.hasNextLine()){
                    break;
                }
                String num = scan.nextLine();
                if(!scan.hasNextLine()){
                    break;
                }
                String url = scan.nextLine();
                if(!scan.hasNextLine()){
                    break;
                }
                scan.nextLine();
                if(!(cat.contains(category)&&sub.contains(subcategory)&&num.contains(number))){
                    if(cat.contains(category)&&sub.contains(subcategory)){
                        num = num.substring(num.indexOf(" ") + 1);
                        String tempStr = number.substring(num.indexOf(" ") + 1);
                        if(Integer.valueOf(num)>Integer.valueOf(tempStr)) {
                            num = String.valueOf(Integer.valueOf(num) - 1);
                        }
                        num = "NO: " + num;
                    }
                    result += cat + "\n" + sub + "\n" + num + "\n" + url + "\n\n";
                }
            }
            fw = new FileWriter(file);
            pw = new PrintWriter(fw);
            pw.println(result);
            pw.close();
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private ImageIcon resizeImage(Image image){
        return new ImageIcon(image.getScaledInstance(120, 120, Image.SCALE_DEFAULT));
    }
    private void addItem(LostItemInfo item) {
        try {
            File file = new File("lostItems.txt");
            fw = new FileWriter(file, true);
            pw = new PrintWriter(fw);
            pw.println(item.toString() + imageurl.getText() + "\n");
            pw.close();
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    private int getNumber(String str){
        return Integer.valueOf(str.substring(str.indexOf(" ") + 1));
    }
}
