package introduction.excercises.firstversion;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Pair;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestTree {
    @Test
    public void testInsertOneNode() {
        var tree = Tree.insert("a", 1, Tree.empty());
        Assertions.assertEquals(tree, new Tree<>(Optional.empty(), "a", 1, Optional.empty()));
    }

    @Test
    public void testInsertLeftBranch() {
        var tree = Tree.insert("a", 1, Tree.insert("b", 2, Tree.empty()));
        Assertions.assertEquals(
            tree,
            new Tree<>(
                Optional.of(new Tree<>(Optional.empty(), "a", 1, Optional.empty())),
                "b",
                2,
                Optional.empty()
            )
        );
    }

    @Test
    public void testInsertRightBranch() {
        var tree = Tree.insert("b", 2, Tree.insert("a", 1, Tree.empty()));
        Assertions.assertEquals(
            tree,
            new Tree<>(
                Optional.empty(),
                "a",
                1,
                Optional.of(new Tree<>(Optional.empty(), "b", 2, Optional.empty()))
            )
        );
    }

    @Test
    public void testInsert() {
        var tree = Tree.insert(
            "f",
            6,
            Tree.insert(
                "a",
                1,
                Tree.insert("d", 4, Tree.insert("b", 2, Tree.insert("c", 3, Tree.empty())))
            )
        );
        Assertions.assertEquals(
            tree,
            new Tree<>(
                Optional.of(new Tree<>(
                    Optional.of(new Tree<>(
                        Optional.empty(),
                        "a",
                        1,
                        Optional.empty()
                    )),
                    "b",
                    2,
                    Optional.empty()
                )),
                "c",
                3,
                Optional.of(new Tree<>(
                    Optional.empty(),
                    "d",
                    4,
                    Optional.of(new Tree<>(
                        Optional.empty(),
                        "f",
                        6,
                        Optional.empty()
                    ))
                ))
            )
        );
    }

    @Test
    void testMember() {
        var tree = Tree.insert(
            "f",
            1,
            Tree.insert(
                "a",
                1,
                Tree.insert("d", 1, Tree.insert("b", 1, Tree.insert("c", 1, Tree.empty())))
            )
        );
        for (var key : List.of("a", "b", "c", "d", "f")) {
            Assertions.assertTrue(Tree.member(key, tree));
        }
        for (var key : List.of("0", "e", "z")) {
            Assertions.assertFalse(Tree.member(key, tree));
        }
    }

    @Test
    void testLookup() {
        var tree = Tree.insert(
            "f",
            6,
            Tree.insert(
                "a",
                1,
                Tree.insert("d", 4, Tree.insert("b", 2, Tree.insert("c", 3, Tree.empty())))
            )
        );
        for (var entry : Map.of("a", 1, "b", 2, "c", 3, "d", 4, "f", 6).entrySet()) {
            Assertions.assertEquals(Tree.lookup(entry.getKey(), tree), Optional.of(entry.getValue()));
        }
        for (var key : List.of("0", "e", "z")) {
            Assertions.assertEquals(Tree.lookup(key, tree), Optional.empty());
        }
    }

    @Test
    void testFromIterator() {
        var tree = Tree.fromIterator(List.of(
            new Pair<>("c", 3),
            new Pair<>("b", 2),
            new Pair<>("d", 4),
            new Pair<>("a", 1),
            new Pair<>("f", 6)
        ).iterator());
        var expectedTree = Optional.of(Tree.insert(
            "f",
            6,
            Tree.insert(
                "a",
                1,
                Tree.insert("d", 4, Tree.insert("b", 2, Tree.insert("c", 3, Tree.empty())))
            )
        ));
        Assertions.assertEquals(expectedTree, tree);
    }
}
