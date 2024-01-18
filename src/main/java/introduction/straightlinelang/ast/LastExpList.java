package introduction.straightlinelang.ast;

public class LastExpList extends ExpList {
    public final Exp head;

    public LastExpList(Exp head) {
        this.head = head;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }

    @Override
    public int length() {
        return 1;
    }
}
