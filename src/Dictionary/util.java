package Dictionary;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class util {
    String regex;

    public util() {
        regex = "[a-z]+";
    }

    public boolean isLower(String input) {
        Pattern regexPattern = Pattern.compile(regex);
        Matcher matcher = regexPattern.matcher(input);
        return matcher.matches();
    }

}
