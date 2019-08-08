# Syntax Highlighting with Java Swing

A simple program to showcase syntax highlighting using the SytledDocument API.

## Keyword Highlighting

Uses a tree to parse text in a JTextPane and identify which words to highlight.

Change word color/style by overriding the insertString() method in a subclass of DefaultStyledDocument.
