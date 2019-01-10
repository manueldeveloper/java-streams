package com.manueldeveloper;

import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class FileStreamTest {

    private static String fileName = "src/test/resources/test.txt";

    @Test
    public void stream_to_file() throws IOException {
        // Given
        String[] words = {
                "hello",
                "refer",
                "world",
                "level"
        };

        // When
        PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get(fileName)));
        Stream.of(words)
                .peek(System.out::println)
                .forEach(writer::println);
        writer.close();
    }

    @Test
    public void file_to_stream() throws IOException {
        // Given
        final List<String> expected = Arrays.asList("refer", "level");

        // When
        final List<String> result = getPalindrome(Files.lines(Paths.get(fileName)), 5);

        // Then
        assertEquals(expected, result);
    }

    private List<String> getPalindrome(Stream<String> stream, int length) {
        return stream.filter(s -> s.length() == length)
                .filter(s -> s.compareToIgnoreCase(new StringBuilder(s).reverse().toString()) == 0)
                .collect(Collectors.toList());
    }
}
