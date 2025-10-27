package wastesystem;

import java.util.*;

public class TaskManager {
    private final Map<String, StorageVehicle> storageVehicles = new HashMap<>();
    private final Map<String, ChargingStation> chargingStations = new HashMap<>();
    private final Map<String, Robot> robots = new HashMap<>();
    private final LogManager logManager;

    public TaskManager(LogManager logManager) {
        this.logManager = logManager;
        logManager.createSystemLog("TaskManager", "TaskManager initialized.");
    }

    public void registerStorageVehicle(StorageVehicle sv) {
        storageVehicles.put(sv.getId(), sv);
        logManager.createSystemLog("TaskManager", "Registered storage vehicle: " + sv.getId());
    }

    public void registerChargingStation(ChargingStation cs) {
        chargingStations.put(cs.getId(), cs);
        logManager.createSystemLog("TaskManager", "Registered charging station: " + cs.getId());
    }

    public void registerRobot(Robot r) {
        robots.put(r.getId(), r);
        logManager.createSystemLog("TaskManager", "Registered robot: " + r.getId());
    }

    // assign a simple pick-and-deposit task
    public void assignPickAndDeposit(String robotId, WasteItem item, String storageVehicleId) {
        Robot r = robots.get(robotId);
        StorageVehicle sv = storageVehicles.get(storageVehicleId);
        if (r == null) {
            System.out.println("No robot with id " + robotId);
            return;
        }
        if (sv == null) {
            System.out.println("No storage vehicle with id " + storageVehicleId);
            return;
        }
        logManager.createSystemLog("TaskAssign", "Assigning robot " + robotId + " to pick " + item.getId() + " and deposit to " + storageVehicleId);
        boolean picked = r.pickUp(item);
        if (!picked) {
            logManager.createSystemLog("TaskAssign", "Robot " + robotId + " failed to pick up item " + item.getId());
            return;
        }
        boolean deposited = r.deposit(sv, item);
        if (!deposited) {
            logManager.createSystemLog("TaskAssign", "Deposit failed; routing to charging station for inspection.");
            // find a charging station to charge (simulate)
            chargingStations.values().stream().findFirst().ifPresent(r::goCharge);
        }
    }
}
