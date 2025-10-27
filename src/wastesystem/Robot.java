package wastesystem;

public class Robot {
    private final String id;
    private final LogManager logManager;

    public Robot(String id, LogManager logManager) {
        this.id = id;
        this.logManager = logManager;
        logManager.createSystemLog("RobotInit", "Robot " + id + " initialized.");
    }

    public String getId() {
        return id;
    }

    public boolean pickUp(WasteItem item) {
        logManager.createSystemLog("RobotAction", "Robot " + id + " picked up " + item.getId());
        return true;
    }

    public boolean deposit(StorageVehicle vehicle, WasteItem item) {
        boolean ok = vehicle.deposit(item);
        if (ok) {
            logManager.createSystemLog("RobotAction", "Robot " + id + " deposited " + item.getId() + " into " + vehicle.getId());
        } else {
            logManager.createSystemLog("RobotAction", "Robot " + id + " failed to deposit " + item.getId() + " into " + vehicle.getId());
        }
        return ok;
    }

    public void goCharge(ChargingStation station) {
        logManager.createSystemLog("RobotAction", "Robot " + id + " going to charge at " + station.getId());
        station.chargeRobot(id);
    }
}
