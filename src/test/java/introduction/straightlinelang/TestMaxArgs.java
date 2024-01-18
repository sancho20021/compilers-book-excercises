package introduction.straightlinelang;

import introduction.straightlinelang.ast.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestMaxArgs {
    @Test
    void testMaxArgs() {
        Stm prog = new CompoundStm(
            new AssignStm("a", new OpExp(new NumExp(5), OpExp.Oper.PLUS, new NumExp(3))),
            new CompoundStm(
                new AssignStm(
                    "b",
                    new EseqExp(
                        new PrintStm(
                            new PairExpList(
                                new IdExp("a"),
                                new LastExpList(new OpExp(new IdExp("a"), OpExp.Oper.MINUS, new NumExp(1)))
                            )
                        ),
                        new OpExp(new NumExp(10), OpExp.Oper.TIMES, new IdExp("a"))
                    )
                ),
                new PrintStm(new LastExpList(new IdExp("b")))
            )
        );
        int maxArgs = MaxArgsVisitor.maxArgs(prog);
        Assertions.assertEquals(maxArgs, 2);
    }
}
