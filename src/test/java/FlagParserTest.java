import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FlagParserTest {

    FlagParser flagParser;

    @BeforeAll
    void setup() {
        flagParser = new FlagParser();
    }

    @Test
    void fileFlagAndNumberShouldBeParsed() {
        Flag flag = flagParser.parse(new String[]{"-f", "-4"});
        assertAll(
                () -> assertEquals(FileType.File, flag.getFileType()),
                () -> assertEquals(4, flag.getNumber())
        );
    }
}