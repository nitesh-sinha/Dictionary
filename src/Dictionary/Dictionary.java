package Dictionary;

import java.util.ArrayList;
import java.util.List;
import Node.Node;
import Exception.InvalidInputException;
import Exception.WordNotFoundException;

public class Dictionary {
    private Node root;
    private int alphabetSize;

    public Dictionary(int size) {
        alphabetSize = size;
        root = new Node(alphabetSize);
    }

    // Inserts a word and its definition into the dictionary.
    public void insert(String word, String definition)
            throws InvalidInputException {
        int index;
        Node iter = root;
        util u = new util();

        if(word.isEmpty() || !u.isLower(word))
            throw new InvalidInputException("Input is either empty or does not have only lowercase letters");

        for(int i=0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';
            if(iter.next[index]==null) {
                // On reaching a null link, extend it
                // by creating new nodes if there are more
                // chars to iterate over
                Node temp = new Node(alphabetSize);
                iter.next[index] = temp;
                iter = temp;
            } else {
                iter = iter.next[index];
            }
        }
        // One node beyond the last character in input word
        // has isEnd set to true and stores the definition
        iter.isEnd = true;
        iter.definition = definition;
        System.out.printf("Inserted \"%s->%s\" into the dictionary \n", word, definition);
    }

    // Returns the definition of the word(if it exists)
    public String getDefinition(String word) throws Exception {
        Node isPresent = searchString(word);
        if(isPresent != null && isPresent.isEnd) {
            // Ensures that prefixes in word are not incorrectly
            // identified as being present in dictionary.
            // Eg: If only word in dictionary = "batman".
            // Search for "bat" shouldnt say present
            return isPresent.definition;
        } else
            throw new WordNotFoundException(word + " not found in dictionary");
    }

    // Returns if there is any word in the dictionary
    // that starts with the given prefix.
    public List<String> startsWith(String prefix) {
        List<String> result = new ArrayList<String>();
        startsWithHelper(prefix, result);
        return result;
    }

    public void delete(String word) throws Exception {
        if(word == null)
            return;

        Node node = searchString(word);
        if(node == null)
            throw new WordNotFoundException(word + " to be deleted not found in dictionary");

        // Remove definition and set isEnd to false thereby removing the word
        // TODO: This might leave a lot of garbage chars before this last node
        // TODO: Enhance logic to eliminate those chars too
        node.isEnd = false;
        node.definition = "";
        System.out.printf("Deleted %s from the dictionary \n", word);
    }

    // Recursive implementation to find all words which start with the prefix string
    private void startsWithHelper(String prefix, List<String> result) {
        Node node = searchString(prefix);

        if(node == null)
            return;

        if(node.isEnd)
            result.add(prefix); // prefix exists in the dictionary

        // Recursively iterate over node's next array
        for(int i=0; i<alphabetSize; i++) {
            if(node.next[i]!=null) {
                String newStr = prefix + (char)('a' + i);
                startsWithHelper(newStr, result);
            }
        }
    }

    // Searches for existence of input in the dictionary
    // and returns the node pointed to by the last character in input(if it exists)
    // or null(if input doesn't exist in dictionary)
    private Node searchString(String input) {
        int index;
        Node iter = root;

        if(input.length() == 0)
            return null;

        for(int i=0; i<input.length(); i++) {
            index = input.charAt(i) - 'a';
            if(iter.next[index] != null)
                iter = iter.next[index];
            else
                return null;
        }

        return iter;
    }
}