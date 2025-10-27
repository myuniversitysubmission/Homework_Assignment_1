package wastesystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Waste Management System (Console) ===");
        LogManager logManager = new LogManager(); // creates folders if needed
        TaskManager taskManager = new TaskManager(logManager);

        // create example devices
        StorageVehicle v1 = new StorageVehicle("vehicle1", 100, logManager);
        StorageVehicle v2 = new StorageVehicle("vehicle2", 150, logManager);
        ChargingStation c1 = new ChargingStation("station1", logManager);
        Robot r1 = new Robot("robot1", logManager);

        taskManager.registerStorageVehicle(v1);
        taskManager.registerStorageVehicle(v2);
        taskManager.registerChargingStation(c1);
        taskManager.registerRobot(r1);

        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. Simulate: Robot picks up waste and deposits");
            System.out.println("2. Create manual system log entry");
            System.out.println("3. List logs (all)");
            System.out.println("4. Search logs by regex (name or date)");
            System.out.println("5. Read a log file (enter relative path)");
            System.out.println("6. Delete a log");
            System.out.println("7. Move a log to archive");
            System.out.println("8. Archive logs by date (zip)");
            System.out.println("9. Show folder structure");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");
            String opt = sc.nextLine().trim();
            try {
                switch (opt) {
                    case "1":
                        // simulate task: create waste, assign to robot
                        System.out.print("Enter waste id (e.g. wasteA): ");
                        String wid = sc.nextLine().trim();
                        WasteItem wi = new WasteItem(wid, "plastic");
                        taskManager.assignPickAndDeposit(r1.getId(), wi, v1.getId());
                        break;
                    case "2":
                        System.out.print("Enter message for system log: ");
                        String msg = sc.nextLine();
                        logManager.createSystemLog("SYSTEM", msg);
                        System.out.println("System log created.");
                        break;
                    case "3":
                        logManager.listAllLogs();
                        break;
                    case "4":
                        System.out.print("Enter regex to search filenames (e.g. vehicle1|2025-10-26): ");
                        String pattern = sc.nextLine();
                        logManager.listLogsByRegex(pattern);
                        break;
                    case "5":
                        System.out.print("Enter relative log path (e.g. logs/storage/vehicle1_2025-10-26.log): ");
                        String path = sc.nextLine();
                        logManager.readLog(path);
                        break;
                    case "6":
                        System.out.print("Enter relative log path to delete: ");
                        String dpath = sc.nextLine();
                        logManager.deleteLog(dpath);
                        break;
                    case "7":
                        System.out.print("Enter relative log path to move to archive: ");
                        String mpath = sc.nextLine();
                        logManager.moveLogToArchive(mpath);
                        break;
                    case "8":
                        System.out.print("Enter date to archive (YYYY-MM-DD), or 'all': ");
                        String date = sc.nextLine().trim();
                        if ("all".equalsIgnoreCase(date)) {
                            logManager.archiveAllLogs();
                        } else {
                            logManager.archiveLogsByDate(date);
                        }
                        break;
                    case "9":
                        logManager.printFolderStructure();
                        break;
                    case "0":
                        exit = true;
                        break;
                    default:
                        System.out.println("Unknown option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace(System.out);
            }
        }
        sc.close();
        System.out.println("Exiting. Goodbye!");
    }
}
