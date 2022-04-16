import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileCreatorTest {

    @Test
    void filesShouldCreatesInPath() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/resources";

        FileCreator fileCreator = new FileCreator(FileTypeEnum.File, path, Arrays.asList("fileOne", "fileTwo", "fileThree"));
        fileCreator.create();

        Path pathFileOne = Paths.get(path + "/fileOne.txt");
        Path pathFileTwo = Paths.get(path + "/fileTwo.txt");
        Path pathFileThree = Paths.get(path + "/fileThree.txt");

        assertAll(
                () -> assertTrue(Files.exists(pathFileOne)),
                () -> assertTrue(Files.exists(pathFileTwo)),
                () -> assertTrue(Files.exists(pathFileThree))
        );

        Files.delete(pathFileOne);
        Files.delete(pathFileTwo);
        Files.delete(pathFileThree);
    }

    @Test
    void directoriesShouldCreatesInPath() throws IOException {
        String path = System.getProperty("user.dir") + "/src/test/resources";

        FileCreator fileCreator = new FileCreator(FileTypeEnum.Directory, path, Arrays.asList("fileOne", "fileTwo", "fileThree"));
        fileCreator.create();

        Path pathFileOne = Paths.get(path + "/fileOne");
        Path pathFileTwo = Paths.get(path + "/fileTwo");
        Path pathFileThree = Paths.get(path + "/fileThree");

        assertAll(
                () -> assertTrue(Files.exists(pathFileOne)),
                () -> assertTrue(Files.exists(pathFileTwo)),
                () -> assertTrue(Files.exists(pathFileThree))
        );

        Files.delete(pathFileOne);
        Files.delete(pathFileTwo);
        Files.delete(pathFileThree);
    }

}