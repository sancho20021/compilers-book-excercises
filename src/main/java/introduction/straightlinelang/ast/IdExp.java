package introduction.straightlinelang.ast;

public record IdExp(String id) implements Exp {
    public IdExp(String id) {
        this.id = id;
    }
}
