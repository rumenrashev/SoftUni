package softuni.exam.constants;

import java.time.format.DateTimeFormatter;

public class GlobalConstants {

    public static final String MAIN_DIRECTORY = "src/main/resources/files/";
    public static final String JSON = "json/";
    public static final String PASSENGER_IMPORT_PATH = MAIN_DIRECTORY + JSON + "passengers.json";
    public static final String TOWNS_IMPORT_PATH = MAIN_DIRECTORY + JSON + "towns.json";
    public static final String XML = "xml/";
    public static final String PLANES_IMPORT_PATH = MAIN_DIRECTORY + XML + "planes.xml";
    public static final String TICKETS_IMPORT_PATH = MAIN_DIRECTORY + XML + "tickets.xml";

    public static final String IMPORT_TOWN = "Successfully imported Town %s - %d";
    public static final String IMPORT_PASSENGER = "Successfully imported Passenger %s - %s";
    public static final String IMPORT_PLANE = "Successfully imported Plane %s";
    public static final String IMPORT_TICKET = "Successfully imported Ticket %s - %s";

    public static final String INVALID_TOWN = "Invalid Town";
    public static final String INVALID_PASSENGER = "Invalid Passenger";
    public static final String INVALID_PLANE = "Invalid Plane";
    public static final String INVALID_TICKET = "Invalid Ticket";

    public static final String DATA_EXPORT_MESSAGE =
            "Passenger %s  %s%n" +
                    "\tEmail - %s%n" +
                    "\tPhone - %s%n" +
                    "\tNumber of tickets - %d";

    public static DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
}
