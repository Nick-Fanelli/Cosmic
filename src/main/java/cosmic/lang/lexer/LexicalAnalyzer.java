package cosmic.lang.lexer;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {

    private static final HashMap<String, TokenType> TOKEN_TYPE_MAP = new HashMap<>();

    private static Pattern tokenRegex;

    private static void AssignTokenMap() {

        // Identifier
        TOKEN_TYPE_MAP.put(";", TokenType.SEMI_COLON);
        TOKEN_TYPE_MAP.put("if", TokenType.IDENTIFIER_IF);

        // Operators
        TOKEN_TYPE_MAP.put("=", TokenType.OPERATOR_EQ);
        TOKEN_TYPE_MAP.put("==", TokenType.OPERATOR_DOUBLE_EQ);
        TOKEN_TYPE_MAP.put("+", TokenType.OPERATOR_PLUS);
        TOKEN_TYPE_MAP.put("-", TokenType.OPERATOR_MINUS);
        TOKEN_TYPE_MAP.put("*", TokenType.OPERATOR_MUL);
        TOKEN_TYPE_MAP.put("/", TokenType.OPERATOR_DIV);

        TOKEN_TYPE_MAP.put("(", TokenType.OPERATOR_OPEN_PAREN);
        TOKEN_TYPE_MAP.put(")", TokenType.OPERATOR_CLOSE_PAREN);
        TOKEN_TYPE_MAP.put("{", TokenType.OPERATOR_OPEN_CURLY_BRACES);
        TOKEN_TYPE_MAP.put("}", TokenType.OPERATOR_CLOSE_CURLY_BRACES);
    }

    private static void BuildRegex() {
        LexicalAnalyzer.AssignTokenMap();

        StringBuilder regex = new StringBuilder("[0-9]+|");
        String needsEscape = "(\\+)|(\\.)|(\\()|(\\))|(\\{)|(\\*)";

        for(String identifier : TOKEN_TYPE_MAP.keySet()) {
            if(identifier.matches(needsEscape))
                identifier = "\\" + identifier;

            regex.append("(").append(identifier).append(")|");
        }

        regex.setLength(regex.length() - 1);
        regex.append("|[a-zA-Z_][a-zA-Z0-9_]*"); // Identifier Declaration

        LexicalAnalyzer.tokenRegex = Pattern.compile(regex.toString());
    }

    public ArrayList<Token> AnalyseCode(HashMap<Integer, String> lines) {

        if(tokenRegex == null)
            BuildRegex();

        ArrayList<Token> tokens = new ArrayList<>();

        lines.forEach((nLine, line) -> {
            ArrayList<Token> lineTokens = AnalyseLine(line.strip(), nLine);
            tokens.addAll(lineTokens);
        });

        return tokens;
    }

    private ArrayList<Token> AnalyseLine(String line, int lineNumber) {

        ArrayList<Token> lineTokens = new ArrayList<>();
        Automation automation = new Automation();

        ArrayList<String> parts = new ArrayList<>();
        Matcher matcher = LexicalAnalyzer.tokenRegex.matcher(line);

        while(matcher.find())
            parts.add(matcher.group());

        for(String str : parts) {

            TokenType type;

            if(TOKEN_TYPE_MAP.containsKey(str.toLowerCase())) {
                type = TOKEN_TYPE_MAP.get(str.toLowerCase());
            } else {
                type = automation.Evaluate(str);
            }

            lineTokens.add(new Token(type, str, lineNumber));
        }

        return lineTokens;

    }

}
