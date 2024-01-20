package introduction.excercises.secondversion;

import java.util.Optional;

public record NonemptyTree<V>(Tree<V> left, String key, V binding, Tree<V> right) implements Tree<V> {
    @Override
    public Tree<V> insert(String key, V binding) {
        var keyCmp = key.compareTo(this.key);
        if (keyCmp < 0) {
            return new NonemptyTree<>(left.insert(key, binding), this.key, this.binding, right);
        } else if (keyCmp > 0) {
            return new NonemptyTree<>(left, this.key, this.binding, right.insert(key, binding));
        } else {
            return new NonemptyTree<>(left, this.key, this.binding, right);
        }
    }

    @Override
    public Optional<V> lookup(String key) {
        var keyCmp = key.compareTo(this.key);
        if (keyCmp < 0) {
            return left.lookup(key);
        } else if (keyCmp > 0) {
            return right.lookup(key);
        } else {
            return Optional.of(binding);
        }
    }
}
