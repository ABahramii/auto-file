import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public enum FlagEnum {
    File,
    Dir,
    Number,
    Path;

    static List<FlagEnum> getValues() {
        return Arrays.asList(FlagEnum.values());
    }
}
