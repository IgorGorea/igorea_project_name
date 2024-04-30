package cfg;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utililities.ConfigReader;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static cfg.BrowserDriver.logger;

public class ScreenshotUtils {
    private String stepName;
    private WebDriver driver = BrowserDriver.getDriver();

    protected void captureScreen(String stepName, boolean isNegativeScenario, Scenario scenario) {
        try {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File source = screenshotDriver.getScreenshotAs(OutputType.FILE);
            //For screens in report
            byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot,"image/png", "Screenshot for " + stepName);

            String currentDate = getCurrentDateTime("yyyy-MM-dd");
            String baseDirPath = "./target/screenshots/" + currentDate + "/";
            createDirectory(baseDirPath);
            String runStartTime = getCurrentDateTime("HH.mm");
            String baseDirPathByHour = baseDirPath + runStartTime + "/";
            createDirectory(baseDirPathByHour);

            String stepDirPath = baseDirPathByHour + stepName + "/";
            createDirectory(stepDirPath);

            if (isNegativeScenario) {
                stepName += "_ErrorMes";
            }

            String screenshotName = stepName + "_" + getCurrentDateTime("HHmmssSSS") + ".png";
            String screenshotPath = stepDirPath + screenshotName;

            Files.copy(source.toPath(), Paths.get(screenshotPath));

        } catch (IOException e) {
            logger.error("Screenshot was not created due to IOException");
            e.printStackTrace();
        }
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }
    public String getStepName() {
        return stepName;
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

    public static void deleteOldScreenshotsDirectories(ConfigReader configReader) {
        String screenshotsDirectoryPath = "./target/screenshots/";
        String screenshotRetentionPeriodProperty = configReader.getProperty("screenshotRetentionPeriodInDays");
        if (screenshotRetentionPeriodProperty == null) {
            logger.error("Screenshot retention period not found in properties file.");
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
                            logger.debug("Deleted directory: " + dateDirectory.getAbsolutePath());
                        }
                    } catch (Exception e) {
                        logger.error("Old screenshots directory was not deleted due to the error:");
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
            logger.error("The path to the screenshot directory is set with errors:");
            e.printStackTrace();
        }
    }
}
