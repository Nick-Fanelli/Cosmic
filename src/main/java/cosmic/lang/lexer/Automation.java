package cosmic.lang.lexer;

import java.util.HashMap;

public class Automation {

    private final HashMap<LexicalState, TokenType> finalStates = new HashMap<>();

    public Automation() {
        finalStates.put(LexicalState.Q1, TokenType.IDENTIFIER);
        finalStates.put(LexicalState.Q3, TokenType.STRING);
        finalStates.put(LexicalState.Q4, TokenType.INTEGER);
        finalStates.put(LexicalState.Q7, TokenType.FLOAT);
    }

    private LexicalState ExecuteTransition(LexicalState currentState, char entry) {
        switch (currentState) {
            case INITIAL -> {
                if ((entry >= 'A' && entry <= 'Z') || (entry >= 'a' && entry <= 'z'))
                    return LexicalState.Q1;
                else if (entry == '"')
                    return LexicalState.Q2;
                else if (entry >= '0' && entry <= '9')
                    return LexicalState.Q4;
                else if (entry == '+' || entry == '-')
                    return LexicalState.Q5;
                else
                    return LexicalState.INVALIDATION_STATE;
            }
            case Q1 -> {
                return (entry >= 'A' && entry <= 'Z')
                        || (entry >= 'a' && entry <= 'z')
                        || (entry >= '0' && entry <= '9')
                        ? LexicalState.Q1 : LexicalState.INVALIDATION_STATE;
            }
            case Q2 -> {
                return (entry == '"') ? LexicalState.Q3 : LexicalState.Q2;
            }
            case Q4 -> {
                if (entry == '.')
                    return LexicalState.Q6;
                else if (entry >= '0' && entry <= '9')
                    return LexicalState.Q4;
                else
                    return LexicalState.INVALIDATION_STATE;
            }
            case Q5 -> {
                return (entry >= '0' && entry <= '9') ? LexicalState.Q4 : LexicalState.INVALIDATION_STATE;
            }
            case Q6, Q7 -> {
                return (entry >= '0' && entry <= '9') ? LexicalState.Q7 : LexicalState.INVALIDATION_STATE;
            }
            default -> {
                return LexicalState.INVALIDATION_STATE;
            }
        }
    }

    public TokenType Evaluate(String str) {

        LexicalState state = LexicalState.INITIAL;

        for(char c : str.toCharArray()){
            state = ExecuteTransition(state, c);
        }

        return finalStates.getOrDefault(state, TokenType.INVALID);

    }
}