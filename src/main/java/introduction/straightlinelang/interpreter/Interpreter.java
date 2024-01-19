package introduction.straightlinelang.interpreter;

import introduction.straightlinelang.ast.*;
import util.Pair;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Interpreter {
    /**
     * Interprets given statement
     * @param stm given statement
     * @throws UnresolvedVariableException if uninitialized variable is used
     */
    public void interp(Stm stm) throws UnresolvedVariableException {
        Table ignored = interp(stm, new Table.Nil());
    }

    private Table interp(Stm stm, Table table) {
        return switch (stm) {
            case AssignStm(String id, Exp exp) -> {
                var valueAndTable = interp(exp, table);
                yield new Table.Cons(id, valueAndTable.first(), valueAndTable.second());
            }
            case CompoundStm(Stm stm1, Stm stm2) -> interp(stm2, interp(stm1, table));
            case PrintStm(ExpList expList) -> {
                var valuesAndApdatedTable = interp(expList, table);
                System.out.println(
                    valuesAndApdatedTable.first().mapToObj(String::valueOf).collect(Collectors.joining(" "))
                );
                yield valuesAndApdatedTable.second();
            }
        };
    }

    private Pair<Integer, Table> interp(Exp exp, Table table) {
        return switch (exp) {
            case EseqExp(Stm stm, Exp innerExp) -> interp(innerExp, interp(stm, table));
            case IdExp(String id) -> new Pair<>(table.lookup(id), table);
            case NumExp(int value) -> new Pair<>(value, table);
            case OpExp(Exp left, Oper op, Exp right) -> {
                var leftValueAndTable = interp(left, table);
                var rightValueAndTable = interp(right, leftValueAndTable.second());
                var result = switch (op) {
                    case PLUS -> leftValueAndTable.first() + rightValueAndTable.first();
                    case MINUS -> leftValueAndTable.first() - rightValueAndTable.first();
                    case TIMES -> leftValueAndTable.first() * rightValueAndTable.first();
                    case DIV -> leftValueAndTable.first() / rightValueAndTable.first();
                };
                yield new Pair<>(result, rightValueAndTable.second());
            }
        };
    }

    private Pair<IntStream, Table> interp(ExpList expList, Table table) {
        return switch (expList) {
            case PairExpList(Exp head, ExpList tail) -> {
                var firstValueAndTable = interp(head, table);
                var tailValuesAndTable = interp(tail, firstValueAndTable.second());
                yield new Pair<>(
                    IntStream.concat(IntStream.of(firstValueAndTable.first()), tailValuesAndTable.first()),
                    tailValuesAndTable.second()
                );
            }
            case LastExpList(Exp head) -> {
                var valueAndTable = interp(head, table);
                yield new Pair<>(IntStream.of(valueAndTable.first()), valueAndTable.second());
            }
        };
    }
}
