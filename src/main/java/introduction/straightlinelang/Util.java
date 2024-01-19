package introduction.straightlinelang;

import introduction.straightlinelang.ast.*;


public class Util {

    /**
     * Calculates maximum number of arguments of any print statement
     * within any subexpression of a given statement
     *
     * @param stm given statement
     * @return maximum number of arguments
     */
    public static int maxArgs(Stm stm) {
        return switch (stm) {
            case AssignStm(String ignored, Exp exp) -> maxArgs(exp);
            case CompoundStm(Stm stm1, Stm stm2) -> Math.max(maxArgs(stm1), maxArgs(stm2));
            case PrintStm(ExpList expList) -> Math.max(expList.length(), maxArgs(expList));
        };
    }

    public static int maxArgs(Exp exp) {
        return switch (exp) {
            case EseqExp(Stm stm, Exp innerExp) -> Math.max(maxArgs(stm), maxArgs(innerExp));
            case IdExp ignored -> 0;
            case NumExp ignored -> 0;
            case OpExp(Exp left, Oper ignored, Exp right) -> Math.max(maxArgs(left), maxArgs(right));
        };
    }

    public static int maxArgs(ExpList expList) {
        return switch (expList) {
            case PairExpList(Exp head, ExpList tail) -> Math.max(maxArgs(head), maxArgs(tail));
            case LastExpList(Exp head) -> maxArgs(head);
        };
    }
}
