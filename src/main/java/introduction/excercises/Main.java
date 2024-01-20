package introduction.excercises;

import introduction.excercises.firstversion.Tree;
import util.Pair;

import java.util.Arrays;
import java.util.Optional;

public class Main {
    private static Optional<Tree<Integer>> treeFromKeys(String... keys) {
        return Tree.fromIterator(Arrays.stream(keys).map(k -> new Pair<>(k, 1)).iterator());
    }

    public static void main(String[] args) {
        var tree1 = treeFromKeys("t", "s", "p", "i", "p", "f", "b", "s", "t");
        Tree.print(tree1);

        var tree2 = treeFromKeys("a", "b", "c", "d", "e", "f", "g", "h", "i");
        Tree.print(tree2);
    }
}
