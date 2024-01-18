package introduction.straightlinelang.ast;

public abstract class Exp {
    public abstract <R> R accept(AstVisitor<R> visitor);
}
