package cosmic.lang.parser;

import cosmic.lang.lexer.Token;
import cosmic.lang.lexer.TokenType;
import cosmic.lang.parser.nodes.BinaryOperationNode;
import cosmic.lang.parser.nodes.Node;
import cosmic.lang.parser.nodes.NumberNode;

public class Parser {

    private final Token[] tokens;
    private int tokenIndex = 0;

    private Token currentToken;

    public Parser(Token[] tokens) {

        this.tokens = tokens;
        this.currentToken = this.tokens[0];

    }

    private void Advance() {
        this.tokenIndex++;

        if(this.tokenIndex < tokens.length)
            this.currentToken = this.tokens[this.tokenIndex];
    }

    private NumberNode Factor() {

        if(this.currentToken.type.IsOfType(TokenType.INTEGER, TokenType.FLOAT, TokenType.STRING)) {
            NumberNode node = new NumberNode(this.currentToken);
            this.Advance();
            return node;
        }

        return null;
    }

    private Node Term() {
        Node left = this.Factor();

        while(this.currentToken.type.IsOfType(TokenType.OPERATOR_MUL, TokenType.OPERATOR_DIV)) {
            Token operatorToken = this.currentToken;
            this.Advance();
            NumberNode right = this.Factor();

            left = new BinaryOperationNode(left, operatorToken, right);
        }

        return left;
    }

    private Node Expression() {
        Node left = this.Term();

        while(this.currentToken.type.IsOfType(TokenType.OPERATOR_PLUS, TokenType.OPERATOR_MINUS)) {
            Token operatorToken = this.currentToken;
            this.Advance();
            Node right = this.Term();

            left = new BinaryOperationNode(left, operatorToken, right);
        }

        return left;
    }

    public Node GenerateAbstractSyntaxTree() {

        return this.Expression();

    }

}
