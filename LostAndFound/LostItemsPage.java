import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LostItemsPage extends JPanel {
    private JLabel title, note;
    private JLabel categoryLabel, subCategoryLabel;
    private JComboBox<String> category, subcategory;
    private JButton search, back;
    private String[] categories = {"Select Category", "Book", "Bag", "Phone", "Clothing", "Currency", "Electronics",
                                    "Eye Wear", "Footwear", "Jewelry", "Keys", "Musical Instrument",
                                    "Sport Equipment", "Wallet", "Others"};
    private String[][] allSubCategories = {{"Address Book", "Diary", "Hard Cover", "Paper Back", "Photo Album", "Soft Cover"},
            {"Backpack", "Briefcase", "Duffle Bag", "Suit Bag", "Hand Bag", "Messenger Bag", "Shopping Bag", "Shoulder Bag", "Suitcase", "Tote Bag"},
            {"iPhone", "Android"},
            {"Coat", "Dress", "Jacket", "Pants", "Shirt", "Skirt", "Sweater", "T-Shirt"},
            {"Cash", "Foreign Currency", "Gift Card", "Check"},
            {"Calculator", "Camera", "Camera Accessories", "Computer", "Computer Accessories", "Earphone", "Ear Buds", "USB"},
            {"Glasses", "Eyeglass Case without Glasses", "Sunglasses"},
            {"Boots", "Sandals", "Shoes", "Slippers", "Sneakers"},
            {"Bracelet", "Cuff Links", "Earrings", "Necklace", "Ring", "Watch"},
            {"Car", "House"},
            {"Clarinet", "Flute", "Guitar", "Harmonica", "Saxophone", "Trumpet", "Violin"},
            {"Ball", "Bats", "Golf Club", "Skateboard", "Bowling Ball"}};
    public LostItemsPage(JFrame parentFrame, int isStudentAccount){
        boolean isStudent = isStudentAccount==1;
        this.setLayout(null);
        this.setBackground(Color.LIGHT_GRAY);
        if(isStudentAccount==3){
            title = new JLabel("   What do you want to remove?");
        }
        else if(isStudent) {
            title = new JLabel("   What did you lose?");
        }
        else{
            title = new JLabel("   What is the lost item?");
        }
        title.setOpaque(true);
        title.setBackground(new Color(3, 100, 252));
        title.setForeground(Color.WHITE);
        title.setFont(new Font("TimesRoman", Font.BOLD, 30));
        title.setSize(new Dimension(1200, 45));
        title.setLocation(0, 0);
        this.add(title);
        categoryLabel = new JLabel("Choose Category: ");
        categoryLabel.setLocation(150, 95);
        categoryLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        categoryLabel.setSize(170, 23);
        this.add(categoryLabel);
        subCategoryLabel = new JLabel("Choose Subcategory: ");
        subCategoryLabel.setLocation(125, 145);
        subCategoryLabel.setFont(new Font("TimesRoman", Font.BOLD, 15));
        subCategoryLabel.setSize(170, 23);
        this.add(subCategoryLabel);
        if(isStudent) {
            note = new JLabel("In order to search out database for your lost item(s), you must complete the following section.");
        }
        else{
            note = new JLabel("Please Enter the lost items information:");
        }
        note.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        note.setSize(new Dimension(1200, 45));
        note.setLocation(35, 40);
        this.add(note);
        category = new JComboBox(categories);
        category.setSize(175, 30);
        category.setFont(new Font("TimesRoman", Font.BOLD, 15));
        category.setLocation(285, 92);
        this.add(category);
        category.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                DefaultComboBoxModel model = (DefaultComboBoxModel) subcategory.getModel();
                model.removeAllElements();
                if(category.getSelectedIndex()!=0) {
                    model.addElement("Select Subcategory");
                    for(String str:allSubCategories[category.getSelectedIndex()-1]){
                        model.addElement(str);
                    }
                    model.addElement("Others");
                }
                else if(category.getSelectedIndex()==10){
                    model.addElement("Wallet");
                }
                else if(category.getSelectedIndex()==11){
                    model.addElement("Others");
                }
                else{
                    model.addElement("No Options");
                }
                subcategory.setModel(model);
            }
        });
        subcategory = new JComboBox(new String[]{"No Options"});
        subcategory.setSize(175, 30);
        subcategory.setLocation(285, 142);
        subcategory.setFont(new Font("TimesRoman", Font.BOLD, 15));
        this.add(subcategory);
        if(isStudentAccount==3){
            search = new JButton("Next");
        }
        else if(isStudent) {
            search = new JButton("Search");
        }
        else{
            search = new JButton("Add Item");
        }
        search.setSize(150, 30);
        search.setLocation(135, 200);
        search.setFont(new Font("TimesRoman", Font.BOLD, 20));
        search.setBackground(new Color(3, 100, 252));
        search.setForeground(Color.WHITE);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(category.getSelectedIndex()!=0&&subcategory.getSelectedIndex()!=0) {
                    parentFrame.dispose();
                    JFrame frame = new JFrame("Lost and Found");
                    frame.setSize(800, 300);
                    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setLocation((int) ((screenSize.getWidth() - frame.getWidth()) / 2), (int) ((screenSize.getHeight() - frame.getHeight()) / 2));
                    frame.setResizable(false);
                    frame.setVisible(true);
                    String categoryText = categories[category.getSelectedIndex()];
                    String subcategoryText = allSubCategories[category.getSelectedIndex()-1][subcategory.getSelectedIndex()-1];
                    if(isStudentAccount==3){
                        frame.getContentPane().add(new AddItemPage(frame, isStudentAccount, categoryText, subcategoryText));
                    }
                    else if(isStudent) {
                        frame.setSize(800, 250);
                        frame.getContentPane().add(new DisplayLostItemsPage(frame, isStudentAccount, categoryText, subcategoryText));
                    }
                    else{
                        frame.getContentPane().add(new AddItemPage(frame, isStudentAccount, categoryText, subcategoryText));
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Category and/or subcategory is not selected.");
                }
            }
        });
        this.add(search);
        back = new JButton("Back");
        back.setSize(80, 20);
        back.setLocation(315, 205);
        back.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        back.setBackground(new Color(3, 100, 252));
        back.setForeground(Color.WHITE);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parentFrame.dispose();
                JFrame frame = new JFrame("Lost and Found");
                if(isStudent) {
                    frame.setSize(800, 300);
                    frame.getContentPane().add(new HomeScreen(frame));
                }
                else{
                    frame.setSize(600, 300);
                    frame.getContentPane().add(new TeacherRemoveAddPage(frame));
                }
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
                frame.setLocation((int) ((screenSize.getWidth() - frame.getWidth()) / 2), (int) ((screenSize.getHeight() - frame.getHeight()) / 2));
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
        this.add(back);
    }
}
