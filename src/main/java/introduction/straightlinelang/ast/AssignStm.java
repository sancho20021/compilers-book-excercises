package introduction.straightlinelang.ast;

public class AssignStm extends Stm {
    public final String id;
    public final Exp exp;

    public AssignStm(String i, Exp e) {
        id = i;
        exp = e;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
