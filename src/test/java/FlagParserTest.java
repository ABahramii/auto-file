import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

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

    @Test
    void dirFlagAndNumberShouldBeParsed() {
        Flag flag = flagParser.parse(new String[]{"-d", "-10"});
        assertAll(
                () -> assertEquals(FileType.Directory, flag.getFileType()),
                () -> assertEquals(10, flag.getNumber())
        );
    }

    @Test
    void dirAndNumberBadFlagShouldNotBeParsed() {
        Flag flag = flagParser.parse(new String[]{"--d", "-10he"});
        assertAll(
                () -> assertNull(flag.getFileType()),
                () -> assertEquals(0, flag.getNumber())
        );
    }

    @Test
    void linuxStylePathShouldBeParsed() {
        Flag flag = flagParser.parse(new String[]{"/usr/share"});
        assertAll(
                () -> assertEquals("/usr/share", flag.getPath())
        );
    }

    @Test
    void windowsStylePathShouldBeParsed() {
        Flag flag = flagParser.parse(new String[]{"\\user\\java"});
        assertAll(
                () -> assertEquals("\\user\\java", flag.getPath())
        );
    }

    @Test
    void badPathShouldNotBeParsed() {
        Flag flag = flagParser.parse(new String[]{"Bad_Path"});
        assertAll(
                () -> assertNull(flag.getPath())
        );
    }


}