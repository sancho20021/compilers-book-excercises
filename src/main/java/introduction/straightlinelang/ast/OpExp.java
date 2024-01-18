package introduction.straightlinelang.ast;

public class OpExp extends Exp {
    public final Exp left;
    public final Exp right;
    public final Oper oper;

    public OpExp(Exp left, Oper oper, Exp right) {
        this.left = left;
        this.oper = oper;
        this.right = right;
    }

    public enum Oper {
        PLUS, MINUS, TIMES, DIV,
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
