package wastesystem;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class LogManager {
    private final Path baseDir;
    private final Path storageDir;
    private final Path chargingDir;
    private final Path systemDir;
    private final Path archiveDir;
    private final DateTimeFormatter dateFmt = DateTimeFormatter.ISO_DATE;

    public LogManager() {
        this.baseDir = Paths.get("logs");
        this.storageDir = baseDir.resolve("storage");
        this.chargingDir = baseDir.resolve("charging");
        this.systemDir = baseDir.resolve("system");
        this.archiveDir = baseDir.resolve("archive");
        try {
            Files.createDirectories(storageDir);
            Files.createDirectories(chargingDir);
            Files.createDirectories(systemDir);
            Files.createDirectories(archiveDir);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create log directories: " + e.getMessage(), e);
        }
    }

    // Compose file name: {name}_{YYYY-MM-DD}.log
    private Path logPathFor(String kindDir, String name, LocalDate date) {
        String filename = name + "_" + dateFmt.format(date) + ".log";
        return baseDir.resolve(kindDir).resolve(filename);
    }

    // Write log using both byte stream and character stream (demonstration)
    public void appendLogUsingStreams(Path path, String message) {
        try {
            // Ensure parent exists
            Files.createDirectories(path.getParent());

            // Write bytes (append)
            try (FileOutputStream fos = new FileOutputStream(path.toFile(), true)) {
                byte[] bytes = (message + System.lineSeparator()).getBytes("UTF-8");
                fos.write(bytes);
                fos.flush();
            }

            // Also append using character stream to show both usages
            try (FileWriter fw = new FileWriter(path.toFile(), true);
                 BufferedWriter bw = new BufferedWriter(fw)) {
                bw.write("[CHAR]" + message);
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println("Failed to append to log: " + e.getMessage());
        }
    }

    public void createStorageLog(String vehicleName, String content) {
        Path p = logPathFor("storage", vehicleName, LocalDate.now());
        appendLogUsingStreams(p, "[" + vehicleName + "] " + content);
    }

    public void createChargingLog(String stationName, String content) {
        Path p = logPathFor("charging", stationName, LocalDate.now());
        appendLogUsingStreams(p, "[" + stationName + "] " + content);
    }

    public void createSystemLog(String source, String content) {
        Path p = logPathFor("system", "system", LocalDate.now());
        appendLogUsingStreams(p, "[SYSTEM - " + source + "] " + content);
    }

    public void readLog(String relativePath) {
        Path path = Paths.get(relativePath);
        if (!Files.exists(path)) {
            System.out.println("Log not found: " + path.toString());
            return;
        }
        System.out.println("----- Reading log: " + path + " -----");
        // Use BufferedReader (character stream)
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("----- End of log -----");
        } catch (IOException e) {
            System.out.println("Error reading log: " + e.getMessage());
        }
    }

    public void listAllLogs() {
        System.out.println("Listing logs in: " + baseDir.toAbsolutePath().toString());
        listDir(storageDir);
        listDir(chargingDir);
        listDir(systemDir);
    }

    private void listDir(Path dir) {
        System.out.println("Directory: " + dir.toString());
        try {
            Files.list(dir)
                 .map(Path::getFileName)
                 .forEach(p -> System.out.println("  " + p));
        } catch (IOException e) {
            System.out.println("  (no files)");
        }
    }

    public void listLogsByRegex(String regex) {
        System.out.println("Searching logs for pattern: " + regex);
        Pattern p = Pattern.compile(regex);
        try {
            Files.walk(baseDir, 2).filter(Files::isRegularFile).forEach(path -> {
                Matcher m = p.matcher(path.getFileName().toString());
                if (m.find()) {
                    System.out.println("  " + baseDir.relativize(path).toString());
                }
            });
        } catch (IOException e) {
            System.out.println("Search error: " + e.getMessage());
        }
    }

    public void deleteLog(String relativePath) {
        Path path = Paths.get(relativePath);
        if (!Files.exists(path)) {
            System.out.println("No such file: " + path);
            return;
        }
        try {
            Files.delete(path);
            System.out.println("Deleted: " + path);
        } catch (IOException e) {
            System.out.println("Delete failed: " + e.getMessage());
        }
    }

    public void moveLogToArchive(String relativePath) {
        Path path = Paths.get(relativePath);
        if (!Files.exists(path)) {
            System.out.println("No such file to move: " + path);
            return;
        }
        Path target = archiveDir.resolve(path.getFileName());
        try {
            Files.move(path, target, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved to archive: " + target);
        } catch (IOException e) {
            System.out.println("Move failed: " + e.getMessage());
        }
    }

    // Create a zip file containing logs from a given date (YYYY-MM-DD)
    public void archiveLogsByDate(String dateString) {
        try {
            LocalDate date = LocalDate.parse(dateString, dateFmt);
            String zipName = "archive_" + dateString + ".zip";
            Path zipPath = archiveDir.resolve(zipName);
            try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()))) {
                Files.walk(baseDir, 2)
                     .filter(Files::isRegularFile)
                     .filter(p -> p.getFileName().toString().contains(dateString))
                     .forEach(p -> {
                         ZipEntry entry = new ZipEntry(baseDir.relativize(p).toString());
                         try (InputStream fis = Files.newInputStream(p)) {
                             zos.putNextEntry(entry);
                             byte[] buffer = new byte[4096];
                             int len;
                             while ((len = fis.read(buffer)) > 0) {
                                 zos.write(buffer, 0, len);
                             }
                             zos.closeEntry();
                         } catch (IOException e) {
                             System.out.println("Error zipping " + p + ": " + e.getMessage());
                         }
                     });
            }
            System.out.println("Created archive: " + zipPath.toString());
        } catch (Exception e) {
            System.out.println("Archive failed: " + e.getMessage());
        }
    }

    // Archive all logs into a timestamped zip
    public void archiveAllLogs() {
        String zipName = "archive_all_" + System.currentTimeMillis() + ".zip";
        Path zipPath = archiveDir.resolve(zipName);
        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipPath.toFile()))) {
            Files.walk(baseDir, 2)
                 .filter(Files::isRegularFile)
                 .forEach(p -> {
                     ZipEntry entry = new ZipEntry(baseDir.relativize(p).toString());
                     try (InputStream fis = Files.newInputStream(p)) {
                         zos.putNextEntry(entry);
                         byte[] buffer = new byte[4096];
                         int len;
                         while ((len = fis.read(buffer)) > 0) {
                             zos.write(buffer, 0, len);
                         }
                         zos.closeEntry();
                     } catch (IOException e) {
                         System.out.println("Error zipping " + p + ": " + e.getMessage());
                     }
                 });
        } catch (IOException e) {
            System.out.println("Archive all failed: " + e.getMessage());
            return;
        }
        System.out.println("Created archive: " + zipPath.toString());
    }

    public void printFolderStructure() {
        try {
            Files.walk(baseDir, 3)
                 .forEach(p -> System.out.println((Files.isDirectory(p) ? "[DIR]  " : "       ") + baseDir.relativize(p).toString()));
        } catch (IOException e) {
            System.out.println("Failed to print folder structure: " + e.getMessage());
        }
    }
}

