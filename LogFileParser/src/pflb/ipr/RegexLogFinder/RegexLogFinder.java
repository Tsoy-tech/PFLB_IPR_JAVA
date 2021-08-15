package pflb.ipr.RegexLogFinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegexLogFinder {

    public static void writeRegexLog(String regex, String logFilePath, String outputFileLog)
    {
        String log = "";
        Pattern pattern = Pattern.compile(regex);

        try (Stream<Path> paths = Files.walk(Paths.get(logFilePath))) {
            ArrayList<Path> files = paths.filter(Files::isRegularFile).collect(Collectors.toCollection(ArrayList::new));

            for(Path file : files)
            {
                try (BufferedReader bufferedReader = Files.newBufferedReader(file, StandardCharsets.ISO_8859_1)) {
                    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(outputFileLog), StandardCharsets.ISO_8859_1)) {
                        while (bufferedReader.read() != -1) {
                            log = bufferedReader.readLine();

                            if (pattern.matcher(log).find()) {
                                bufferedWriter.write(log + '\n');
                                bufferedWriter.flush();
                            }
                        }

                    }
                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
    } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
