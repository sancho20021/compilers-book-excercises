package introduction.excercises.secondversion;


import java.util.Optional;

public record EmptyTree<V>() implements Tree<V> {
    @Override
    public Tree<V> insert(String key, V binding) {
        return new NonemptyTree<>(new EmptyTree<>(), key, binding, new EmptyTree<>());
    }

    @Override
    public Optional<V> lookup(String key) {
        return Optional.empty();
    }
}
