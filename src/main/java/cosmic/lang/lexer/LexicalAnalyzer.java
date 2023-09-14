package cosmic.lang.lexer;

import java.util.HashMap;

public class LexicalAnalyzer {

    private final HashMap<String, TokenType> tokenTypeMap = new HashMap<>();

    // Assign all token types
    public LexicalAnalyzer() {

        // Keywords
        tokenTypeMap.put("var", TokenType.KEYWORD);

        // Operators
        tokenTypeMap.put("=", TokenType.OPERATOR_EQ);

    }

}
