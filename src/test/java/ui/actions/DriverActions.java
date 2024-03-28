package ui.actions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Scenario;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import ui.context.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//TODO read more about lombok
@Getter
public abstract class DriverActions {
    protected static WebDriver abstractDriver;
    protected static ConfigReader configReader = new ConfigReader();
    protected final Logger logger = LogManager.getLogger();
    protected String runStartTime;
    protected String stepName;

    public void setWebDriver(WebDriver webDriver) {
        abstractDriver = webDriver;
    }
    public void openPage(String url){
        abstractDriver.navigate().to(url);
        logger.info("The " + url + " has been opened");
    }

    protected void captureScreen(String stepName, boolean isNegativeScenario) {
            try {
                TakesScreenshot screenshotDriver = (TakesScreenshot) abstractDriver;
                File source = screenshotDriver.getScreenshotAs(OutputType.FILE);

                String currentDate = getCurrentDateTime("yyyy-MM-dd");
                String baseDirPath = "./target/screenshots/" + currentDate + "/";
                createDirectory(baseDirPath);
                runStartTime = getCurrentDateTime("HH.mm");
                String baseDirPathByHour = baseDirPath + runStartTime + "/";
                createDirectory(baseDirPathByHour);

                String stepDirPath = baseDirPathByHour + stepName + "/";
                createDirectory(stepDirPath);

                if (isNegativeScenario) {
                    stepName += "_Error";
                }

                String screenshotName = stepName + "_" + getCurrentDateTime("HHmmssSSS") + ".png";
                String screenshotPath = stepDirPath + screenshotName;

                Files.copy(source.toPath(), Paths.get(screenshotPath));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    protected String getCurrentDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }
    private void createDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists()) {
                directory.mkdirs();
        }
    }
    protected boolean isNegativeScenario(Scenario scenario) {
        return scenario.getSourceTagNames().contains("@Negative");
    }
    public static void deleteOldScreenshotsDirectories() {
        String screenshotsDirectoryPath = "./target/screenshots/";
        String screenshotRetentionPeriodProperty = configReader.getProperty("screenshotRetentionPeriodInDays");
        if (screenshotRetentionPeriodProperty == null) {
            System.out.println("Screenshot retention period not found in properties file.");
            return;
        }
        int screenshotRetentionPeriod = Integer.parseInt(screenshotRetentionPeriodProperty);
        LocalDate thresholdDate = LocalDate.now().minusDays(screenshotRetentionPeriod);

        File screenshotsDirectory = new File(screenshotsDirectoryPath);
        File[] dateDirectories = screenshotsDirectory.listFiles();
        if (dateDirectories != null) {
            for (File dateDirectory : dateDirectories) {
                if (dateDirectory.isDirectory()) {
                    try {
                        LocalDate directoryDate = LocalDate.parse(dateDirectory.getName(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                        if (directoryDate.isBefore(thresholdDate)) {
                            deleteDirectory(dateDirectory);
                            System.out.println("Deleted directory: " + dateDirectory.getAbsolutePath());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    private static void deleteDirectory(File directory) {
        if (!directory.exists()) {
            return;
        }
        try {
            Files.walkFileTree(directory.toPath(), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
