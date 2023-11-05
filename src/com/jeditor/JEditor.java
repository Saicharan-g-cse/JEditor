package com.jeditor;

import com.jeditor.ui.JEMenu;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public final class JEditor extends JFrame implements Runnable {
    private static JTabbedPane jTabbedPane;
    private static JFrame jFrame;

    static {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }
    }

    public JEditor() {
        jFrame = this;
        jTabbedPane = new JTabbedPane();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new JEditor());
    }

    private void initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(800, 600);
    }

    @Override
    public void run() {
        initFrame();
        setJMenuBar(new JEMenu(this));
        getContentPane().add(jTabbedPane);
        setVisible(true);
    }

    public static JFrame getjFrame() {
        return jFrame;
    }

    public static JTabbedPane getTabbedPane() {
        return jTabbedPane;
    }
}
