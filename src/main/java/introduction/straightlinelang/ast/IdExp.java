package introduction.straightlinelang.ast;

public class IdExp extends Exp {
    public final String id;

    public IdExp(String id) {
        this.id = id;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }
}
