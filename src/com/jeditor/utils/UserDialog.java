package com.jeditor.utils;

import com.jeditor.JEditor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class UserDialog {
    private static final JFrame jFrame;

    static {
        jFrame = JEditor.getjFrame();
    }

    public static void showError(String msg) {
        JOptionPane.showMessageDialog(jFrame, msg);
    }
}
