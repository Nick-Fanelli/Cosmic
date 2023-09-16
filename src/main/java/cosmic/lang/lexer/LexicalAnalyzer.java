package cosmic.lang.lexer;

import java.sql.Array;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {

    private final HashMap<String, TokenType> tokenTypeMap = new HashMap<>();

    // Assign all token types
    public LexicalAnalyzer() {

        // Keywords
        tokenTypeMap.put("var", TokenType.KEYWORD);

        tokenTypeMap.put(";", TokenType.SEMI_COLON);

        // Operators
        tokenTypeMap.put("=", TokenType.OPERATOR_EQ);
        tokenTypeMap.put("+", TokenType.OPERATOR_PLUS);
        tokenTypeMap.put("-", TokenType.OPERATOR_MINUS);

    }

    public ArrayList<Token> analyzeCode(HashMap<Integer, String> lines) {

        ArrayList<Token> tokens = new ArrayList<>();

        lines.forEach((nLine, line) -> {
            HashMap<String, TokenType> lexLine = AnalyseLine(line.strip());
            lexLine.forEach((value, token) -> tokens.add(new Token(token, value, nLine)));
        });

        return tokens;
    }

    private LinkedHashMap<String, TokenType> AnalyseLine(String line) {

        LinkedHashMap<String, TokenType> lineTokens = new LinkedHashMap<>();
        Automation automation = new Automation();

        ArrayList<String> parts = new ArrayList<>();
        Matcher matcher = Pattern.compile("([^\"]\\S*|\".+?\")\\s*").matcher(line);

        while(matcher.find())
            parts.add(matcher.group(1));

        for(String str : parts) {

            TokenType type;

            if(tokenTypeMap.containsKey(str.toLowerCase())) {
                type = tokenTypeMap.get(str.toLowerCase());
            } else {
                type = automation.evaluate(str);
            }

            lineTokens.put(str, type);
        }

        return lineTokens;

    }

}
