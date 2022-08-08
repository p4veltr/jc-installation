import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        final File mainDir = new File("c://games");
        StringBuilder log = new StringBuilder();

        runInstallation(mainDir, log);

        try (FileWriter fw = new FileWriter(new File(mainDir + "//temp", "temp.txt"), false)) {
            fw.write(log.toString());
            fw.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void runInstallation(File mainDir, StringBuilder log) {
        if (!mkDir(mainDir, "src", log)) return;
        if (!mkDir(mainDir, "res", log)) return;
        if (!mkDir(mainDir, "savegames", log)) return;
        if (!mkDir(mainDir, "temp", log)) return;

        File dir = new File(mainDir, "src");
        if (!mkDir(dir, "main", log)) return;
        if (!mkDir(dir, "test", log)) return;

        dir = new File(mainDir + "//src", "main");
        if (!newFile(dir, "Main.java", log)) return;
        if (!newFile(dir, "Utils.java", log)) return;

        dir = new File(mainDir, "res");
        if (!mkDir(dir, "drawables", log)) return;
        if (!mkDir(dir, "vectors", log)) return;
        if (!mkDir(dir, "icons", log)) return;

        dir = new File(mainDir, "temp");
        if (!newFile(dir, "temp.txt", log)) return;

        log.append("Устновка завершена!");
    }

    private static boolean newFile(File mainDir, String fileName, StringBuilder log) {
        if (!mainDir.exists()) {
            log.append("Ошибка! ")
                    .append(mainDir)
                    .append(" не существует!")
                    .append("\n");
            return false;
        }
        File newFile = new File(mainDir, fileName);
        if (newFile.exists()) {
            log.append("В каталоге ")
                    .append(mainDir)
                    .append(" уже существует файл ")
                    .append(fileName)
                    .append("\n");
            return true;
        }
        try {
            if (newFile.createNewFile()) {
                log.append("В каталоге  ")
                        .append(mainDir)
                        .append(" создан файл ")
                        .append(fileName)
                        .append("\n");
                return true;
            } else {
                log.append("Ошибка! В каталоге ")
                        .append(mainDir)
                        .append(" не удалось создать файл ")
                        .append(fileName)
                        .append("\n");
                return false;
            }
        } catch (IOException e) {
            log.append("Ошибка! В каталоге ")
                    .append(mainDir)
                    .append(" не удалось создать файл ")
                    .append(fileName)
                    .append("\n");
            return false;
        }
    }

    private static boolean mkDir(File mainDir, String dirName, StringBuilder log) {
        if (!mainDir.exists()) {
            log.append("Ошибка! ")
                    .append(mainDir)
                    .append(" не существует!")
                    .append("\n");
            return false;
        }
        File newDir = new File(mainDir, dirName);
        if (newDir.exists()) {
            log.append("В каталоге ")
                    .append(mainDir)
                    .append(" уже существует подкаталог ")
                    .append(dirName)
                    .append("\n");
            return true;
        }
        if (newDir.mkdir()) {
            log.append("В каталоге ")
                    .append(mainDir)
                    .append(" создан подкаталог ")
                    .append(dirName)
                    .append("\n");
            return true;
        } else {
            log.append("Ошибка! В каталоге ")
                    .append(mainDir)
                    .append(" не удалось создать подкаталог ")
                    .append(dirName)
                    .append("!")
                    .append("\n");
            return false;
        }
    }
}