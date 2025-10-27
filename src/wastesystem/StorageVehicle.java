package wastesystem;

public class StorageVehicle {
    private final String id;
    private final int capacity;
    private int currentLoad;
    private final LogManager logManager;

    public StorageVehicle(String id, int capacity, LogManager logManager) {
        this.id = id;
        this.capacity = capacity;
        this.currentLoad = 0;
        this.logManager = logManager;
        logManager.createStorageLog(id, "StorageVehicle created with capacity " + capacity);
    }

    public String getId() {
        return id;
    }

    public boolean deposit(WasteItem item) {
        if (currentLoad + 1 > capacity) {
            logManager.createStorageLog(id, "Deposit failed for " + item.getId() + " - capacity reached.");
            return false;
        }
        currentLoad += 1;
        logManager.createStorageLog(id, "Deposited waste " + item.getId() + " (type: " + item.getType() + "). CurrentLoad: " + currentLoad);
        return true;
    }

    public int getCurrentLoad() {
        return currentLoad;
    }
}
