package introduction.excercises.secondversion;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import util.Pair;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class TestTree {
    @Test
    public void testInsertOneNode() {
        var tree = new EmptyTree<>().insert("a", 1);
        assertEquals(new NonemptyTree<>(new EmptyTree<>(), "a", 1, new EmptyTree<>()), tree);
    }

    @Test
    public void testInsertLeftBranch() {
        var tree = new EmptyTree<>().insert("b", 2).insert("a", 1);
        assertEquals(
            new NonemptyTree<>(
                new NonemptyTree<>(
                    new EmptyTree<>(),
                    "a",
                    1,
                    new EmptyTree<>()
                ),
                "b",
                2,
                new EmptyTree<>()
            ),
            tree
        );
    }

    @Test
    public void testInsertRightBranch() {
        var tree = new EmptyTree<>().insert("a", 1).insert("b", 2);
        assertEquals(
            new NonemptyTree<>(
                new EmptyTree<>(),
                "a",
                1,
                new NonemptyTree<>(
                    new EmptyTree<>(),
                    "b",
                    2,
                    new EmptyTree<>()
                )
            ),
            tree
        );
    }

    @Test
    public void testInsert() {
        var tree = new EmptyTree<>()
            .insert("c", 3)
            .insert("b", 2)
            .insert("d", 4)
            .insert("a", 1)
            .insert("f", 6);
        assertEquals(
            new NonemptyTree<>(
                new NonemptyTree<>(
                    new NonemptyTree<>(
                        new EmptyTree<>(),
                        "a",
                        1,
                        new EmptyTree<>()
                    ),
                    "b",
                    2,
                    new EmptyTree<>()
                ),
                "c",
                3,
                new NonemptyTree<>(
                    new EmptyTree<>(),
                    "d",
                    4,
                    new NonemptyTree<>(
                        new EmptyTree<>(),
                        "f",
                        6,
                        new EmptyTree<>()
                    )
                )
            ),
            tree
        );
    }

    @Test
    void testLookup() {
        var tree = new EmptyTree<>()
            .insert("c", 3)
            .insert("b", 2)
            .insert("d", 4)
            .insert("a", 1)
            .insert("f", 6);
        for (var entry : Map.of("a", 1, "b", 2, "c", 3, "d", 4, "f", 6).entrySet()) {
            assertEquals(Optional.of(entry.getValue()), tree.lookup(entry.getKey()));
        }
        for (var key : List.of("0", "e", "z")) {
            assertEquals(Optional.empty(), tree.lookup(key));
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
        var expectedTree = new EmptyTree<>()
            .insert("c", 3)
            .insert("b", 2)
            .insert("d", 4)
            .insert("a", 1)
            .insert("f", 6);
        assertEquals(expectedTree, tree);
    }
}
