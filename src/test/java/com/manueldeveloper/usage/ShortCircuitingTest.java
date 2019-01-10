package com.manueldeveloper.usage;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class ShortCircuitingTest {

    @Test
    public void Should_SkipFirstThreeElementsAndOnlyReturnFiveElements() {
        // Given
        Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

        // When
        final List<Integer> result = infiniteStream
                .skip(3)
                .limit(5)
                .collect(Collectors.toList());

        // Then
        assertEquals(result, Arrays.asList(16, 32, 64, 128, 256));
    }
}
