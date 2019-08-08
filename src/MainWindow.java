import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class MainWindow extends JFrame {

    public static void main(String args[]) {
        MainWindow main = new MainWindow();
        main.setVisible(true);
    }

    private static final long serialVersionUID = 1L;

    JTextPane editor;

    private MainWindow() {

        // Initialize the on-screen components
        JPanel mainPanel = new JPanel(new BorderLayout());

        editor = new JTextPane(new HighlightDocument());
        editor.setText("select * from my_schema.tablename\r\n" +
                "where row is not null\r\n" +
                "and trim(name) like 'Jim%'\r\n" +
                "order by last_name");

        editor.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));

        // Add components to the main panel
        // Layout Docs:
        // https://docs.oracle.com/javase/tutorial/uiswing/layout/border.html
        mainPanel.add(editor, BorderLayout.CENTER);

        // Add main panel to frame
        setContentPane(mainPanel);

        setTitle("Syntax Highlight Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1000, 500);
    }

}
