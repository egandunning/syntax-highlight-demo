import java.awt.Color;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class HighlightDocument extends DefaultStyledDocument {

    private static final long serialVersionUID = 1L;
    private Node pTree;
    private SimpleAttributeSet boldBlue;

    public HighlightDocument() {
        super();

        // Set up parse tree
        String[] keywords = { "ADD",
                "ADD CONSTRAINT",
                "ALTER",
                "ALTER COLUMN",
                "ALTER TABLE",
                "ALL",
                "AND",
                "ANY",
                "AS",
                "ASC",
                "BACKUP DATABASE",
                "BETWEEN",
                "CASE",
                "CHECK",
                "COLUMN",
                "CONSTRAINT",
                "CREATE",
                "CREATE DATABASE",
                "CREATE INDEX",
                "CREATE OR REPLACE VIEW",
                "CREATE TABLE",
                "CREATE PROCEDURE",
                "CREATE UNIQUE INDEX",
                "CREATE VIEW",
                "DATABASE",
                "DEFAULT",
                "DELETE",
                "DESC",
                "DISTINCT",
                "DROP",
                "DROP COLUMN",
                "DROP CONSTRAINT",
                "DROP DATABASE",
                "DROP DEFAULT",
                "DROP INDEX",
                "DROP TABLE",
                "DROP VIEW",
                "EXEC",
                "EXISTS",
                "FOREIGN KEY",
                "FROM",
                "FULL OUTER JOIN",
                "GROUP BY",
                "HAVING",
                "IN",
                "INDEX",
                "INNER JOIN",
                "INSERT INTO",
                "INSERT INTO SELECT",
                "IS NULL",
                "IS NOT NULL",
                "JOIN",
                "LEFT JOIN",
                "LIKE",
                "LIMIT",
                "NOT",
                "NOT NULL",
                "OR",
                "ORDER BY",
                "OUTER JOIN",
                "PRIMARY KEY",
                "PROCEDURE",
                "RIGHT JOIN",
                "ROWNUM",
                "SELECT",
                "SELECT DISTINCT",
                "SELECT INTO",
                "SELECT TOP",
                "SET",
                "TABLE",
                "TOP",
                "TRUNCATE TABLE",
                "UNION",
                "UNION ALL",
                "UNIQUE",
                "UPDATE",
                "VALUES",
                "VIEW",
                "WHERE" };

        pTree = Node.createTree(keywords);

        // AttributeSet used for syntax highlighting
        boldBlue = new SimpleAttributeSet();
        StyleConstants.setForeground(boldBlue, Color.blue);
        StyleConstants.setBold(boldBlue, true);

    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
        super.insertString(offs, str, a);
        
        highlightWords();
    }
    
    private void highlightWords() {
        String token = "";
        int beginIdx = -1;
        Node pTreePos = pTree;

        
        for (int i = 0; i < this.getLength(); i++) {

            // The current character
            String ch;
            
            try {
                ch = this.getText(i, 1);
            } catch (BadLocationException e1) {
                e1.printStackTrace();
                return;
            }
            
            // Token ends on whitespace
            if (ch.matches("\\s")) {

                System.out.println("token end:" + token + " " + beginIdx
                        + "," + i);
                pTreePos = pTreePos.getChild(token);

                if (pTreePos == null) {
                    pTreePos = pTree;
                    beginIdx = -1;
                }

                if (pTreePos.isLeaf()) {

                    this.setCharacterAttributes(
                            beginIdx, i - beginIdx, boldBlue, false);
                    
                    pTreePos = pTree;
                    beginIdx = -1;
                }

                token = "";

            } else {
                if (beginIdx == -1) {
                    beginIdx = i;
                }
                token += ch;

            }
            
        }
    }
}
