package cosmic.lang.parser.derivers;

import cosmic.lang.lexer.Token;
import cosmic.lang.lexer.TokenType;
import cosmic.lang.parser.Parser;
import cosmic.lang.parser.nodes.BinaryOperationNode;
import cosmic.lang.parser.nodes.Node;
import cosmic.lang.parser.nodes.NumberNode;

public class NumericalExpressionContextDeriver extends ContextDeriver {

    private NumberNode Factor() {

        if(super.currentToken.type.IsOfType(TokenType.INTEGER, TokenType.FLOAT, TokenType.STRING)) {
            NumberNode node = new NumberNode(super.currentToken);
            this.Advance();
            return node;
        }

        return null;
    }

    private Node Term() {
        Node left = this.Factor();

        while(super.currentToken.type.IsOfType(TokenType.OPERATOR_MUL, TokenType.OPERATOR_DIV)) {
            Token operatorToken = super.currentToken;
            this.Advance();
            NumberNode right = this.Factor();

            left = new BinaryOperationNode(left, operatorToken, right);
        }

        return left;
    }

    private Node Expression() {
        Node left = this.Term();

        while(super.currentToken.type.IsOfType(TokenType.OPERATOR_PLUS, TokenType.OPERATOR_MINUS)) {
            Token operatorToken = super.currentToken;
            this.Advance();
            Node right = this.Term();

            System.out.println(right);

            left = new BinaryOperationNode(left, operatorToken, right);
        }

        return left;
    }

    @Override
    protected Node InternalDeriveContext(Token[] tokens) {

        return Expression();

    }

}
