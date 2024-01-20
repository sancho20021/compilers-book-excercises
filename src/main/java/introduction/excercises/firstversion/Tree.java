package introduction.excercises.firstversion;

import util.Pair;

import java.util.Iterator;
import java.util.Optional;

public record Tree<V>(Optional<Tree<V>> left, String key, V binding, Optional<Tree<V>> right) {
    public static <V> Tree<V> insert(String key, V binding, Tree<V> tree) {
        return insert(key, binding, Optional.of(tree));
    }

    public static <V> Tree<V> insert(String key, V binding, Optional<Tree<V>> tree) {
        if (tree.isEmpty()) {
            return new Tree<>(Optional.empty(), key, binding, Optional.empty());
        }
        var treePresent = tree.get();
        var keyCmp = key.compareTo(treePresent.key);
        if (keyCmp < 0) {
            return new Tree<>(
                Optional.of(insert(key, binding, treePresent.left)),
                treePresent.key,
                treePresent.binding,
                treePresent.right
            );
        } else if (keyCmp > 0) {
            return new Tree<>(
                treePresent.left,
                treePresent.key,
                treePresent.binding,
                Optional.of(insert(key, binding, treePresent.right))
            );
        } else {
            return new Tree<>(treePresent.left, key, binding, treePresent.right);
        }
    }

    public static <V> boolean member(String key, Tree<V> tree) {
        return member(key, Optional.of(tree));
    }

    public static <V> boolean member(String key, Optional<Tree<V>> tree) {
        return lookup(key, tree).isPresent();
    }

    public static <V> Optional<V> lookup(String key, Tree<V> tree) {
        return lookup(key, Optional.of(tree));
    }

    public static <V> Optional<V> lookup(String key, Optional<Tree<V>> tree) {
        if (tree.isEmpty()) {
            return Optional.empty();
        }
        var treePresent = tree.get();
        var keyCmp = key.compareTo(treePresent.key);
        if (keyCmp < 0) {
            return lookup(key, treePresent.left);
        } else if (keyCmp > 0) {
            return lookup(key, treePresent.right);
        } else {
            return Optional.of(treePresent.binding);
        }
    }

    public static <V> Optional<Tree<V>> empty() {
        return Optional.empty();
    }

    public static <V> Optional<Tree<V>> fromIterator(Iterator<Pair<String, V>> iterator) {
        return insertAll(iterator, Tree.empty());
    }

    public static <V> void print(Optional<Tree<V>> tree) {
        print(tree, 0);
    }

    private static <V> void print(Optional<Tree<V>> tree, int indent) {
        if (tree.isPresent()) {
            var treePresent = tree.get();
            System.out.println(" ".repeat(indent) + treePresent.key + " -> " + treePresent.binding);
            if (treePresent.left.isPresent()) {
                System.out.println(" ".repeat(indent) + "left:");
                print(treePresent.left, indent + 2);
            }
            if (treePresent.right.isPresent()) {
                System.out.println(" ".repeat(indent) + "right:");
                print(treePresent.right, indent + 2);
            }
        }
    }

    private static <V> Optional<Tree<V>> insertAll(Iterator<Pair<String, V>> entries, Optional<Tree<V>> tree) {
        if (entries.hasNext()) {
            var kv = entries.next();
            return insertAll(entries, Optional.of(insert(kv.first(), kv.second(), tree)));
        } else {
            return tree;
        }
    }
}
