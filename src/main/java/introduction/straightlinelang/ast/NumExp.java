package introduction.straightlinelang.ast;

public class NumExp extends Exp {
    public final int num;

    public NumExp(int num) {
        this.num = num;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
