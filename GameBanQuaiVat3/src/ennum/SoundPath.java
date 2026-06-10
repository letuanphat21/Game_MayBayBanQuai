package ennum;

public enum SoundPath {
	HOVER("/res/SELECT.wav");

    private final String path;

    SoundPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
