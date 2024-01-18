package introduction.straightlinelang.ast;

public class CompoundStm extends Stm {
    public final Stm stm1;
    public final Stm stm2;

    public CompoundStm(Stm s1, Stm s2) {
        stm1 = s1;
        stm2 = s2;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
