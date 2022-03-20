import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FlagParser {
    private final static Pattern fileFlagPattern = Pattern.compile("^-f$");
    private final static Pattern dirFlagPattern = Pattern.compile("^-d$");
    private final static Pattern numberFlagPattern = Pattern.compile("^-\\d+$");
    private final static Pattern pathFlagPattern = Pattern.compile(".+[/\\\\]");
    private final static Pattern nameFlagPattern = Pattern.compile("^-n$");

    public Flag parse(String[] inputArgs) throws Exception {
        Map<FlagEnum, Pattern> flagEnumPatternMap = new HashMap<>() {{
            put(FlagEnum.File, fileFlagPattern);
            put(FlagEnum.Dir, dirFlagPattern);
            put(FlagEnum.Number, numberFlagPattern);
            put(FlagEnum.Path, pathFlagPattern);
            put(FlagEnum.Name, nameFlagPattern);
        }};

        Flag flag = new Flag();

        for (String input : inputArgs) {
            boolean invalidArg = true;

            for (FlagEnum flagEnum : flagEnumPatternMap.keySet()) {
                if (flagEnumPatternMap.get(flagEnum).matcher(input).find()) {
                    invalidArg = false;

                    if (flagEnum.equals(FlagEnum.File)) {
                        flag.setFileType(FileType.File);
                    } else if (flagEnum.equals(FlagEnum.Dir)) {
                        flag.setFileType(FileType.Directory);
                    } else if (flagEnum.equals(FlagEnum.Number)) {
                        flag.setNumber(Integer.parseInt(input.split("-")[1]));
                    } else if (flagEnum.equals(FlagEnum.Path)) {
                        flag.setPath(input);
                    } else if (flagEnum.equals(FlagEnum.Name)) {
                        flag.setNamable(true);
                    }

                    flagEnumPatternMap.remove(flagEnum);
                    break;
                }
            }

            if (invalidArg) {
                throw new Exception(input + ": invalid argument");
            }
        }

        return flag;
    }
}
