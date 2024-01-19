package introduction.straightlinelang.interpreter;

import introduction.straightlinelang.ast.*;
import org.junit.jupiter.api.Test;

public class TestInterpreter {
    @Test
    void testInterpreter() {
        Stm prog = new CompoundStm(
            new AssignStm("a", new OpExp(new NumExp(5), Oper.PLUS, new NumExp(3))),
            new CompoundStm(
                new AssignStm(
                    "b",
                    new EseqExp(
                        new PrintStm(
                            new PairExpList(
                                new IdExp("a"),
                                new LastExpList(new OpExp(new IdExp("a"), Oper.MINUS, new NumExp(1)))
                            )
                        ),
                        new OpExp(new NumExp(10), Oper.TIMES, new IdExp("a"))
                    )
                ),
                new PrintStm(new LastExpList(new IdExp("b")))
            )
        );
        new Interpreter().interp(prog);
    }
}
