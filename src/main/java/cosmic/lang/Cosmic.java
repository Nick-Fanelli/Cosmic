package cosmic.lang;

import cosmic.lang.entry.ContentAdapter;
import cosmic.lang.lexer.Lexer;

public class Cosmic {

    public static void Interpret(ContentAdapter contentAdapter) {

        String fileContents = contentAdapter.GetFileContents();

        Lexer lexer = new Lexer(fileContents);
        lexer.ExtractTokens();

    }

}
