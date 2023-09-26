package cosmic.lang.parser.nodes;

import cosmic.lang.lexer.Token;

public class BinaryOperationNode extends Node {

    public final Node leftNode;
    public final Token operationToken;
    public final Node rightNode;

    public BinaryOperationNode(Node leftNode, Token operationToken, Node rightNode) {
        this.leftNode = leftNode;
        this.operationToken = operationToken;
        this.rightNode = rightNode;
    }

    @Override
    public String toString() {
        return "(" + leftNode.toString() + ", " + operationToken.toString() + ", " + rightNode.toString() + ")";
    }
}
