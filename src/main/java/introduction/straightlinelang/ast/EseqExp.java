package introduction.straightlinelang.ast;

public class EseqExp extends Exp {
    public final Stm stm;
    public final Exp exp;

    public EseqExp(Stm stm, Exp exp) {
        this.stm = stm;
        this.exp = exp;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
