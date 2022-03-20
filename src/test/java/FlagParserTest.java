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
    void fileFlagAndNumberShouldBeParsed() throws Exception {
        Flag flag = flagParser.parse(new String[]{"-f", "-4"});
        assertAll(
                () -> assertEquals(FileType.File, flag.getFileType()),
                () -> assertEquals(4, flag.getNumber())
        );
    }

    @Test
    void dirFlagAndNumberShouldBeParsed() throws Exception {
        Flag flag = flagParser.parse(new String[]{"-d", "-10"});
        assertAll(
                () -> assertEquals(FileType.Directory, flag.getFileType()),
                () -> assertEquals(10, flag.getNumber())
        );
    }

    @Test
    void dirAndNumberInvalidFlagShouldThrowsException() {
        assertThrows(Exception.class, () -> flagParser.parse(new String[]{"--d", "-10he"}));
    }

    @Test
    void parseInvalidArgShouldThrowsExceptionWithMessage() {
        Exception exception = assertThrows(Exception.class, () -> flagParser.parse(new String[]{"--d", "-10he"}));
        assertEquals("--d" + ": invalid argument", exception.getMessage());
    }

    @Test
    void linuxStylePathShouldBeParsed() throws Exception {
        Flag flag = flagParser.parse(new String[]{"/usr/share"});
        assertAll(
                () -> assertEquals("/usr/share", flag.getPath())
        );
    }

    @Test
    void windowsStylePathShouldBeParsed() throws Exception {
        Flag flag = flagParser.parse(new String[]{"\\user\\java"});
        assertAll(
                () -> assertEquals("\\user\\java", flag.getPath())
        );
    }


}