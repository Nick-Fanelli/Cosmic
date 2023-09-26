package cosmic.lang.parser;

import cosmic.lang.lexer.Token;
import cosmic.lang.lexer.TokenType;
import cosmic.lang.parser.derivers.ContextDeriver;
import cosmic.lang.parser.derivers.NumericalExpressionContextDeriver;
import cosmic.lang.parser.nodes.BinaryOperationNode;
import cosmic.lang.parser.nodes.Node;
import cosmic.lang.parser.nodes.NumberNode;

import javax.naming.Context;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    private final Token[] tokens;
    private int tokenIndex = 0;

    private Token currentToken;

    private final ContextDeriver[] contextDerivers = new ContextDeriver[] {
            new NumericalExpressionContextDeriver()
    };

    public Parser(Token[] tokens) {

        this.tokens = tokens;
        this.currentToken = this.tokens[0];

    }

    public void Advance(int n) {
        this.tokenIndex += n;

        if(this.tokenIndex >= this.tokens.length)
            this.tokenIndex = this.tokens.length - 1;

        this.currentToken = this.tokens[this.tokenIndex];
    }

    public void Retreat() {
        this.tokenIndex--;

        if(this.tokenIndex < 0)
            this.tokenIndex = 0;

        this.currentToken = this.tokens[this.tokenIndex];
    }

    public void GenerateAbstractSyntaxTree() {

        ArrayList<Node> nodes = new ArrayList<>();

        for(ContextDeriver contextDeriver : contextDerivers) {

            Token[] subArray = Arrays.copyOfRange(this.tokens, this.tokenIndex, this.tokens.length - 1);

            Node node = contextDeriver.DeriveContext(subArray);

            if(node != null) {
                nodes.add(node);
            }

            this.Advance(contextDeriver.GetAdvanceNumber());

        }

        for(Node node : nodes) {
            System.out.println(node);
        }

    }

    public Token GetCurrentToken() { return this.currentToken; }

}
