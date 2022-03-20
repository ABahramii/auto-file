import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
                () -> assertEquals(FileTypeEnum.File, flag.getFileType()),
                () -> assertEquals(4, flag.getNumber())
        );
    }

    @Test
    void dirFlagAndNumberShouldBeParsed() throws Exception {
        Flag flag = flagParser.parse(new String[]{"-d", "-10"});
        assertAll(
                () -> assertEquals(FileTypeEnum.Directory, flag.getFileType()),
                () -> assertEquals(10, flag.getNumber())
        );
    }

    @Test
    void linuxStylePathShouldBeParsed() throws Exception {
        Flag flag = flagParser.parse(new String[]{"-f", "-5", "/home"});
        assertEquals("/home", flag.getPath());
    }

    @Test
    void windowsStylePathShouldBeParsed() throws Exception {
        Flag flag = flagParser.parse(new String[]{"-f", "-5", "\\user\\java"});
        assertEquals("\\user\\java", flag.getPath());
    }

    @Test
    void namableFlagShouldBeParsed() throws Exception {
        Flag flag = flagParser.parse(new String[]{"-f", "-5", "-n"});
        assertTrue(flag.isNamable());
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
    @Disabled
    void missingFileTypeFlagShouldThrowsException() {
        Exception exception = assertThrows(Exception.class, () -> flagParser.parse(new String[]{"-10", "-n", "/home/"}));
        assertEquals("missing operand file type: -f or -d", exception.getMessage());
    }

}