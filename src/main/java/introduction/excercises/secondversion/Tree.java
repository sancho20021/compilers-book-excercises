package introduction.excercises.secondversion;

import util.Pair;

import java.util.Iterator;
import java.util.Optional;

public sealed interface Tree<V> permits EmptyTree, NonemptyTree {
    Tree<V> insert(String key, V binding);

    Optional<V> lookup(String key);

    default Tree<V> insertAll(Iterator<Pair<String, V>> entries) {
        if (entries.hasNext()) {
            var kv = entries.next();
            return insert(kv.first(), kv.second()).insertAll(entries);
        } else {
            return this;
        }
    }

    static <V> Tree<V> fromIterator(Iterator<Pair<String, V>> entries) {
        return new EmptyTree<V>().insertAll(entries);
    }
}
