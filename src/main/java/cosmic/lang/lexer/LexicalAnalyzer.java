package cosmic.lang.lexer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LexicalAnalyzer {

    private final HashMap<String, TokenType> tokenTypeMap = new HashMap<>();

    // Assign all token types
    public LexicalAnalyzer() {

        // Keywords
        tokenTypeMap.put("var", TokenType.KEYWORD);

        // Operators
        tokenTypeMap.put("=", TokenType.OPERATOR_EQ);

    }

    public ArrayList<Token> analyzeCode(HashMap<Integer,String> lines) {

        ArrayList<Token> tokens = new ArrayList<>();

        lines.forEach((nLine, line ) ->{
            HashMap<String, TokenType> lexLine = AnalyseLine(line.strip());
            lexLine.forEach((value, token) -> tokens.add(new Token(token, value, nLine)));
        });

        return tokens;
    }

    private HashMap<String, TokenType> AnalyseLine(String line) {

        HashMap<String, TokenType> lineTokens = new HashMap<>();

        Automation automation = new Automation();

        for(String str : line.split(" ")) {
            if(tokenTypeMap.containsKey(str.toLowerCase()))
                lineTokens.put(str, tokenTypeMap.get(str.toLowerCase()));
            else
                lineTokens.put(str, automation.evaluate(str));
        }

        return lineTokens;

    }

}
