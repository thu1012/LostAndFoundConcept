import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Scanner;

public class LostItemInfo {
    private String category, subcategory;
    private Image image;
    private int NO;
    public LostItemInfo(String category, String subcategory, Image image){
        this.category = category;
        this.subcategory = subcategory;
        this.image = image;
        this.NO = getNO();
    }
    public LostItemInfo(String category, String subcategory, int NO, Image image){
        this.category = category;
        this.subcategory = subcategory;
        this.image = image;
        this.NO = NO;
    }
    private int getNO(){
        int result = 0;
        try {
            File file = new File("lostItems.txt");
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()){
                if(scan.nextLine().contains(category)){
                    if(scan.nextLine().contains(subcategory)){
                        if(getNumber(scan.nextLine())<=result) {
                            result++;
                        }
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        return result;
    }
    private int getNumber(String str){
        return Integer.valueOf(str.substring(str.indexOf(" ") + 1));
    }
    public void drawOnPanel(JPanel panel, Point p){
        JLabel categoryLabel = new JLabel(category + ":" + subcategory);
        categoryLabel.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        categoryLabel.setSize(600, 20);
        categoryLabel.setLocation(p.x+10, p.y+120);
        JLabel numberLabel = new JLabel("#" + NO);
        numberLabel.setFont(new Font("TimesRoman", Font.PLAIN, 12));
        numberLabel.setSize(600, 20);
        numberLabel.setLocation(p.x+10, p.y+130);
        JLabel imageLabel = new JLabel(resizeImage(image));
        imageLabel.setSize(120, 120);
        imageLabel.setLocation(p.x, p.y);
        panel.add(categoryLabel);
        panel.add(numberLabel);
        panel.add(imageLabel);
    }
    public String toString(){
        return "Category: " + category + "\nSubcategory: " + subcategory + "\nNO: " + NO + "\n";
    }
    private ImageIcon resizeImage(Image image){
        return new ImageIcon(image.getScaledInstance(120, 120, Image.SCALE_DEFAULT));
    }
}
