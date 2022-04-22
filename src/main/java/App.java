import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class App {
    public void run(String[] args) throws Exception {
        Flag flag = new FlagParser().parse(args);

        List<String> names;
        if (flag.isNamable()) {
            names = getCustomNames(flag.getFileType(), flag.getNumber());
        } else {
            names = generateDefaultNames(flag.getNumber());
        }

        FileCreator fileCreator = new FileCreator(flag.getFileType(), flag.getPath(), names);
        fileCreator.create();

        System.out.println(flag.getFileType().getCreateMessage());
    }

    public List<String> getCustomNames(FileTypeEnum fileType, int number) {
        Scanner sc = new Scanner(System.in);
        List<String> names = new ArrayList<>(number);
        String typeName = fileType.getTitleName();
        for (int i = 1; i <= number; i++) {
            System.out.print(typeName + " " + i + ": ");
            names.add(sc.nextLine());
        }
        return names;
    }

    public List<String> generateDefaultNames(int number) {
        return IntStream.range(1, number + 1)
                .mapToObj(String::valueOf)
                .collect(Collectors.toList());
    }
}
