import org.junit.jupiter.api.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AppTest {

    App app;
    InputStream sysInBackup;
    PrintStream sysOutBackup;
    Path tmpOutput;

    @BeforeAll
    void setup() throws IOException {
        app = new App();
        sysInBackup = System.in;
        sysOutBackup = System.out;
        Files.deleteIfExists(Paths.get("src/test/resources/tmp.tmp"));
        tmpOutput = Files.createFile(Paths.get("src/test/resources/tmp.tmp"));
    }

    @AfterAll
    void afterTestsRuns() throws IOException {
        System.setIn(sysInBackup);
        System.setOut(sysOutBackup);
    }

    @Test
    void ifNamableIsTrueItShouldGetNames() throws IOException {
        changeSysInput();
        changeSysOutputToTmp();
        List<String> names = app.getCustomNames(FileTypeEnum.File, 3);
        Assertions.assertArrayEquals(new String[]{"fileOne", "fileTwo", "fileThree"}, names.toArray());
    }

    @Test
    void ifNamableIsFalseItShouldGenerateNames() {
        List<String> names = app.generateDefaultNames(5);
        Assertions.assertArrayEquals(
                new String[]{"1", "2", "3", "4", "5"}, names.toArray()
        );
    }

    private void changeSysInput() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("src/test/resources/nameInput.txt");
        System.setIn(inputStream);
    }

    private void changeSysOutputToTmp() throws IOException {
        PrintStream printStream = new PrintStream(new FileOutputStream(tmpOutput.toFile()));
        System.setOut(printStream);
    }

}