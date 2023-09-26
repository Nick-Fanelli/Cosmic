package cosmic.lang.parser.nodes;

import cosmic.lang.lexer.Token;

public class NumberNode extends Node {

    public final Token token;

    public NumberNode(Token token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return token.toString();
    }
}
