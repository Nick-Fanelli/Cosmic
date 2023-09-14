package cosmic.lang.lexer;

public class Token {

    private TokenType type;
    private String value;
    private int line;

    public Token(TokenType type, String value, int line) {

        this.type = type;
        this.value = value;
        this.line = line;

    }

    public TokenType GetTokenType() { return this.type; }
    public String GetValue() { return this.value; }
    public int GetLineNumber() { return this.line; }

    public void SetTokenType(TokenType type) { this.type = type; }
    public void SetValue(String value) { this.value = value; }
    public void SetLineNumber(int line) { this.line = line; }

}
