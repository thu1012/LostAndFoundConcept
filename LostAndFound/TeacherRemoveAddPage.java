import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherRemoveAddPage extends JPanel {
    private JLabel title;
    private JButton back;
    private JButton addItem, removeItem;
    public TeacherRemoveAddPage(JFrame parentFrame){
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        title = new JLabel("   Welcome Teacher!");
        title.setOpaque(true);
        title.setBackground(new Color(3, 100, 252));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("TimesRoman", Font.BOLD, 30));
        title.setSize(new Dimension(1200, 45));
        title.setLocation(0, 0);
        this.add(title);
        back = new JButton("Back to Log In");
        back.setSize(200, 30);
        back.setLocation(200, 150);
        back.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        back.setBackground(new Color(3, 100, 252));
        back.setForeground(Color.WHITE);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parentFrame.dispose();
                JFrame frame = new JFrame("Lost and Found");
                frame.setSize(800, 300);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((int) ((screenSize.getWidth() - frame.getWidth()) / 2), (int) ((screenSize.getHeight() - frame.getHeight()) / 2));
                frame.getContentPane().add(new HomeScreen(frame));
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        this.add(back);
        addItem = new JButton("Add Item");
        addItem.setSize(250, 50);
        addItem.setLocation(15, 75);
        addItem.setFont(new Font("TimesRoman", Font.BOLD, 30));
        addItem.setBackground(new Color(3, 100, 252));
        addItem.setForeground(Color.WHITE);
        addItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parentFrame.dispose();
                JFrame frame = new JFrame("Lost and Found");
                frame.setSize(600, 300);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((int) ((screenSize.getWidth() - frame.getWidth()) / 2), (int) ((screenSize.getHeight() - frame.getHeight()) / 2));
                frame.getContentPane().add(new LostItemsPage(frame, 2));
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        this.add(addItem);
        removeItem = new JButton("Remove Item");
        removeItem.setSize(250, 50);
        removeItem.setLocation(315, 75);
        removeItem.setFont(new Font("TimesRoman", Font.BOLD, 30));
        removeItem.setBackground(new Color(3, 100, 252));
        removeItem.setForeground(Color.WHITE);
        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parentFrame.dispose();
                JFrame frame = new JFrame("Lost and Found");
                frame.setSize(600, 300);
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((int) ((screenSize.getWidth() - frame.getWidth()) / 2), (int) ((screenSize.getHeight() - frame.getHeight()) / 2));
                frame.getContentPane().add(new LostItemsPage(frame, 3));
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
        this.add(removeItem);
    }
}
