package introduction.straightlinelang.ast;

public record LastExpList(Exp head) implements ExpList {
    @Override
    public int length() {
        return 1;
    }
}
