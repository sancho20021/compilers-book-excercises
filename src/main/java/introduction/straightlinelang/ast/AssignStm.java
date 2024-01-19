package introduction.straightlinelang.ast;


public record AssignStm(String id, Exp exp) implements Stm {
}
