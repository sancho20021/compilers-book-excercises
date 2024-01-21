package chap2lexing;

import chap2lexing.minijava.generated.MiniJavaLexer;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class MiniJavaLexerTest {
    public final static String SAMPLE_PROGRAM = """
        class Factorial {
            public static void main(String[] a) {
                System.out.println(new Fac().ComputeFac(10));
            }
        }
        class Fac {
            public int ComputeFac(int num) {
                int num_aux;
                if (num < 1)
                    num_aux = 1;
                else
                    num_aux = num * (this.ComputeFac(num-1));
                return num_aux;
            }
        }
        """;

    @ParameterizedTest
    @ValueSource(strings = {"id", "Id", "id0_", "_", "_id"})
    void testValidIds(String id) {
        var lexer = new MiniJavaLexer(CharStreams.fromString(id));
        var tokens = lexer.getAllTokens();
        Assertions.assertEquals(1, tokens.size());
        Assertions.assertEquals("ID", lexer.getVocabulary().getSymbolicName(tokens.get(0).getType()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"0", ".123"})
    void testInvalidIds(String id) {
        var lexer = new MiniJavaLexer(CharStreams.fromString(id));
        var token = lexer.nextToken();
        Assertions.assertNotEquals(
            "ID",
            lexer.getVocabulary().getSymbolicName(token.getType())
        );
    }

    @ParameterizedTest
    @ValueSource(strings = {"~", ":"})
    void testInvalidChars(String s) {
        Assertions.assertThrows(ParseCancellationException.class, () -> {
            var lexer = new MiniJavaLexer(CharStreams.fromString(s));
            lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
            lexer.nextToken();
        });
    }

    @Test
    void testSampleProgram() {
        var lexer = new MiniJavaLexer(CharStreams.fromString(SAMPLE_PROGRAM));
        lexer.addErrorListener(ThrowingErrorListener.INSTANCE);
        Assertions.assertDoesNotThrow(lexer::getAllTokens);
    }
}
