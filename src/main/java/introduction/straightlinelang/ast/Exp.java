package introduction.straightlinelang.ast;

import util.Pair;

public sealed interface Exp permits EseqExp, IdExp, NumExp, OpExp {
}
