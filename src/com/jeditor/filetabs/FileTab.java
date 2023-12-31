package com.jeditor.filetabs;

import com.jeditor.syntaxhighlighter.SyntaxHighlighter;
import com.jeditor.utils.UserDialog;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import java.awt.Font;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;

public class FileTab extends JTextPane {
    private static final LinkedList<FileTab> currentFileTabs;
    private final File file;
    private JScrollPane jScrollPane = null;
    private String fileText;
    private SyntaxHighlighter syntaxHighlighter;

    static {
        currentFileTabs = new LinkedList<>();
    }

    public FileTab(File file) {
        this.file = file;
        if (!readFileContent())
            return;
        setFont(new Font("Monospaced", Font.PLAIN, 14));
        jScrollPane = new JScrollPane(this);
        syntaxHighlighter = new SyntaxHighlighter(this, "java");
    }

    private boolean readFileContent() {
        Path filePath = Path.of(file.toURI());
        try {
            fileText = Files.readString(filePath);
        }
        catch (Exception e) {
            UserDialog.showError(e.getMessage());
            return false;
        }
        return true;
    }

    public static JScrollPane addFileTab(File file) {
        FileTab tab = new FileTab(file);
        tab.setText(tab.fileText);
        currentFileTabs.add(tab);
        return tab.jScrollPane;
    }

    public static FileTab getFileTab(int index) {
        return index < currentFileTabs.size() ? currentFileTabs.get(index) : null;
    }

    public SyntaxHighlighter getSyntaxHighlighter() {
        return syntaxHighlighter;
    }
}
