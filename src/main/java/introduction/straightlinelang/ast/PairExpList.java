package introduction.straightlinelang.ast;

public class PairExpList extends ExpList {
    public final Exp head;
    public final ExpList tail;

    public PairExpList(Exp head, ExpList tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public <R> R accept(AstVisitor<R> visitor) {
        return visitor.visit(this);
    }

    public int length() {
        return tail.length() + 1;
    }
}
