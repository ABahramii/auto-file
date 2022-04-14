import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public void run(String[] args) throws Exception {
        Flag flag = new FlagParser().parse(args);

        // Todo: write test
        List<String> names = new ArrayList<>(flag.getNumber());
        if (flag.isNamable()) {
            String typeName = getTypeName(flag.getFileType());
            Scanner sc = new Scanner(System.in);
            for (int i = 1; i <= flag.getNumber(); i++) {
                System.out.print(typeName + " " + i + ": ");
                String name = sc.nextLine();
                names.add(name);
            }
        } else {
            names = IntStream.range(1, flag.getNumber() + 1)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.toList());
        }

        names.forEach(System.out::println);
    }

    private String getTypeName(FileTypeEnum fileType) {
        if (fileType.equals(FileTypeEnum.File)) {
            return "filename";
        }
        // FileTypeEnum.Directory
        return "dirname";
    }
}
