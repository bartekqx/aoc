package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InputReader {

    private InputReader() {

    }

    private static final String rootPath = System.getProperty("user.dir") + "/src";

    public static List<String> readLines(String inputPath) throws IOException {
        return Files.lines(Paths.get(rootPath + inputPath))
                .collect(Collectors.toList());

    }
}
