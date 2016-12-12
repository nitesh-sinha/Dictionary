import java.util.List;
import java.util.ArrayList;
import Dictionary.Dictionary;


public class DictClient {

    public static void main(String[] args) {
        testDict();
    }

    static void testDict() {
        Dictionary dict = new Dictionary(26);
        List<String> words = new ArrayList<String>();

        // Insert words in dictionary
        try {
            dict.insert("bat", "solid surface to hit the ball with");
            dict.insert("cat", "carnivorous mammal");
            dict.insert("battle", "war");
            dict.insert("blast", "loud sound");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Get definition of a non-existant word
        try {
            System.out.println(dict.getDefinition("apple"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Get definition of a word which exists in dictionary
        try {
            String word = "battle";
            String def = dict.getDefinition("battle");
            System.out.printf("Word %s found. Its definition is %s \n", word, def);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Delete a word from dictionary
        try {
            dict.delete("bat");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Get definition of a word which does not exist in dictionary
        try {
            System.out.println(dict.getDefinition("bat"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // delete word which does not exist in dictionary
        try {
            dict.delete("blah");
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        // Search for words with prefix "b"
        words = dict.startsWith("b");
        System.out.println("All words in dictionary that start with prefix b are:");
        for (String w : words) {
            System.out.println(w);
        }
    }
}

