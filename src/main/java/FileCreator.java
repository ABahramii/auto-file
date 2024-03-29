import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileCreator {
    private final FileTypeEnum fileType;
    private final String rootPath;
    private final List<String> fileNames;

    public FileCreator(FileTypeEnum fileType, String path, List<String> fileNames) {
        this.fileType = fileType;
        this.rootPath = path;
        this.fileNames = fileNames;
    }

    public void create() throws IOException {
        if (fileType.equals(FileTypeEnum.File)) {
            createFile();
        } else {
            // FileTypeEnum.Directory
            createDirectory();
        }
    }

    private void createFile() throws IOException {
        for (String fileName : fileNames) {
            String mainPath = rootPath + "/" + fileName + ".txt";
            Path path = Paths.get(mainPath);
            Files.createFile(path);
        }
    }

    private void createDirectory() throws IOException {
        for (String fileName : fileNames) {
            String mainPath = rootPath + "/" + fileName + "/";
            Path path = Paths.get(mainPath);
            Files.createDirectory(path);
        }
    }
}
