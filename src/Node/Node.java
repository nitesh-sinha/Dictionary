package Node;

/**
 * Node object stores definition of the words in dictionary.
 * It also forms the smallest logical unit of the Trie data structure used to
 * implement this dictionary. The index of array "next" specifies the character
 * in the word with index=0 to 25 representing a to z with the assumption that
 * this is an English dictionary implementation with lowercase characters only.
 */
public class Node {
    public String definition; // to store the definition of the word
    public boolean isEnd; // to check for overlapping strings(eg: door vs do)
    public Node[] next; // to point to node. Index number implicitly specifies char in word

    public Node(int size) {
        next = new Node[size];
        isEnd = false;
        definition = "";
    }
}