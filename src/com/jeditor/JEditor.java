package com.jeditor;

import com.jeditor.filetabs.FileTab;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public final class JEditor extends JFrame implements Runnable {
    private final JTabbedPane jTabbedPane;
    private static JFrame jFrame = null;

    public JEditor() {
        jFrame = this;
        jTabbedPane = new JTabbedPane();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new JEditor());
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
    }

    public static JFrame getjFrame() {
        return jFrame;
    }

    @Override
    public void run() {
        initFrame();

        jTabbedPane.addTab("Test.java", FileTab.addFileTab("C:\\Users\\saicharang\\Desktop\\Test.java"));
        jTabbedPane.addTab("Test.java", FileTab.addFileTab("C:\\Users\\saicharang\\Desktop\\Test.java"));

        getContentPane().add(jTabbedPane);
        setVisible(true);
    }
}
