import javax.swing.*;
import java.awt.*;

public class LostAndFoundClient {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Online Lost and Found");
        frame.setSize(800, 300);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int) ((screenSize.getWidth() - frame.getWidth()) / 2), (int) ((screenSize.getHeight() - frame.getHeight()) / 2));
        frame.getContentPane().add(new HomeScreen(frame));
        frame.setResizable(false);
        frame.setVisible(true);
    }
}
