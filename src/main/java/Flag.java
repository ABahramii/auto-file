public class Flag {
    private FileType fileType;
    private int number;
    private String path;
    private boolean hasName;

    public FileType getFileType() {
        return fileType;
    }

    public void setFileType(FileType fileType) {
        this.fileType = fileType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isHasName() {
        return hasName;
    }

    public void setHasName(boolean hasName) {
        this.hasName = hasName;
    }
}
