package introduction.straightlinelang;

import introduction.straightlinelang.ast.*;


public class MaxArgsVisitor implements AstVisitor<Integer> {
    @Override
    public Integer visit(AssignStm assignStm) {
        return assignStm.exp.accept(this);
    }

    @Override
    public Integer visit(CompoundStm compoundStm) {
        return Math.max(compoundStm.stm1.accept(this), compoundStm.stm2.accept(this));
    }

    @Override
    public Integer visit(EseqExp eseqExp) {
        return Math.max(eseqExp.stm.accept(this), eseqExp.exp.accept(this));
    }

    @Override
    public Integer visit(IdExp idExp) {
        return 0;
    }

    @Override
    public Integer visit(LastExpList lastExpList) {
        return lastExpList.head.accept(this);
    }

    @Override
    public Integer visit(NumExp numExp) {
        return 0;
    }

    @Override
    public Integer visit(OpExp opExp) {
        return Math.max(opExp.left.accept(this), opExp.right.accept(this));
    }

    @Override
    public Integer visit(PairExpList pairExpList) {
        return Math.max(pairExpList.head.accept(this), pairExpList.tail.accept(this));
    }

    @Override
    public Integer visit(PrintStm printStm) {
        return Math.max(printStm.exps.accept(this), printStm.exps.length());
    }

    public static int maxArgs(Stm stm) {
        return stm.accept(new MaxArgsVisitor());
    }
}
