package cosmic.lang;

import cosmic.lang.entry.ContentAdapter;
import cosmic.lang.lexer.Lexer;
import cosmic.lang.lexer.Token;
import cosmic.lang.parser.Parser;
import cosmic.lang.parser.nodes.Node;

public class EntryPoint {

    public static void Interpret(ContentAdapter contentAdapter) {

        String fileContents = contentAdapter.GetFileContents();

        Lexer lexer = new Lexer(fileContents);
        Token[] tokens = lexer.ExtractTokens();

        Parser parser = new Parser(tokens);
        Node ast = parser.GenerateAbstractSyntaxTree();

        System.out.println(ast);

    }

}
