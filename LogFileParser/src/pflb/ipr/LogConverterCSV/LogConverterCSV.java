package pflb.ipr.LogConverterCSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogConverterCSV {
    /*Regex Pattern*/
    String date = "^(\\d{2}\\.\\d{2}\\.\\d{4})\\h";
    String time = "(\\d{2}:\\d{2}:\\d{2}\\.\\d{3})\\h";
    String TAG = "(\\D*:)\\h?";
    String IPnPort = "(\\d*?\\.?\\d*?\\.?\\d*?\\.?\\d*?:?\\d*?\\.?[\\w])?\\h";
    String Descrioption = "(.*)$";

    public void CreateLogCSV(String delimeter, String logFilePath, String outputFileCSV)
    {
        String log = "";
        Pattern pattern = Pattern.compile(date + time + TAG + IPnPort + Descrioption);
        Matcher matcher = null;

        /*try (Stream<Path> paths = Files.walk(Paths.get(logFilePath))) {
            ArrayList<Path> files = paths.filter(Files::isRegularFile).collect(Collectors.toCollection(ArrayList::new));

            for(Path file : files)
            {*/
                try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(logFilePath), StandardCharsets.ISO_8859_1))
                {
                    try (BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(outputFileCSV + ".csv"), StandardCharsets.ISO_8859_1))
                    {
                        while ((log = bufferedReader.readLine()) != null)
                        {
                            matcher = pattern.matcher(log);

                            if(matcher.find())
                            {
                                if(matcher.group(4) != null)
                                {
                                    bufferedWriter.write(matcher.group(1) + delimeter +
                                        matcher.group(2) + delimeter +
                                        matcher.group(3) + delimeter +
                                        matcher.group(4) + delimeter +
                                        matcher.group(5) + '\n');

                                } else
                                {
                                    bufferedWriter.write(matcher.group(1) + delimeter +
                                            matcher.group(2) + delimeter +
                                            matcher.group(3) + delimeter +
                                            matcher.group(5) + '\n');
                                }
                            }
                        }
                    }
                }catch (IOException exception) { exception.printStackTrace(); }
            }
        //} catch (IOException e) { e.printStackTrace(); }
    //}
}
