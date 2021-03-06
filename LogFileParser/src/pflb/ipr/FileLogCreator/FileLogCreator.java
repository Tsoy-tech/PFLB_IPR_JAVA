package pflb.ipr.FileLogCreator;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileLogCreator {

    long logLinesCount = 0;
    int linesPerLogCount = 0;
    int fileCount = 0;

    String fileFrom;
    String fileTo;

    public FileLogCreator(int fileCount, String fileFrom, String fileTo) {
        this.fileCount = fileCount;
        this.fileFrom = fileFrom;
        this.fileTo = fileTo;
    }

    public void writeNewFile() {
        linesPerLogCount = linesCount();
        System.out.println("Files per log: " + linesPerLogCount);
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileFrom), Charset.forName("ISO-8859-1"))) {
            for (int i = 1; i <= fileCount; i++) {
                try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fileTo + i + ".log"))) {
                    int currentLine = 0;
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        bufferedWriter.write(line + '\n');
                        bufferedWriter.flush();
                        currentLine++;

                        if (currentLine == linesPerLogCount)
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private int linesCount() {
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileFrom), Charset.forName("ISO-8859-1"))) {
            while (bufferedReader.readLine() != null) {
                logLinesCount++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Lines in log: " + logLinesCount);
        System.out.println("Files: " + fileCount);

        return (int) (logLinesCount / fileCount + logLinesCount % fileCount);
    }

}