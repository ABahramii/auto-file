public enum FileTypeEnum {
    File("filename", "Files created successfully."),
    Directory("dirname", "Directories created successfully");

    private final String titleName;
    private final String createMessage;

    FileTypeEnum(String titleName, String createMessage) {
        this.titleName = titleName;
        this.createMessage = createMessage;
    }

    public String getTitleName() {
        return titleName;
    }

    public String getCreateMessage() {
        return createMessage;
    }
}
