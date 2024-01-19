package introduction.straightlinelang;

import introduction.straightlinelang.ast.AssignStm;
import introduction.straightlinelang.ast.CompoundStm;
import introduction.straightlinelang.ast.EseqExp;
import introduction.straightlinelang.ast.IdExp;
import introduction.straightlinelang.ast.LastExpList;
import introduction.straightlinelang.ast.NumExp;
import introduction.straightlinelang.ast.OpExp;
import introduction.straightlinelang.ast.Oper;
import introduction.straightlinelang.ast.PairExpList;
import introduction.straightlinelang.ast.PrintStm;
import introduction.straightlinelang.ast.Stm;

public class Main {
    public static void main(String[] args) {
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
        System.out.println("prog:\n" + prog);
        System.out.println("Max args of prog: " + Util.maxArgs(prog));
    }
}
