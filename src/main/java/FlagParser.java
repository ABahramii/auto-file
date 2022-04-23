import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class FlagParser {
    private final static Pattern fileFlagPattern = Pattern.compile("^-f$");
    private final static Pattern dirFlagPattern = Pattern.compile("^-d$");
    private final static Pattern numberFlagPattern = Pattern.compile("^-\\d+$");
    private final static Pattern pathFlagPattern = Pattern.compile(".*[/\\\\]");
    private final static Pattern nameFlagPattern = Pattern.compile("^-n$");
    private Map<FlagEnum, Pattern> flagEnumPatternMap;
    private Flag flag;

    public Flag parse(String[] inputArgs) throws Exception {
        createFlagEnumPatternMap();
        flag = new Flag();

        for (String input : inputArgs) {
            boolean invalidArg = true;

            for (FlagEnum flagEnum : flagEnumPatternMap.keySet()) {
                if (flagEnumPatternMap.get(flagEnum).matcher(input).find()) {
                    invalidArg = false;
                    fillFlag(flagEnum, input);
                    flagEnumPatternMap.remove(flagEnum);
                    break;
                }
            }

            if (invalidArg) {
                throw new Exception(input + ": invalid argument");
            }
        }

        validateFlag(flag);
        return flag;
    }

    private void fillFlag(FlagEnum flagEnum, String input) {
        switch (flagEnum) {
            case File -> flag.setFileType(FileTypeEnum.File);
            case Dir -> flag.setFileType(FileTypeEnum.Directory);
            case Number -> flag.setNumber(Integer.parseInt(input.split("-")[1]));
            case Path -> flag.setPath(input);
            case Name -> flag.setNamable(true);
        }
    }

    private void createFlagEnumPatternMap() {
        flagEnumPatternMap = new HashMap<>();
        flagEnumPatternMap.put(FlagEnum.File, fileFlagPattern);
        flagEnumPatternMap.put(FlagEnum.Dir, dirFlagPattern);
        flagEnumPatternMap.put(FlagEnum.Number, numberFlagPattern);
        flagEnumPatternMap.put(FlagEnum.Path, pathFlagPattern);
        flagEnumPatternMap.put(FlagEnum.Name, nameFlagPattern);
    }

    private void validateFlag(Flag flag) throws Exception {
        if (flag.getFileType() == null) {
            throw new Exception("missing operand file type: -f or -d");
        }
        if (flag.getNumber() == 0) {
            throw new Exception("missing operand number of files: -<number>");
        }
    }
}
