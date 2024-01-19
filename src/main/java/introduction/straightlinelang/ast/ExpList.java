package introduction.straightlinelang.ast;


public sealed interface ExpList permits PairExpList, LastExpList {
    int length();
}
