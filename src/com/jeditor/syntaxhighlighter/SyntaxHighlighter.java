package com.jeditor.syntaxhighlighter;

import com.jeditor.languages.LangJava;

import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyleConstants;
import javax.swing.text.Style;
import java.awt.Color;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxHighlighter {
    private final StyledDocument styledDocument;
    private final JTextPane jTextPane;
    private final Pattern keywordPattern;
    private final Pattern loopsKeywordPattern;
    private final Pattern importKeywordPattern;
    private final Pattern commentPattern;
    private final Pattern stringPattern;

    public SyntaxHighlighter(JTextPane jTextPane, String lang) {
        this.jTextPane = jTextPane;

        styledDocument = jTextPane.getStyledDocument();
        Style documentStyle = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);

        HashMap<String, LangJava.KeyWords> keyWords = LangJava.getKeyWords();

        Style primaryKeyWordsStyle = styledDocument.addStyle("primaryKeyWords", documentStyle);
        StyleConstants.setForeground(primaryKeyWordsStyle, keyWords.get("primaryKeyWords").color);
        StyleConstants.setBold(primaryKeyWordsStyle, true);

        Style loopKeywordStyle = styledDocument.addStyle("Loops", documentStyle);
        StyleConstants.setForeground(loopKeywordStyle, keyWords.get("secondaryKeyWords").color);
        StyleConstants.setBold(loopKeywordStyle, true);

        Style importStyle = styledDocument.addStyle("Imports", documentStyle);
        StyleConstants.setForeground(importStyle, keyWords.get("specialKeyWords").color);

        Style commentStyle = styledDocument.addStyle("Comment", documentStyle);
        StyleConstants.setForeground(commentStyle, Color.GRAY);

        Style stringLiteralStyle = styledDocument.addStyle("StringLiteral", documentStyle);
        StyleConstants.setForeground(stringLiteralStyle, new Color(255,105,180));

        keywordPattern = Pattern.compile("\\b(" + String.join("|", keyWords.get("primaryKeyWords").words) + ")\\b");
        loopsKeywordPattern = Pattern.compile("\\b(" + String.join("|", keyWords.get("secondaryKeyWords").words) + ")\\b");
        importKeywordPattern = Pattern.compile("\\b(" + String.join("|", keyWords.get("specialKeyWords").words) + ")\\b");
        commentPattern = Pattern.compile("//.*|/\\*(.|\\n)*?\\*/");
        stringPattern = Pattern.compile("\".*?\"");

        addSyntaxHighlighting();
    }

    private void highlightSyntax() {
        String text = textFromDocument(styledDocument);
        if (text.isEmpty())
            return;
        parseHighlightText(keywordPattern.matcher(text), styledDocument, styledDocument.getStyle("primaryKeyWords"));
        parseHighlightText(loopsKeywordPattern.matcher(text), styledDocument, styledDocument.getStyle("Loops"));
        parseHighlightText(importKeywordPattern.matcher(text), styledDocument, styledDocument.getStyle("Imports"));
        parseHighlightText(commentPattern.matcher(text), styledDocument, styledDocument.getStyle("Comment"));
        parseHighlightText(stringPattern.matcher(text), styledDocument, styledDocument.getStyle("StringLiteral"));
    }

    private static String textFromDocument(StyledDocument styledDocument) {
        try {
            return styledDocument.getText(0, styledDocument.getLength());
        } catch (BadLocationException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }

    private static void parseHighlightText(Matcher matcher, StyledDocument styledDocument, Style style) {
        while (matcher.find()) {
            int length = matcher.end() - matcher.start();
            styledDocument.setCharacterAttributes(matcher.start(), length, style, false);
        }
    }

    private static void parseRemoveHighlightText(StyledDocument doc) {
        doc.setCharacterAttributes(0, doc.getLength(), doc.getStyle(StyleContext.DEFAULT_STYLE), true);
    }

    private void addSyntaxHighlighting() {
        jTextPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public synchronized void insertUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> highlightSyntax());
            }

            @Override
            public synchronized void removeUpdate(DocumentEvent e) {
                SwingUtilities.invokeLater(() -> {
                    parseRemoveHighlightText(styledDocument);
                    highlightSyntax();
                });
            }

            @Override
            public void changedUpdate(DocumentEvent e) {

            }
        });
    }
}
