package cosmic.lang.entry;

public abstract class ContentAdapter {

    private final String fileContents;

    public ContentAdapter(String fileContents) {

        this.fileContents = fileContents;

    }

    public String GetFileContents() { return fileContents; }

}
