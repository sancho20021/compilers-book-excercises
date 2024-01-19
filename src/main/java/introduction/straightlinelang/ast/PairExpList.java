package introduction.straightlinelang.ast;

public record PairExpList(Exp head, ExpList tail) implements ExpList {
    public int length() {
        return tail.length() + 1;
    }
}
