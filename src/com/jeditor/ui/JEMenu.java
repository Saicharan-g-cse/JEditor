package com.jeditor.ui;

import com.jeditor.JEditor;
import com.jeditor.filetabs.FileTab;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;
import java.io.File;

public class JEMenu extends JMenuBar {
    public JEMenu(JFrame jFrame) {
        JMenu fileMenu = new JMenu("File");
        JMenuItem jFileMenuItem1 = new JMenuItem("Open file");
        JMenuItem jFileMenuItem2 = new JMenuItem("Exit");

        jFileMenuItem1.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser(System.getProperty("user.dir"));
            int result = jFileChooser.showOpenDialog(jFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                JTabbedPane jTabbedPane = JEditor.getTabbedPane();
                jTabbedPane.addTab(file.getName(), FileTab.addFileTab(file));
                jTabbedPane.setSelectedIndex(jTabbedPane.getTabCount() - 1);
            }
        });
        jFileMenuItem2.addActionListener(e -> {
            jFrame.setVisible(false);
            jFrame.dispose();
            System.exit(0);
        });

        fileMenu.add(jFileMenuItem1);
        fileMenu.add(jFileMenuItem2);
        add(fileMenu);
    }
}
