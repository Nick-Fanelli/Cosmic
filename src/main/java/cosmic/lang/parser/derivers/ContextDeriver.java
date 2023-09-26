package cosmic.lang.parser.derivers;

import cosmic.lang.lexer.Token;
import cosmic.lang.parser.Parser;
import cosmic.lang.parser.nodes.Node;

public abstract class ContextDeriver {

    private Token[] tokens;

    protected Token currentToken;
    private int tokenIndex = 0;

    protected void Advance() {
        this.tokenIndex++;

        if(this.tokenIndex >= this.tokens.length)
            this.tokenIndex = this.tokens.length - 1;

        this.currentToken = this.tokens[this.tokenIndex];
    }

    protected void Retreat() {
        this.tokenIndex--;

        if(this.tokenIndex < 0)
            this.tokenIndex = 0;

        this.currentToken = this.tokens[this.tokenIndex];
    }

    public Node DeriveContext(Token[] tokens) {
        this.tokenIndex = 0;
        this.tokens = tokens;

        this.currentToken = this.tokens[this.tokenIndex];
        return InternalDeriveContext(tokens);
    }

    protected abstract Node InternalDeriveContext(Token[] tokens);

    public int GetAdvanceNumber() { return this.tokenIndex; }

}
