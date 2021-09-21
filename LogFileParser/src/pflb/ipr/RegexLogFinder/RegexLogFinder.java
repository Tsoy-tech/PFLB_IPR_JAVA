package pflb.ipr.RegexLogFinder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RegexLogFinder {

    public static void writeRegexLog(String regex, String logFilePath, String outputFileLog)
    {
        Pattern pattern = Pattern.compile(regex);

        try (Stream<Path> paths = Files.walk(Paths.get(logFilePath))) {
            ArrayList<Path> files = paths.filter(Files::isRegularFile).collect(Collectors.toCollection(ArrayList::new));

            for(Path file : files)
            {
                try (BufferedReader bufferedReader = Files.newBufferedReader(file, Charset.forName("ISO-8859-1"))) {
                    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(outputFileLog), Charset.forName("ISO-8859-1"))) {
                        String log;

                        while ((log = bufferedReader.readLine()) != null) {

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
