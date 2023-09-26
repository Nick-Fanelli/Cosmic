package cosmic.lang.lexer;

import java.util.ArrayList;
import java.util.HashMap;

public class Lexer {

    private final String fileContents;

    public Lexer(String fileContents) {

        this.fileContents = fileContents;

    }

    public void ExtractTokens() {

        String[] lines = fileContents.split("\n");
        HashMap<Integer, String> code = new HashMap<>();

        boolean isMultilineLock = true;

        for(int i = 0; i < lines.length; i++) {
            String line = lines[i];
            if(!line.strip().startsWith("//") && isMultilineLock && !line.strip().startsWith("/*") && !line.isBlank())
                code.put(i + 1, line);
            if(line.strip().startsWith("/*"))
                isMultilineLock = false;
            if(line.strip().endsWith("*/"))
                isMultilineLock = true;
        }

        ArrayList<Token> tokens = new LexicalAnalyzer().AnalyseCode(code);

        for(Token token : tokens) {
            System.out.println(token);
        }

    }

}
