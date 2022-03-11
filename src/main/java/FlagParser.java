import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class FlagParser {
    private final static Pattern fileFlagPattern = Pattern.compile("^-f$");
    private final static Pattern dirFlagPattern = Pattern.compile("^-d$");
    private final static Pattern numberFlagPattern = Pattern.compile("^-\\d+$");
    private final static Pattern pathFlagPattern = Pattern.compile(".+[/\\\\]");

    public Flag parse(String[] args) {
        Map<FlagEnum, Pattern> map = new HashMap<>() {{
            put(FlagEnum.File, fileFlagPattern);
            put(FlagEnum.Dir, dirFlagPattern);
            put(FlagEnum.Number, numberFlagPattern);
            put(FlagEnum.Path, pathFlagPattern);
        }};



        List<String> inputFlags = Arrays.asList(args);

        Flag f = new Flag();

        for (String input : inputFlags) {

            for (FlagEnum flag : map.keySet()) {
                if (map.get(flag).matcher(input).find()) {

                    if (flag.equals(FlagEnum.File)) {
                        f.setFileType(FileType.File);
                    }
                    else if (flag.equals(FlagEnum.Dir)) {
                        f.setFileType(FileType.Directory);
                    }
                    else if (flag.equals(FlagEnum.Number)) {
                        f.setNumber(Integer.parseInt(input.split("-")[1]));
                    }
                    else if (flag.equals(FlagEnum.Path)) {
                        f.setPath(input);
                    }

                    map.remove(flag);
                    break;
                }
            }

        }

        return f;
    }
}
