package introduction.straightlinelang.ast;

public record OpExp(Exp left, Oper oper, Exp right) implements Exp {
}
