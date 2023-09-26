package cosmic.lang.lexer;

public enum TokenType {

    IDENTIFIER,

    KEYWORD_IF, KEYWORD_TYPE_PRIMITIVE,

    OPERATOR_EQ, OPERATOR_DOUBLE_EQ, OPERATOR_PLUS, OPERATOR_MINUS, OPERATOR_MUL, OPERATOR_DIV,
    OPERATOR_OPEN_PAREN, OPERATOR_CLOSE_PAREN, OPERATOR_OPEN_CURLY_BRACES, OPERATOR_CLOSE_CURLY_BRACES,

    INTEGER, STRING, FLOAT,

    SEMI_COLON, INVALID;

    public boolean IsOfType(TokenType... tokenTypes) {

        for (TokenType tokenType : tokenTypes) {
            if (tokenType == this)
                return true;
        }

        return false;

    }

}
