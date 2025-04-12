package org.example.util;

import java.io.File;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class DataInitializer {

    public static void initializeDataFiles() {
        File dataDir = new File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
            System.out.println("✅ 'data' folder created.");
        }

        File flightFile = new File("data/flights.txt");
        if (!flightFile.exists()) {
            List<String> sampleFlights = Arrays.asList(
                    "1;Kiev;" + LocalDateTime.now().plusHours(3) + ";100",
                    "2;Paris;" + LocalDateTime.now().plusHours(5) + ";120",
                    "3;Berlin;" + LocalDateTime.now().plusHours(8) + ";80",
                    "4;London;" + LocalDateTime.now().plusHours(3) + ";100"
            );
            FileUtil.writeFile("data/flights.txt", sampleFlights);
        }else {
            System.out.println("ℹ️ Flights file already exists.");
        }

        File bookingFile = new File("data/bookings.txt");
        if (!bookingFile.exists()) {
            FileUtil.writeFile("data/bookings.txt", List.of());
            System.out.println("✅ Empty bookings file created.");
        }
    }
}
