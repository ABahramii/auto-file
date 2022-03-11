import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlagParser {
    private final static String fileFlagReg = "-f";
    private final static String numberFlagReg = "-\\d";

    public Flag parse(String[] args) {
        Map<FlagEnum, String> map = new HashMap<>() {{
            put(FlagEnum.File, fileFlagReg);
            put(FlagEnum.Number, numberFlagReg);
        }};

        List<String> inputFlags = Arrays.asList(args);

        Flag f = new Flag();

        for (String input : inputFlags) {

            for (FlagEnum flag : map.keySet()) {
                if (input.matches(map.get(flag))) {

                    if (flag.equals(FlagEnum.File)) {
                        f.setFileType(FileType.File);
                    }
                    else if (flag.equals(FlagEnum.Number)) {
                        f.setNumber(Integer.parseInt(input.split("-")[1]));
                    }

                    map.remove(flag);
                    break;
                }
            }

        }

        return f;
    }
}
