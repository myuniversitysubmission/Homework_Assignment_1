package wastesystem;

public class WasteItem {
    private final String id;
    private final String type;

    public WasteItem(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
