final class DataPayload {
    private final String title;
    private final String link;
    private final String sourceCode;

    DataPayload(final String title, final String link, final String sourceCode) {
        this.title = title;
        this.link = link;
        this.sourceCode = sourceCode;
    }

    String getTitle() {
        return title;
    }

    String getLink() {
        return link;
    }

    String getSourceCode() {
        return sourceCode;
    }
}
