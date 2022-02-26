import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DisplayLostItemsPage extends JPanel {
    private JButton back;
    private JLabel title;
    public DisplayLostItemsPage(JFrame parentFrame, int isStudentAccount, String category, String subcategory){
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        title = new JLabel("   Add Lost Item (" + category + " : " + subcategory + ")");
        title.setOpaque(true);
        title.setBackground(new Color(3, 100, 252));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("TimesRoman", Font.BOLD, 30));
        title.setSize(new Dimension(1200, 45));
        title.setLocation(0, 0);
        back = new JButton("Back to Lost Item Info");
        back.setSize(170, 20);
        back.setLocation(315, 15);
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
        drawAllItems(parentFrame, category, subcategory);
    }
    private void drawAllItems(JFrame parentFrame, String category, String subcategory){
        ArrayList<LostItemInfo> list = new ArrayList<>();
        File file = new File("lostItems.txt");
        Point point = new Point(-100, 50);
        boolean isEmpty = true;
        try{
            Scanner scan = new Scanner(file);
            while(scan.hasNextLine()){
                String cat = scan.nextLine();
                cat = cat.substring(cat.indexOf(" ") + 1);
                if(!scan.hasNextLine()){
                    break;
                }
                String sub = scan.nextLine();
                sub = sub.substring(sub.indexOf(" ") + 1);
                if(!scan.hasNextLine()){
                    break;
                }
                String num = scan.nextLine();
                num = num.substring(num.indexOf(" ") + 1);
                if(!scan.hasNextLine()){
                    break;
                }
                String imgURL = scan.nextLine();
                if(!scan.hasNextLine()){
                    break;
                }
                scan.nextLine();
                if(cat.equals(category)&&sub.equals(subcategory)) {
                    isEmpty = false;
                    point.x += 140;
                    point.y += 0;
                    if (point.x >= 700) {
                        point.x = 40;
                        point.y += 160;
                        parentFrame.setSize(parentFrame.getWidth(), parentFrame.getHeight() + 160);
                        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                        parentFrame.setLocation((int) ((screenSize.getWidth() - parentFrame.getWidth()) / 2), (int) ((screenSize.getHeight() - parentFrame.getHeight()) / 2));
                    }
                    new LostItemInfo(cat, sub, Integer.valueOf(num), Toolkit.getDefaultToolkit().getImage(imgURL)).drawOnPanel(this, point);
                }
            }
            if(isEmpty){
                JLabel empty = new JLabel("Sorry we did not find item to your description.");
                empty.setFont(new Font("TimesRoman", Font.PLAIN, 20));
                empty.setSize(600, 20);
                empty.setLocation(200, 90);
                this.add(empty);
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
