import java.util.HashSet;
import java.util.Set;

public class Node {

    public String word;
    public Set<Node> children;
    
    /**
     * Create a ParseTree with a list of keywords. For example:
     * 
     * {"SELECT", "GROUP BY"}
     * 
     * @param keywords
     *            a list of keywords. Keywords may have spaces.
     */
    public static Node createTree(String[] keywords) {
        Node root = new Node();

        // Initialize the children set with a capacity of the size of the
        // keyword array.
        root.children = new HashSet<>(keywords.length);

        String[][] keywordTable = new String[keywords.length][];

        for (int i = 0; i < keywords.length; i++) {
            keywordTable[i] = keywords[i].split(" ");

            
            Node n = root;
            for (int j = 0; j < keywordTable[i].length; j++) {

                System.out.println(
                        "(" + i + "," + j + ") = " + keywordTable[i][j]);

                if (n == null) {
                    System.out.println("node is null");
                    continue;
                }
                n.addChild(new Node(keywordTable[i][j]));
            }
        }

        return root;
    }

    public Node() {
        children = new HashSet<>();
    }

    public Node(String word) {
        children = new HashSet<>();
        this.word = word;
    }

    public void addChild(Node n) {
        this.children.add(n);
    }

    public Node getChild(String word) {
        // A leaf has no children
        if (this.isLeaf()) {
            return null;
        }

        // Search children for matching node
        for (Node n : this.children) {

            if (n == null || n.word == null) {
                return null;
            }

            if (n.word.equalsIgnoreCase(word)) {
                return n;
            }
        }

        return null;
    }

    public boolean isLeaf() {
        return this.children == null || this.children.isEmpty();
    }

}
