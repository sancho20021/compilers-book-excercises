package introduction.straightlinelang.interpreter;

public sealed interface Table permits Table.Cons, Table.Nil {
    record Cons(String id, int value, Table tail) implements Table {

        @Override
        public int lookup(String id) throws UnresolvedVariableException {
            return this.id.equals(id) ? value : tail.lookup(id);
        }
    }

    final class Nil implements Table {
        @Override
        public int lookup(String id) throws UnresolvedVariableException {
            throw new UnresolvedVariableException();
        }
    }

    int lookup(String id) throws UnresolvedVariableException;
}