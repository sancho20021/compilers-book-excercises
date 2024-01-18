package introduction.straightlinelang.ast;

public abstract class Stm {
    public abstract <R> R accept(AstVisitor<R> visitor);
}
