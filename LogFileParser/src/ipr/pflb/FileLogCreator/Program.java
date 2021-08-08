package ipr.pflb.FileLogCreator;

import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {

        if(args.length != 2)
        {
            System.out.println("java -jar <название jar-файла> <имя_лог_файла> <постоянная_часть_имени_полученных_файлов>");
            return;
        }

        FileLogCreator fileLogCreator = new FileLogCreator(6, args[0], args[1]);
        fileLogCreator.writeNewFile();
    }
}
