package com.jeditor;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicSyntaxHighlighter {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static Style keywordStyle;

    public static void createAndShowGUI() {
        JFrame frame = new JFrame("Dynamic Syntax Highlighter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        JTextPane textPane = new JTextPane();
        textPane.setFont(new Font("Monospaced", Font.PLAIN, 12));

        JScrollPane scrollPane = new JScrollPane(textPane);
        frame.getContentPane().add(scrollPane);

        frame.setVisible(true);

        StyledDocument doc = textPane.getStyledDocument();
        addStylesToDocument(doc);

        textPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> highlightSyntax(doc));
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> removeHighlighting(doc));
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // Not used for plain text components
            }
        });
    }

    private static void addStylesToDocument(StyledDocument doc) {
        Style def = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

        keywordStyle = doc.addStyle("Keyword", def);
        StyleConstants.setForeground(keywordStyle, Color.BLUE);
    }

    private static void highlightSyntax(StyledDocument doc) {
        String text = textFromDocument(doc);
        Pattern keywordPattern = Pattern.compile("\\b(public|class|static|void|main|String|System|out|println)\\b");
        Matcher matcher = keywordPattern.matcher(text);

        while (matcher.find()) {
            doc.setCharacterAttributes(matcher.start(), matcher.end() - matcher.start(), keywordStyle, false);
        }
    }

    private static void removeHighlighting(StyledDocument doc) {
        doc.setCharacterAttributes(0, doc.getLength(), doc.getStyle(StyleContext.DEFAULT_STYLE), true);
    }

    private static String textFromDocument(StyledDocument doc) {
        try {
            return doc.getText(0, doc.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
        return "";
    }
}
