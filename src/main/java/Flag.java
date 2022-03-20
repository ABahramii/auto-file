public class Flag {
    private FileTypeEnum fileType;
    private int number;
    private String path;
    private boolean namable;

    public FileTypeEnum getFileType() {
        return fileType;
    }

    public void setFileType(FileTypeEnum fileTypeEnum) {
        this.fileType = fileTypeEnum;
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

    public boolean isNamable() {
        return namable;
    }

    public void setNamable(boolean namable) {
        this.namable = namable;
    }
}
