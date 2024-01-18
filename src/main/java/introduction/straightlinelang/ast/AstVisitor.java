package introduction.straightlinelang.ast;

public interface AstVisitor<R> {
    R visit(AssignStm assignStm);
    R visit(CompoundStm compoundStm);
    R visit(EseqExp eseqExp);
    R visit(IdExp idExp);
    R visit(LastExpList lastExpList);
    R visit(NumExp numExp);
    R visit(OpExp opExp);
    R visit(PairExpList pairExpList);
    R visit(PrintStm printStm);
}
