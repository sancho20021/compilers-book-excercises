package introduction.straightlinelang.ast;

public class PrintStm extends Stm {
    public final ExpList exps;

    public PrintStm(ExpList exps) {
        this.exps = exps;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
