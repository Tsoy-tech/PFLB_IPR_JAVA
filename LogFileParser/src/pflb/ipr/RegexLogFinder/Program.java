package pflb.ipr.RegexLogFinder;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {

        if (args.length < 3) {
            System.out.println("java -jar <название jar-файла> <фраза_поиска> <путь_до_лог_файла(ов)> <имя_нового_файла>");
            System.out.println("Примеры фразы поиска: .*INFO.*");
            return;
        }

        //RegexLogFinder.writeRegexLog("^.*INFO.*", "B:\\Log", "newlog.log");
        //RegexLogFinder.writeRegexLog("^.*INFO.*", "/home/abstract/Downloads/main.log.2014-11-17", "newlog.log");

        RegexLogFinder.writeRegexLog(args[0], args[1], args[2]);
    }
}
