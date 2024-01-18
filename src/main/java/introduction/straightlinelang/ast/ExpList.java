package introduction.straightlinelang.ast;

public abstract class ExpList {
    public abstract <R> R accept(AstVisitor<R> visitor);

    public abstract int length();
}
