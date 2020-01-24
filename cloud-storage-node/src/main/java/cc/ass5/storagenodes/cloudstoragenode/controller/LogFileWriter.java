package cc.ass5.storagenodes.cloudstoragenode.controller;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.time.Instant;

/**
 * static implementation to log relevant events for test class in custom file
 */
@Slf4j
public class LogFileWriter {

    /**
     * Adds new entry to custom log. Also logs to actual spring-logger
     */
    public static void logInfo(Instant timestamp, String message) {
        log.info(message);
        String logMsg = timestamp.toString() + ": " + message;
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter("testlog.log", true));
            writer.append("\n");
            writer.append(logMsg);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getLogs() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("testlog.log"));
            StringBuilder builder = new StringBuilder();
            String currentLine = reader.readLine();
            while (currentLine != null) {
                builder.append(currentLine);
                builder.append("\n");
                currentLine = reader.readLine();
            }
            reader.close();
            return builder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
