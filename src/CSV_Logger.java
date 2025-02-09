import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CSV_Logger {

    private String CSV_FILE_PATH;

    public CSV_Logger(String measure, String fileName) {
        this.CSV_FILE_PATH = String.format("./%s_log.csv", fileName);
        String unit;
        //i used cases but forgot break 5555555
        if (measure.equals("time")){
            unit = " ns";
        }
        else if (measure.equals("space")){
            unit = " bytes";
        }
        else {
            unit = " unit";
        }
        String header = String.format("operation, %s skip_list %s,%s map %s", measure,unit, measure, unit);

        // Check if the file exists before appending the header
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            // Write the header only if the file is empty
            if (new java.io.File(CSV_FILE_PATH).length() == 0) {
                writer.write(header);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to log messages with log level
    public void log(String size, String op, String sk, String map) {

        String logEntry = String.format("%s,%s,%s,%s%n", size, op, sk, map);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.write(logEntry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

