package com.manueldeveloper;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class InfiniteStreamTest {

    @Test
    public void generate_operation() {
        // Then
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    @Test
    public void iterate_operation() {
        // Given
        final Stream<Integer> stream = Stream.iterate(2, i -> i * 2);

        // When
        final List<Integer> result = stream.limit(5)
                .collect(Collectors.toList());

        // Then
        assertEquals(Arrays.asList(2, 4, 8, 16, 32), result);
    }
}
