package pflb.ipr.LogConverterCSV;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {

       if (args.length < 3) {
            System.out.println("java -jar <название jar-файла> <тип_разделителя> <путь_до_лог_файла(ов)> <имя_нового_файла>");
            return;
        }

        LogConverterCSV converterCSV = new LogConverterCSV();
        converterCSV.CreateLogCSV(args[0], args[1], args[2]);

        //converterCSV.CreateLogCSV(";", "B:\\Log", "log");
        //converterCSV.CreateLogCSV(";", "/home/abstract/Downloads/main.log.2014-11-17", "log");
    }
}
