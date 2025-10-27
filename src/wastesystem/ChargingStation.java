package wastesystem;

public class ChargingStation {
    private final String id;
    private final LogManager logManager;

    public ChargingStation(String id, LogManager logManager) {
        this.id = id;
        this.logManager = logManager;
        this.idCheck();
        logManager.createChargingLog(id, "ChargingStation created.");
    }

    private void idCheck() {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ChargingStation id cannot be empty");
        }
    }

    public String getId() {
        return id;
    }

    public void chargeRobot(String robotId) {
        logManager.createChargingLog(id, "Robot " + robotId + " started charging.");
        try {
            Thread.sleep(300); // simulate small delay
        } catch (InterruptedException ignored) {}
        logManager.createChargingLog(id, "Robot " + robotId + " finished charging.");
    }
}
